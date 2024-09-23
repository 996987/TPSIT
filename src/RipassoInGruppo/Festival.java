package src.RipassoInGruppo;
import java.util.concurrent.Semaphore;

public class Festival {
    private final SemaphoreWithQueue[] tornelli;
    private final SemaphoreWithQueue[] bodyguard;

    public Festival(int Ntornelli) {
        if (Ntornelli < 2) {
            throw new IllegalArgumentException("Number of turnstiles must be at least 2.");
        }

        // Initialize the arrays with the specified number of turnstiles
        this.tornelli = new SemaphoreWithQueue[Ntornelli];
        this.bodyguard = new SemaphoreWithQueue[Ntornelli];

        for (int i = 0; i < Ntornelli; i++) {
            this.tornelli[i] = new SemaphoreWithQueue(1);
            this.bodyguard[i] = new SemaphoreWithQueue(1);
        }
    }


    public void entra(String idBiglietto) {
    }


}
