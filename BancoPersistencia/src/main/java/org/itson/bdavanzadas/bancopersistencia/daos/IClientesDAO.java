package org.itson.bdavanzadas.bancopersistencia.daos;

import java.util.List;
import org.itson.bdavanzadas.bancodominio.Cliente;
import org.itson.bdavanzadas.bancopersistencia.dtos.ClienteNuevoDTO;
import org.itson.bdavanzadas.bancopersistencia.excepciones.PersistenciaException;

public interface IClientesDAO {

    /**
     * Permite obtener una lista con todos los clientes de la base de datos.
     *
     * @return Una lista con todos los clientes
     * @throws PersistenciaException Si no se pueden obtener los clientes
     */
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

    /**
     * Permite que un cliente inicie sesión en el sistema.
     *
     * @param usuario El usuario del cliente
     * @param contrasena La contraseña del cliente
     * @return El cliente que inició sesión
     * @throws PersistenciaException Si el cliente no puede iniciar sesion
     */
    Cliente iniciarSesion(String usuario, String contrasena) throws PersistenciaException;

    /**
     * Permite actualizar la información de un cliente en la base de datos.
     *
     * @param cliente El cliente a actulizar
     * @return El cliente actualizado
     * @throws PersistenciaException Si no se puede actualizar el cliente
     */
    Cliente actualizar(Cliente cliente) throws PersistenciaException;

    /**
     * Verifica que exista un nombre de usuario en la base de datos
     *
     * @param nombreUsuario El nomber de usuari
     * @return regresa si existe o no el nombre de usuario
     * @throws PersistenciaException Si no existe el nombre de usuario
     */
    boolean existeUsuario(String nombreUsuario) throws PersistenciaException;
}
