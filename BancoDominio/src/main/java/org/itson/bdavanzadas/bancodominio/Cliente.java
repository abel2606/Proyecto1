package org.itson.bdavanzadas.bancodominio;

import java.util.Objects;

public class Cliente {

    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Fecha fechaNacimiento;
    private String usuario;
    private String contrasena;

    /**
     * Constructor vacío.
     */
    public Cliente() {
    }

    /**
     * Constructor que recibe id, nombre, apellido paterno, apellido materno y
     * fecha de nacimiento del cliente.
     *
     * @param id El id en la base de datos
     * @param nombre El nombre
     * @param apellidoPaterno El apellido paterno
     * @param apellidoMaterno El apellido materno
     * @param fechaNacimiento La fecha de nacimiento
     */
    public Cliente(Long id, String nombre, String apellidoPaterno, String apellidoMaterno, Fecha fechaNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Constructor que recibe nombre, apellido paterno, apellido materno y fecha
     * de nacimiento del cliente.
     *
     * @param nombre El nombre
     * @param apellidoPaterno El apellido paterno
     * @param apellidoMaterno El apellido materno
     * @param fechaNacimiento La fecha de nacimiento
     */
    public Cliente(String nombre, String apellidoPaterno, String apellidoMaterno, Fecha fechaNacimiento) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
    }

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
     * Permite establecer un código único al cliente basándose en el id.
     *
     * @return Un código único.
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * Permite comparar un cliente con otra entidad.
     *
     * @param obj con el que se comparará al cliente
     * @return true si son el mismo objeto, false en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        return Objects.equals(this.id, other.id);
    }

    /**
     * Permite obtener una cadena de texto con los atributos del cliente.
     *
     * @return Una cadena de texto con los atributos del cliente.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente{");
        sb.append("id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", apellidoPaterno=").append(apellidoPaterno);
        sb.append(", apellidoMaterno=").append(apellidoMaterno);
        sb.append(", fechaNacimiento=").append(fechaNacimiento);
        sb.append(", usuario=").append(usuario);
        sb.append(", contrasena=").append(contrasena);
        sb.append('}');
        return sb.toString();
    }

}
