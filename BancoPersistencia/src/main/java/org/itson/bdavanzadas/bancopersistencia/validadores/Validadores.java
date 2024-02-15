package org.itson.bdavanzadas.bancopersistencia.validadores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validadores {
    
    /**
     * Permite validar que la cadena de texto enviada en el parámetro represente
     * el nombre de una persona.
     *
     * @param nombre Cadena de texto que representa el nombre de una persona.
     * @return True si el nombre es válido, false en caso contrario
     */
    public boolean validarNombre(String nombre) {
        String patron = "^[A-Za-zÁáÉéÍíÓóÚúÜüÑñÇç]+([-'][A-Za-zÁáÉéÍíÓóÚúÜüÑñÇç]+)*(\\s+[A-Za-zÁáÉéÍíÓóÚúÜüÑñÇç]+([-'][A-Za-zÁáÉéÍíÓóÚúÜüÑñÇç]+)*)*$";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(nombre);
        return matcher.matches();
    }
}
