package V1;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**********************************************************************************************************************************************
 *   APLICACIÓN: "Faros Nauticos"                                                                                                             *
 **********************************************************************************************************************************************
 *   PROGRAMACIÓN DE SERVICIOS Y PROCESOS 2DAM  -  Eclipse IDE for Java Developers v2021-09 (4.21.0)                                          *
 **********************************************************************************************************************************************
 *   @author  Diego lacasa                                                                                                                    *
 *   @version 1.0 - Versión inicial del programa.                                                                                             *
 *   @since   25OCT2023                                                                                                                       *
 **********************************************************************************************************************************************
 *   COMENTARIOS:                                                                                                                             *
 *        - Esta tarea implementa Runnable                                                                                                    *
 *        - El radiofaro A tiene una cadencia de 3 segundos                                                                                   *
 *        - El radiofaro B tiene una cadencia de 2 segundos                                                                                   *
 **********************************************************************************************************************************************/
public class FarosNauticos {
    // Constantespara configurar los radiofaros
    public static final int NUMERO_HILOS = 2;
    public static final int CADENCIA_A = 3;
    public static final int CADENCIA_B = 2;
    public static final int PAUSA_INICIAL_A = 0;
    public static final int PAUSA_INICIAL_B = 3;
    public static final int DURACION_EJECUTOR = 21;
    public static boolean EJECUCION = false; // Variable para controlar la colisión de emisiones

    // Clase interna que representa un radiofaro
    static class Radiofaro implements Runnable {
        private final String a_id;
        public Radiofaro(String p_id) {
            this.a_id = p_id;
        }

        @Override
        public void run() {
            // Se obtiene la hora de inicio de la emisión
            Calendar l_CalendarioInicio = new GregorianCalendar();
            String horaInicio = l_CalendarioInicio.get(
                    Calendar.HOUR_OF_DAY) + ":" +
                    l_CalendarioInicio.get(Calendar.MINUTE) + ":" +
                    l_CalendarioInicio.get(Calendar.SECOND);
            System.out.print(horaInicio + " > " + a_id + " > ");

            // Se comprueba si hay colisión con otra emisión
            if (!EJECUCION) {
                EJECUCION = true;
                System.out.println("No colisiona");
            } else {
                System.out.println("Colisiona");
            }

            // Simulamos la duracion de la emision (2 segundos)
            try {
                Thread.sleep(2_000);
            } catch (InterruptedException p_Excepcion) {
                System.out.println("ERROR: Ha fallado el sleep(): " + p_Excepcion);
            }
            EJECUCION = false;
        }   // run()
    }   // Radiofaro

    public static void main(String[] args) throws InterruptedException {
        // Se muestra la hora de inicio de la ejecución del programa principal
        Calendar l_Calendario = new GregorianCalendar();
        System.out.println(l_Calendario.get(Calendar.HOUR_OF_DAY) + ":" +
                l_Calendario.get(Calendar.MINUTE) + ":" +
                l_Calendario.get(Calendar.SECOND) + " > Inicio main()");

        // Se crea un pool de hilos con 2 hilos disponibles
        final ScheduledExecutorService l_EjecutorPlan = Executors.newScheduledThreadPool(NUMERO_HILOS);

        // Se crean los objetos Runnable para los radiofaros A y B
        final Runnable l_ObjRunnableA = new Radiofaro("A ·-");
        final Runnable l_ObjRunnableB = new Radiofaro("B -···");

        // Se programa la ejecución de los radiofaros con sus respectivas cadencias y pausas iniciales
        l_EjecutorPlan.scheduleWithFixedDelay(l_ObjRunnableA, PAUSA_INICIAL_A, CADENCIA_A, TimeUnit.SECONDS);
        l_EjecutorPlan.scheduleWithFixedDelay(l_ObjRunnableB, PAUSA_INICIAL_B, CADENCIA_B, TimeUnit.SECONDS);

        // Se espera 21 segundos antes de finalizar la ejecución de los hilos
        l_EjecutorPlan.awaitTermination(DURACION_EJECUTOR, TimeUnit.SECONDS);

        // Se detiene el pool de hilos y se muestra la hora de finalización
        l_EjecutorPlan.shutdown();
        Calendar l_CalendarioFinal = new GregorianCalendar();
        System.out.println(l_CalendarioFinal.get(Calendar.HOUR_OF_DAY) +
                ":" + l_CalendarioFinal.get(Calendar.MINUTE) +
                ":" + l_CalendarioFinal.get(Calendar.SECOND) + " > Fin main()");
    }   // main()
}   // Main