package src.RipassoInGruppo;

public class FestivalTest {
    public static void main(String[] args) throws InterruptedException {
        Festival fest = new Festival(4);
        Spettatore spet0 = new Spettatore(0, fest);
        Spettatore spet1 = new Spettatore(1, fest);
        Spettatore spet2 = new Spettatore(2, fest);
        Spettatore spet3 = new Spettatore(3, fest);

        Thread thr0 = new Thread(spet0);
        Thread thr1 = new Thread(spet1);
        Thread thr2 = new Thread(spet2);
        Thread thr3 = new Thread(spet3);

        thr0.start();
        Thread.sleep(1234);
        thr1.start();
        Thread.sleep(1234);
        thr2.start();
        Thread.sleep(1234);
        thr3.start();
    }
}
