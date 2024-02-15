package org.itson.bdavanzadas.bancopersistencia.dtos;

import org.itson.bdavanzadas.bancodominio.Fecha;
import org.itson.bdavanzadas.bancopersistencia.excepciones.CuentaNoValidaException;
import org.itson.bdavanzadas.bancopersistencia.validadores.Validadores;

public class CuentaNuevaDTO {

    private String nombrePropietario;
    private String alias;
    private Float saldo;
    private Fecha fechaApertura;
    private Boolean activa;

    /**
     * Permite obtener el nombre del propietario de la cuenta.
     *
     * @return El nombre del propietario de la cuenta
     */
    public String getNombrePropietario() {
        return nombrePropietario;
    }

    /**
     * Permite establecer el nombre del propietario de la cuenta.
     *
     * @param nombrePropietario El nombre del propietario de la cuenta
     */
    public void setNombrePropietario(String nombrePropietario) {
        this.nombrePropietario = nombrePropietario;
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
     * Permite saber si una cuenta es válida o no.
     *
     * @return true si la cuenta es válida
     * @throws CuentaNoValidaException Si la cuenta no es válida
     */
    public boolean isValid() throws CuentaNoValidaException {
        Validadores validador = new Validadores();
        if (!validador.validarNombre(nombrePropietario)) {
            throw new CuentaNoValidaException("El nombre del propietario no es válido.");
        }
        if (alias.isBlank()) {
            throw new CuentaNoValidaException("El alias de la cuenta no es válido.");
        }
        return true;
    }

}
