package src.RipassoInGruppo;

public class Spettatore implements Runnable{
    private int idBiglietto;
    private Festival concert;

    public Spettatore(int idBiglietto, Festival concert) {
        this.idBiglietto = idBiglietto;
        this.concert = concert;
    }

    public int getIdBiglietto() {
        return idBiglietto;
    }

    @Override
    public String toString() {
        return STR."Spettatore{idBiglietto='\{idBiglietto}\{'\''}\{'}'}";
    }

    @Override
    public void run() {
        concert.entra(this.idBiglietto);
    }
}
