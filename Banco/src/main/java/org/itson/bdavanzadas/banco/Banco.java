package org.itson.bdavanzadas.banco;

import java.util.List;
import java.util.logging.Level;
import org.itson.bdavanzadas.bancopersistencia.conexion.Conexion;
import org.itson.bdavanzadas.bancopersistencia.conexion.IConexion;
import java.util.logging.Logger;
import org.itson.bdavanzadas.banco.interfaces.PantallaCuentas;
import org.itson.bdavanzadas.bancodominio.Cliente;
import org.itson.bdavanzadas.bancopersistencia.daos.ClientesDAO;
import org.itson.bdavanzadas.bancopersistencia.daos.CuentasDAO;
import org.itson.bdavanzadas.bancopersistencia.daos.IClientesDAO;
import org.itson.bdavanzadas.bancopersistencia.daos.ICuentasDAO;
import org.itson.bdavanzadas.bancopersistencia.excepciones.PersistenciaException;

public class Banco {

    static final Logger logger = Logger.getLogger(Banco.class.getName());

    public static void main(String[] args) {
        String cadenaConexion = "jdbc:mysql://localhost/banco";
        String usuario = "root";
        String contrasenia = "password";

        IConexion conexion = new Conexion(cadenaConexion, usuario, contrasenia);
        ICuentasDAO cuentasDAO = new CuentasDAO(conexion);
        IClientesDAO clientesDAO = new ClientesDAO(conexion);

        List<Cliente> listaClientes = null;
        try {
            listaClientes = clientesDAO.consultar();
            listaClientes.forEach(socio -> System.out.println(socio));
        } catch (PersistenciaException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        Cliente cliente = listaClientes.get(1);
        
        PantallaCuentas pantallaCuentas = new PantallaCuentas(conexion, cliente);
        pantallaCuentas.setVisible(true);

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
