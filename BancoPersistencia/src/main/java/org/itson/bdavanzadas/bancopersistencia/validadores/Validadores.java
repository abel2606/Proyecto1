package org.itson.bdavanzadas.bancopersistencia.validadores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validadores {

    /**
     * Permite validar que la cadena de texto enviada en el parámetro represente
     * el nombre de una persona.
     *
     * @param nombre Cadena de texto que representa el nombre de una persona
     * @return True si el nombre es válido, false en caso contrario
     */
    public boolean validarNombre(String nombre) {
        String patron = "^[A-Za-zÁáÉéÍíÓóÚúÜüÑñÇç]+([-'][A-Za-zÁáÉéÍíÓóÚúÜüÑñÇç]+)*(\\s+[A-Za-zÁáÉéÍíÓóÚúÜüÑñÇç]+([-'][A-Za-zÁáÉéÍíÓóÚúÜüÑñÇç]+)*)*{50}$";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(nombre);
        return matcher.matches();
    }

    /**
     * Permite validar que la cadena de texto enviada en el parámetro represente
     * el nombre de una calle.
     *
     * @param calle Cadena de texto que representa el nombre de una calle
     * @return True si la calle es válida, false en caso contrario
     */
    public boolean validarCalle(String calle) {
        String patron = "^(?!.*\\s{2})[a-zA-ZáéíóúÁÉÍÓÚüÜ\\s]{1,50}$";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(calle);
        return matcher.matches();
    }

    /**
     * Permite validar que la cadena de texto enviada en el parámetro represente
     * el número de una dirección.
     *
     * @param numero Cadena de texto que representa el número de una dirección
     * @return True si el número es válida, false en caso contrario
     */
    public boolean validarNumero(String numero) {
        String patron = "^(?=(?:.*\\d){2})\\d{1,10}$";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(numero);
        return matcher.matches();
    }
    
    /**
     * Permite validar que la cadena de texto enviada en el parámetro represente
     * un código postal.
     *
     * @param codigoPostal Cadena de texto que representa un código postal
     * @return True si el código postal es válido, false en caso contrario
     */
    public boolean validarCodigoPostal(String codigoPostal){
        String patron = "^[0-9]{5}$";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(codigoPostal);
        return matcher.matches();
    }
    
    public boolean validarCiudad(String ciudad) {
        String patron = "^(?!.*\\s{2})[a-zA-ZáéíóúÁÉÍÓÚüÜ\\s]{1,50}$";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(ciudad);
        return matcher.matches();
    }
    
    /**
     * Permite validar que la cadena de texto enviada en el parámetro represente
     * un nombre de usuario.
     *
     * @param usuario Cadena de texto que representa un usuario
     * @return True si el usuario es válido, false en caso contrario
     */
    public boolean validarUsuario(String usuario) {
        String patron = "(\\w[.]?){3,15}";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(usuario);
        return matcher.matches();
    }
    
    public boolean validarFecha(String fecha) {
        String patron = "^([1-9]|[0-2][0-9]|3[0-1])(\\/)([1-9]|0[1-9]|1[0-2])\\2(\\d{4})$";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(fecha);
        return matcher.matches();
    }

}
