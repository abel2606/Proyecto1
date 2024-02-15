package org.itson.bdavanzadas.bancopersistencia.daos;

import java.util.List;
import org.itson.bdavanzadas.bancodominio.Cliente;
import org.itson.bdavanzadas.bancopersistencia.dtos.ClienteNuevoDTO;
import org.itson.bdavanzadas.bancopersistencia.excepciones.PersistenciaException;

public interface IClientesDAO {

    List<Cliente> consultar() throws PersistenciaException;
    
    /**
     * Permite agregar un cliente nuevo a la base de datos.
     *
     * @param clienteNuevo El cliente a agregar
     * @return El cliente agregado
     * @throws PersistenciaException Si no se puede agregar el cliente a la base
     * de datos
     */
    Cliente agregar(ClienteNuevoDTO clienteNuevo) throws PersistenciaException;
}
