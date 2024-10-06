package src.Esercizio2Verifica;

public class Team implements Runnable {
    private String tipoTeam;
    private SalaConferenza salaConferenze;
    private int tempoRiunione;

    public Team(String tipoTeam, SalaConferenza salaConferenze, int tempoRiunione) {
        this.tipoTeam = tipoTeam;
        this.salaConferenze = salaConferenze;
        this.tempoRiunione = tempoRiunione;
    }

    @Override
    public void run() {
        try{
            salaConferenze.accediAllaSala(tipoTeam);
            //System.out.println(tipoTeam + " sta tenendo una riunione di " + tempoRiunione + " secondi");
            Thread.sleep(tempoRiunione * 1000);
            salaConferenze.esciDallaSala(tipoTeam);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

