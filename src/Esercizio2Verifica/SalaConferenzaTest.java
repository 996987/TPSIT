package src.Esercizio2Verifica;

public class SalaConferenzaTest {
    public static void main(String[] args) {
        SalaConferenza sala = new SalaConferenza(2);
        Team team5 = new Team("tecnico", sala, 3);
        Team team2 = new Team("marketing", sala, 2);
        Team team3 = new Team("marketing", sala, 2);
        Team team4 = new Team("marketing", sala, 2);
        Team team1 = new Team("tecnico", sala, 3);

        // ... altri team

        Thread t1 = new Thread(team5);
        Thread t2 = new Thread(team2);
        Thread t3 = new Thread(team3);
        Thread t4 = new Thread(team4);
        Thread t5 = new Thread(team1);


        // ... altri thread


        t2.start();
        t3.start();
        t1.start();
        t4.start();
        t5.start();

        // ... avvia altri thread
    }
}