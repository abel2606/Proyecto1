package org.itson.bdavanzadas.bancodominio;

public class Periodo {

    private Fecha desde, hasta;

    /**
     * Constructor que crea un periodo de tiempo con las fechas de sus
     * parámetros
     *
     * @param desde Fecha inicial del periodo
     * @param hasta Fecha final del periodo
     */
    public Periodo(Fecha desde, Fecha hasta) {
        this.desde = desde;
        this.hasta = hasta;
    }

    /**
     * Devuelve la fecha inicial del periodo
     *
     * @return La fecha inicial del periodo
     */
    public Fecha getDesde() {
        return desde;
    }

    /**
     * Asigan un valor a la fecha inicial del periodo
     *
     * @param desde La fecha inicial del perido
     */
    public void setDesde(Fecha desde) {
        this.desde = desde;
    }

    /**
     * Devuelve la fecha final del periodo
     *
     * @return La fecha final del periodo
     */
    public Fecha getHasta() {
        return hasta;
    }

    /**
     * Asigna un valor a la fecha final del perido
     *
     * @param hasta La fecha final del periodo
     */
    public void setHasta(Fecha hasta) {
        this.hasta = hasta;
    }

    /**
     * Nos indica si una fecha existe o no dentro de un periodo, retornando un
     * valor booleano.
     *
     * @param fecha La fecha a analizar.
     * @return True si la fecha está contenida en el periodo y false en caso
     * contrario.
     */
    public boolean contiene(Fecha fecha) {
        if (fecha.before(desde) || fecha.after(hasta)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Convierte un periodo de tiempo a una cadena de texto
     *
     * @return Una cadena de texto que representa un periodo de tiempo
     */
    @Override
    public String toString() {
        return desde.toString() + " a " + hasta.toString();
    }
}
