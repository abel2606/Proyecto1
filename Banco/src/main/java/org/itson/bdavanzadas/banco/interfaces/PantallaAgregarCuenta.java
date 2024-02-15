package org.itson.bdavanzadas.banco.interfaces;

import javax.swing.JOptionPane;
import org.itson.bdavanzadas.bancodominio.Cuenta;
import org.itson.bdavanzadas.bancodominio.Fecha;
import org.itson.bdavanzadas.bancopersistencia.conexion.IConexion;
import org.itson.bdavanzadas.bancopersistencia.daos.CuentasDAO;
import org.itson.bdavanzadas.bancopersistencia.dtos.CuentaNuevaDTO;
import org.itson.bdavanzadas.bancopersistencia.excepciones.CuentaNoValidaException;
import org.itson.bdavanzadas.bancopersistencia.excepciones.PersistenciaException;

public class PantallaAgregarCuenta extends javax.swing.JFrame {

    /**
     * Creates new form PantallaAgregarCuenta.
     *
     * @param conexion La conexión a la base de datos
     */
    public PantallaAgregarCuenta(IConexion conexion) {
        initComponents();
        setTitle("Agregar Cuenta");
        cuentasDAO = new CuentasDAO(conexion);
    }

    /**
     * Permite guardar los datos capturados en el form y crear una cuenta con
     * ellos.
     */
    public void guardar() {
        String nombrePropietario = txtPropietario.getText();
        String alias = txtAliasCuenta.getText();

        CuentaNuevaDTO cuentaNueva = new CuentaNuevaDTO();
        cuentaNueva.setAlias(alias);
        cuentaNueva.setNombrePropietario(nombrePropietario);
        cuentaNueva.setFechaApertura(new Fecha());
        cuentaNueva.setSaldo(0.0f);
        cuentaNueva.setActiva(true);

        try {
            cuentaNueva.isValid();
            Cuenta cuentaAgregada = cuentasDAO.agregar(cuentaNueva);
            JOptionPane.showMessageDialog(this, "Se registró la cuenta con el número " + cuentaAgregada.getNumero(),
                    "Notificación.", JOptionPane.INFORMATION_MESSAGE);
        } catch (PersistenciaException ex) {
            JOptionPane.showMessageDialog(this, "No fue posible agregar la cuenta.",
                    "Error de almacenamiento.", JOptionPane.ERROR_MESSAGE);
        } catch (CuentaNoValidaException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Error de almacenamiento.", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Permite limpiar los datos de los campos de texto.
     */
    private void limpiarDatos() {
        txtPropietario.setText("");
        txtAliasCuenta.setText("");
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
        lblPropietario = new javax.swing.JLabel();
        txtPropietario = new javax.swing.JTextField();
        txtAliasCuenta = new javax.swing.JTextField();
        lblAliasCuenta = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(189, 255, 188));
        jPanel1.setPreferredSize(new java.awt.Dimension(1100, 450));

        jPanel2.setBackground(new java.awt.Color(0, 255, 117));
        jPanel2.setPreferredSize(new java.awt.Dimension(864, 83));

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setText("AGREGAR CUENTA");

        lblPokebolaIzq.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PokeBolaBlancaVerde.png"))); // NOI18N

        lblPokebolaDer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PokeBolaBlancaVerde.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblPokebolaIzq)
                .addGap(243, 243, 243)
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        lblPropietario.setBackground(new java.awt.Color(255, 255, 255));
        lblPropietario.setFont(new java.awt.Font("Arial", 1, 34)); // NOI18N
        lblPropietario.setForeground(new java.awt.Color(41, 92, 52));
        lblPropietario.setText("Propietario");

        txtPropietario.setFont(new java.awt.Font("Arial", 1, 34)); // NOI18N
        txtPropietario.setForeground(new java.awt.Color(99, 134, 107));
        txtPropietario.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 117), 2, true));

        txtAliasCuenta.setFont(new java.awt.Font("Arial", 1, 34)); // NOI18N
        txtAliasCuenta.setForeground(new java.awt.Color(99, 134, 107));
        txtAliasCuenta.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 117), 2, true));

        lblAliasCuenta.setBackground(new java.awt.Color(255, 255, 255));
        lblAliasCuenta.setFont(new java.awt.Font("Arial", 1, 34)); // NOI18N
        lblAliasCuenta.setForeground(new java.awt.Color(41, 92, 52));
        lblAliasCuenta.setText("Alias de la Cuenta");

        btnCancelar.setBackground(new java.awt.Color(0, 255, 117));
        btnCancelar.setFont(new java.awt.Font("Arial", 1, 34)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("CANCELAR");
        btnCancelar.setFocusCycleRoot(true);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnAceptar.setBackground(new java.awt.Color(0, 255, 117));
        btnAceptar.setFont(new java.awt.Font("Arial", 1, 34)); // NOI18N
        btnAceptar.setForeground(new java.awt.Color(255, 255, 255));
        btnAceptar.setText("ACEPTAR");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(149, 149, 149)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblAliasCuenta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addComponent(txtAliasCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblPropietario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPropietario, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(149, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPropietario)
                    .addComponent(txtPropietario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAliasCuenta)
                    .addComponent(txtAliasCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(81, 81, 81)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnAceptar))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiarDatos();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        guardar();
        limpiarDatos();
    }//GEN-LAST:event_btnAceptarActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblAliasCuenta;
    private javax.swing.JLabel lblPokebolaDer;
    private javax.swing.JLabel lblPokebolaIzq;
    private javax.swing.JLabel lblPropietario;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtAliasCuenta;
    private javax.swing.JTextField txtPropietario;
    // End of variables declaration//GEN-END:variables
    private CuentasDAO cuentasDAO;
}
