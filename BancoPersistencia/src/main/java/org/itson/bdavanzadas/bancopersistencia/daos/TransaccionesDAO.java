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
import org.itson.bdavanzadas.bancodominio.Fecha;
import org.itson.bdavanzadas.bancodominio.Retiro;
import org.itson.bdavanzadas.bancodominio.TransaccionTabla;
import org.itson.bdavanzadas.bancodominio.Transferencia;
import org.itson.bdavanzadas.bancopersistencia.conexion.IConexion;
import org.itson.bdavanzadas.bancopersistencia.dtos.RetiroNuevoDTO;
import org.itson.bdavanzadas.bancopersistencia.dtos.TransaccionNuevaDTO;
import org.itson.bdavanzadas.bancopersistencia.dtos.TransferenciaNuevaDTO;
import org.itson.bdavanzadas.bancopersistencia.excepciones.PersistenciaException;

public class TransaccionesDAO implements ITransaccionesDAO {

    final IConexion conexionBD;
    private static final Logger logger = Logger.getLogger(TransaccionesDAO.class.getName());
    

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
        String sentenciaUltimoidSQL = """
                              SELECT last_insert_id() AS ultimoId, contrasena FROM retiros WHERE folio = last_insert_id();
                              """;
        try (
            Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL);
            PreparedStatement comando2 = conexion.prepareStatement(sentenciaUltimoidSQL);
        ) {
            comando.setFloat(1, transaccionNueva.getMonto());
            comando.setString(2, transaccionNueva.getFechaRealizacion().toStringHora());
            comando.setLong(3, transaccionNueva.getNumeroCuentaOrigen());
            comando.setString(4, retiroNuevo.getEstado());

            comando.executeQuery();
            
            ResultSet idsGenerados = comando2.executeQuery();
            
            long idGenerado = 0;
            long contrasenaGenerada = 0;
            if (idsGenerados.next()) {
                idGenerado = idsGenerados.getLong("ultimoId");
                contrasenaGenerada = idsGenerados.getLong("contrasena");
            }

            Retiro retiro = new Retiro(idGenerado, transaccionNueva.getMonto(),
                    transaccionNueva.getFechaRealizacion(), transaccionNueva.getNumeroCuentaOrigen(),
                    contrasenaGenerada, retiroNuevo.getEstado());

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
    public Boolean existeRetiro(Long folio, Long contrasena) throws PersistenciaException {
        String sentenciaSQL = "SELECT COUNT(folio) AS total FROM retiros WHERE folio = ? AND contrasena = ?";
        try (
                Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL);) {
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

    /**
     * Permite saber si existe un folio para el retiro
     *
     * @param folio El folio del retiro
     * @return regresa verdadero si existe el folio
     * @throws PersistenciaException lanza un excepcion en caso de error
     */
    @Override
    public Boolean existeFolioRetiro(Long folio) throws PersistenciaException {
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
    public void hacerRetiro(Long folio, Long contrasena) throws PersistenciaException {
        try {
            if (!existeRetiro(folio, contrasena)) {
                throw new PersistenciaException("Folio o contraseña incorrecto");
            }
            String sentenciaSQL = "CALL HacerRetiro(?, ?)";
            try (
                    Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL);) {
                comando.setLong(1, folio);
                comando.setLong(2, contrasena);
                comando.executeQuery();
            } catch (SQLException ex) {
                logger.log(Level.SEVERE, "Error al obtener el retiro", ex);
            }
        } catch (PersistenciaException ex) {
            logger.log(Level.SEVERE, "Error al hacer el retiro", ex);   
        }
    }

    /**
     * Obtiene una fecha de una transaccion
     *
     * @param folio El folio de la transaccion
     * @return regresa el valor del folio
     * @throws PersistenciaException lanza una excepcion en caso de error
     */
    @Override
    public Fecha consultarFechaTransaccion(Long folio) throws PersistenciaException {
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
    public String estadoRetiro(Long folio) throws PersistenciaException {
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

    /**
     * Permite consultar todas las transacciones de la cuenta mandada en el parámetro.
     * 
     * @param numeroCuenta La cuenta
     * @return Una lista con todas las transacciones de la cuenta
     * @throws PersistenciaException Si no se pueden consultar las transacciones
     */
    @Override
    public List<TransaccionTabla> consultar(Long numeroCuenta) throws PersistenciaException {
        String sentenciaSQL = """
                              SELECT tr.monto, tr.fechaRealizacion, 
                              CASE
                              	WHEN r.folio IS NOT NULL THEN 'RETIRO'
                                WHEN ta.folio IS NOT NULL THEN 'TRANSFERENCIA'
                              END AS 'tipo',
                              CASE 
                              	WHEN r.folio IS NOT NULL THEN r.estado
                                WHEN ta.folio IS NOT NULL THEN 'REALIZADA'
                              END AS 'estado'
                              FROM transacciones AS tr
                              LEFT JOIN transferencias AS ta
                              ON tr.folio = ta.folio
                              LEFT JOIN retiros AS r
                              ON tr.folio = r.folio
                              INNER JOIN cuentas as c
                              ON tr.numeroCuentaOrigen = c.numero
                              WHERE tr.numeroCuentaOrigen = ?
                              ORDER BY tr.fechaRealizacion;
                              """;
        List<TransaccionTabla> transacciones = new LinkedList<>(); 
        try (
            Connection conexion = this.conexionBD.obtenerConexion(); 
            PreparedStatement comando = conexion.prepareStatement(sentenciaSQL);
        ){
            
          comando.setLong(1, numeroCuenta);
            
          ResultSet resultados = comando.executeQuery();
            while (resultados.next()) {
                Float monto = resultados.getFloat("tr.monto");
                String fechaRealizacion = resultados.getString("tr.fechaRealizacion");
                String tipo = resultados.getString("tipo");
                String estado = resultados.getString("estado");
                TransaccionTabla transaccion = new TransaccionTabla(monto, new Fecha(fechaRealizacion), tipo, estado);
                transacciones.add(transaccion);
            }  
            
            return transacciones;
            
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se pudieron obtener las transacciones.", ex);
            throw new PersistenciaException("No se pudieron obtener las transacciones.");
        }
    }
    
    /**
     * Permite cambiar el estado de un retiro
     *
     * @param folio El folio del retiro
     * @param estado El estado del retiro
     * @throws PersistenciaException Si no se puede actualizar el retiro
     */
    @Override
    public void actualizarEstadoRetiro(Long folio, String estado) throws PersistenciaException{
        String sentenciaSQL = """
                              UPDATE retiros SET estado = ? WHERE folio = ?;
                              """;
        try (
            Connection conexion = this.conexionBD.obtenerConexion(); 
            PreparedStatement comando = conexion.prepareStatement(sentenciaSQL);
        ){
            comando.setString(1, estado);
            comando.setLong(2, folio);
            
            comando.executeUpdate();
            
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se pudo actualizar el estado del retiro.", ex);
            throw new PersistenciaException("No se pudo actualizar el estado del retiro.");
        }
    }

}