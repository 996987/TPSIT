package EsercizioRipasso2;

public class SommaParziale implements Runnable {
    private final Numero numero;
    private final int inizio;
    private final int fine;
    private int sommaParziale;

    public SommaParziale(Numero numero, int inizio, int fine) {
        this.numero = numero;
        this.inizio = inizio;
        this.fine = fine;
    }

    @Override
    public void run() {
        sommaParziale = numero.sommaParziale(inizio, fine);
    }

    public int getSommaParziale() {
        return sommaParziale;
    }
}
