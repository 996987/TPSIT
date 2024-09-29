package src.RipassoInGruppo;
import java.util.concurrent.Semaphore;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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



    public void entra(int idBiglietto) {
        // Trova il tornello con il minor numero di thread in attesa
        int minWaitingThreads = tornelli[0].getWaitingThreads();
        List<Integer> bestTornelli = new ArrayList<>();
        bestTornelli.add(0); // Aggiungi il primo tornello alla lista dei migliori

        for (int i = 1; i < tornelli.length; i++) {
            int waitingThreads = tornelli[i].getWaitingThreads();  // Numero di thread in attesa sul tornello i
            if (waitingThreads < minWaitingThreads) {
                // Se trovi un tornello con meno thread, svuota la lista e aggiungi questo
                bestTornelli.clear();
                bestTornelli.add(i);
                minWaitingThreads = waitingThreads;
            } else if (waitingThreads == minWaitingThreads) {
                // Se trovi un tornello con lo stesso numero minimo di thread, aggiungilo alla lista
                bestTornelli.add(i);
            }
        }

        // Seleziona casualmente uno dei tornelli con il numero minimo di thread in attesa
        int randomIndex = ThreadLocalRandom.current().nextInt(bestTornelli.size());
        int nBestTornello = bestTornelli.get(randomIndex);

        try {
            System.out.println("Spettatore con biglietto " + idBiglietto + " in attesa al tornello " + nBestTornello);

            // Acquisisce il semaforo del tornello selezionato
            tornelli[nBestTornello].acquire();

            int randomSleepTime = ThreadLocalRandom.current().nextInt(4000, 5000);
            Thread.sleep(5000);

        } catch (InterruptedException e) {
            System.out.println("Spettatore " + idBiglietto + " è stato interrotto.");
        } finally {
            // Rilascia il semaforo dopo il passaggio
            tornelli[nBestTornello].release();
            System.out.println("Spettatore con biglietto " + idBiglietto + " è passato attraverso il tornello " + nBestTornello);
        }

        // Passa alla fase di perquisizione
        perquisizione(idBiglietto, nBestTornello);
    }




    public void perquisizione(int idBiglietto, int nBodyguard) {
        try {
            System.out.println("Spettatore con biglietto " + idBiglietto + " è in attesa della perquisizione da parte della guardia " + nBodyguard);

            // Acquisisce il semaforo della guardia selezionata
            bodyguard[nBodyguard].acquire();  // Cambia questo: utilizza i semafori dei bodyguard

            // Genera un tempo di attesa casuale tra 1000 ms e 3000 ms
            int randomSleepTime = ThreadLocalRandom.current().nextInt(1000, 3000);
            Thread.sleep(randomSleepTime);  // Usa il tempo di attesa casuale

            System.out.println("Spettatore con biglietto " + idBiglietto + " ha atteso " + randomSleepTime + " ms per la perquisizione.");

        } catch (InterruptedException e) {
            System.out.println("Spettatore " + idBiglietto + " è stato interrotto.");
        } finally {
            // Rilascia il semaforo della guardia dopo la perquisizione
            bodyguard[nBodyguard].release();  // Cambia questo: utilizza i semafori dei bodyguard
            System.out.println("Spettatore con biglietto " + idBiglietto + " è stato perquisito dalla guardia " + nBodyguard);
        }
    }

}
