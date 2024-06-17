package V2;

public class Radiofaro implements Runnable {
    private final Integer PAUSA_RADIOFARO = 2_000;
    private final Buzon a_Buzon;
    private final String a_ID;

    public Radiofaro(Buzon p_Buzon, String p_ID) {
        this.a_Buzon = p_Buzon;
        this.a_ID = p_ID;
    }

    @Override
    public void run() {
        // COMENTARIO.
        String mensaje = a_ID + " > ";

        // COMENTARIO.
        if (!a_Buzon.a_Ejecucion) {
            a_Buzon.a_Ejecucion = true;
            mensaje += "No colisiona";
        } else mensaje += "Colisiona";
        a_Buzon.imprimirHora(mensaje);

        // COMENTARIO.
        try {
            Thread.sleep(PAUSA_RADIOFARO);
            a_Buzon.a_Ejecucion = false;
        } catch (InterruptedException p_Exception) {
            System.out.println(">>> Error: Ha falado el sleep(): " + p_Exception);
        }
    }
}
