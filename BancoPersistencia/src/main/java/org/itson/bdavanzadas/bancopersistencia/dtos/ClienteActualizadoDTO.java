package org.itson.bdavanzadas.bancopersistencia.dtos;

import org.itson.bdavanzadas.bancodominio.Fecha;
import org.itson.bdavanzadas.bancopersistencia.excepciones.ClienteNoValidoException;
import org.itson.bdavanzadas.bancopersistencia.validadores.Validadores;

public class ClienteActualizadoDTO {

    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Fecha fechaNacimiento;
    private String usuario;
    private String contrasena;
    private String calle;
    private String numero;
    private String colonia;
    private String codigoPostal;
    private String ciudad;

    /**
     * Permite obtener el id del cliente.
     *
     * @return El id del cliente
     */
    public Long getId() {
        return id;
    }

    /**
     * Permite establecer el id del cliente.
     *
     * @param id El id del cliente
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Permite obtener el nombre del cliente.
     *
     * @return El nombre del cliente
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Permite establecer el nombre del cliente.
     *
     * @param nombre El nombre del cliente
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Permite obtener el apellido paterno del cliente.
     *
     * @return El apellido paterno del cliente
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Permite establecer el apellido paterno del cliente.
     *
     * @param apellidoPaterno El apellidoPaterno del cliente
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * Permite obtener el apellido materno del cliente.
     *
     * @return El apellido materno del cliente
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Permite establecer el apellido materno del cliente.
     *
     * @param apellidoMaterno El apellido materno del cliente
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * Permite obtener la fecha de nacimiento del cliente.
     *
     * @return La fecha de nacimiento del cliente
     */
    public Fecha getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Permite establecer la fecha de nacimiento del cliente.
     *
     * @param fechaNacimiento La fecha de nacimiento del cliente
     */
    public void setFechaNacimiento(Fecha fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Permite obtener el usuario del cliente.
     *
     * @return El usuario del cliente
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Permite establecer el usuario del cliente.
     *
     * @param usuario El usuario del cliente
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Permite obtener la contraseña del cliente.
     *
     * @return La contraseña del cliente
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Permite establecer la contraseña del cliente.
     *
     * @param contrasena La contraseña del cliente
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Permite obtener la calle del cliente
     *
     * @return la calle del cliente
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Permite establecer el valor de la calle del cliente
     *
     * @param calle la calle del cliente
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     * Permite establecer el valor del numero de domicilio del cliente
     *
     * @return numero de domicilio del cliente
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Permite establecer el valor de un numeroo
     *
     * @param numero valor del numero
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * permite obtener el valor de la colonia del cliente
     *
     * @return la colonia del cliente
     */
    public String getColonia() {
        return colonia;
    }

    /**
     * permite establecer el valor de la colonia
     *
     * @param colonia la colonia del cliente
     */
    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    /**
     * permite obtener el codigo postal del cliente
     *
     * @return regresa el valor del codigo postal del cliente
     */
    public String getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * permite obtener el codigo postal
     *
     * @param codigoPostal valor del codigo postal del cliente
     */
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    /**
     * permite obtener la ciudad del cliente
     *
     * @return la ciudad del cliente
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * permite establecer el valor de la ciudad
     *
     * @param ciudad la ciudad
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
    public boolean isValid() throws ClienteNoValidoException {
        Validadores validador = new Validadores();
        if (!validador.validarNombre(nombre)) {
            throw new ClienteNoValidoException("El nombre del cliente no es válido.");
        }
        if (!validador.validarNombre(apellidoPaterno)) {
            throw new ClienteNoValidoException("El apellido paterno del cliente no es válido.");
        }
        if (!validador.validarNombre(apellidoMaterno)) {
            throw new ClienteNoValidoException("El apellido materno del cliente no es válido.");
        }
        if (!validador.validarCalle(calle)) {
            throw new ClienteNoValidoException("El nombre de la calle no es válida.");
        }
        if (!validador.validarNumero(numero)) {
            throw new ClienteNoValidoException("El número no es válida.");
        }
        if (colonia.isBlank()) {
            throw new ClienteNoValidoException("La colonia no es válida.");
        }
        if (!validador.validarCodigoPostal(codigoPostal)) {
            throw new ClienteNoValidoException("El código postal no es válido.");
        }
        if (!validador.validarNombre(ciudad)) {
            throw new ClienteNoValidoException("La ciudad no es válida.");
        }
        return true;
    }
}
