/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.banco.persistencia.dtos;

import org.itson.bdavanzadas.banco.persistencia.excepciones.ValidacionDTOException;

/**
 * Clase de tipo cuentaDTO
 * @author Abel
 */
public class CuentaDTO {
    public String numero;
    public String alias;
    public float saldo;
    public Fecha fechaApertura;
    public boolean activa;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public Fecha getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Fecha fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
    
    
   
    public boolean esValido() throws ValidacionDTOException {
        if (this.numero == null
                || this.numero.isBlank()
                || this.numero.trim().length() > 10) {
            throw new ValidacionDTOException("Número invalido");
        }
        
        return true;
    }
    

}
