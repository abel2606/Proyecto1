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
import org.itson.bdavanzadas.bancodominio.Cliente;
import org.itson.bdavanzadas.bancodominio.Fecha;
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
     * Permite obtener una lista con todos los clientes de la base de datos.
     *
     * @return Una lista con todos los clientes
     * @throws PersistenciaException Si no se pueden obtener los clientes
     */
    @Override
    public List<Cliente> consultar() throws PersistenciaException {
        String sentenciaSQL = """
        SELECT c.*, d.* FROM clientes c
        INNER JOIN domicilios d ON c.identificador = d.identificadorCliente;
        """;
        List<Cliente> listaCliente = new LinkedList<>();
        try (
            Connection conexion = this.conexionBD.obtenerConexion(); 
            PreparedStatement comando = conexion.prepareStatement(sentenciaSQL);
        ) {
            ResultSet resultados = comando.executeQuery();
            while (resultados.next()) {
                Long id = resultados.getLong("identificador");
                String nombre = resultados.getString("nombre");
                String apellidoPaterno = resultados.getString("apellidoPaterno");
                String apellidoMaterno = resultados.getString("apellidoMaterno");
                Fecha fechaNacimiento = new Fecha(resultados.getString("fechaNacimiento"));
                String usuario = resultados.getString("usuario");
                String contrasena = resultados.getString("contrasena");
                String calle = resultados.getString("calle");
                String numero = resultados.getString("numero");
                String colonia = resultados.getString("colonia");
                String codigoPostal = resultados.getString("codigoPostal");
                String ciudad = resultados.getString("ciudad");
                Cliente cliente = new Cliente(id, nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, usuario, contrasena,
                         calle, colonia, numero, codigoPostal, ciudad);
                listaCliente.add(cliente);
            }
            logger.log(Level.INFO, "Se consultaron {0} clientes", listaCliente.size());
            return listaCliente;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "No se pudieron consultar los clientes.", e);
            throw new PersistenciaException("No se pudieron consultar los clientes.", e);
        }
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
        String sentenciaClienteSQL = """
        INSERT INTO clientes(nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, usuario, contrasena)
        VALUES (?, ?, ?, ?, ?, ?)
        """;
        String sentenciaDomicilioSQL = """
        INSERT INTO domicilios(calle, numero, colonia, codigoPostal, ciudad, identificadorCliente)
        VALUES (?, ?, ?, ?, ?, ?)
        """;
        try (
            Connection conexion = this.conexionBD.obtenerConexion(); 
            PreparedStatement comandoCliente = conexion.prepareStatement(sentenciaClienteSQL, Statement.RETURN_GENERATED_KEYS); 
                PreparedStatement comandoDomicilio = conexion.prepareStatement(sentenciaDomicilioSQL);
        ) {
            conexion.setAutoCommit(false);

            comandoCliente.setString(1, clienteNuevo.getNombre());
            comandoCliente.setString(2, clienteNuevo.getApellidoPaterno());
            comandoCliente.setString(3, clienteNuevo.getApellidoMaterno());
            comandoCliente.setString(4, clienteNuevo.getFechaNacimiento().toString());
            comandoCliente.setString(5, clienteNuevo.getUsuario());
            comandoCliente.setString(6, clienteNuevo.getContrasena());

            int numRegistrosInsertadosCliente = comandoCliente.executeUpdate();
            logger.log(Level.INFO, "Se agregaron {0} clientes", numRegistrosInsertadosCliente);

            ResultSet idsGenerados = comandoCliente.getGeneratedKeys();
            long idClienteGenerado = 0;
            if (idsGenerados.next()) {
                idClienteGenerado = idsGenerados.getLong(1);
            }

            comandoDomicilio.setString(1, clienteNuevo.getCalle());
            comandoDomicilio.setString(2, clienteNuevo.getNumero());
            comandoDomicilio.setString(3, clienteNuevo.getColonia());
            comandoDomicilio.setString(4, clienteNuevo.getCodigoPostal());
            comandoDomicilio.setString(5, clienteNuevo.getCiudad());
            comandoDomicilio.setLong(6, idClienteGenerado);

            int numRegistrosInsertadosDomicilio = comandoDomicilio.executeUpdate();
            logger.log(Level.INFO, "Se agregaron {0} domicilios", numRegistrosInsertadosDomicilio);

            conexion.commit();

            Cliente cliente = new Cliente(idClienteGenerado, clienteNuevo.getNombre(), clienteNuevo.getApellidoPaterno(),
                    clienteNuevo.getApellidoMaterno(), clienteNuevo.getFechaNacimiento(), clienteNuevo.getUsuario(),
                    clienteNuevo.getContrasena(), clienteNuevo.getCalle(), clienteNuevo.getColonia(), clienteNuevo.getNumero(),
                    clienteNuevo.getCodigoPostal(), clienteNuevo.getCiudad());
            return cliente;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se pudo guardar el cliente", ex);
            throw new PersistenciaException("No se pudo guardar el cliente");
        }
    }

}
