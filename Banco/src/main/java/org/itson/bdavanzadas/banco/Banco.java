package org.itson.bdavanzadas.banco;

import org.itson.bdavanzadas.bancodominio.Cuenta;
import org.itson.bdavanzadas.bancopersistencia.conexion.Conexion;
import org.itson.bdavanzadas.bancopersistencia.conexion.IConexion;
import org.itson.bdavanzadas.bancopersistencia.daos.CuentaDAO;
import org.itson.bdavanzadas.bancopersistencia.daos.ICuentaDAO;
import org.itson.bdavanzadas.bancopersistencia.dtos.CuentaDTO;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.bdavanzadas.bancodominio.Fecha;
import org.itson.bdavanzadas.bancopersistencia.excepciones.PersistenciaException;

public class Banco {

    static final Logger logger = Logger.getLogger(Banco.class.getName());

    public static void main(String[] args) {
        String cadenaConexion = "jdbc:mysql://localhost/banco";
        String usuario = "root";
        String contrasenia = "Abel123";

        IConexion conexion = new Conexion(cadenaConexion, usuario, contrasenia);
        ICuentaDAO cuentaDAO = new CuentaDAO(conexion);

        CuentaDTO cuentaNueva = new CuentaDTO();
        cuentaNueva.setNumero("1234543236");
         cuentaNueva.setSaldo(0);
        cuentaNueva.setAlias("Pokemones");
        cuentaNueva.setFechaApertura(new Fecha(12, 12, 2023));
        cuentaNueva.setActiva(true);
       

        try {
            Cuenta cuentaAgregada = cuentaDAO.agregar(cuentaNueva);
            logger.log(Level.INFO, cuentaAgregada.toString());
        } catch (PersistenciaException e) {
            logger.log(Level.SEVERE, null, e);
        }

    }
}
