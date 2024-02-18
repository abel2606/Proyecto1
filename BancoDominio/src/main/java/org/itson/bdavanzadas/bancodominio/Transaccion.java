package org.itson.bdavanzadas.bancodominio;

import java.util.Objects;

public class Transaccion {

    public Long folio;
    public Float monto;
    public Fecha fechaRealizacion;
    public Long numeroCuentaOrigen;

    /**
     * Constructor vacío.
     */
    public Transaccion() {
    }

    /**
     * Constructor que recibe el folio, el monto, la fecha de realización y el
     * número de la cuenta origen de la transacción.
     *
     * @param folio El folio
     * @param monto El monto
     * @param fechaRealizacion La fecha de realización
     * @param numeroCuentaOrigen El número de la cuenta origen
     */
    public Transaccion(Long folio, Float monto, Fecha fechaRealizacion, Long numeroCuentaOrigen) {
        this.folio = folio;
        this.monto = monto;
        this.fechaRealizacion = fechaRealizacion;
        this.numeroCuentaOrigen = numeroCuentaOrigen;
    }

    /**
     * Constructor que recibe la fecha de realización y el número de la cuenta
     * origen de la transacción.
     *
     * @param monto El monto
     * @param fechaRealizacion La fecha de realización
     * @param numeroCuentaOrigen El número de la cuenta origen
     */
    public Transaccion(Float monto, Fecha fechaRealizacion, Long numeroCuentaOrigen) {
        this.monto = monto;
        this.fechaRealizacion = fechaRealizacion;
        this.numeroCuentaOrigen = numeroCuentaOrigen;
    }

    /**
     * Permite obtener el folio de la transacción.
     *
     * @return El folio de la transacción
     */
    public Long getFolio() {
        return folio;
    }

    /**
     * Permite establecer el folio de la transacción.
     *
     * @param folio El folio de la transacción
     */
    public void setFolio(Long folio) {
        this.folio = folio;
    }

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

    /**
     * Permite establecer un código único al cliente basándose en el id.
     *
     * @return Un código único.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.folio);
        return hash;
    }

    /**
     * Permite comparar una transacción con otra entidad.
     *
     * @param obj con el que se comparará a la transacción
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
        final Transaccion other = (Transaccion) obj;
        return Objects.equals(this.folio, other.folio);
    }

    /**
     * Permite obtener una cadena de texto con los atributos de la transacción.
     *
     * @return Una cadena de texto con los atributos de la transacción.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Transaccion{");
        sb.append("folio=").append(folio);
        sb.append(", monto=").append(monto);
        sb.append(", fechaRealizacion=").append(fechaRealizacion);
        sb.append(", numeroCuentaOrigen=").append(numeroCuentaOrigen);
        sb.append('}');
        return sb.toString();
    }

}
