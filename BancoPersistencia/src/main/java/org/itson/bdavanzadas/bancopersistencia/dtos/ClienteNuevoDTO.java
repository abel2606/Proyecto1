package org.itson.bdavanzadas.bancopersistencia.dtos;

import org.itson.bdavanzadas.bancodominio.Fecha;

public class ClienteNuevoDTO {
 
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Fecha fechaNacimiento;
    private String usuario;
    private String contrasena;
    
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
    
}
