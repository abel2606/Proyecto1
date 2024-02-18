package org.itson.bdavanzadas.bancodominio;

public class Transferencia extends Transaccion {

    private Long numeroCuentaDestino;

    /**
     * Constructor vacío.
     */
    public Transferencia() {
        super();
    }

    /**
     * Constructor que recibe el folio, la fecha de realización, el número de la
     * cuenta origen y el número de la cuenta destino de la transacción.
     *
     * @param folio El folio
     * @param monto El monto
     * @param fechaRealizacion La fecha de realización
     * @param numeroCuentaOrigen El número de la cuenta origen
     * @param numeroCuentaDestino El número de la cuenta destino
     */
    public Transferencia(Long folio, Float monto, Fecha fechaRealizacion, Long numeroCuentaOrigen, Long numeroCuentaDestino) {
        super(folio, monto, fechaRealizacion, numeroCuentaOrigen);
        this.numeroCuentaDestino = numeroCuentaDestino;
    }

    /**
     * Constructor que recibe la fecha de realización, el número de la cuenta
     * origen y el número de la cuenta destino de la transacción.
     *
     * @param monto El monto
     * @param fechaRealizacion La fecha de realización
     * @param numeroCuentaOrigen El número de la cuenta origen
     * @param numeroCuentaDestino El número de la cuenta destino
     */
    public Transferencia(Float monto, Fecha fechaRealizacion, Long numeroCuentaOrigen, Long numeroCuentaDestino) {
        super(monto, fechaRealizacion, numeroCuentaOrigen);
        this.numeroCuentaDestino = numeroCuentaDestino;
    }

    /**
     * Permite obtener el número de la cuenta destino de la transferencia.
     *
     * @return El número de la cuenta destino de la transferencia
     */
    public Long getNumeroCuentaDestino() {
        return numeroCuentaDestino;
    }

    /**
     * Permite establecer el número de la cuenta destino de la transferencia.
     *
     * @param numeroCuentaDestino El número de la cuenta destino de la
     * transferencia
     */
    public void setNumeroCuentaDestino(Long numeroCuentaDestino) {
        this.numeroCuentaDestino = numeroCuentaDestino;
    }

    /**
     * Permite obtener una cadena de texto con los atributos de la
     * transferencia.
     *
     * @return Una cadena de texto con los atributos de la transferencia.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Transferencia{");
        sb.append("folio=").append(super.folio);
        sb.append(", monto=").append(super.monto);
        sb.append(", fechaRealizacion=").append(super.fechaRealizacion);
        sb.append(", numeroCuentaOrigen=").append(super.numeroCuentaOrigen);
        sb.append(", numeroCuentaDestino=").append(numeroCuentaDestino);
        sb.append('}');
        return sb.toString();
    }

}
