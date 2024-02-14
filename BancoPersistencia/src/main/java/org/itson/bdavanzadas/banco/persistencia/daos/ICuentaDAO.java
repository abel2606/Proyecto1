/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.bdavanzadas.banco.persistencia.daos;

import org.itson.bdavanzadas.banco.persistencia.dtos.CuentaDTO;
import org.itson.bdavanzadas.banco.persistencia.excepciones.PersistenciaException;

/**
 *
 * @author Abe
 */
public interface ICuentaDAO {
    Cuenta agregar(CuentaDTO cuentaAgregar) throws PersistenciaException;
}
