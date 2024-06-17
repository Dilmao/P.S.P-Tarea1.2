package V2;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Buzon {
    boolean a_Ejecucion = false;

    public void imprimirHora(String input) {
        Calendar l_Calendario = new GregorianCalendar();
        System.out.println(
                l_Calendario.get(Calendar.HOUR_OF_DAY) + ":" +
                        l_Calendario.get(Calendar.MINUTE) + ":" +
                        l_Calendario.get(Calendar.SECOND) + " > " + input
        );
    }
}
