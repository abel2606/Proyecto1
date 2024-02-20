package org.itson.bdavanzadas.bancodominio;

public class TransaccionTabla {

    private Float monto;
    private Fecha fechaRealizacion;
    private String tipo;
    private String estado;

    /**
     * Constructor que recibe el monto, la fecha de realización, el tipo y el
     * estado de la transacción.
     *
     * @param monto El monto
     * @param fechaRealizacion La fecha de realización
     * @param tipo El tipo
     * @param estado El estado
     */
    public TransaccionTabla(Float monto, Fecha fechaRealizacion, String tipo, String estado) {
        this.monto = monto;
        this.fechaRealizacion = fechaRealizacion;
        this.tipo = tipo;
        this.estado = estado;
    }

    /**
     * Permite obtener el monto de la transacción.
     *
     * @return El monto de la transacción
     */
    public Float getMonto() {
        return monto;
    }

    /**
     * Permite establecer el monto de la transacción.
     *
     * @param monto El monto de la transacción
     */
    public void setMonto(Float monto) {
        this.monto = monto;
    }

    /**
     * Permite obtener la fecha de realización de la transacción.
     *
     * @return La fecha de realización de la transacción
     */
    public Fecha getFechaRealizacion() {
        return fechaRealizacion;
    }

    /**
     * Permite establecer la fecha de realización de la transacción.
     *
     * @param fechaRealizacion La fecha de realización de la transacción
     */
    public void setFechaRealizacion(Fecha fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    /**
     * Permite obtener el tipo de la transacción.
     *
     * @return El tipo de la transacción
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Permite establecer el tipo de la transacción.
     *
     * @param tipo El tipo de la transacción
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Permite obtener el estado de la transacción.
     *
     * @return El estado de la transacción
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Permite establecer el estado de la transacción.
     *
     * @param estado El estado de la transacción
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

}
