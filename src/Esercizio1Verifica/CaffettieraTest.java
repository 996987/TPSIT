package src.Esercizio1Verifica;

public class CaffettieraTest {
    public static void main(String[] args) throws InterruptedException {
        Caffettiera caf1 = new Caffettiera();
        Thread latte = new Thread(new Latte("Soia", caf1));
        Thread caffe = new Thread(new Caffe("Arabica", caf1));

        latte.start();
        caffe.start();
        caffe.join();
        System.out.println("Bevande pronte! Caffè e latte sono stati preparati.");
    }
}
