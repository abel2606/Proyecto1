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
        
        String fechaTexto[] = s.split("-");
        
        int dia, mes, anio;
        
        anio = Integer.parseInt(fechaTexto[0]);
        mes = Integer.parseInt(fechaTexto[1]);
        dia = Integer.parseInt(fechaTexto[2]);
        
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
     * Permite darle formato a la fecha de "dia de mes de año".
     *
     * @return La fecha con formato de "dia de mes de año"
     */
    public String formatearFecha() {
        String mes = "";
        switch (MONTH - 1) {
            case 0 -> mes = "Enero";
            case 1 -> mes = "Febrero";
            case 2 -> mes = "Marzo";
            case 3 -> mes = "Abril";
            case 4 -> mes = "Mayo";
            case 5 -> mes = "Junio";
            case 6 -> mes = "Julio";
            case 7 -> mes = "Agosto";
            case 8 -> mes = "Septiembre";
            case 9 -> mes = "Octubre";
            case 10 -> mes = "Noviembre";
            case 11 -> mes = "Diciembre";
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
        return (getAnio() + "-" + getMes() + "-" + getDia() + " " + get(HOUR) + ":" + get(SECOND) + ":" + get(MILLISECOND)).substring(0, 17);
    }

}
