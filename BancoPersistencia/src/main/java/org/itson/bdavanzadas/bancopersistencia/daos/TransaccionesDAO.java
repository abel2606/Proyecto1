package org.itson.bdavanzadas.bancopersistencia.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.bdavanzadas.bancodominio.Retiro;
import org.itson.bdavanzadas.bancodominio.Transferencia;
import org.itson.bdavanzadas.bancopersistencia.conexion.IConexion;
import org.itson.bdavanzadas.bancopersistencia.dtos.RetiroNuevoDTO;
import org.itson.bdavanzadas.bancopersistencia.dtos.TransaccionNuevaDTO;
import org.itson.bdavanzadas.bancopersistencia.dtos.TransferenciaNuevaDTO;
import org.itson.bdavanzadas.bancopersistencia.excepciones.PersistenciaException;

public class TransaccionesDAO implements ITransaccionesDAO{

    final IConexion conexionBD;
    static final Logger logger = Logger.getLogger(CuentasDAO.class.getName());
    
    public TransaccionesDAO(IConexion conexionBD) {
        this.conexionBD = conexionBD;
    }

    @Override
    public Transferencia agregarTransferencia(TransaccionNuevaDTO transaccionNueva, TransferenciaNuevaDTO transferenciaNueva) throws PersistenciaException {
        String sentenciaTransaccionSQL = """
                                         INSERT INTO transacciones(monto, fechaRealizacion, numeroCuentaOrigen)
                                         VALUES(?, ?, ?)
                                         """;
        String sentenciaTransferenciaSQL = """
                                           INSERT INTO transferencias(folio, numeroCuentaDestino)
                                           VALUES(?, ?);
                                           """;
        try (
            Connection conexion = this.conexionBD.obtenerConexion(); 
            PreparedStatement comandoTransaccion= conexion.prepareStatement(sentenciaTransaccionSQL);
            PreparedStatement comandoTransferencia = conexion.prepareStatement(sentenciaTransferenciaSQL);
        ) {
            comandoTransaccion.setString(1, transaccionNueva.getFechaRealizacion().toString());
            comandoTransaccion.setFloat(2, transaccionNueva.getMonto());
            comandoTransaccion.setLong(3, transaccionNueva.getNumeroCuentaOrigen());
            
            int numRegistrosInsertadosTransaccion = comandoTransaccion.executeUpdate();
            logger.log(Level.INFO, "Se agregaron {0} transacciones", numRegistrosInsertadosTransaccion);
            
            ResultSet idsGenerados = comandoTransaccion.getGeneratedKeys();
            long idTransaccionGenerado = 0;
            if (idsGenerados.next()) {
                idTransaccionGenerado = idsGenerados.getLong(1);
            }
            
            comandoTransferencia.setLong(1, idTransaccionGenerado);
            comandoTransferencia.setLong(2, transferenciaNueva.getNumeroCuentaDestino());
            
            int numRegistrosInsertadosTransferencia = comandoTransferencia.executeUpdate();
            logger.log(Level.INFO, "Se agregaron {0} transferencias", numRegistrosInsertadosTransferencia);
            
            Transferencia transferencia = new Transferencia(idTransaccionGenerado, transaccionNueva.getMonto(), 
                    transaccionNueva.getFechaRealizacion(), transaccionNueva.getNumeroCuentaOrigen(), 
                    transferenciaNueva.getNumeroCuentaDestino());
            
            return transferencia;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al agregar la transferencia.", ex);
            throw new PersistenciaException("Error al agregar la transferencia.");
        }
    }

    @Override
    public Retiro agregarRetiro(TransaccionNuevaDTO transaccionNueva, RetiroNuevoDTO retiroNuevo) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
