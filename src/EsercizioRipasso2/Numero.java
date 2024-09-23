package src.EsercizioRipasso2;

public class Numero {
    private final int[] numeri;

    public Numero(int[] numeri) {
        this.numeri = numeri;
    }

    public synchronized int sommaParziale(int inizio, int fine) {
        int somma = 0;
        for (int i = inizio; i <= fine; i++) {
            somma += numeri[i];
        }
        return somma;
    }
}
