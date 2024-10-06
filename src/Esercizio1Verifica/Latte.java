package src.Esercizio1Verifica;

public class Latte implements Runnable{
    String tipoLatte;
    Caffettiera caffettiera;

    public Latte(String tipoLatte, Caffettiera caffettiera) {
        this.tipoLatte = tipoLatte;
        this.caffettiera = caffettiera;
    }

    public void run(){
        caffettiera.latte();
    }
}
