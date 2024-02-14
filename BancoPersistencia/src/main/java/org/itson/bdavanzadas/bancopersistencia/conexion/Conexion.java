package org.itson.bdavanzadas.bancopersistencia.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion implements IConexion {

    final String cadenaConexion;
    final String usuario;
    final String password;
    static final Logger logger = Logger.getLogger(Conexion.class.getName());

    /**
     * Constructor que recibe e inicializa los atributoos de la clase.
     *
     * @param cadenaConexion La dirección de la base de datos
     * @param usuario El usuario de la base de datos
     * @param password La contraseña del usuario
     */
    public Conexion(String cadenaConexion, String usuario, String password) {
        this.cadenaConexion = cadenaConexion;
        this.usuario = usuario;
        this.password = password;
    }

    /**
     * Permite obtener la conexión con una base de datos.
     *
     * @return La conexión con la base de datos
     * @throws SQLException Si no se pudo obtener la conexión.
     */
    @Override
    public Connection obtenerConexion() throws SQLException {
        Connection conexion = DriverManager.getConnection(cadenaConexion, usuario, password);
        logger.log(Level.INFO, "Conexion establecida {0}", cadenaConexion);
        return conexion;
    }

}
