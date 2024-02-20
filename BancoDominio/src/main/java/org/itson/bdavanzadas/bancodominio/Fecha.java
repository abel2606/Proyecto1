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
    }

    /**
     * Constructor que crea una fecha a partir de una cadena de texto con
     * formato "aaaa-mm-dd"
     *
     * @param s Cadena de texto con formato "aaaa-mm-dd"
     */
    public Fecha(String s) {
        super();
        String[] fechaTiempo = s.split(" ");

        String[] fechaTexto = fechaTiempo[0].split("-");
        int anio = Integer.parseInt(fechaTexto[0]);
        int mes = Integer.parseInt(fechaTexto[1]) - 1;
        int dia = Integer.parseInt(fechaTexto[2]);

        set(Calendar.YEAR, anio);
        set(Calendar.MONTH, mes);
        set(Calendar.DATE, dia);

        if (fechaTiempo.length > 1) {
            String[] tiempo = fechaTiempo[1].split(":");
            int hora = Integer.parseInt(tiempo[0]);
            int minuto = Integer.parseInt(tiempo[1]);
            int segundo = tiempo.length > 2 ? Integer.parseInt(tiempo[2]) : 0;

            set(Calendar.HOUR_OF_DAY, hora);
            set(Calendar.MINUTE, minuto);
            set(Calendar.SECOND, segundo);
        } else {
            set(Calendar.HOUR_OF_DAY, 0);
            set(Calendar.MINUTE, 0);
            set(Calendar.SECOND, 0);
        }
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
     * Permite darle formato a la fecha de "dia de mes de año".
     *
     * @return La fecha con formato de "dia de mes de año"
     */
    public String formatearFecha() {
        String mes = "";
        switch (MONTH - 1) {
            case 0 ->
                mes = "Enero";
            case 1 ->
                mes = "Febrero";
            case 2 ->
                mes = "Marzo";
            case 3 ->
                mes = "Abril";
            case 4 ->
                mes = "Mayo";
            case 5 ->
                mes = "Junio";
            case 6 ->
                mes = "Julio";
            case 7 ->
                mes = "Agosto";
            case 8 ->
                mes = "Septiembre";
            case 9 ->
                mes = "Octubre";
            case 10 ->
                mes = "Noviembre";
            case 11 ->
                mes = "Diciembre";
        }
        return getDia() + " de " + mes + " de " + getAnio();
    }

    /**
     * Devuelve una cadena de texto con una fecha con formato "aaaa-mm-dd"
     *
     * @return Una cadena de texto con una fecha con formato "aaaa-mm-dd"
     */
    @Override
    public String toString() {
        String texto = getAnio() + "-" + getMes() + "-" + getDia();
        return texto;
    }

    public String toStringHora() {
        String texto = getAnio() + "-" + getMes() + "-" + getDia() + " " + get(HOUR) + ":" + get(MINUTE) + ":" + get(SECOND);
        return texto;
    }

}
