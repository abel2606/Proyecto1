/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.bancopersistencia.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.bdavanzadas.bancopersistencia.dtos.CuentaDTO;
import org.itson.bdavanzadas.bancopersistencia.excepciones.PersistenciaException;

/**
 * Objetos DAO de tipo cuenta para ingresar las cuentas en la base de datos
 * @author Abel
 */
public class CuentaDAO implements ICuentaDAO{

    final IConexion conexionBD;
    static final Logger logger = Logger.getLogger(CuentaDAO.class.getName());
    
    /**
     * Agrega una cuenta a la base de datos
     * @param cuentaAgregar
     * @return
     * @throws PersistenciaException
     */
    @Override
    public Cuenta agregar(CuentaDTO cuentaAgregar) throws PersistenciaException {
        String sentenciaSQL = """
                             INSERT INTO cuentas(numero,saldo,alias,fechaApertura,activa)
                             VALUES (?,?,?);
                             """;
        try (
                Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL, Statement.RETURN_GENERATED_KEYS);) {

            comando.setString(1, cuentaAgregar.getNumero());
            comando.setString(2, Float.toString(cuentaAgregar.getSaldo()));
            comando.setString(3, cuentaAgregar.getAlias());
            comando.setString(4, cuentaAgregar.getAlias());
            comando.setString(5, cuentaAgregar.getFechaApertura());
            comando.setString(6, String.valueOf(cuentaAgregar.isActiva()));
            int numRegistrosInsertados = comando.executeUpdate();
            logger.log(Level.INFO, "Se agregaron {0} cuentas", numRegistrosInsertados);

            ResultSet idsGenerados = comando.getGeneratedKeys();
            idsGenerados.next();
            Cuenta socio = new Cuenta(
                    idsGenerados.getLong(1), 
            );
            return socio;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se pudo guardar la cuenta", ex);
            throw new PersistenciaException("No se pudo guardar la cuenta");
        }
    }
    
}
