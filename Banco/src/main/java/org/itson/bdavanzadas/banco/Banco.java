package org.itson.bdavanzadas.banco;

import org.itson.bdavanzadas.bancopersistencia.conexion.Conexion;
import org.itson.bdavanzadas.bancopersistencia.conexion.IConexion;
import java.util.logging.Logger;
import org.itson.bdavanzadas.banco.interfaces.PantallaInicio;
import org.itson.bdavanzadas.bancopersistencia.daos.ClientesDAO;
import org.itson.bdavanzadas.bancopersistencia.daos.CuentasDAO;
import org.itson.bdavanzadas.bancopersistencia.daos.IClientesDAO;
import org.itson.bdavanzadas.bancopersistencia.daos.ICuentasDAO;
import org.itson.bdavanzadas.bancopersistencia.daos.ITransaccionesDAO;
import org.itson.bdavanzadas.bancopersistencia.daos.TransaccionesDAO;

public class Banco {

    static final Logger logger = Logger.getLogger(Banco.class.getName());

    public static void main(String[] args) {
        String cadenaConexion = "jdbc:mysql://localhost/banco";
        String usuario = "root";
        String contrasenia = "password";

        IConexion conexion = new Conexion(cadenaConexion, usuario, contrasenia);
        ICuentasDAO cuentasDAO = new CuentasDAO(conexion);
        IClientesDAO clientesDAO = new ClientesDAO(conexion);
        ITransaccionesDAO transaccionesDAO = new TransaccionesDAO(conexion);
        
        PantallaInicio pantallaInicio = new PantallaInicio(conexion);
        pantallaInicio.setVisible(true);
        
//        //Prueba actualizar estado cuenta
//        CuentaActualizadaDTO cuentaActualizada = new CuentaActualizadaDTO();
//        cuentaActualizada.setNumero(Long.valueOf("1"));
//        cuentaActualizada.setSaldo(500.0f);
//        cuentaActualizada.setAlias("Ahorros Navidad");
//        cuentaActualizada.setFechaApertura(new Fecha("2024-02-17"));
//        cuentaActualizada.setIdCliente(Long.valueOf("1"));
//        cuentaActualizada.setActiva(true);
//        
//        Cuenta cuenta = null;
//        try {
//            cuenta = cuentasDAO.actualizar(cuentaActualizada);
//            logger.log(Level.INFO, cuenta.toString());
//        } catch (PersistenciaException e) {
//            logger.log(Level.SEVERE, null, e);
//        }
//        //-------------------------------
        
//        //Prueba agregar retiro
//        TransaccionNuevaDTO transaccionNueva = new TransaccionNuevaDTO();
//        transaccionNueva.setMonto(50.0f);
//        transaccionNueva.setFechaRealizacion(new Fecha());
//        transaccionNueva.setNumeroCuentaOrigen(Long.valueOf("1"));

//        RetiroNuevoDTO retiroNuevo = new RetiroNuevoDTO();
//        retiroNuevo.setContrasena(Long.valueOf("9182691282"));
//        retiroNuevo.setEstado("EN ESPERA");
        
//        Retiro retiro = null;
//        try {
//            retiro = transaccionesDAO.agregarRetiro(transaccionNueva, retiroNuevo);
//            logger.log(Level.INFO, retiro.toString());
//        } catch (PersistenciaException e) {
//            logger.log(Level.SEVERE, null, e);
//        }
//        //---------------------
        
//        // Prueba agregar transferencia
//        TransaccionNuevaDTO transaccionNueva = new TransaccionNuevaDTO();
//        transaccionNueva.setMonto(100.0f);
//        transaccionNueva.setFechaRealizacion(new Fecha());
//        transaccionNueva.setNumeroCuentaOrigen(Long.valueOf("1"));
        
//        TransferenciaNuevaDTO transferenciaNueva = new TransferenciaNuevaDTO();
//        transferenciaNueva.setNumeroCuentaDestino(Long.valueOf("2"));
        
//        Transferencia transferencia = null;
//        try {
//            transferencia = transaccionesDAO.agregarTransferencia(transaccionNueva, transferenciaNueva);
//            logger.log(Level.INFO, transferencia.toString());
//        } catch (PersistenciaException e) {
//            logger.log(Level.SEVERE, null, e);
//        }
//        // ----------------------------


//        Cliente clienteBuscado = null; 
//        try {
//            clienteBuscado = clientesDAO.iniciarSesion("abel123", "Password");;
//            logger.log(Level.INFO, clienteBuscado.toString());
//        } catch (PersistenciaException e) {
//            logger.log(Level.SEVERE, null, e);
//        }
//
//        ClienteNuevoDTO clienteNuevo = new ClienteNuevoDTO();
//        clienteNuevo.setNombre("Ricardo Alán");
//        clienteNuevo.setApellidoPaterno("Gutiérrez");
//        clienteNuevo.setApellidoMaterno("Garcés");
//        clienteNuevo.setFechaNacimiento(new Fecha("2004-03-21"));
//        clienteNuevo.setUsuario("imnotrichi");
//        clienteNuevo.setContrasena("password");
//        clienteNuevo.setCalle("Jalisco");
//        clienteNuevo.setColonia("Primavera");
//        clienteNuevo.setNumero("1782");
//        clienteNuevo.setCodigoPostal("85099");
//        clienteNuevo.setCiudad("Nogales");
//
//        Cliente clienteAgregado = null;
//
//        try {
//            clienteAgregado = clientesDAO.agregar(clienteNuevo);
//            logger.log(Level.INFO, clienteAgregado.toString());
//        } catch (PersistenciaException e) {
//            logger.log(Level.SEVERE, null, e);
//        }
//        
//        clienteNuevo.setColonia("Primero de mayo");
//        try {
//            clienteAgregado = clientesDAO.agregar(clienteNuevo);
//            logger.log(Level.INFO, clienteAgregado.toString());
//        } catch (PersistenciaException e) {
//            logger.log(Level.SEVERE, null, e);
//        }
//        
//        Cliente clienteActualizado = null;
//        try {
//            clienteActualizado = clientesDAO.actualizar(clienteAgregado);
//            logger.log(Level.INFO, clienteActualizado.toString());
//        } catch (PersistenciaException e) {
//            logger.log(Level.SEVERE, null, e);
//        }
        
//        List<Cliente> listaClientes = null;
//        try {
//            listaClientes = clientesDAO.consultar();
//            listaClientes.forEach(cliente -> System.out.println(cliente));
//        } catch (PersistenciaException ex) {
//            logger.log(Level.SEVERE, null, ex);
//        }
//        Cliente cliente = listaClientes.get(0);
        
//        PantallaInicio pantallaInicio = new PantallaInicio(conexion);
//        pantallaInicio.setVisible(true);
        
//        PantallaCuentas pc = new PantallaCuentas(conexion, cliente);
//        pc.setVisible(true);

//        List<Cliente> listaClientes = null;
//        try {
//            listaClientes = clientesDAO.consultar();
//            listaClientes.forEach(cliente -> System.out.println(cliente));
//        } catch (PersistenciaException ex) {
//            logger.log(Level.SEVERE, null, ex);
//        }
//        Cliente cliente = listaClientes.get(1);
//        
//        PantallaCuentas pantallaCuentas = new PantallaCuentas(conexion, cliente);
//        pantallaCuentas.setVisible(true);
//        try {
//            List<Cuenta> listaCuentas = cuentasDAO.consultar(Long.valueOf("1"));
//            listaCuentas.forEach(socio -> System.out.println(socio));
//        } catch (PersistenciaException ex) {
//            logger.log(Level.SEVERE,null,ex);
//        }
//
//        ClienteNuevoDTO clienteNuevo = new ClienteNuevoDTO();
//        clienteNuevo.setNombre("Ricardo Alán");
//        clienteNuevo.setApellidoPaterno("Gutiérrez");
//        clienteNuevo.setApellidoMaterno("Garcés");
//        clienteNuevo.setFechaNacimiento(new Fecha(21, 03, 2004));
//        clienteNuevo.setUsuario("imnotrichi");
//        clienteNuevo.setContrasena("password");
//
//        Cliente clienteAgregado = null;
//
//        try {
//            clienteAgregado = clientesDAO.agregar(clienteNuevo);
//            logger.log(Level.INFO, clienteAgregado.toString());
//        } catch (PersistenciaException e) {
//            logger.log(Level.SEVERE, null, e);
//        }
//
//        CuentaNuevaDTO cuentaNueva = new CuentaNuevaDTO();
//        cuentaNueva.setSaldo(0);
//        cuentaNueva.setAlias("Pokemones");
//        cuentaNueva.setFechaApertura(new Fecha());
//        cuentaNueva.setIdCliente(Long.valueOf("1"));
//        cuentaNueva.setActiva(true);
//
//        try {
//            Cuenta cuentaAgregada = cuentasDAO.agregar(cuentaNueva);
//            logger.log(Level.INFO, cuentaAgregada.toString());
//        } catch (PersistenciaException e) {
//            logger.log(Level.SEVERE, null, e);
//        }
    }
}
