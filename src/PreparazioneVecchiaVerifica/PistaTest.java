package src.PreparazioneVecchiaVerifica;

public class PistaTest {
    public static void main(String[] args) {
        // Crea un'istanza della pista
        Pista pista = new Pista();

        // Crea e avvia i karts in thread separati
        for (int i = 1; i <= 5; i++) {
            Kart kart = new Kart("Kart " + i,pista);

            // Avvia il kart in un nuovo thread
            new Thread(kart).start();
        }
    }
}
