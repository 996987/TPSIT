package src.PreparazioneVecchiaVerifica;

public class Kart implements Runnable{
    private String nomeKart;
    private Pista pista;

    public Kart(String nomeKart,Pista pista) {
        this.nomeKart = nomeKart;
        this.pista = pista;
    }


    @Override
    public void run() {
        try {
            pista.entra(nomeKart);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            corri();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            pista.esci(nomeKart);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void corri() throws InterruptedException {
        System.out.println("Il kart" + nomeKart + " sta correndo");
        Thread.sleep(6500);
    }
}
