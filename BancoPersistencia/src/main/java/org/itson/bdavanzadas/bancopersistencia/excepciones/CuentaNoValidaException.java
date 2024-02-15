package org.itson.bdavanzadas.bancopersistencia.excepciones;

public class CuentaNoValidaException extends Exception {

    /**
     * Constructor vacío.
     */
    public CuentaNoValidaException() {
    }

    /**
     * Constructor que establece el mensaje de la excepción.
     *
     * @param message de la excepción
     */
    public CuentaNoValidaException(String message) {
        super(message);
    }

    /**
     * Constructor que establece el mensaje y la causa de la excepción.
     *
     * @param message de la excepción
     * @param cause de la excepción
     */
    public CuentaNoValidaException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor que establece la causa de la excepción.
     *
     * @param cause de la excepción
     */
    public CuentaNoValidaException(Throwable cause) {
        super(cause);
    }

    /**
     * 
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace 
     */
    public CuentaNoValidaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
