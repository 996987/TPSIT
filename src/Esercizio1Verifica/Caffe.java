package src.Esercizio1Verifica;

public class Caffe implements Runnable{
    String tipoLatte;
    Caffettiera caffettiera;

    public Caffe(String tipoLatte, Caffettiera caffettiera) {
        this.tipoLatte = tipoLatte;
        this.caffettiera = caffettiera;
    }

    public void run(){
        caffettiera.caffe();
    }
}
