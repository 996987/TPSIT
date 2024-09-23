package EsercizioRipasso2;

public class TestSommaParallela {
    public static void main(String[] args) {
        int[] numeri = {2, 5, 8, 12, 3, 7, 100, 6};

        Numero numero = new Numero(numeri);

        int lunghezza = numeri.length;
        int puntoDiDivisione = lunghezza / 2;

        SommaParziale sommaParziale1 = new SommaParziale(numero, 0, puntoDiDivisione - 1);
        SommaParziale sommaParziale2 = new SommaParziale(numero, puntoDiDivisione, lunghezza - 1);

        Thread thread1 = new Thread(sommaParziale1);
        Thread thread2 = new Thread(sommaParziale2);

        thread1.start();
        thread2.start();



        int totale = sommaParziale1.getSommaParziale() + sommaParziale2.getSommaParziale();

        System.out.println("La somma totale è: " + totale);
    }
}
