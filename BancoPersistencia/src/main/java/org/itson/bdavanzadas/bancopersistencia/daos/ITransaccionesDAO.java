package org.itson.bdavanzadas.bancopersistencia.daos;

import java.util.List;
import org.itson.bdavanzadas.bancodominio.Fecha;
import org.itson.bdavanzadas.bancodominio.Retiro;
import org.itson.bdavanzadas.bancodominio.TransaccionTabla;
import org.itson.bdavanzadas.bancodominio.Transferencia;
import org.itson.bdavanzadas.bancopersistencia.dtos.RetiroNuevoDTO;
import org.itson.bdavanzadas.bancopersistencia.dtos.TransaccionNuevaDTO;
import org.itson.bdavanzadas.bancopersistencia.dtos.TransferenciaNuevaDTO;
import org.itson.bdavanzadas.bancopersistencia.excepciones.PersistenciaException;

public interface ITransaccionesDAO {

    /**
     * Hace una transferencia de una cuenta a otra cuenta
     *
     * @param transaccionNueva La transaccion nueva
     * @param transferenciaNueva La transferencia nueva
     * @return regrea la transferencia
     * @throws PersistenciaException Si ocurre una exepcion
     */
    Transferencia hacerTransferencia(TransaccionNuevaDTO transaccionNueva, TransferenciaNuevaDTO transferenciaNueva) throws PersistenciaException;

    /**
     * Genera un retiro
     *
     * @param transaccionNueva La nueva transaccion
     * @param retiroNuevo El retiro nuveo
     * @return Regresa el valor del retiro recien creado
     * @throws PersistenciaException Lanza una excepcion si no existe
     */
    Retiro generarRetiro(TransaccionNuevaDTO transaccionNueva, RetiroNuevoDTO retiroNuevo) throws PersistenciaException;

    /**
     * Permite hacer un retiro
     *
     * @param folio El folio del retiro
     * @param contrasena La contraseña del retiro
     * @throws PersistenciaException lanza una excepcion si no existe el retiro
     */
    void hacerRetiro(Long folio, Long contrasena) throws PersistenciaException;

    /**
     * Permite saber si existe un retiro
     *
     * @param folio El folio
     * @return regresa el valor del folio
     * @throws PersistenciaException lanza una excepcion si no existe el retiro
     */
    Boolean existeRetiro(Long folio, Long contrasena) throws PersistenciaException;

    /**
     * Permite saber el estado de un retiro
     *
     * @param folio valor del folio
     * @return El estado del retiro
     * @throws PersistenciaException lanza una excepcion si no puede acceder al
     * retiro
     */
    String estadoRetiro(Long folio) throws PersistenciaException;

    /**
     * Obtiene una fecha de una transaccion
     *
     * @param folio El folio de la transaccion
     * @return regresa el valor del folio
     * @throws PersistenciaException lanza una excepcion en caso de error
     */
    Fecha consultarFechaTransaccion(Long folio) throws PersistenciaException;

    /**
     * Permite saber si existe un folio para el retiro
     *
     * @param folio El folio del retiro
     * @return regresa verdadero si existe el folio
     * @throws PersistenciaException lanza un excepcion en caso de error
     */
    Boolean existeFolioRetiro(Long folio) throws PersistenciaException;
    
    /**
     * Permite consultar todas las transacciones de la cuenta mandada en el parámetro.
     * 
     * @param numeroCuenta La cuenta
     * @return Una lista con todas las transacciones de la cuenta
     * @throws PersistenciaException Si no se pueden consultar las transacciones
     */
    List<TransaccionTabla> consultar(Long numeroCuenta) throws PersistenciaException;
    
    
}
