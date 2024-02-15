package org.itson.bdavanzadas.bancopersistencia.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.bdavanzadas.bancodominio.Cliente;
import org.itson.bdavanzadas.bancopersistencia.conexion.IConexion;
import org.itson.bdavanzadas.bancopersistencia.dtos.ClienteNuevoDTO;
import org.itson.bdavanzadas.bancopersistencia.excepciones.PersistenciaException;

public class ClientesDAO implements IClientesDAO {

    final IConexion conexionBD;
    static final Logger logger = Logger.getLogger(ClientesDAO.class.getName());

    /**
     * Constructor que recibe la conexión a la base de datos.
     *
     * @param conexionBD La conexión a la base de datosf
     */
    public ClientesDAO(IConexion conexionBD) {
        this.conexionBD = conexionBD;
    }

    /**
     * Permite agregar un cliente nuevo a la base de datos.
     *
     * @param clienteNuevo El cliente a agregar
     * @return El cliente agregado
     * @throws PersistenciaException Si no se puede agregar el cliente a la base
     * de datos
     */
    @Override
    public Cliente agregar(ClienteNuevoDTO clienteNuevo) throws PersistenciaException {
        String sentenciaSQL = """
                             INSERT INTO clientes(nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, usuario, contrasena)
                             VALUES (?,?,?,?,?,?);
                             """;
        try (
            Connection conexion = this.conexionBD.obtenerConexion(); 
            PreparedStatement comando = conexion.prepareStatement(sentenciaSQL, Statement.RETURN_GENERATED_KEYS);
        ) {
            comando.setString(1, clienteNuevo.getNombre());
            comando.setString(2, clienteNuevo.getApellidoPaterno());
            comando.setString(3, clienteNuevo.getApellidoMaterno());
            comando.setString(4, clienteNuevo.getFechaNacimiento().toString());
            comando.setString(5, clienteNuevo.getUsuario());
            comando.setString(6, clienteNuevo.getContrasena());

            int numRegistrosInsertados = comando.executeUpdate();
            logger.log(Level.INFO, "Se agregaron {0} clientes", numRegistrosInsertados);
            ResultSet idsGenerados = comando.getGeneratedKeys();
            idsGenerados.next();
            Cliente cliente = new Cliente(idsGenerados.getLong(1), clienteNuevo.getNombre(), 
                    clienteNuevo.getApellidoPaterno(), clienteNuevo.getApellidoMaterno(), 
                    clienteNuevo.getFechaNacimiento(), clienteNuevo.getUsuario(), clienteNuevo.getContrasena());
            return cliente;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se pudo guardar el cliente", ex);
            throw new PersistenciaException("No se pudo guardar el cliente");
        }
    }

}
