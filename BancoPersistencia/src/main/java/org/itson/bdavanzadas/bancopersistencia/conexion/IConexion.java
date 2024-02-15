package org.itson.bdavanzadas.bancopersistencia.conexion;

import java.sql.Connection;
import java.sql.SQLException;

public interface IConexion {

    /**
     * Permite obtener la conexión con una base de datos.
     *
     * @return La conexión con la base de datos
     * @throws SQLException Si no se pudo obtener la conexión.
     */
    Connection obtenerConexion() throws SQLException;
    
}
