package src.PreparazioneVecchiaVerifica;
import java.util.Scanner;
public class StampaTest {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Integer[] numeri = new Integer[3];

        for(int i = 0; i < numeri.length; i++){
            System.out.print("Inserisci un numero intero: ");
            numeri[i] = scanner.nextInt();
        }

        Stampa st1 = new Stampa(0,numeri);
        Stampa st2 = new Stampa(1,numeri);

        st1.start();
        st1.join();
        st2.start();
    }
}
