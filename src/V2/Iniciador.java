package V2;

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
    public static final int DURACION_EXECUTOR = 21;

    public static void main(String[] args) throws InterruptedException {
        // Crear una instancia de Buzon.
        Buzon l_Buzon = new Buzon();
        l_Buzon.imprimirHora("Inicio main()");

        // Crear un ScheduledExecutorService.
        final ScheduledExecutorService l_ExecutorPlan = Executors.newScheduledThreadPool(NUMERO_HILOS);

        // Crear instancias de Radiofaro con sus respectivas configuraciones.
        final Runnable l_RadiofaroA = new Radiofaro(l_Buzon, "A ·-");
        final Runnable l_RadiofaroB = new Radiofaro(l_Buzon, "B -···");

        // Programar las tareas para que se ejecuten con un retraso inicial y una cadencia especifica.
        l_ExecutorPlan.scheduleWithFixedDelay(l_RadiofaroA, PAUSA_INICIAL_A, CADENCIA_A, TimeUnit.SECONDS);
        l_ExecutorPlan.scheduleWithFixedDelay(l_RadiofaroB, PAUSA_INICIAL_B, CADENCIA_B, TimeUnit.SECONDS);

        // Esperar a que el executor termine después de la duración especificada.
        l_ExecutorPlan.awaitTermination(DURACION_EXECUTOR, TimeUnit.SECONDS);

        // Apagar el executor después de la duración especificada.
        l_ExecutorPlan.shutdown();

        // Imprimir la hora de finalización del main.
        l_Buzon.imprimirHora("Fin main()");
    }
}
