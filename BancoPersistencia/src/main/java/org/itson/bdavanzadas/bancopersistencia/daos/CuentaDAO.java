package org.itson.bdavanzadas.bancopersistencia.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.bdavanzadas.bancodominio.Cuenta;
import org.itson.bdavanzadas.bancopersistencia.conexion.IConexion;
import org.itson.bdavanzadas.bancopersistencia.dtos.CuentaDTO;
import org.itson.bdavanzadas.bancopersistencia.excepciones.PersistenciaException;

public class CuentaDAO implements ICuentaDAO {
    
    final IConexion conexionBD;
    static final Logger logger = Logger.getLogger(CuentaDAO.class.getName());
    
    /**
     * Constructor que recibe la conexión a la base de datos.
     * @param conexion La conexión a la base de datos
     */
    public CuentaDAO(IConexion conexion) {
        this.conexionBD = conexion;
    }

    /**
     * Permite agregar una cuenta nueva a la base de datos.
     *
     * @param cuentaNueva La cuenta a agregar
     * @return La cuenta agregada
     * @throws PersistenciaException Si no se puede agregar la cuenta a la base
     * de datos
     */
    @Override
    public Cuenta agregar(CuentaDTO cuentaNueva) throws PersistenciaException {
        String sentenciaSQL = """
                             INSERT INTO cuentas(numero, saldo, alias, fechaApertura, activa)
                             VALUES (?, ?, ?, ?, ?);
                             """;
        try (Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL, Statement.RETURN_GENERATED_KEYS)) {
            
            int esActiva = 0;
            if (cuentaNueva.isActiva()) {
                esActiva = 1;
            }
            comando.setString(1, cuentaNueva.getNumero());
            comando.setFloat(2, cuentaNueva.getSaldo());
            comando.setString(3, cuentaNueva.getAlias());
            comando.setString(4, cuentaNueva.getFechaApertura().toString());
            comando.setString(5, Integer.toString(esActiva));
            int numRegistrosInsertados = comando.executeUpdate();
            logger.log(Level.INFO, "Se agregaron {0} cuentas", numRegistrosInsertados);
            ResultSet idsGenerados = comando.getGeneratedKeys();
            idsGenerados.next();
            Cuenta cuenta = new Cuenta(idsGenerados.getString(1), cuentaNueva.getAlias(),
                    cuentaNueva.getSaldo(), cuentaNueva.getFechaApertura(), cuentaNueva.isActiva());
            return cuenta;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se pudo guardar la cuenta", ex);
            throw new PersistenciaException("No se pudo guardar la cuenta");
        }
    }
    
}
