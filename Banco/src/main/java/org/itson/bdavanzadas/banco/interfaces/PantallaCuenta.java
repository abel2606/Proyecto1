package org.itson.bdavanzadas.banco.interfaces;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.itson.bdavanzadas.bancodominio.Cuenta;
import org.itson.bdavanzadas.bancodominio.Fecha;
import org.itson.bdavanzadas.bancodominio.Periodo;
import org.itson.bdavanzadas.bancodominio.TransaccionTabla;
import org.itson.bdavanzadas.bancopersistencia.conexion.IConexion;
import org.itson.bdavanzadas.bancopersistencia.daos.CuentasDAO;
import org.itson.bdavanzadas.bancopersistencia.daos.ICuentasDAO;
import org.itson.bdavanzadas.bancopersistencia.daos.ITransaccionesDAO;
import org.itson.bdavanzadas.bancopersistencia.daos.TransaccionesDAO;
import org.itson.bdavanzadas.bancopersistencia.excepciones.PersistenciaException;
import org.itson.bdavanzadas.bancopersistencia.validadores.Validadores;

public class PantallaCuenta extends javax.swing.JDialog {

    /**
     * Creates new form PantallaCuentas.
     *
     * @param parent
     * @param modal
     * @param conexion
     * @param cuenta
     */
    public PantallaCuenta(java.awt.Frame parent, boolean modal, IConexion conexion, Cuenta cuenta) {
        super(parent, modal);
        initComponents();
        centraCuadroDialogo(parent);
        setTitle("Cuenta");
        this.parent = parent;
        this.conexion = conexion;
        this.cuenta = cuenta;
        transaccionesDAO = new TransaccionesDAO(conexion);
        cuentasDAO = new CuentasDAO(conexion);

        txtAlias.setText(cuenta.getAlias());
        txtNumero.setText(cuenta.getNumero().toString());
        txtSaldo.setText(cuenta.getSaldo().toString());

        if (cuenta.isActiva()) {
            btnActivarCuenta.setText("Desactivar Cuenta");
        } else {
            btnActivarCuenta.setText("Activar Cuenta");
        }

        llenarTabla("ninguno");
    }

    /**
     * Este método centra el cuadro de dialogo sobre la ventana de la
     * aplicación.
     *
     * @param parent Ventana sobre la que aparecerá el cuadro de diálogo
     */
    private void centraCuadroDialogo(java.awt.Frame parent) {
        // Obtiene el tamaño y posición de la ventana de la aplicación
        Dimension frameSize = parent.getSize();
        Point loc = parent.getLocation();
        // Obtiene el tamaño del cuadro de diálogo
        Dimension dlgSize = getPreferredSize();
        // Centra el cuadro de diálogo sobre la ventana padre
        setLocation((frameSize.width - dlgSize.width) / 2 + loc.x, (frameSize.height - dlgSize.height) / 2 + loc.y);
    }

    /**
     * Permite llenar la tabla de transacciones.
     *
     * @param filtro El filtro que se aplicará
     */
    private void llenarTabla(String filtro) {
        // Obtener la lista de socios
        List<TransaccionTabla> listaTransacciones;
        try {
            listaTransacciones = transaccionesDAO.consultar(cuenta.getNumero());
            DefaultTableModel modelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return column == getColumnCount() - 1; // Solo la última columna es editable
                }
            };
            modelo.addColumn("MONTO");
            modelo.addColumn("FECHA REALIZACION");
            modelo.addColumn("HORA REALIZACION");
            modelo.addColumn("TIPO");
            modelo.addColumn("ESTADO");

            if (filtro.equalsIgnoreCase("NINGUNO")) {
                for (TransaccionTabla transaccion : listaTransacciones) {
                    String[] datosFechaRealizacion = transaccion.getFechaRealizacion().toStringHora().split(" ");
                    String[] datosFecha = datosFechaRealizacion[0].split("-");
                    Object[] fila = {transaccion.getMonto(), datosFecha[2] + "/" + datosFecha[1] + "/" + datosFecha[0],
                        datosFechaRealizacion[1], transaccion.getTipo(), transaccion.getEstado()};
                    modelo.addRow(fila);
                }
                tblTransacciones.setModel(modelo);
            } else if (filtro.equalsIgnoreCase("TRANSFERENCIAS")) {
                for (TransaccionTabla transaccion : listaTransacciones) {
                    if (transaccion.getTipo().equalsIgnoreCase("Transferencia")) {
                        String[] datosFechaRealizacion = transaccion.getFechaRealizacion().toStringHora().split(" ");
                        String[] datosFecha = datosFechaRealizacion[0].split("-");
                        Object[] fila = {transaccion.getMonto(), datosFecha[2] + "/" + datosFecha[1] + "/" + datosFecha[0],
                            datosFechaRealizacion[1], transaccion.getTipo(), transaccion.getEstado()};
                        modelo.addRow(fila);
                    }
                }
                tblTransacciones.setModel(modelo);
            } else if (filtro.equalsIgnoreCase("RETIROS")) {
                for (TransaccionTabla transaccion : listaTransacciones) {
                    if (transaccion.getTipo().equalsIgnoreCase("Retiro")) {
                        String[] datosFechaRealizacion = transaccion.getFechaRealizacion().toStringHora().split(" ");
                        String[] datosFecha = datosFechaRealizacion[0].split("-");
                        Object[] fila = {transaccion.getMonto(), datosFecha[2] + "/" + datosFecha[1] + "/" + datosFecha[0],
                            datosFechaRealizacion[1], transaccion.getTipo(), transaccion.getEstado()};
                        modelo.addRow(fila);
                    }
                }
                tblTransacciones.setModel(modelo);
            } else if (filtro.equalsIgnoreCase("PERIODO")) {
                for (TransaccionTabla transaccion : listaTransacciones) {
                    if (periodo.contiene(transaccion.getFechaRealizacion())) {
                        String[] datosFechaRealizacion = transaccion.getFechaRealizacion().toStringHora().split(" ");
                        String[] datosFecha = datosFechaRealizacion[0].split("-");
                        Object[] fila = {transaccion.getMonto(), datosFecha[2] + "/" + datosFecha[1] + "/" + datosFecha[0],
                            datosFechaRealizacion[1], transaccion.getTipo(), transaccion.getEstado()};
                        modelo.addRow(fila);
                    }
                }
                tblTransacciones.setModel(modelo);
            }

        } catch (PersistenciaException e) {
            JOptionPane.showMessageDialog(this, "Error al consultar la información de los socios: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblPokebolaIzq = new javax.swing.JLabel();
        lblPokebolaDer = new javax.swing.JLabel();
        btnAtras = new javax.swing.JButton();
        btnTransferir = new javax.swing.JButton();
        btnRetirar = new javax.swing.JButton();
        txtAlias = new javax.swing.JTextField();
        lblCuenta = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        lblNumero = new javax.swing.JLabel();
        txtSaldo = new javax.swing.JTextField();
        lblSaldo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTransacciones = new javax.swing.JTable();
        rdbTransferencias = new javax.swing.JRadioButton();
        rdbRetiros = new javax.swing.JRadioButton();
        rdbPeriodo = new javax.swing.JRadioButton();
        txtDesde = new javax.swing.JTextField();
        lblDesde = new javax.swing.JLabel();
        lblHasta = new javax.swing.JLabel();
        txtHasta = new javax.swing.JTextField();
        btnActivarCuenta = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(189, 255, 188));
        jPanel1.setPreferredSize(new java.awt.Dimension(1100, 450));

        jPanel2.setBackground(new java.awt.Color(0, 255, 117));
        jPanel2.setPreferredSize(new java.awt.Dimension(864, 83));

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setText("CUENTA");

        lblPokebolaIzq.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PokeBolaBlancaVerde.png"))); // NOI18N

        lblPokebolaDer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PokeBolaBlancaVerde.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblPokebolaIzq)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 370, Short.MAX_VALUE)
                .addComponent(lblTitulo)
                .addGap(370, 370, 370)
                .addComponent(lblPokebolaDer)
                .addGap(19, 19, 19))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblPokebolaIzq)
                    .addComponent(lblTitulo)
                    .addComponent(lblPokebolaDer))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        btnAtras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Flecha.png"))); // NOI18N
        btnAtras.setBorderPainted(false);
        btnAtras.setContentAreaFilled(false);
        btnAtras.setFocusPainted(false);
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        btnTransferir.setBackground(new java.awt.Color(0, 255, 117));
        btnTransferir.setFont(new java.awt.Font("Arial", 1, 26)); // NOI18N
        btnTransferir.setForeground(new java.awt.Color(255, 255, 255));
        btnTransferir.setText("TRANSFERIR");
        btnTransferir.setPreferredSize(new java.awt.Dimension(200, 47));
        btnTransferir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransferirActionPerformed(evt);
            }
        });

        btnRetirar.setBackground(new java.awt.Color(0, 255, 117));
        btnRetirar.setFont(new java.awt.Font("Arial", 1, 26)); // NOI18N
        btnRetirar.setForeground(new java.awt.Color(255, 255, 255));
        btnRetirar.setText("RETIRAR");
        btnRetirar.setPreferredSize(new java.awt.Dimension(200, 47));
        btnRetirar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetirarActionPerformed(evt);
            }
        });

        txtAlias.setEditable(false);
        txtAlias.setFont(new java.awt.Font("Arial", 1, 26)); // NOI18N
        txtAlias.setForeground(new java.awt.Color(99, 134, 107));
        txtAlias.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 117), 2, true));

        lblCuenta.setBackground(new java.awt.Color(255, 255, 255));
        lblCuenta.setFont(new java.awt.Font("Arial", 1, 26)); // NOI18N
        lblCuenta.setForeground(new java.awt.Color(41, 92, 52));
        lblCuenta.setText("Alias");

        txtNumero.setEditable(false);
        txtNumero.setFont(new java.awt.Font("Arial", 1, 26)); // NOI18N
        txtNumero.setForeground(new java.awt.Color(99, 134, 107));
        txtNumero.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 117), 2, true));

        lblNumero.setBackground(new java.awt.Color(255, 255, 255));
        lblNumero.setFont(new java.awt.Font("Arial", 1, 26)); // NOI18N
        lblNumero.setForeground(new java.awt.Color(41, 92, 52));
        lblNumero.setText("Número");

        txtSaldo.setEditable(false);
        txtSaldo.setFont(new java.awt.Font("Arial", 1, 26)); // NOI18N
        txtSaldo.setForeground(new java.awt.Color(99, 134, 107));
        txtSaldo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 117), 2, true));

        lblSaldo.setBackground(new java.awt.Color(255, 255, 255));
        lblSaldo.setFont(new java.awt.Font("Arial", 1, 26)); // NOI18N
        lblSaldo.setForeground(new java.awt.Color(41, 92, 52));
        lblSaldo.setText("Saldo");

        tblTransacciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblTransacciones);

        rdbTransferencias.setBackground(new java.awt.Color(189, 255, 188));
        buttonGroup1.add(rdbTransferencias);
        rdbTransferencias.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        rdbTransferencias.setForeground(new java.awt.Color(41, 92, 52));
        rdbTransferencias.setText("Transferencias");
        rdbTransferencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbTransferenciasActionPerformed(evt);
            }
        });

        rdbRetiros.setBackground(new java.awt.Color(189, 255, 188));
        buttonGroup1.add(rdbRetiros);
        rdbRetiros.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        rdbRetiros.setForeground(new java.awt.Color(41, 92, 52));
        rdbRetiros.setText("Retiros");
        rdbRetiros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbRetirosActionPerformed(evt);
            }
        });

        rdbPeriodo.setBackground(new java.awt.Color(189, 255, 188));
        buttonGroup1.add(rdbPeriodo);
        rdbPeriodo.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        rdbPeriodo.setForeground(new java.awt.Color(41, 92, 52));
        rdbPeriodo.setText("Periodo");
        rdbPeriodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbPeriodoActionPerformed(evt);
            }
        });

        txtDesde.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        txtDesde.setForeground(new java.awt.Color(99, 134, 107));
        txtDesde.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 117), 2, true));

        lblDesde.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblDesde.setForeground(new java.awt.Color(41, 92, 52));
        lblDesde.setText("Desde");

        lblHasta.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblHasta.setForeground(new java.awt.Color(41, 92, 52));
        lblHasta.setText("Hasta");

        txtHasta.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        txtHasta.setForeground(new java.awt.Color(99, 134, 107));
        txtHasta.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 117), 2, true));

        btnActivarCuenta.setBackground(new java.awt.Color(0, 255, 117));
        btnActivarCuenta.setFont(new java.awt.Font("Arial", 1, 26)); // NOI18N
        btnActivarCuenta.setForeground(new java.awt.Color(255, 255, 255));
        btnActivarCuenta.setText("Desactivar cuenta");
        btnActivarCuenta.setPreferredSize(new java.awt.Dimension(200, 47));
        btnActivarCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActivarCuentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(770, 770, 770)
                        .addComponent(btnRetirar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(472, 472, 472)
                        .addComponent(btnActivarCuenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(38, 38, 38)
                        .addComponent(btnTransferir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rdbTransferencias)
                .addGap(18, 18, 18)
                .addComponent(rdbRetiros)
                .addGap(18, 18, 18)
                .addComponent(rdbPeriodo)
                .addGap(67, 67, 67)
                .addComponent(lblDesde)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblHasta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblNumero)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAtras)
                        .addGap(34, 34, 34)
                        .addComponent(lblCuenta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtAlias, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34)
                .addComponent(lblSaldo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAtras))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtAlias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblCuenta)
                        .addComponent(lblSaldo)
                        .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTransferir, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNumero)
                            .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnActivarCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(12, 12, 12)
                .addComponent(btnRetirar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbRetiros)
                    .addComponent(rdbTransferencias)
                    .addComponent(rdbPeriodo)
                    .addComponent(txtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDesde)
                    .addComponent(txtHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHasta))
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        dispose();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnTransferirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferirActionPerformed
        PantallaHacerTransferencia pantallaTransferencia = new PantallaHacerTransferencia(parent, true, conexion, cuenta);
        pantallaTransferencia.setVisible(true);
        llenarTabla("ninguno");
    }//GEN-LAST:event_btnTransferirActionPerformed

    private void btnRetirarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetirarActionPerformed
        PantallaGenerarRetiro pantallaGenerarRetiro = new PantallaGenerarRetiro(parent, true, conexion, cuenta);
        pantallaGenerarRetiro.setVisible(true);
        llenarTabla("ninguno");
    }//GEN-LAST:event_btnRetirarActionPerformed

    private void rdbTransferenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbTransferenciasActionPerformed
        llenarTabla("transferencias");
    }//GEN-LAST:event_rdbTransferenciasActionPerformed

    private void rdbRetirosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbRetirosActionPerformed
        llenarTabla("retiros");
    }//GEN-LAST:event_rdbRetirosActionPerformed

    private void rdbPeriodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbPeriodoActionPerformed
        JOptionPane.showMessageDialog(this, "Ingrese las fechas del periodo en los campos de abajo. Asegúrese de que tengan formato dd/mm/aaaa", "Información", JOptionPane.INFORMATION_MESSAGE);
        if (validarFechas()) {
            String[] datosFechaDesde = txtDesde.getText().split("/");
            String[] datosFechaHasta = txtHasta.getText().split("/");

            String fechaDesde = datosFechaDesde[2] + "-" + datosFechaDesde[1] + "-" + datosFechaDesde[0];
            String fechaHasta = datosFechaHasta[2] + "-" + datosFechaHasta[1] + "-" + datosFechaHasta[0];

            periodo = new Periodo(new Fecha(fechaDesde), new Fecha(fechaHasta));

            llenarTabla("periodo");
        } else if (!txtDesde.getText().isBlank() && !txtHasta.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Las fechas ingresadas no cuentan con el formato dd/mm/aaaa");
        }
    }//GEN-LAST:event_rdbPeriodoActionPerformed

    private void btnActivarCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActivarCuentaActionPerformed
        desactivarCuenta();
    }//GEN-LAST:event_btnActivarCuentaActionPerformed

    private boolean validarFechas() {
        Validadores validador = new Validadores();
        if (validador.validarFecha(txtDesde.getText()) && validador.validarFecha(txtHasta.getText())) {
            return true;
        }
        return false;
    }

    public void desactivarCuenta() {
        if (btnActivarCuenta.getText().equalsIgnoreCase("Desactivar cuenta")) {
            int operacion = JOptionPane.showConfirmDialog(this, "¿Deseas " + btnActivarCuenta.getText() + "?",
                    "Confirmación", JOptionPane.OK_CANCEL_OPTION);
            if (operacion == JOptionPane.OK_OPTION) {
                try {
                    cuentasDAO.desactivarCuenta(cuenta.getNumero());
                } catch (PersistenciaException e) {
                }

                JOptionPane.showMessageDialog(this, "Se desactivó la cuenta correctamente",
                        "Información", JOptionPane.INFORMATION_MESSAGE);
                btnActivarCuenta.setText("Activar Cuenta");
            }
        } else {
            int operacion = JOptionPane.showConfirmDialog(this, "¿Deseas " + btnActivarCuenta.getText() + "?",
                    "Confirmación", JOptionPane.OK_CANCEL_OPTION);
            if (operacion == JOptionPane.OK_OPTION) {
                try {
                    cuentasDAO.activarCuenta(cuenta.getNumero());
                } catch (PersistenciaException e) {
                }

                JOptionPane.showMessageDialog(this, "Se activó la cuenta correctamente",
                        "Información", JOptionPane.INFORMATION_MESSAGE);
                btnActivarCuenta.setText("Desactivar Cuenta");
            }
        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActivarCuenta;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnRetirar;
    private javax.swing.JButton btnTransferir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCuenta;
    private javax.swing.JLabel lblDesde;
    private javax.swing.JLabel lblHasta;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblPokebolaDer;
    private javax.swing.JLabel lblPokebolaIzq;
    private javax.swing.JLabel lblSaldo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JRadioButton rdbPeriodo;
    private javax.swing.JRadioButton rdbRetiros;
    private javax.swing.JRadioButton rdbTransferencias;
    private javax.swing.JTable tblTransacciones;
    private javax.swing.JTextField txtAlias;
    private javax.swing.JTextField txtDesde;
    private javax.swing.JTextField txtHasta;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtSaldo;
    // End of variables declaration//GEN-END:variables
    private IConexion conexion;
    private ITransaccionesDAO transaccionesDAO;
    private ICuentasDAO cuentasDAO;
    private Cuenta cuenta;
    private Frame parent;
    private Periodo periodo;
}
