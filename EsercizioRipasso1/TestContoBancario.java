package EsercizioRipasso1;

public class TestContoBancario {
    public static void main(String[] args) {
        ContoBancario conto = new ContoBancario(1000000.00);
        Persona per1 = new Persona("Natascia", "Sabatino",conto);
        Persona per2 = new Persona("Samuel", "Rossi",conto);

        Thread thr1 = new Thread(per1);
        Thread thr2 = new Thread(per2);

        thr1.setName("Natascia Sabatino");
        thr1.setName("Samuel Rossi");



        thr1.start();
        thr2.start();




    }
}
