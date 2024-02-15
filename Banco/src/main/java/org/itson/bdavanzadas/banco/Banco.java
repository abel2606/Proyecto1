package org.itson.bdavanzadas.banco;

import java.util.logging.Level;
import org.itson.bdavanzadas.bancopersistencia.conexion.Conexion;
import org.itson.bdavanzadas.bancopersistencia.conexion.IConexion;
import java.util.logging.Logger;
import org.itson.bdavanzadas.banco.interfaces.PantallaCuentas;
import org.itson.bdavanzadas.bancodominio.Cliente;
import org.itson.bdavanzadas.bancodominio.Fecha;
import org.itson.bdavanzadas.bancopersistencia.daos.CuentasDAO;
import org.itson.bdavanzadas.bancopersistencia.daos.IClientesDAO;
import org.itson.bdavanzadas.bancopersistencia.daos.ICuentasDAO;
import org.itson.bdavanzadas.bancopersistencia.dtos.ClienteNuevoDTO;
import org.itson.bdavanzadas.bancopersistencia.excepciones.PersistenciaException;

public class Banco {

    static final Logger logger = Logger.getLogger(Banco.class.getName());

    public static void main(String[] args) {
        String cadenaConexion = "jdbc:mysql://localhost/banco";
        String usuario = "root";
        String contrasenia = "password";

        IConexion conexion = new Conexion(cadenaConexion, usuario, contrasenia);
        ICuentasDAO cuentaDAO = new CuentasDAO(conexion);
        IClientesDAO clientesDAO = new ClientesDAO(conexion);
        PantallaCuentas pantallaCuentas = new PantallaCuentas(conexion);
        pantallaCuentas.setVisible(true);
        
        ClienteNuevoDTO clienteNuevo = new ClienteNuevoDTO();
        clienteNuevo.setNombre("Ricardo Alán");
        clienteNuevo.setApellidoPaterno("Gutiérrez");
        clienteNuevo.setApellidoMaterno("Garcés");
        clienteNuevo.setFechaNacimiento(new Fecha(21, 03, 2004));
        clienteNuevo.setUsuario("imnotrichi");
        clienteNuevo.setContrasena("password");
        
        try {
            Cliente cliente = clientesDAO
        } catch (PersistenciaException e) {
            logger.log(Level.SEVERE, null, e);
        }

//        CuentaNuevaDTO cuentaNueva = new CuentaNuevaDTO();
//        cuentaNueva.setSaldo(0);
//        cuentaNueva.setAlias("Pokemones");
//        cuentaNueva.setFechaApertura(new Fecha(12, 12, 2023));
//        cuentaNueva.setActiva(true);
//
//        try {
//            Cuenta cuentaAgregada = cuentaDAO.agregar(cuentaNueva);
//            logger.log(Level.INFO, cuentaAgregada.toString());
//        } catch (PersistenciaException e) {
//            logger.log(Level.SEVERE, null, e);
//        }

    }
}
