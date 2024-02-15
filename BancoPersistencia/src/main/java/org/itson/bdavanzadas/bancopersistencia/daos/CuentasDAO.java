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
import org.itson.bdavanzadas.bancopersistencia.dtos.CuentaNuevaDTO;
import org.itson.bdavanzadas.bancopersistencia.excepciones.PersistenciaException;

public class CuentasDAO implements ICuentasDAO {

    final IConexion conexionBD;
    static final Logger logger = Logger.getLogger(CuentasDAO.class.getName());

    /**
     * Constructor que recibe la conexión a la base de datos.
     *
     * @param conexion La conexión a la base de datos
     */
    public CuentasDAO(IConexion conexion) {
        this.conexionBD = conexion;
    }

    /**
     * Permite agregar una cuenta a la base de datos.
     *
     * @param cuentaNueva La cuenta a agregar
     * @return La cuenta agregada
     * @throws PersistenciaException Si no se pudo agregar la cuenta a la base
     * de datos
     */
    @Override
    public Cuenta agregar(CuentaNuevaDTO cuentaNueva) throws PersistenciaException {
        String sentenciaSQL = """
                             INSERT INTO cuentas(saldo, alias, fechaApertura, activa)
                             VALUES (?, ?, ?, ?);
                             """;
        try (
                Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL, Statement.RETURN_GENERATED_KEYS);) {
            int esActiva = 0;
            if (cuentaNueva.isActiva()) {
                esActiva = 1;
            }
            comando.setFloat(1, cuentaNueva.getSaldo());
            comando.setString(2, cuentaNueva.getAlias());
            comando.setString(3, cuentaNueva.getFechaApertura().toString());
            comando.setString(4, String.valueOf(esActiva));
            int numRegistrosInsertados = comando.executeUpdate();
            logger.log(Level.INFO, "Se agregaron {0} cuentas", numRegistrosInsertados);
            ResultSet idsGenerados = comando.getGeneratedKeys();
            idsGenerados.next();
            Cuenta cuenta = new Cuenta(idsGenerados.getString(1), cuentaNueva.getAlias(),
                    cuentaNueva.getSaldo(), cuentaNueva.getFechaApertura(), cuentaNueva.isActiva(),
                    cuentaNueva.getIdCliente());
            return cuenta;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se pudo guardar la cuenta", ex);
            throw new PersistenciaException("No se pudo guardar la cuenta");
        }
    }

}
