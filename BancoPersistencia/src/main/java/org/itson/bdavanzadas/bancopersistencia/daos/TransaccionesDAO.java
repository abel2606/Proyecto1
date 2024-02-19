package org.itson.bdavanzadas.bancopersistencia.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    @Override
    public Transferencia hacerTransferencia(TransaccionNuevaDTO transaccionNueva, TransferenciaNuevaDTO transferenciaNueva) throws PersistenciaException {
        String sentenciaSQL = """
                              CALL HacerTransferencia(?, ?, ?, ?);
                              """;
        try (
            Connection conexion = this.conexionBD.obtenerConexion(); 
            PreparedStatement comando = conexion.prepareStatement(sentenciaSQL, Statement.RETURN_GENERATED_KEYS);
        ) {
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

    @Override
    public Retiro generarRetiro(TransaccionNuevaDTO transaccionNueva, RetiroNuevoDTO retiroNuevo) throws PersistenciaException {
        String sentenciaSQL = """
                              CALL GenerarRetiro(?, ?, ?, ?, ?);
                              """;
        try (
            Connection conexion = this.conexionBD.obtenerConexion();
            PreparedStatement comando = conexion.prepareStatement(sentenciaSQL, Statement.RETURN_GENERATED_KEYS);
        ) {
            comando.setFloat(1, transaccionNueva.getMonto());
            comando.setString(2, transaccionNueva.getFechaRealizacion().toString());
            comando.setLong(3, transaccionNueva.getNumeroCuentaOrigen());
            comando.setLong(4, retiroNuevo.getContrasena());
            comando.setString(5, retiroNuevo.getEstado());

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

}
