package org.itson.bdavanzadas.bancopersistencia.dtos;

import org.itson.bdavanzadas.bancodominio.Fecha;

public class TransaccionNuevaDTO {
    
    private Float monto;
    private Fecha fechaRealizacion;
    private Long numeroCuentaOrigen;

    /**
     * Permite obtener el monto de la transacción;
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
     * Permite establecer el número de la cuenta origen de la transacción.
     *
     * @return El número de la cuenta origen de la transacción
     */
    public Long getNumeroCuentaOrigen() {
        return numeroCuentaOrigen;
    }

    /**
     * Permite establecer el número de la cuenta origen de la transacción.
     *
     * @param numeroCuentaOrigen El número de la cuenta origen de la transacción
     */
    public void setNumeroCuentaOrigen(Long numeroCuentaOrigen) {
        this.numeroCuentaOrigen = numeroCuentaOrigen;
    }

    public boolean isValid() {
        return false;
    }

}
