package org.itson.bdavanzadas.banco.interfaces;

import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JOptionPane;
import org.itson.bdavanzadas.banco.Encriptador;
import org.itson.bdavanzadas.bancodominio.Fecha;
import org.itson.bdavanzadas.bancopersistencia.conexion.IConexion;
import org.itson.bdavanzadas.bancopersistencia.daos.ClientesDAO;
import org.itson.bdavanzadas.bancopersistencia.daos.IClientesDAO;
import org.itson.bdavanzadas.bancopersistencia.dtos.ClienteNuevoDTO;
import org.itson.bdavanzadas.bancopersistencia.excepciones.ClienteNoValidoException;
import org.itson.bdavanzadas.bancopersistencia.excepciones.PersistenciaException;
import org.itson.bdavanzadas.bancopersistencia.validadores.Validadores;

public class PantallaRegistrarCliente extends javax.swing.JDialog {

    /**
     * Creates new form PantallaRegistrarCliente.
     *
     * @param parent
     * @param modal
     * @param conexion
     */
    public PantallaRegistrarCliente(java.awt.Frame parent, boolean modal, IConexion conexion) {
        super(parent, modal);
        initComponents();
        centraCuadroDialogo(parent);
        setTitle("Registrar Cliente");
        this.conexion = conexion;
        clientesDAO = new ClientesDAO(conexion);
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
     * Permite capturar la información del cliente del formulario y guardarla en
     * la base de datos.
     */
    public void guardar() {
        Validadores validador = new Validadores();
        
        if (validador.validarFecha(txtFechaNacimiento.getText())) {
            String nombre = txtNombre.getText().trim();
            String apellidoPaterno = txtApellidoPaterno.getText().trim();
            String apellidoMaterno = txtApellidoMaterno.getText().trim();

            String[] datosFecha = txtFechaNacimiento.getText().trim().split("/");

            String fechaNacimiento = datosFecha[2] + "-" + datosFecha[1] + "-" + datosFecha[0];
            String calle = txtCalle.getText().trim();
            String numero = txtNumero.getText().trim();
            String colonia = txtColonia.getText().trim();
            String codigoPostal = txtCodigoPostal.getText().trim();
            String ciudad = txtCiudad.getText().trim();
            String usuario = txtUsuario.getText().trim();
            String contrasena = Encriptador.encriptar(pswContrasena.getText().trim());
            String contrasenaConfirmar = Encriptador.encriptar(pswConfirmarContrasena.getText().trim());


            ClienteNuevoDTO clienteNuevo = new ClienteNuevoDTO();
            clienteNuevo.setNombre(nombre);
            clienteNuevo.setApellidoPaterno(apellidoPaterno);
            clienteNuevo.setApellidoMaterno(apellidoMaterno);
            clienteNuevo.setFechaNacimiento(new Fecha(fechaNacimiento));
            clienteNuevo.setCalle(calle);
            clienteNuevo.setNumero(numero);
            clienteNuevo.setColonia(colonia);
            clienteNuevo.setCodigoPostal(codigoPostal);
            clienteNuevo.setCiudad(ciudad);
            clienteNuevo.setUsuario(usuario);
            clienteNuevo.setContrasena(contrasena);

            try {
                if (clienteNuevo.isValid()) {
                    if (!clientesDAO.existeUsuario(usuario)) {
                        if (contrasena.equals(contrasenaConfirmar)) {
                            clientesDAO.agregar(clienteNuevo);
                            dispose();
                            JOptionPane.showMessageDialog(this, "Se agregó al cliente correctamente.",
                                    "Información", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden",
                                    "Error de contraseñas", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "El usuario ingresado ya existe.",
                                    "Error de usuario", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (PersistenciaException ex) {
                JOptionPane.showMessageDialog(this, "No fue posible agregar el cliente.",
                        "Error de almacenamiento.", JOptionPane.ERROR_MESSAGE);
            } catch (ClienteNoValidoException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                        "Error de almacenamiento.", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "La fecha ingresada no cumple con el formato dd/mm/aaaa.",
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
        jPanel3 = new javax.swing.JPanel();
        lblDatosPersonales = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblApellidoMarteno = new javax.swing.JLabel();
        txtApellidoMaterno = new javax.swing.JTextField();
        lblApellidoPaterno = new javax.swing.JLabel();
        txtApellidoPaterno = new javax.swing.JTextField();
        lblDomicilio = new javax.swing.JLabel();
        txtCalle = new javax.swing.JTextField();
        lblCalle = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        lblNumero = new javax.swing.JLabel();
        txtColonia = new javax.swing.JTextField();
        lblColonia = new javax.swing.JLabel();
        txtCodigoPostal = new javax.swing.JTextField();
        lblCodigoPostal = new javax.swing.JLabel();
        txtCiudad = new javax.swing.JTextField();
        lblCiudad = new javax.swing.JLabel();
        lblFechaNacimiento = new javax.swing.JLabel();
        txtFechaNacimiento = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblCuenta = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        lblContrasena = new javax.swing.JLabel();
        pswContrasena = new javax.swing.JPasswordField();
        lblConfirmarContrasena = new javax.swing.JLabel();
        pswConfirmarContrasena = new javax.swing.JPasswordField();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(189, 255, 188));
        jPanel1.setPreferredSize(new java.awt.Dimension(1100, 450));

        jPanel2.setBackground(new java.awt.Color(0, 255, 117));
        jPanel2.setPreferredSize(new java.awt.Dimension(864, 83));

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setText("REGISTRAR CLIENTE");

        lblPokebolaIzq.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PokeBolaBlancaVerde.png"))); // NOI18N

        lblPokebolaDer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PokeBolaBlancaVerde.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblPokebolaIzq)
                .addGap(219, 219, 219)
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

        jPanel3.setBackground(new java.awt.Color(189, 255, 188));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 117), 2, true));
        jPanel3.setPreferredSize(new java.awt.Dimension(579, 455));

        lblDatosPersonales.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        lblDatosPersonales.setForeground(new java.awt.Color(41, 92, 52));
        lblDatosPersonales.setText("DATOS PERSONALES");

        lblNombre.setFont(new java.awt.Font("Arial", 1, 19)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(0, 168, 37));
        lblNombre.setText("Nombre(s)");

        txtNombre.setFont(new java.awt.Font("Arial", 1, 19)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(99, 134, 107));
        txtNombre.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 117), 2, true));

        lblApellidoMarteno.setFont(new java.awt.Font("Arial", 1, 19)); // NOI18N
        lblApellidoMarteno.setForeground(new java.awt.Color(0, 168, 37));
        lblApellidoMarteno.setText("Apellido Materno");

        txtApellidoMaterno.setFont(new java.awt.Font("Arial", 1, 19)); // NOI18N
        txtApellidoMaterno.setForeground(new java.awt.Color(99, 134, 107));
        txtApellidoMaterno.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 117), 2, true));

        lblApellidoPaterno.setFont(new java.awt.Font("Arial", 1, 19)); // NOI18N
        lblApellidoPaterno.setForeground(new java.awt.Color(0, 168, 37));
        lblApellidoPaterno.setText("Apellido Paterno");

        txtApellidoPaterno.setFont(new java.awt.Font("Arial", 1, 19)); // NOI18N
        txtApellidoPaterno.setForeground(new java.awt.Color(99, 134, 107));
        txtApellidoPaterno.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 117), 2, true));

        lblDomicilio.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        lblDomicilio.setForeground(new java.awt.Color(41, 92, 52));
        lblDomicilio.setText("DOMICILIO");

        txtCalle.setFont(new java.awt.Font("Arial", 1, 19)); // NOI18N
        txtCalle.setForeground(new java.awt.Color(99, 134, 107));
        txtCalle.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 117), 2, true));

        lblCalle.setFont(new java.awt.Font("Arial", 1, 19)); // NOI18N
        lblCalle.setForeground(new java.awt.Color(0, 168, 37));
        lblCalle.setText("Calle");

        txtNumero.setFont(new java.awt.Font("Arial", 1, 19)); // NOI18N
        txtNumero.setForeground(new java.awt.Color(99, 134, 107));
        txtNumero.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 117), 2, true));

        lblNumero.setFont(new java.awt.Font("Arial", 1, 19)); // NOI18N
        lblNumero.setForeground(new java.awt.Color(0, 168, 37));
        lblNumero.setText("Número");

        txtColonia.setFont(new java.awt.Font("Arial", 1, 19)); // NOI18N
        txtColonia.setForeground(new java.awt.Color(99, 134, 107));
        txtColonia.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 117), 2, true));

        lblColonia.setFont(new java.awt.Font("Arial", 1, 19)); // NOI18N
        lblColonia.setForeground(new java.awt.Color(0, 168, 37));
        lblColonia.setText("Colonia");

        txtCodigoPostal.setFont(new java.awt.Font("Arial", 1, 19)); // NOI18N
        txtCodigoPostal.setForeground(new java.awt.Color(99, 134, 107));
        txtCodigoPostal.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 117), 2, true));

        lblCodigoPostal.setFont(new java.awt.Font("Arial", 1, 19)); // NOI18N
        lblCodigoPostal.setForeground(new java.awt.Color(0, 168, 37));
        lblCodigoPostal.setText("Código Postal");

        txtCiudad.setFont(new java.awt.Font("Arial", 1, 19)); // NOI18N
        txtCiudad.setForeground(new java.awt.Color(99, 134, 107));
        txtCiudad.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 117), 2, true));

        lblCiudad.setFont(new java.awt.Font("Arial", 1, 19)); // NOI18N
        lblCiudad.setForeground(new java.awt.Color(0, 168, 37));
        lblCiudad.setText("Ciudad");

        lblFechaNacimiento.setFont(new java.awt.Font("Arial", 1, 19)); // NOI18N
        lblFechaNacimiento.setForeground(new java.awt.Color(0, 168, 37));
        lblFechaNacimiento.setText("Fecha de Nacimiento");

        txtFechaNacimiento.setFont(new java.awt.Font("Arial", 1, 19)); // NOI18N
        txtFechaNacimiento.setForeground(new java.awt.Color(99, 134, 107));
        txtFechaNacimiento.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 117), 2, true));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 235, 125));
        jLabel1.setText("(Formato dd/mm/aaaa)");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCiudad)
                    .addComponent(txtColonia, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblColonia)
                    .addComponent(txtCalle, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCalle)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblDatosPersonales)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(lblNombre)
                                        .addGap(186, 186, 186))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                                            .addComponent(txtApellidoMaterno, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblApellidoMarteno, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGap(23, 23, 23)))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(lblDomicilio)
                                    .addGap(168, 168, 168)))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtFechaNacimiento)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblApellidoPaterno)
                                        .addComponent(txtApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNumero)
                                        .addComponent(txtCodigoPostal, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblCodigoPostal)
                                        .addComponent(lblFechaNacimiento))
                                    .addGap(0, 0, Short.MAX_VALUE))))))
                .addContainerGap(16, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(80, 80, 80))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblDatosPersonales)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(lblApellidoPaterno))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApellidoMarteno)
                    .addComponent(lblFechaNacimiento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDomicilio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lblCalle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lblNumero)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblColonia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtColonia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodigoPostal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(lblCodigoPostal)
                        .addGap(33, 33, 33)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCiudad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(189, 255, 188));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 117), 2, true));
        jPanel4.setPreferredSize(new java.awt.Dimension(579, 455));

        lblCuenta.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        lblCuenta.setForeground(new java.awt.Color(41, 92, 52));
        lblCuenta.setText("CUENTA");

        lblUsuario.setFont(new java.awt.Font("Arial", 1, 19)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(0, 168, 37));
        lblUsuario.setText("Usuario");

        txtUsuario.setFont(new java.awt.Font("Arial", 1, 19)); // NOI18N
        txtUsuario.setForeground(new java.awt.Color(99, 134, 107));
        txtUsuario.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 117), 2, true));

        lblContrasena.setFont(new java.awt.Font("Arial", 1, 19)); // NOI18N
        lblContrasena.setForeground(new java.awt.Color(0, 168, 37));
        lblContrasena.setText("Contraseña");

        pswContrasena.setFont(new java.awt.Font("Arial", 1, 19)); // NOI18N
        pswContrasena.setForeground(new java.awt.Color(99, 134, 107));
        pswContrasena.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 117), 2, true));

        lblConfirmarContrasena.setFont(new java.awt.Font("Arial", 1, 19)); // NOI18N
        lblConfirmarContrasena.setForeground(new java.awt.Color(0, 168, 37));
        lblConfirmarContrasena.setText("Confirmar Contraseña");

        pswConfirmarContrasena.setFont(new java.awt.Font("Arial", 1, 19)); // NOI18N
        pswConfirmarContrasena.setForeground(new java.awt.Color(99, 134, 107));
        pswConfirmarContrasena.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 117), 2, true));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblContrasena)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblConfirmarContrasena)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtUsuario)
                                .addComponent(pswContrasena)
                                .addComponent(pswConfirmarContrasena)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblUsuario)
                                        .addComponent(lblCuenta))
                                    .addGap(331, 331, 331))))
                        .addGap(0, 16, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblCuenta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblContrasena)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pswContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblConfirmarContrasena)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pswConfirmarContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        btnAceptar.setBackground(new java.awt.Color(0, 255, 117));
        btnAceptar.setFont(new java.awt.Font("Arial", 1, 29)); // NOI18N
        btnAceptar.setForeground(new java.awt.Color(255, 255, 255));
        btnAceptar.setText("ACEPTAR");
        btnAceptar.setPreferredSize(new java.awt.Dimension(200, 47));
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
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

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        guardar();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblApellidoMarteno;
    private javax.swing.JLabel lblApellidoPaterno;
    private javax.swing.JLabel lblCalle;
    private javax.swing.JLabel lblCiudad;
    private javax.swing.JLabel lblCodigoPostal;
    private javax.swing.JLabel lblColonia;
    private javax.swing.JLabel lblConfirmarContrasena;
    private javax.swing.JLabel lblContrasena;
    private javax.swing.JLabel lblCuenta;
    private javax.swing.JLabel lblDatosPersonales;
    private javax.swing.JLabel lblDomicilio;
    private javax.swing.JLabel lblFechaNacimiento;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblPokebolaDer;
    private javax.swing.JLabel lblPokebolaIzq;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPasswordField pswConfirmarContrasena;
    private javax.swing.JPasswordField pswContrasena;
    private javax.swing.JTextField txtApellidoMaterno;
    private javax.swing.JTextField txtApellidoPaterno;
    private javax.swing.JTextField txtCalle;
    private javax.swing.JTextField txtCiudad;
    private javax.swing.JTextField txtCodigoPostal;
    private javax.swing.JTextField txtColonia;
    private javax.swing.JTextField txtFechaNacimiento;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
    private IConexion conexion;
    private IClientesDAO clientesDAO;
}
