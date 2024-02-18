package org.itson.bdavanzadas.bancopersistencia.dtos;public class TransferenciaNuevaDTO extends TransaccionNuevaDTO {

    private Long numeroCuentaDestino;

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

}
