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
                Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL);) {
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
                Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL, Statement.RETURN_GENERATED_KEYS);) {
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
                Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL);) {
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

    /**
     * Permite saber si exite una ceuenta con el numero de la cuenta
     *
     * @param numeroCuenta El numero de la cuenta
     * @return Regersa verdadero si xiste cuenta
     * @throws PersistenciaException Lanza excepcion en caso de un error
     */
    @Override
    public boolean existeCuenta(long numeroCuenta) throws PersistenciaException {
        String sentenciaSQL = "SELECT COUNT(numero) AS total FROM cuentas WHERE numero = ?";
        try (
                Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL);) {
            comando.setLong(1, numeroCuenta);
            try (ResultSet resultado = comando.executeQuery()) {
                if (resultado.next()) {
                    int total = resultado.getInt("total");
                    return total > 0;
                } else {
                    throw new PersistenciaException("No se pudo obtener la cuenta");
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al verificar la existencia de la cuenta", ex);
            throw new PersistenciaException("Error al verificar Si existe la cuenta");
        }
    }

    /**
     * Permite saber el estado de una cuenta.
     *
     * @param numeroCuenta El numero de la cuenta a buscar
     * @return Regresa verdadero si la cuenta es activa
     * @throws PersistenciaException Lanza una excepcion en caso de error
     */
    @Override
    public boolean isActiva(long numeroCuenta) throws PersistenciaException {
        String sentenciaSQL = "SELECT activa FROM cuentas WHERE numero = ?";
        try (
                Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL);) {
            comando.setLong(1, numeroCuenta);
            try (ResultSet resultado = comando.executeQuery()) {
                if (resultado.next()) {
                    if (resultado.getInt("activa") == 1) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    throw new PersistenciaException("No se encontró la cuenta");
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al obtener la cuenta", ex);
            throw new PersistenciaException("Error al obtener la cuenta");
        }
    }

 
    @Override
    public void desactivarCuenta(long numeroCuenta) throws PersistenciaException {
        String sentenciaSQL = "UPDATE cuentas SET activa = 0 WHERE numero = ?";
        try (
                Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL);) {
            comando.setLong(1, numeroCuenta);
            int numeroRegistrosActualizados = comando.executeUpdate();
            logger.log(Level.INFO, "Se desactivó la cuenta con número {0}", numeroCuenta);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al desactivar la cuenta", ex);
            throw new PersistenciaException("Error al desactivar la cuenta");
        }
    }
    
    @Override
    public void activarCuenta(long numeroCuenta) throws PersistenciaException {
        String sentenciaSQL = "UPDATE cuentas SET activa = 1 WHERE numero = ?";
        try (
                Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL);) {
            comando.setLong(1, numeroCuenta);
            int numeroRegistrosActualizados = comando.executeUpdate();
            logger.log(Level.INFO, "Se desactivó la cuenta con número {0}", numeroCuenta);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al desactivar la cuenta", ex);
            throw new PersistenciaException("Error al desactivar la cuenta");
        }
    }
}
