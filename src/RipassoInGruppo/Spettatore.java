package src.RipassoInGruppo;

import static java.lang.StringTemplate.STR;

public class Spettatore implements Runnable{
    private int idBiglietto;
    private Festival concert;


    //metodo costruttore
    public Spettatore(int idBiglietto, Festival concert) {
        this.idBiglietto = idBiglietto;
        this.concert = concert;
    }

    public int getIdBiglietto() {
        return idBiglietto;
    }


    // Metodo per stampare le informazioni dello spettatore
    @Override
    public String toString() {
        return STR."Spettatore{idBiglietto='\{idBiglietto}\{'\''}\{'}'}";
    }

    // Metodo run eseguito nel thread
    @Override
    public void run() {
        concert.entra(this.idBiglietto);
    }
}
