package org.itson.bdavanzadas.bancopersistencia.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.bdavanzadas.bancodominio.Fecha;
import org.itson.bdavanzadas.bancodominio.Retiro;
import org.itson.bdavanzadas.bancodominio.Transferencia;
import org.itson.bdavanzadas.bancopersistencia.conexion.IConexion;
import org.itson.bdavanzadas.bancopersistencia.dtos.RetiroNuevoDTO;
import org.itson.bdavanzadas.bancopersistencia.dtos.TransaccionNuevaDTO;
import org.itson.bdavanzadas.bancopersistencia.dtos.TransferenciaNuevaDTO;
import org.itson.bdavanzadas.bancopersistencia.excepciones.PersistenciaException;

public class TransaccionesDAO implements ITransaccionesDAO {

    final IConexion conexionBD;
    static final Logger logger = Logger.getLogger(CuentasDAO.class.getName());

    /**
     * Constructor que recibe la conexión a la base de datos.
     *
     * @param conexionBD La conexión a la base de datos
     */
    public TransaccionesDAO(IConexion conexionBD) {
        this.conexionBD = conexionBD;
    }

    /**
     * Hace una transferencia de una cuenta a otra cuenta
     *
     * @param transaccionNueva La transaccion nueva
     * @param transferenciaNueva La transferencia nueva
     * @return regrea la transferencia
     * @throws PersistenciaException Si ocurre una exepcion
     */
    @Override
    public Transferencia hacerTransferencia(TransaccionNuevaDTO transaccionNueva, TransferenciaNuevaDTO transferenciaNueva) throws PersistenciaException {
        String sentenciaSQL = """
                              CALL HacerTransferencia(?, ?, ?, ?);
                              """;
        try (
                Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL, Statement.RETURN_GENERATED_KEYS);) {
            comando.setFloat(1, transaccionNueva.getMonto());
            comando.setString(2, transaccionNueva.getFechaRealizacion().toString());
            comando.setLong(3, transaccionNueva.getNumeroCuentaOrigen());
            comando.setLong(4, transferenciaNueva.getNumeroCuentaDestino());

            int numRegistrosInsertadosTransaccion = comando.executeUpdate();
            logger.log(Level.INFO, "Se agregaron {0} transferencias", numRegistrosInsertadosTransaccion);

            ResultSet idsGenerados = comando.getGeneratedKeys();
            long idGenerado = 0;
            if (idsGenerados.next()) {
                idGenerado = idsGenerados.getLong(1);
            }

            Transferencia transferencia = new Transferencia(idGenerado, transaccionNueva.getMonto(),
                    transaccionNueva.getFechaRealizacion(), transaccionNueva.getNumeroCuentaOrigen(),
                    transferenciaNueva.getNumeroCuentaDestino());

            return transferencia;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al agregar la transferencia.", ex);
            throw new PersistenciaException("Error al agregar la transferencia.");
        }
    }

    /**
     * Genera un retiro
     *
     * @param transaccionNueva La nueva transaccion
     * @param retiroNuevo El retiro nuveo
     * @return Regresa el valor del retiro recien creado
     * @throws PersistenciaException Lanza una excepcion si no existe
     */
    @Override
    public Retiro generarRetiro(TransaccionNuevaDTO transaccionNueva, RetiroNuevoDTO retiroNuevo) throws PersistenciaException {
        String sentenciaSQL = """
                              CALL GenerarRetiro(?, ?, ?, ?);
                              """;
        try (
                Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL, Statement.RETURN_GENERATED_KEYS);) {
            comando.setFloat(1, transaccionNueva.getMonto());
            comando.setString(2, transaccionNueva.getFechaRealizacion().toString());
            comando.setLong(3, transaccionNueva.getNumeroCuentaOrigen());
            comando.setString(4, retiroNuevo.getEstado());

            int numRegistrosInsertadosTransaccion = comando.executeUpdate();
            logger.log(Level.INFO, "Se agregaron {0} transacciones", numRegistrosInsertadosTransaccion);

            ResultSet idsGenerados = comando.getGeneratedKeys();
            long idGenerado = 0;
            if (idsGenerados.next()) {
                idGenerado = idsGenerados.getLong(1);
            }

            Retiro retiro = new Retiro(idGenerado, transaccionNueva.getMonto(),
                    transaccionNueva.getFechaRealizacion(), transaccionNueva.getNumeroCuentaOrigen(),
                    retiroNuevo.getContrasena(), retiroNuevo.getEstado());

            return retiro;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al agregar el retiro.", ex);
            throw new PersistenciaException("Error al agregar el retiro.");
        }

    }

    /**
     * Permite saber si existe un retiro
     *
     * @param folio El folio
     * @return regresa el valor del folio
     * @throws PersistenciaException lanza una excepcion si no existe el retiro
     */
    @Override
    public boolean existeRetiro(long folio,long contrasena) throws PersistenciaException {
        String sentenciaSQL = "SELECT COUNT(folio) AS total FROM retiros WHERE folio = ? AND contrasena = ?";
        try (
                Connection conexion = this.conexionBD.obtenerConexion(); 
                PreparedStatement comando = conexion.prepareStatement(sentenciaSQL);) {
            comando.setLong(1, folio);
            comando.setLong(2, contrasena);
            try (ResultSet resultado = comando.executeQuery()) {
                if (resultado.next()) {
                    int total = resultado.getInt("total");
                    return total > 0;
                } else {
                    throw new PersistenciaException("No se pudo obtener el retiro");
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al verificar si existe la cuenta", ex);
            throw new PersistenciaException("Error al verificar si existe la cuenta");
        }
    }
    
    @Override
    /**
     * Permite saber si existe un folio para el retiro
     * @param folio El folio del retiro
     * @return regresa verdadero si existe el folio
     * @throws PersistenciaException lanza un excepcion en caso de error
     */
    public boolean existeFolioRetiro(long folio) throws PersistenciaException {
        String sentenciaSQL = "SELECT COUNT(*) AS total FROM retiros WHERE folio = ?";
        try (
                Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL);) {
            comando.setLong(1, folio);
            try (ResultSet resultado = comando.executeQuery()) {
                if (resultado.next()) {
                    int total = resultado.getInt("total");
                    return total > 0;
                } else {
                    throw new PersistenciaException("No se pudo obtener el resultado de la consulta.");
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al verificar la existencia del retiro", ex);
            throw new PersistenciaException("Error al verificar la existencia del retiro.");
        }
    }

    /**
     * Permite hacer un retiro
     *
     * @param folio El folio del retiro
     * @param contrasena La contraseña del retiro
     * @throws PersistenciaException lanza una excepcion si no existe el retiro
     */
    @Override
    public void hacerRetiro(long folio, long contrasena) throws PersistenciaException {
        try {
            if (!existeRetiro(folio,contrasena)) {
                throw new PersistenciaException("Folio o contraseña incorrecto");
            }

            String sentenciaSQL = "CALL HacerRetiro(?, ?)";
            try (
                    Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL);) {
                comando.setLong(1, folio);
                comando.setLong(2, contrasena);
                comando.executeQuery();
                int numeroRetiros = comando.executeUpdate();
                logger.log(Level.INFO, "Se realizaron {0} retiros", numeroRetiros);

            } catch (SQLException ex) {
                logger.log(Level.SEVERE, "Error al obtener el retiro", ex);
            }
        } catch (PersistenciaException ex) {
            logger.log(Level.SEVERE, "Error al hacer el retiro", ex);
            throw ex;
        }
    }

    /**
     * Obtiene una fecha de una transaccion
     *
     * @param folio El folio de la transaccion
     * @return regresa el valor del folio
     * @throws PersistenciaException lanza una excepcion en caso de error
     */
    public Fecha consultarFechaTransaccion(long folio) throws PersistenciaException {
        String sentenciaSQL = "SELECT fechaRealizacion as fecha FROM transacciones WHERE folio = ?";
        try (
                Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL);) {
            comando.setLong(1, folio);
            try (ResultSet resultado = comando.executeQuery()) {
                if (resultado.next()) {
                    Fecha fecha = new Fecha(resultado.getString("fecha"));
                    return fecha;
                } else {
                    throw new PersistenciaException("No se pudo obtener la fecha");
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al verificar la fecha", ex);
            throw new PersistenciaException("Error al verificar la fecha");
        }
    }

    /**
     * Permite saber el estado de un retiro
     *
     * @param folio valor del folio
     * @throws PersistenciaException lanza una excepcion si no puede acceder al
     * retiro
     */
    @Override
    public String estadoRetiro(long folio) throws PersistenciaException {
        String sentenciaSQL = "SELECT estado FROM retiros WHERE folio = ?";
        try (
                Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL);) {
            comando.setLong(1, folio);
            try (ResultSet resultado = comando.executeQuery()) {
                if (resultado.next()) {
                    return resultado.getString("estado");
                } else {
                    throw new PersistenciaException("No se encontró un retiro con el folio");
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al obtener el estado del retiro", ex);
            throw new PersistenciaException("Error al obtener el estado del retiro");
        }
    }

}
