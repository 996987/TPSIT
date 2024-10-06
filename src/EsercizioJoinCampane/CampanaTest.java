package src.EsercizioJoinCampane;

public class CampanaTest {
    public static void main(String[] args) throws InterruptedException {
        Campana din = new Campana("din");
        Campana don = new Campana("don");
        Campana dan = new Campana("dan");

        Thread din1 = new Thread(din);
        Thread don1 = new Thread(don);
        Thread dan1 = new Thread(dan);

        din1.start();
        din1.join();

        don1.start();
        don1.join();

        dan1.start();
        dan1.join();
        System.out.println("Tutti i suoni delle campane sono stati emessi.");
    }
}
