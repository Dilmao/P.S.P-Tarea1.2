package V2;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Buzon {
    boolean a_EnEjecucion = false;

    // Método sincronizado para iniciar la ejecución.
    public synchronized boolean iniciarEjecucion() {
        if (a_EnEjecucion) {
            return false;
        } else {
            a_EnEjecucion = true;
            return true;
        }
    }

    // Método sincronizado para finalizar la ejecución.
    public synchronized void finalizarEjecucion() {
        a_EnEjecucion = false;
    }

    // Método para imprimir la hora actual junto con un mensaje dado.
    public void imprimirHora(String input) {
        Calendar l_Calendario = new GregorianCalendar();
        System.out.println(
                l_Calendario.get(Calendar.HOUR_OF_DAY) + ":" +
                        l_Calendario.get(Calendar.MINUTE) + ":" +
                        l_Calendario.get(Calendar.SECOND) + " > " + input
        );
    }
}
