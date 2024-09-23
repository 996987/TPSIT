package src.RipassoInGruppo;

public class Spettatore implements Runnable{
    private String idBiglietto;
    private Festival concert;

    public Spettatore(String idBiglietto) {
        this.idBiglietto = idBiglietto;
    }

    public String getIdBiglietto() {
        return idBiglietto;
    }



    @Override
    public String toString() {
        return "Spettatore{" +
                "idBiglietto='" + idBiglietto + '\'' +
                '}';
    }

    @Override
    public void run() {
        concert.entra(this.idBiglietto);
    }
}
