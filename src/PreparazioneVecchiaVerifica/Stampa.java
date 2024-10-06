package src.PreparazioneVecchiaVerifica;
import java.util.Arrays;
import java.util.Collections;
public class Stampa extends Thread{
    private final int verso;
    private Integer[] numeri;

    public Stampa(int verso, Integer[] numeri) {
        this.verso = verso;
        this.numeri = numeri;
    }


    @Override
    public void run() {
        stampa();
    }

    public synchronized void stampa(){
        if(verso==0){
            Arrays.sort(numeri);
            System.out.println(Arrays.toString(numeri));
        }else{
            Arrays.sort(numeri, Collections.reverseOrder());
            System.out.println(Arrays.toString(numeri));
        }
    }
}
