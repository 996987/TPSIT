package src.EsercizioJoinCampane;

public class Campana implements Runnable {
    private String suono;

    public Campana(String suono) {
        this.suono = suono;
    }

    @Override
    public void run() {
        System.out.println(suono);
    }
}
