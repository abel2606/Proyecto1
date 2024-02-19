package org.itson.bdavanzadas.banco.interfaces;

import java.awt.Component;
import java.awt.Dimension;
import java.util.List;
import java.awt.Frame;
import java.awt.Point;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.itson.bdavanzadas.bancodominio.Cliente;
import org.itson.bdavanzadas.bancodominio.Cuenta;
import org.itson.bdavanzadas.bancopersistencia.conexion.IConexion;
import org.itson.bdavanzadas.bancopersistencia.daos.ClientesDAO;
import org.itson.bdavanzadas.bancopersistencia.daos.CuentasDAO;
import org.itson.bdavanzadas.bancopersistencia.daos.IClientesDAO;
import org.itson.bdavanzadas.bancopersistencia.daos.ICuentasDAO;
import org.itson.bdavanzadas.bancopersistencia.excepciones.PersistenciaException;

public class PantallaCuentas extends javax.swing.JDialog {

    /**
     * Creates new form PantallaCuentas.
     *
     * @param parent
     * @param modal
     * @param conexion
     * @param cliente
     */
    public PantallaCuentas(java.awt.Frame parent, boolean modal, IConexion conexion, Cliente cliente) {
        super(parent, modal);
        initComponents();
        centraCuadroDialogo(parent);
        setTitle("Cuentas");
        this.parent = parent;
        this.conexion = conexion;
        cuentasDAO = new CuentasDAO(conexion);
        this.cliente = cliente;
        String[] nombresCliente = cliente.getNombre().split(" ");
        lblNombreCliente.setText("Hola, " + nombresCliente[0]);
        cuentasDAO = new CuentasDAO(conexion);
        clientesDAO = new ClientesDAO(conexion);
        llenarTabla();
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

    private void llenarTabla() {
        // Obtener la lista de socios
        List<Cuenta> listaCuentas;
        try {
            listaCuentas = cuentasDAO.consultar(cliente.getId());
            DefaultTableModel modelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return column == getColumnCount() - 1; // Solo la última columna es editable
                }
            };
            modelo.addColumn("ALIAS");
            modelo.addColumn("SALDO");
            modelo.addColumn("FECHA APERTURA");
            modelo.addColumn("ACTIVA");
            modelo.addColumn("");

            // Agregar los socios al modelo de la tabla
            for (Cuenta cuenta : listaCuentas) {
                Boolean activa = cuenta.isActiva();
                String activaString;
                if (activa) {
                    activaString = "Si";
                } else {
                    activaString = "No";
                }

                Object[] fila = {cuenta.getAlias(), cuenta.getSaldo(), cuenta.getFechaApertura().formatearFecha(), activaString, "ver"};
                modelo.addRow(fila);
            }
            tblCuentas.setModel(modelo);

            ButtonColumn buttonColumn = new ButtonColumn("Ver", (e) -> {
                int fila = tblCuentas.convertRowIndexToModel(tblCuentas.getSelectedRow());
                Cuenta cuenta = listaCuentas.get(fila);
                PantallaCuenta pantallaCuenta = new PantallaCuenta(parent, true, conexion, cuenta);
                pantallaCuenta.setVisible(true);
            });
            tblCuentas.getColumnModel().getColumn(tblCuentas.getColumnCount() - 1).setCellRenderer(buttonColumn);
            tblCuentas.getColumnModel().getColumn(tblCuentas.getColumnCount() - 1).setCellEditor(buttonColumn);
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblPokebolaIzq = new javax.swing.JLabel();
        lblPokebolaDer = new javax.swing.JLabel();
        btnAgregarCuenta = new javax.swing.JButton();
        btnActualizarCliente = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCuentas = new javax.swing.JTable();
        lblNombreCliente = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(189, 255, 188));
        jPanel1.setPreferredSize(new java.awt.Dimension(1100, 450));

        jPanel2.setBackground(new java.awt.Color(0, 255, 117));
        jPanel2.setPreferredSize(new java.awt.Dimension(864, 83));

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setText("CUENTAS");

        lblPokebolaIzq.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PokeBolaBlancaVerde.png"))); // NOI18N

        lblPokebolaDer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PokeBolaBlancaVerde.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblPokebolaIzq)
                .addGap(354, 354, 354)
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 354, Short.MAX_VALUE)
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

        btnAgregarCuenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AgregarTarjeta.png"))); // NOI18N
        btnAgregarCuenta.setBorderPainted(false);
        btnAgregarCuenta.setContentAreaFilled(false);
        btnAgregarCuenta.setFocusPainted(false);
        btnAgregarCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarCuentaActionPerformed(evt);
            }
        });

        btnActualizarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ActualizarCliente.png"))); // NOI18N
        btnActualizarCliente.setBorderPainted(false);
        btnActualizarCliente.setContentAreaFilled(false);
        btnActualizarCliente.setFocusPainted(false);
        btnActualizarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarClienteActionPerformed(evt);
            }
        });

        btnCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CerrarSesion.png"))); // NOI18N
        btnCerrarSesion.setBorderPainted(false);
        btnCerrarSesion.setContentAreaFilled(false);
        btnCerrarSesion.setFocusPainted(false);
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        tblCuentas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblCuentas);

        lblNombreCliente.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        lblNombreCliente.setForeground(new java.awt.Color(41, 92, 52));
        lblNombreCliente.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCerrarSesion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnActualizarCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAgregarCuenta)
                .addGap(12, 12, 12))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombreCliente)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 946, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(77, 77, 77))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnActualizarCliente)
                    .addComponent(btnCerrarSesion)
                    .addComponent(btnAgregarCuenta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(lblNombreCliente)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
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

    private void btnAgregarCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarCuentaActionPerformed
        PantallaAgregarCuenta pantallaAgregarCuenta = new PantallaAgregarCuenta(parent, true, conexion, cliente);
        pantallaAgregarCuenta.setVisible(true);
        llenarTabla();
    }//GEN-LAST:event_btnAgregarCuentaActionPerformed


    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        int operacion = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas cerrar sesión?", "Cerrar Sesión", JOptionPane.OK_CANCEL_OPTION);
        if (operacion == JOptionPane.OK_OPTION) {
            dispose();
        }
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void btnActualizarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarClienteActionPerformed
        PantallaActualizarCliente pantallaActualizarCliente = new PantallaActualizarCliente(parent, true, conexion, cliente);
        pantallaActualizarCliente.setVisible(true);
        try {
            this.cliente = clientesDAO.obtenerUsuario(cliente.getId());
        } catch (PersistenciaException e) {

        }
        llenarTabla();
    }//GEN-LAST:event_btnActualizarClienteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizarCliente;
    private javax.swing.JButton btnAgregarCuenta;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNombreCliente;
    private javax.swing.JLabel lblPokebolaDer;
    private javax.swing.JLabel lblPokebolaIzq;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tblCuentas;
    // End of variables declaration//GEN-END:variables
    private IConexion conexion;
    private IClientesDAO clientesDAO;
    private ICuentasDAO cuentasDAO;
    private Cliente cliente;
    private Frame parent;
}
