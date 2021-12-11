package common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataEHoraHelper {

    public Date getDataInicial(int hora, int minutos, int segundos) {
        Calendar date = Calendar.getInstance();
        date.set(Calendar.HOUR_OF_DAY, hora);
        date.set(Calendar.MINUTE, minutos);
        date.set(Calendar.SECOND, segundos);

        return date.getTime();
    }

    public SimpleDateFormat getFormatoDeHoraSimples() {
        return new SimpleDateFormat("HH:mm ");
    }

    public String formatarHora(Date date) {
        return getFormatoDeHoraSimples().format(date);
    }
}
