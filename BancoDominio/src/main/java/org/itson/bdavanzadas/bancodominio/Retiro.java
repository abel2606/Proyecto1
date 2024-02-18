package org.itson.bdavanzadas.bancodominio;

public class Retiro extends Transaccion {

    private Long contrasena;
    private String estado;

    /**
     * Constructor vacío.
     */
    public Retiro() {
    }

    /**
     * Constructor que recibe el folio, la fecha de realización, el número de la
     * cuenta origen, la contraseña y el estado del retiro.
     *
     * @param folio El folio
     * @param monto El monto
     * @param fechaRealizacion La fecha de realización
     * @param numeroCuentaOrigen El número de la cuenta origen
     * @param contrasena La contraseña
     * @param estado El estado
     */
    public Retiro(Long folio, Float monto, Fecha fechaRealizacion, Long numeroCuentaOrigen, Long contrasena, String estado) {
        super(folio, monto, fechaRealizacion, numeroCuentaOrigen);
        this.contrasena = contrasena;
        this.estado = estado;
    }

    /**
     * Constructor que recibe la fecha de realización, el número de la cuenta
     * origen, la contraseña y el estado del retiro.
     *
     * @param monto El monto
     * @param fechaRealizacion La fecha de realización
     * @param numeroCuentaOrigen El número de la cuenta origen
     * @param contrasena La contraseña
     * @param estado El estado
     */
    public Retiro(Float monto, Fecha fechaRealizacion, Long numeroCuentaOrigen, Long contrasena, String estado) {
        super(monto, fechaRealizacion, numeroCuentaOrigen);
        this.contrasena = contrasena;
        this.estado = estado;
    }

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
     * @return El estado del retiro
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Permite establecer el estado del retiro.
     * @param estado El estado del retiro
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Permite obtener una cadena de texto con los atributos del retiro.
     *
     * @return Una cadena de texto con los atributos del retiro
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Retiro{");
        sb.append("folio=").append(super.folio);
        sb.append(", monto=").append(super.monto);
        sb.append(", fechaRealizacion=").append(super.fechaRealizacion);
        sb.append(", numeroCuentaOrigen=").append(super.numeroCuentaOrigen);
        sb.append(", contrasena=").append(contrasena);
        sb.append(", estado=").append(estado);
        sb.append('}');
        return sb.toString();
    }

}
