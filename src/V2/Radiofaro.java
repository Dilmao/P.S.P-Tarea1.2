package V2;

public class Radiofaro implements Runnable {
    private static final int PAUSA_RADIOFARO = 2_000;
    private final Buzon a_Buzon;
    private final String a_ID;

    public Radiofaro(Buzon p_Buzon, String p_ID) {
        this.a_Buzon = p_Buzon;
        this.a_ID = p_ID;
    }

    @Override
    public void run() {
        // Variables.
        String mensaje = a_ID + " > ";
        boolean l_EjecucionIniciada = a_Buzon.iniciarEjecucion();

        // Determinar el mensaje según si la ejecución se inició sin colisión.
        if (l_EjecucionIniciada) mensaje += "No colisiona";
        else mensaje += "Colisiona";

        // Imprimir la hora y el mensaje correspondiente.
        a_Buzon.imprimirHora(mensaje);

        // Intentar poner en pausa la ejecución.
        try {
            Thread.sleep(PAUSA_RADIOFARO);
        } catch (InterruptedException p_Exception) {
            // Manejar la excepción de interrupción del hilo.
            System.out.println(">>> Error: Ha falado el sleep(): " + p_Exception);
        } finally {
            // Finalizar la ejecución si fue iniciada correctamente.
            if (l_EjecucionIniciada) {
                a_Buzon.finalizarEjecucion();
            }
        }
    }
}
