package src.PreparazioneVecchiaVerifica;

import java.util.concurrent.Semaphore;

public class Pista {
    private Semaphore slot = new Semaphore(4);

    public Pista() {
    }

    public void entra(String nome) throws InterruptedException {
        slot.acquire();
        System.out.println("Il kart " + nome + " è appena entrato in pista!");
    }

    public void esci(String nome) throws InterruptedException{
        slot.release();
        System.out.println("Il kart " + nome + "è appena uscito dalla in pista!");

    }
}
