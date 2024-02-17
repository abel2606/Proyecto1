package org.itson.bdavanzadas.bancopersistencia.dtos;

public class RetiroNuevoDTO extends TransaccionNuevaDTO {

    private Long contrasena;
    private String estado;

    /**
     * Permite obtener la contraseña del retiro.
     *
     * @return La contraseña del retiro
     */
    public Long getContrasena() {
        return contrasena;
    }

    /**
     * Permite establecer la contraseña del retiro.
     *
     * @param contrasena La contraseña del retiro
     */
    public void setContrasena(Long contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Permite obtener el estado del retiro.
     *
     * @return El estado del retiro
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Permite establecer el estado del retiro.
     *
     * @param estado El estado del retiro
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

}
