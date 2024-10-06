package src.Esercizio1Verifica;

public class Caffettiera {

    public void latte(){
        System.out.println("Preparazione del latte in corso...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Latte pronto!");
    }

    public void caffe(){
        System.out.println("Preparazione del caffè in corso...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Caffe pronto!");
    }
}
