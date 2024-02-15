package org.itson.bdavanzadas.bancopersistencia.daos;

import org.itson.bdavanzadas.bancodominio.Cuenta;
import org.itson.bdavanzadas.bancopersistencia.dtos.CuentaNuevaDTO;
import org.itson.bdavanzadas.bancopersistencia.excepciones.PersistenciaException;

public interface ICuentasDAO {

    /**
     * Permite agregar una cuenta a la base de datos.
     *
     * @param cuentaNueva La cuenta a agregar
     * @return La cuenta agregada
     * @throws PersistenciaException Si no se pudo agregar la cuenta a la base
     * de datos
     */
    Cuenta agregar(CuentaNuevaDTO cuentaNueva) throws PersistenciaException;
}
