package org.itson.bdavanzadas.bancodominio;

import java.util.Objects;

public class Cuenta {

    private String numero;
    private String alias;
    private Float saldo;
    private Fecha fechaApertura;
    private Boolean activa;

    /**
     * Constructor vacío.
     */
    public Cuenta() {
    }

    /**
     * Constructor que recibe e inicializa los atributos de la clase.
     *
     * @param numero El número de la cuenta
     * @param alias El alias de la cuenta
     * @param saldo El saldo que tiene la cuenta
     * @param fechaApertura La fecha en la que se abrió la cuenta
     * @param activa Si la cuenta está activa o no
     */
    public Cuenta(String numero, String alias, float saldo, Fecha fechaApertura, boolean activa) {
        this.numero = numero;
        this.alias = alias;
        this.saldo = saldo;
        this.fechaApertura = fechaApertura;
        this.activa = activa;
    }

    /**
     * Constructor que recibe e inicializa todos los atributos de la clase menos
     * el número de cuenta.
     *
     * @param alias El alias de la cuenta
     * @param saldo El saldo que tiene la cuenta
     * @param fechaApertura La fecha en la que se abrió la cuenta
     * @param activa Si la cuenta está activa o no
     */
    public Cuenta(String alias, float saldo, Fecha fechaApertura, boolean activa) {
        this.alias = alias;
        this.saldo = saldo;
        this.fechaApertura = fechaApertura;
        this.activa = activa;
    }

    /**
     * Permite obtener el número de la cuenta.
     *
     * @return El número de la cuenta
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Permite establecer el número de la cuenta.
     *
     * @param numero El número de la cuenta
     */
    public void setNumero(String numero) {
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
     * Permite establecer un código único a la cuenta basándose en el número.
     *
     * @return Un código único.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.numero);
        return hash;
    }

    /**
     * Permite comparar una cuenta con otra entidad.
     *
     * @param obj con el que se comparará al Socio
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
        final Cuenta other = (Cuenta) obj;
        return this.numero == other.numero;
    }

    /**
     * Permite obtener una cadena de texto con los atributos de la cuenta.
     *
     * @return Una cadena de texto con los atributos de la cuenta.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cuenta{");
        sb.append("numero=").append(numero);
        sb.append(", saldo=").append(saldo);
        sb.append(", fechaApertura=").append(fechaApertura);
        sb.append(", activa=").append(activa);
        sb.append('}');
        return sb.toString();
    }

}
