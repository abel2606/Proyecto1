package org.itson.bdavanzadas.bancopersistencia.dtos;

import org.itson.bdavanzadas.bancodominio.Fecha;
import org.itson.bdavanzadas.bancopersistencia.excepciones.CuentaNoValidaException;

public class CuentaActualizadaDTO {

    private Long numero;
    private String alias;
    private Float saldo;
    private Fecha fechaApertura;
    private Boolean activa;
    private Long idCliente;

    /**
     * Permite obtener el número de la cuenta.
     *
     * @return El número de la cuenta
     */
    public Long getNumero() {
        return numero;
    }

    /**
     * Permite establecer el número de la cuenta.
     *
     * @param numero El número de la cuenta
     */
    public void setNumero(Long numero) {
        this.numero = numero;
    }

    /**
     * Permite obtener el alias de la cuenta.
     *
     * @return El alias de la cuenta
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Permite establecer el alias de la cuenta.
     *
     * @param alias El alias de la cuenta
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * Permite obtener el saldo de la cuenta.
     *
     * @return El saldo de la cuenta
     */
    public float getSaldo() {
        return saldo;
    }

    /**
     * Permite establecer el saldo de la cuenta.
     *
     * @param saldo El saldo de la cuenta
     */
    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    /**
     * Permite obtener la fecha de apertura de la cuenta.
     *
     * @return La fecha de apertura de la cuenta
     */
    public Fecha getFechaApertura() {
        return fechaApertura;
    }

    /**
     * Permite establecer la fecha de apertura de la cuenta.
     *
     * @param fechaApertura La fecha de apertura de la cuenta
     */
    public void setFechaApertura(Fecha fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    /**
     * Permite saber si la cuenta está activa o no.
     *
     * @return Si la cuenta está activa o no
     */
    public boolean isActiva() {
        return activa;
    }

    /**
     * Permite establecer si la cuenta está activa o no.
     *
     * @param activa Si la cuenta está activa o no
     */
    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    /**
     * Permite obtener el id del cliente al que pertenece la cuenta.
     *
     * @return El id del cliente al que pertenece la cuenta
     */
    public Long getIdCliente() {
        return idCliente;
    }

    /**
     * Permite establecer el id del cliente al que pertenece la cuenta.
     *
     * @param idCliente El id del cliente al que pertence la cuenta
     */
    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * Permite saber si los datos de la cuenta son válidos o no.
     *
     * @return True si la cuenta es válida
     * @throws CuentaNoValidaException Si la cuenta no es válida
     */
    public boolean isValid() throws CuentaNoValidaException {
        if (saldo < 0) {
            throw new CuentaNoValidaException("Saldo insuficiente.");
        }
        return true;
    }

}
