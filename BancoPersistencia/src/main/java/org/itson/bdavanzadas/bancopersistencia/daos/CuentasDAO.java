package org.itson.bdavanzadas.bancopersistencia.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.bdavanzadas.bancodominio.Cuenta;
import org.itson.bdavanzadas.bancodominio.Fecha;
import org.itson.bdavanzadas.bancopersistencia.conexion.IConexion;
import org.itson.bdavanzadas.bancopersistencia.dtos.CuentaActualizadaDTO;
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
     * Permite obtener de la base de datos una lista con las cuentas del cliente
     * con el id del parámetro.
     *
     * @param idCliente El id del cliente del cual se quieren obtener las
     * cuentas
     * @return Una lista con las cuentas del cliente con el id del parámetro
     * @throws PersistenciaException Si no se pueden obtener las cuentas del
     * cliente
     */
    @Override
    public List<Cuenta> consultar(Long idCliente) throws PersistenciaException {
        String sentenciaSQL = """
                              SELECT numero, saldo, alias, fechaApertura, activa, identificadorCliente 
                              FROM cuentas 
                              WHERE identificadorCliente = ?
                              """;
        List<Cuenta> cuentas = new LinkedList<>();
        try (
            Connection conexion = this.conexionBD.obtenerConexion(); 
            PreparedStatement comando = conexion.prepareStatement(sentenciaSQL);
        ) {
            comando.setString(1, idCliente.toString());
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                Cuenta cuenta = new Cuenta(resultado.getLong("numero"), resultado.getString("alias"), 
                        resultado.getFloat("saldo"), new Fecha(resultado.getString("fechaApertura")),
                        resultado.getBoolean("activa"), resultado.getLong("identificadorCliente"));
                cuentas.add(cuenta);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al consultar las cuentas del cliente", ex);
            throw new PersistenciaException("Error al consultar las cuentas del cliente");
        }
        return cuentas;
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
                             INSERT INTO cuentas(saldo, alias, fechaApertura, identificadorCliente, activa)
                             VALUES (?, ?, ?, ?, ?);
                             """;
        try (
            Connection conexion = this.conexionBD.obtenerConexion(); 
            PreparedStatement comando = conexion.prepareStatement(sentenciaSQL, Statement.RETURN_GENERATED_KEYS);
        ) {
            int esActiva = 0;
            if (cuentaNueva.isActiva()) {
                esActiva = 1;
            }
            comando.setFloat(1, cuentaNueva.getSaldo());
            comando.setString(2, cuentaNueva.getAlias());
            comando.setString(3, cuentaNueva.getFechaApertura().toString());
            comando.setString(4, cuentaNueva.getIdCliente().toString());
            comando.setString(5, String.valueOf(esActiva));
            int numRegistrosInsertados = comando.executeUpdate();
            logger.log(Level.INFO, "Se agregaron {0} cuentas", numRegistrosInsertados);
            ResultSet idsGenerados = comando.getGeneratedKeys();
            idsGenerados.next();
            Cuenta cuenta = new Cuenta(idsGenerados.getLong(1), cuentaNueva.getAlias(),
                    cuentaNueva.getSaldo(), cuentaNueva.getFechaApertura(), cuentaNueva.isActiva(),
                    cuentaNueva.getIdCliente());
            return cuenta;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se pudo guardar la cuenta", ex);
            throw new PersistenciaException("No se pudo guardar la cuenta");
        }
    }

    /**
     * Permite cambiar el saldo y el estado de la cuenta a activa o desactivada.
     *
     * @param cuentaActualizada La cuenta con el estado actualizado
     * @return La cuenta con el estado actualizado
     * @throws PersistenciaException Si no se puede actualizar la cuenta
     */
    @Override
    public Cuenta actualizar(CuentaActualizadaDTO cuentaActualizada) throws PersistenciaException {
        String sentenciaSQL = """
                              UPDATE cuentas
                              SET saldo = ?, activa = ? 
                              WHERE numero = ?
                              """;
        try (
            Connection conexion = this.conexionBD.obtenerConexion();
            PreparedStatement comando = conexion.prepareStatement(sentenciaSQL);
        ){
            comando.setFloat(1, cuentaActualizada.getSaldo());
            comando.setBoolean(2, cuentaActualizada.isActiva());
            comando.setLong(3, cuentaActualizada.getNumero());
            
            int numeroRegistrosActualizados = comando.executeUpdate();
            logger.log(Level.INFO, "Se actualizaron {0} registros en la tabla cuentas", numeroRegistrosActualizados);

            Cuenta cuenta = new Cuenta(cuentaActualizada.getNumero(), cuentaActualizada.getAlias(),
                    cuentaActualizada.getSaldo(), cuentaActualizada.getFechaApertura(),
                    cuentaActualizada.isActiva(), cuentaActualizada.getIdCliente());

            return cuenta;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se pudo actualizar la cuenta", ex);
            throw new PersistenciaException("No se pudo actualizar la cuenta");
        }
    }
    
}
