package org.itson.bdavanzadas.bancopersistencia.daos;

import java.util.List;
import org.itson.bdavanzadas.bancodominio.Cuenta;
import org.itson.bdavanzadas.bancopersistencia.dtos.CuentaActualizadaDTO;
import org.itson.bdavanzadas.bancopersistencia.dtos.CuentaNuevaDTO;
import org.itson.bdavanzadas.bancopersistencia.excepciones.PersistenciaException;

public interface ICuentasDAO {

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
    List<Cuenta> consultar(Long idCliente) throws PersistenciaException;

    /**
     * Permite agregar una cuenta a la base de datos.
     *
     * @param cuentaNueva La cuenta a agregar
     * @return La cuenta agregada
     * @throws PersistenciaException Si no se pudo agregar la cuenta a la base
     * de datos
     */
    Cuenta agregar(CuentaNuevaDTO cuentaNueva) throws PersistenciaException;

    /**
     * Permite cambiar el saldo y el estado de la cuenta a activa o desactivada.
     *
     * @param cuentaActualizada La cuenta con el estado actualizado
     * @return La cuenta con el estado actualizado
     * @throws PersistenciaException Si no se puede actualizar la cuenta
     */
    Cuenta actualizar(CuentaActualizadaDTO cuentaActualizada) throws PersistenciaException;
    
    /**
     * Permite saber si exite una ceuenta con el numero de la cuenta
     * @param numeroCuentaDestino El numero de la cuenta
     * @return Regersa verdadero si xiste cuenta
     * @throws PersistenciaException Lanza excepcion en caso de un error
     */
    public boolean existeCuentaDestino(long numeroCuentaDestino) throws PersistenciaException;
    
    /**
     * Permite saber el estado de una cuenta
     * @param numeroCuenta El numero de la cuenta a buscar
     * @return Regresa verdadero si la cuenta es activa
     * @throws PersistenciaException Lanza una excepcion en caso de error
     */
    public boolean esActiva(long numeroCuenta)throws PersistenciaException;
    
}
