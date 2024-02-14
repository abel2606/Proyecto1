package org.itson.bdavanzadas.bancodominio;

import java.util.Calendar;
import static java.util.Calendar.MONTH;
import java.util.GregorianCalendar;

public class Fecha extends GregorianCalendar {

    /**
     * Constructor que crea una fecha y la inicializa a la fecha del sistema.
     */
    public Fecha() {
        super();
        set(Calendar.HOUR, 0);
        set(Calendar.MINUTE, 0);
        set(Calendar.SECOND, 0);
        set(Calendar.MILLISECOND, 0);
    }

    /**
     * Constructor que crea una fecha con los valores de los parámetros
     *
     * @param dia El día
     * @param mes El mes
     * @param anio El año
     */
    public Fecha(int dia, int mes, int anio) {
        super(anio, mes - 1, dia);
        set(Calendar.HOUR, 0);
        set(Calendar.MINUTE, 0);
        set(Calendar.SECOND, 0);
        set(Calendar.MILLISECOND, 0);
    }

    /**
     * Constructor que crea una fecha copiando los valores de la fecha recibida
     * en el parámetro
     *
     * @param fecha La fecha
     */
    public Fecha(Fecha fecha) {
        super(fecha.get(fecha.YEAR), fecha.get(fecha.MONTH), fecha.get(fecha.DATE), fecha.get(Calendar.HOUR), fecha.get(Calendar.MINUTE), fecha.get(Calendar.SECOND));
        set(Calendar.MILLISECOND, fecha.get(Calendar.MILLISECOND));
    }

    /**
     * Constructor que crea una fecha a partir de una cadena de texto con
     * formato "dd/mm/aaaa"
     *
     * @param s Cadena de texto con formato "dd/mm/aaaa"
     */
    public Fecha(String s) {
        super();

        String fechaTexto[] = s.split("/");

        int dia, mes, anio;

        dia = Integer.parseInt(fechaTexto[0]);
        mes = Integer.parseInt(fechaTexto[1]);
        anio = Integer.parseInt(fechaTexto[2]);

        set(Calendar.YEAR, anio);
        set(Calendar.MONTH, mes - 1);
        set(Calendar.DATE, dia);
        set(Calendar.HOUR, 0);
        set(Calendar.MINUTE, 0);
        set(Calendar.SECOND, 0);
        set(Calendar.MILLISECOND, 0);
    }

    /**
     * Devuelve el día
     *
     * @return El día
     */
    public int getDia() {
        return get(Calendar.DATE);
    }

    /**
     * Establece un valor para día
     *
     * @param dia El día
     */
    public void setDia(int dia) {
        set(Calendar.DATE, dia);
    }

    /**
     * Devuelve el mes
     *
     * @return El mes
     */
    public int getMes() {
        return super.get(MONTH) + 1;
    }

    /**
     * Establece un valor para el mes
     *
     * @param mes El mes
     */
    public void setMes(int mes) {
        set(Calendar.MONTH, mes - 1);
    }

    /**
     * Devuelve el año
     *
     * @return El año
     */
    public int getAnio() {
        return get(Calendar.YEAR);
    }

    /**
     * Establece un valor para el año
     *
     * @param anio El año
     */
    public void setAnio(int anio) {
        set(Calendar.YEAR, anio);
    }

    /**
     * Establece valores para el día, el mes y el año
     *
     * @param dia El día
     * @param mes El mes
     * @param anio El año
     */
    public void setFecha(int dia, int mes, int anio) {
        set(Calendar.DATE, dia);
        set(Calendar.MONTH, mes - 1);
        set(Calendar.YEAR, anio);
    }

    /**
     * Suma los días, meses y años de los parámetros a una fecha
     *
     * @param dias Los días
     * @param meses Los meses
     * @param anios Los años
     * @return Una nueva fecha con los días, meses y años ya sumados
     */
    public Fecha vencimiento(int dias, int meses, int anios) {
        Fecha fecha = new Fecha(this);
        fecha.add(Calendar.DATE, dias);
        fecha.add(Calendar.MONTH, meses);
        fecha.add(Calendar.YEAR, anios);
        return fecha;
    }

    /**
     * Suma los días y meses de los parámetros a una fecha
     *
     * @param dias Los días
     * @param meses Los meses
     * @return Una nueva fecha con los días y meses ya sumados
     */
    public Fecha vencimiento(int dias, int meses) {
        Fecha fecha = new Fecha(this);
        fecha.add(Calendar.DATE, dias);
        fecha.add(Calendar.MONTH, meses);
        return fecha;
    }

    /**
     * Suma los días del parámetro a una fecha
     *
     * @param dias Los días
     * @return Una nueva fecha con los días ya sumados
     */
    public Fecha vencimiento(int dias) {
        Fecha fecha = new Fecha(this);
        fecha.add(Calendar.DATE, dias);
        return fecha;
    }

    /**
     * Calcula los días entre dos fechas
     *
     * @param desde Una fecha
     * @return Los días entre dos fechas
     */
    public int lapso(Fecha desde) {
        return (int) (((((desde.getTimeInMillis() - this.getTimeInMillis()) / 1000) / 60) / 60) / 24) + 1;
    }

    /**
     * Devuelve una cadena de texto con una fecha con formato "dd/mm/aaaa"
     *
     * @return Una cadena de texto con una fecha con formato "dd/mm/aaaa"
     */
    @Override
    public String toString() {
        return getDia() + "/" + getMes() + "/" + getAnio();
    }
}
