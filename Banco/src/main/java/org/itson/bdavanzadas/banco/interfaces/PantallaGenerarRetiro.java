package org.itson.bdavanzadas.banco.interfaces;

import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JOptionPane;
import org.itson.bdavanzadas.bancodominio.Cuenta;
import org.itson.bdavanzadas.bancodominio.Fecha;
import org.itson.bdavanzadas.bancopersistencia.conexion.IConexion;
import org.itson.bdavanzadas.bancopersistencia.daos.ITransaccionesDAO;
import org.itson.bdavanzadas.bancopersistencia.daos.TransaccionesDAO;
import org.itson.bdavanzadas.bancopersistencia.dtos.TransaccionNuevaDTO;
import org.itson.bdavanzadas.bancopersistencia.dtos.TransferenciaNuevaDTO;
import org.itson.bdavanzadas.bancopersistencia.excepciones.PersistenciaException;

public class PantallaGenerarRetiro extends javax.swing.JDialog {

    /**
     * Creates new form PantallaCuentas.
     *
     * @param parent
     * @param modal
     * @param conexion
     * @param cuenta
     */
    public PantallaGenerarRetiro(java.awt.Frame parent, boolean modal, IConexion conexion, Cuenta cuenta) {
        super(parent, modal);
        initComponents();
        centraCuadroDialogo(parent);
        setTitle("Hacer Transferencia");
        this.conexion = conexion;
        this.cuenta = cuenta;
        transaccionesDAO = new TransaccionesDAO(conexion);

        txtAliasCuenta.setText(cuenta.getAlias());
        txtNumeroCuenta.setText(cuenta.getNumero().toString());
        txtSaldo.setText(cuenta.getSaldo().toString());
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

    private void generarRetiro() {
        if (cuenta.getSaldo() >= Float.valueOf(txtMonto.getText())) {
            
        } else {
            JOptionPane.showMessageDialog(this, "El saldo de la cuenta no es suficiente para generar el retiro.",
                    "Error", JOptionPane.ERROR_MESSAGE);
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
        lblMonto = new javax.swing.JLabel();
        txtAliasCuenta = new javax.swing.JTextField();
        lblDatosCuenta = new javax.swing.JLabel();
        txtSaldo = new javax.swing.JTextField();
        lblSaldo = new javax.swing.JLabel();
        lblCuenta = new javax.swing.JLabel();
        lblNumeroCuenta = new javax.swing.JLabel();
        txtNumeroCuenta = new javax.swing.JTextField();
        lblDatosTransferencia = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnGenerar = new javax.swing.JButton();
        txtMonto = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(189, 255, 188));
        jPanel1.setPreferredSize(new java.awt.Dimension(1100, 450));

        jPanel2.setBackground(new java.awt.Color(0, 255, 117));
        jPanel2.setPreferredSize(new java.awt.Dimension(864, 83));

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setText("GENERAR RETIRO");

        lblPokebolaIzq.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PokeBolaBlancaVerde.png"))); // NOI18N

        lblPokebolaDer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PokeBolaBlancaVerde.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblPokebolaIzq)
                .addGap(252, 252, 252)
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 253, Short.MAX_VALUE)
                .addComponent(lblPokebolaDer)
                .addGap(19, 19, 19))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTitulo)
                    .addComponent(lblPokebolaIzq)
                    .addComponent(lblPokebolaDer))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        lblMonto.setBackground(new java.awt.Color(255, 255, 255));
        lblMonto.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        lblMonto.setForeground(new java.awt.Color(41, 92, 52));
        lblMonto.setText("Monto del Retiro");

        txtAliasCuenta.setEditable(false);
        txtAliasCuenta.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        txtAliasCuenta.setForeground(new java.awt.Color(99, 134, 107));
        txtAliasCuenta.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 117), 2, true));

        lblDatosCuenta.setBackground(new java.awt.Color(255, 255, 255));
        lblDatosCuenta.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        lblDatosCuenta.setForeground(new java.awt.Color(41, 92, 52));
        lblDatosCuenta.setText("DATOS DE LA CUENTA");

        txtSaldo.setEditable(false);
        txtSaldo.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        txtSaldo.setForeground(new java.awt.Color(99, 134, 107));
        txtSaldo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 117), 2, true));

        lblSaldo.setBackground(new java.awt.Color(255, 255, 255));
        lblSaldo.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        lblSaldo.setForeground(new java.awt.Color(41, 92, 52));
        lblSaldo.setText("Saldo");

        lblCuenta.setBackground(new java.awt.Color(255, 255, 255));
        lblCuenta.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        lblCuenta.setForeground(new java.awt.Color(41, 92, 52));
        lblCuenta.setText("Alias");

        lblNumeroCuenta.setBackground(new java.awt.Color(255, 255, 255));
        lblNumeroCuenta.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        lblNumeroCuenta.setForeground(new java.awt.Color(41, 92, 52));
        lblNumeroCuenta.setText("Número");

        txtNumeroCuenta.setEditable(false);
        txtNumeroCuenta.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        txtNumeroCuenta.setForeground(new java.awt.Color(99, 134, 107));
        txtNumeroCuenta.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 117), 2, true));

        lblDatosTransferencia.setBackground(new java.awt.Color(255, 255, 255));
        lblDatosTransferencia.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        lblDatosTransferencia.setForeground(new java.awt.Color(41, 92, 52));
        lblDatosTransferencia.setText("DATOS DEL RETIRO");

        btnCancelar.setBackground(new java.awt.Color(0, 255, 117));
        btnCancelar.setFont(new java.awt.Font("Arial", 1, 29)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("CANCELAR");
        btnCancelar.setPreferredSize(new java.awt.Dimension(179, 47));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnGenerar.setBackground(new java.awt.Color(0, 255, 117));
        btnGenerar.setFont(new java.awt.Font("Arial", 1, 29)); // NOI18N
        btnGenerar.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerar.setText("GENERAR");
        btnGenerar.setPreferredSize(new java.awt.Dimension(200, 47));
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });

        txtMonto.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        txtMonto.setForeground(new java.awt.Color(99, 134, 107));
        txtMonto.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 117), 2, true));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblCuenta)
                        .addGap(18, 18, 18)
                        .addComponent(txtAliasCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addComponent(lblNumeroCuenta)
                        .addGap(18, 18, 18)
                        .addComponent(txtNumeroCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblMonto)
                        .addGap(64, 64, 64)
                        .addComponent(txtMonto)))
                .addGap(120, 120, 120))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(350, 350, 350)
                .addComponent(lblSaldo)
                .addGap(18, 18, 18)
                .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDatosCuenta)
                .addGap(381, 381, 381))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(401, 401, 401)
                .addComponent(lblDatosTransferencia)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(lblDatosCuenta)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCuenta)
                    .addComponent(txtAliasCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNumeroCuenta)
                    .addComponent(txtNumeroCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSaldo)
                    .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(lblDatosTransferencia)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMonto)
                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
        generarRetiro();
    }//GEN-LAST:event_btnGenerarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGenerar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblCuenta;
    private javax.swing.JLabel lblDatosCuenta;
    private javax.swing.JLabel lblDatosTransferencia;
    private javax.swing.JLabel lblMonto;
    private javax.swing.JLabel lblNumeroCuenta;
    private javax.swing.JLabel lblPokebolaDer;
    private javax.swing.JLabel lblPokebolaIzq;
    private javax.swing.JLabel lblSaldo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtAliasCuenta;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextField txtNumeroCuenta;
    private javax.swing.JTextField txtSaldo;
    // End of variables declaration//GEN-END:variables
    private IConexion conexion;
    private ITransaccionesDAO transaccionesDAO;
    private Cuenta cuenta;
}
