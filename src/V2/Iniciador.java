package V2;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Iniciador {
    // Constantespara configurar los radiofaros
    public static final int NUMERO_HILOS = 2;
    public static final int CADENCIA_A = 3;
    public static final int CADENCIA_B = 2;
    public static final int PAUSA_INICIAL_A = 0;
    public static final int PAUSA_INICIAL_B = 3;
    public static final int DURACION_EJECUTOR = 21;
    public static boolean EJECUCION = false;

    public static void main(String[] args) throws InterruptedException {
        // COMENTARIO.
        Buzon l_Buzon = new Buzon();
        l_Buzon.imprimirHora("Inicio main()");

        // COMENTARIO.
        final ScheduledExecutorService l_ExecutorPlan = Executors.newScheduledThreadPool(NUMERO_HILOS);

        // COMENTARIO.
        final Runnable l_ObjRunnableA = new Radiofaro(l_Buzon, "A ·-");
        final Runnable l_ObjRunnableB = new Radiofaro(l_Buzon, "B -···");

        // COMENTARIO.
        l_ExecutorPlan.scheduleWithFixedDelay(l_ObjRunnableA, PAUSA_INICIAL_A, CADENCIA_A, TimeUnit.SECONDS);
        l_ExecutorPlan.scheduleWithFixedDelay(l_ObjRunnableB, PAUSA_INICIAL_B, CADENCIA_B, TimeUnit.SECONDS);

        // COMENTARIO.
        l_ExecutorPlan.awaitTermination(DURACION_EJECUTOR, TimeUnit.SECONDS);

        // COMENTARIO.
        l_ExecutorPlan.shutdown();

        // COMENTARIO.
        l_Buzon.imprimirHora("Fin main()");
    }
}
