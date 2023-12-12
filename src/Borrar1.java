import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Borrar1 {
    public static final int PAUSA_INICIAL = 0;
    public static final int CADENCIA = 3;
    public static final int LONGITUD_ARRAY = 100;
    public static final int UMBRAL = 1;
    public static final int TANDA = 10;

    // Clase interna
    static class Calculador implements Runnable {
        private final int[] a_Array;
        Calculador(int[] p_Array) {
            a_Array = p_Array;
        }

        @Override
        public void run() {
            Calendar l_CalendarioInicio = new GregorianCalendar();
            System.out.println(" -> " + darHora(l_CalendarioInicio));

            for (int l_Num : a_Array) {
                if (esPrimo(l_Num)) {
                    System.out.println(l_Num);
                }
            }

            Calendar l_CalendarioFin = new GregorianCalendar();
            System.out.println(" -> " + darHora(l_CalendarioFin));
        }   // run()
    }   // Calcular()


    public static void main(String[] args) throws InterruptedException {
        // Se imprime la hora de inicio de la ejecución del programa principal
        Calendar l_CalendarioInicio = new GregorianCalendar();
        System.out.println(darHora(l_CalendarioInicio) + " > Inicio main()");

        // Variables
        final ScheduledExecutorService l_EjecutorPlan = Executors.newScheduledThreadPool(2);
        int[] l_Array = crearArray(LONGITUD_ARRAY);
        final Runnable l_ObjRunnable = new Calculador(l_Array);

        // Se programa la ejecución
        l_EjecutorPlan.scheduleWithFixedDelay(l_ObjRunnable, PAUSA_INICIAL, CADENCIA, TimeUnit.SECONDS);

        Calendar l_CalendarioFin = new GregorianCalendar();
        System.out.println(darHora(l_CalendarioFin) + " > Fin main()");
    }   // main()

    /* **************************************** FUNCIONES **************************************** */
    public static String darHora(Calendar p_Calendario) {
        return p_Calendario.get(Calendar.HOUR_OF_DAY) + ":" +
                p_Calendario.get(Calendar.MINUTE) + ":" +
                p_Calendario.get(Calendar.SECOND);
    }   // darHora()

    public static int[] crearArray(int p_Longitud) {
        int[] l_Array = new int[p_Longitud];
        int l_Contador = 0;

        for (l_Contador = 0; l_Contador < p_Longitud; l_Contador++) {
            l_Array[l_Contador] = l_Contador;
        }

        return l_Array;
    }

    public static boolean esPrimo(int p_Num) {
        int l_Contador = 0;
        boolean esPrimo = true;

        if (p_Num <= 1) {
            esPrimo = false;
        }

        for (l_Contador = 2; l_Contador <= p_Num/2; l_Contador++) {
            if (p_Num % l_Contador == 0) {
                esPrimo = false;
            }
        }

        return (esPrimo);
    }
}
