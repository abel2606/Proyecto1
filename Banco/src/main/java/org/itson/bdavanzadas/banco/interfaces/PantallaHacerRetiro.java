package org.itson.bdavanzadas.banco.interfaces;

import java.awt.Dimension;
import java.awt.Point;
import java.sql.SQLException;
import java.util.Calendar;
import javax.swing.JOptionPane;
import org.itson.bdavanzadas.bancodominio.Fecha;
import org.itson.bdavanzadas.bancopersistencia.conexion.IConexion;
import org.itson.bdavanzadas.bancopersistencia.daos.CuentasDAO;
import org.itson.bdavanzadas.bancopersistencia.daos.ICuentasDAO;
import org.itson.bdavanzadas.bancopersistencia.daos.ITransaccionesDAO;
import org.itson.bdavanzadas.bancopersistencia.daos.TransaccionesDAO;
import org.itson.bdavanzadas.bancopersistencia.excepciones.PersistenciaException;

public class PantallaHacerRetiro extends javax.swing.JDialog {

    /**
     * Creates new form PantallaCuentas.
     *
     * @param parent
     * @param modal
     * @param conexion
     */
    public PantallaHacerRetiro(java.awt.Frame parent, boolean modal, IConexion conexion) {
        super(parent, modal);
        initComponents();
        centraCuadroDialogo(parent);
        setTitle("Retiro Sin Cuenta");
        this.conexion = conexion;
        cuentasDAO = new CuentasDAO(conexion);
        transaccionesDAO = new TransaccionesDAO(conexion);
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
        btnAtras = new javax.swing.JButton();
        lblFolio = new javax.swing.JLabel();
        txtFolio = new javax.swing.JTextField();
        lblContrasena = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JButton();
        lblInstrucciones = new javax.swing.JLabel();
        pswContrasena = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(189, 255, 188));
        jPanel1.setPreferredSize(new java.awt.Dimension(1100, 450));

        jPanel2.setBackground(new java.awt.Color(0, 255, 117));
        jPanel2.setPreferredSize(new java.awt.Dimension(864, 83));

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setText("RETIRO SIN CUENTA");

        lblPokebolaIzq.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PokeBolaBlancaVerde.png"))); // NOI18N

        lblPokebolaDer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PokeBolaBlancaVerde.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblPokebolaIzq)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 226, Short.MAX_VALUE)
                .addComponent(lblTitulo)
                .addGap(226, 226, 226)
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

        lblFolio.setBackground(new java.awt.Color(255, 255, 255));
        lblFolio.setFont(new java.awt.Font("Arial", 1, 34)); // NOI18N
        lblFolio.setForeground(new java.awt.Color(41, 92, 52));
        lblFolio.setText("Folio del Retiro");

        txtFolio.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        txtFolio.setForeground(new java.awt.Color(99, 134, 107));
        txtFolio.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 117), 2, true));

        lblContrasena.setBackground(new java.awt.Color(255, 255, 255));
        lblContrasena.setFont(new java.awt.Font("Arial", 1, 34)); // NOI18N
        lblContrasena.setForeground(new java.awt.Color(41, 92, 52));
        lblContrasena.setText("Contraseña");

        btnAceptar.setBackground(new java.awt.Color(0, 255, 117));
        btnAceptar.setFont(new java.awt.Font("Arial", 1, 34)); // NOI18N
        btnAceptar.setForeground(new java.awt.Color(255, 255, 255));
        btnAceptar.setText("ACEPTAR");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        lblInstrucciones.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        lblInstrucciones.setForeground(new java.awt.Color(41, 92, 52));
        lblInstrucciones.setText("INGRESE LOS DATOS SOLICITADOS");

        pswContrasena.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        pswContrasena.setForeground(new java.awt.Color(99, 134, 107));
        pswContrasena.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 117), 2, true));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btnAtras)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(149, 149, 149)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFolio)
                    .addComponent(lblContrasena))
                .addGap(65, 65, 65)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pswContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(440, 440, 440))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblInstrucciones)
                .addGap(233, 233, 233))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAtras)
                .addGap(18, 18, 18)
                .addComponent(lblInstrucciones)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFolio)
                    .addComponent(txtFolio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblContrasena)
                    .addComponent(pswContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(86, 86, 86)
                .addComponent(btnAceptar)
                .addGap(48, 48, 48))
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

    public void hacerRetiro() {
        long folio = 0;
        long contrasena = 0;
        try {
            folio = Long.parseLong(txtFolio.getText());
            contrasena = Long.parseLong(pswContrasena.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Solo se pueden introducir valores numericos",
                    "Error de almacenamiento.", JOptionPane.ERROR_MESSAGE);
        }

        try {
            if (transaccionesDAO.existeFolioRetiro(folio)) {

                if (transaccionesDAO.existeRetiro(folio, contrasena)) {
                    Fecha fechaRetiro = transaccionesDAO.consultarFechaTransaccion(folio);
                    Fecha fechaAcutal = new Fecha();

                    System.out.println(fechaRetiro.toStringHora());
                    System.out.println(fechaAcutal.toStringHora());
                    System.out.println(diferenciaMayorA10Minutos(fechaAcutal, fechaAcutal));
                    if (true) {
                        if (transaccionesDAO.estadoRetiro(folio).equalsIgnoreCase("COBRADO")) {
                            JOptionPane.showMessageDialog(this, "El retiro ya ha sido cobrado",
                                    "Error de estado.", JOptionPane.ERROR_MESSAGE);
                            transaccionesDAO.hacerRetiro(folio, contrasena);
                        } else if (transaccionesDAO.estadoRetiro(folio).equalsIgnoreCase("EN ESPERA")) {
                            transaccionesDAO.hacerRetiro(folio, contrasena);
                        } else if (transaccionesDAO.estadoRetiro(folio).equalsIgnoreCase("NO COBRADO")) {
                            JOptionPane.showMessageDialog(this, "El retiro ya no es valido pasados 10 minutos",
                                    "Error de tiempo", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "La contraseña esta incorrecta",
                            "Error de folio", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "El folio no existe",
                        "Error de folio", JOptionPane.ERROR_MESSAGE);
            }

        } catch (PersistenciaException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Error de almacenamiento.", JOptionPane.ERROR_MESSAGE);
        }

    }

    public boolean diferenciaMayorA10Minutos(Fecha fecha1, Fecha fecha2) {
        long diferenciaMilisegundos = fecha1.getTimeInMillis() - fecha2.getTimeInMillis();

        long diferenciaMinutos = diferenciaMilisegundos / (60 * 1000);

        return diferenciaMinutos > 10 && fecha1.get(Calendar.YEAR) == fecha2.get(Calendar.YEAR) && fecha1.get(Calendar.DAY_OF_YEAR) == fecha2.get(Calendar.DAY_OF_YEAR);
    }
    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        dispose();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        hacerRetiro();
    }//GEN-LAST:event_btnAceptarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnAtras;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblContrasena;
    private javax.swing.JLabel lblFolio;
    private javax.swing.JLabel lblInstrucciones;
    private javax.swing.JLabel lblPokebolaDer;
    private javax.swing.JLabel lblPokebolaIzq;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPasswordField pswContrasena;
    private javax.swing.JTextField txtFolio;
    // End of variables declaration//GEN-END:variables
    private IConexion conexion;
    private ITransaccionesDAO transaccionesDAO;
    private ICuentasDAO cuentasDAO;
}
