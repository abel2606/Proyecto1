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
import org.itson.bdavanzadas.bancopersistencia.dtos.ClienteActualizadoDTO;
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
        SELECT identificador, nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, usuario, contrasena,
        calle, numero, colonia, codigoPostal, ciudad
        FROM clientes c
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
                Cliente cliente = new Cliente(id, nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, usuario, contrasena, calle, colonia, numero, codigoPostal, ciudad);

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

    /**
     * Permite que un cliente inicie sesión en el sistema.
     *
     * @param usuario El usuario del cliente
     * @param contrasena La contraseña del cliente
     * @return El cliente que inició sesión
     * @throws PersistenciaException Si el cliente no puede iniciar sesion
     */
    @Override
    public Cliente iniciarSesion(String usuario, String contrasena) throws PersistenciaException {
        String consultaSQL = """
        SELECT c.identificador,c.nombre,apellidoPaterno,c.apellidoMaterno,c.fechaNacimiento
        ,c.usuario,c.contrasena, d.calle, d.numero, d.colonia, d.codigoPostal, d.ciudad
        FROM clientes c
        INNER JOIN domicilios d ON c.identificador = d.identificadorCliente
        WHERE c.usuario = ? AND c.contrasena = ?;
        """;
        try (
            Connection conexion = this.conexionBD.obtenerConexion(); 
            PreparedStatement consulta = conexion.prepareStatement(consultaSQL);
        ) {
            consulta.setString(1, usuario);
            consulta.setString(2, contrasena);
            try (
                ResultSet resultado = consulta.executeQuery()) {
                if (resultado.next()) {
                    long idCliente = resultado.getLong("identificador");
                    String nombre = resultado.getString("nombre");
                    String apellidoPaterno = resultado.getString("apellidoPaterno");
                    String apellidoMaterno = resultado.getString("apellidoMaterno");
                    String fechaNacimiento = resultado.getString("fechaNacimiento");
                    String calle = resultado.getString("calle");
                    String numero = resultado.getString("numero");
                    String colonia = resultado.getString("colonia");
                    String codigoPostal = resultado.getString("codigoPostal");
                    String ciudad = resultado.getString("ciudad");
                    Cliente cliente = new Cliente(idCliente, nombre, apellidoPaterno, apellidoMaterno, new Fecha(fechaNacimiento), usuario, contrasena,
                            calle, colonia, numero, codigoPostal, ciudad);
                    return cliente;
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al iniciar sesión", ex);
            throw new PersistenciaException("No se pudo acceder al cliente");
        }
        return null;
    }

    /**
     * Permite actualizar la información de un cliente en la base de datos.
     *
     * @param clienteActualizado El cliente con la información actualizada
     * @return El cliente actualizado
     * @throws PersistenciaException Si no se puede actualizar el cliente
     */
    @Override
    public Cliente actualizar(ClienteActualizadoDTO clienteActualizado) throws PersistenciaException {
        String sentenciaSQL = """
                         UPDATE clientes 
                         SET nombre=?, apellidoPaterno=?, apellidoMaterno=?, fechaNacimiento=?, usuario=?, contrasena=?
                         WHERE identificador=?
                         """;
        String sentenciaDomicilioSQL = """
                                UPDATE domicilios
                                SET calle=?, numero=?, colonia=?, codigoPostal=?, ciudad=?
                                WHERE identificadorCliente=?
                                """;
        try (
            Connection conexion = this.conexionBD.obtenerConexion();
            PreparedStatement comandoCliente = conexion.prepareStatement(sentenciaSQL); 
            PreparedStatement comandoDomicilio = conexion.prepareStatement(sentenciaDomicilioSQL);
        ) {
            // Actualizar datos del cliente
            comandoCliente.setString(1, clienteActualizado.getNombre());
            comandoCliente.setString(2, clienteActualizado.getApellidoPaterno());
            comandoCliente.setString(3, clienteActualizado.getApellidoMaterno());
            comandoCliente.setString(4, clienteActualizado.getFechaNacimiento().toString());
            comandoCliente.setString(5, clienteActualizado.getUsuario());
            comandoCliente.setString(6, clienteActualizado.getContrasena());
            comandoCliente.setLong(7, clienteActualizado.getId());

            int numRegistrosActualizadosCliente = comandoCliente.executeUpdate();
            logger.log(Level.INFO, "Se actualizaron {0} registros en la tabla clientes", numRegistrosActualizadosCliente);

            // Actualizar datos del domicilio
            comandoDomicilio.setString(1, clienteActualizado.getCalle());
            comandoDomicilio.setString(2, clienteActualizado.getNumero());
            comandoDomicilio.setString(3, clienteActualizado.getColonia());
            comandoDomicilio.setString(4, clienteActualizado.getCodigoPostal());
            comandoDomicilio.setString(5, clienteActualizado.getCiudad());
            comandoDomicilio.setLong(6, clienteActualizado.getId());

            int numRegistrosActualizadosDomicilio = comandoDomicilio.executeUpdate();
            logger.log(Level.INFO, "Se actualizaron {0} registros en la tabla domicilios", numRegistrosActualizadosDomicilio);
            
            Cliente cliente = new Cliente();
            cliente.setId(cliente.getId());
            cliente.setNombre(cliente.getNombre());
            cliente.setApellidoPaterno(cliente.getApellidoPaterno());
            cliente.setApellidoMaterno(cliente.getApellidoMaterno());
            cliente.setFechaNacimiento(cliente.getFechaNacimiento());
            cliente.setUsuario(cliente.getUsuario());
            cliente.setContrasena(cliente.getContrasena());
            cliente.setCalle(cliente.getCalle());
            cliente.setNumero(cliente.getNumero());
            cliente.setColonia(cliente.getColonia());
            cliente.setCodigoPostal(cliente.getCodigoPostal());
            cliente.setCiudad(cliente.getCiudad());

            return cliente;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al actualizar el cliente", ex);
            throw new PersistenciaException("No se pudo actualizar el cliente");
        }
    }

    /**
     * Regresa un valor booleano si existe un usuario
     *
     * @param nombreUsuario El nombre del usuario
     * @return regresa si existe el usuario
     * @throws PersistenciaException lanza una exception si existe un error
     */
    @Override
    public boolean existeUsuario(String nombreUsuario) throws PersistenciaException {
        String sentenciaSQL = """
                              SELECT COUNT(identificador) AS total 
                              FROM clientes 
                              WHERE usuario = ?
                              """;
        try (
            Connection conexion = this.conexionBD.obtenerConexion(); 
            PreparedStatement consulta = conexion.prepareStatement(sentenciaSQL);
        ) {
            consulta.setString(1, nombreUsuario);
            ResultSet cantidadUsuario = consulta.executeQuery();
            if (cantidadUsuario.next()) {
                int total = cantidadUsuario.getInt("total");
                return total > 0; 
            }

            return false; 
        } catch (SQLException e) {
            throw new PersistenciaException("Error al verificar la existencia del usuario", e);
        }
    }
    
    /**
     * Permite obtener un cliente por su ID.
     *
     * @param idCliente El ID del cliente 
     * @return El cliente encontrado
     * @throws PersistenciaException Si ocurre un exvepcion al obtener el cliente
     */
    @Override
    public Cliente obtenerUsuario(long idCliente) throws PersistenciaException {
        String sentenciaSQL = """
            SELECT identificador, nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, usuario, contrasena,
            calle, numero, colonia, codigoPostal, ciudad
            FROM clientes c
            INNER JOIN domicilios d ON c.identificador = d.identificadorCliente
            WHERE c.identificador = ?
        """;
        try (
            Connection conexion = this.conexionBD.obtenerConexion(); 
            PreparedStatement comando = conexion.prepareStatement(sentenciaSQL);
        ) {
            comando.setLong(1, idCliente);
            ResultSet resultados = comando.executeQuery();
            if (resultados.next()) {
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
                Cliente cliente = new Cliente(idCliente, nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, usuario, contrasena, calle, colonia, numero, codigoPostal, ciudad);
                return cliente;
            } else {
                return null; // No se encontró ningún cliente con el ID especificado
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener el cliente.", e);
            throw new PersistenciaException("Error al obtener el cliente.", e);
        }
    }

}
