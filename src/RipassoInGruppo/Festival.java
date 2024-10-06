package src.RipassoInGruppo;
import java.util.concurrent.Semaphore;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Festival {
    private final SemaphoreWithQueue[] tornelli; //array di semafori con coda per  i tornelli
    private final SemaphoreWithQueue[] bodyguard; //array di semafori con coda per i bodygurd

    private final Semaphore cancello; //semaforo per gestire l'uscita dal cancello

    // Costruttore che inizializza il numero di tornelli
    public Festival(int Ntornelli) {
        //verifichiamo che il numero di tornelli sia almeno due, in caso contrario lanciamo un eccezione
        if (Ntornelli < 2) {
            throw new IllegalArgumentException("Il numero di tornelli deve essere almeno 2");
        }

        // Inizializza gli array con il numero di tornelli specificato
        this.tornelli = new SemaphoreWithQueue[Ntornelli];
        this.bodyguard = new SemaphoreWithQueue[Ntornelli];

        // Crea un semaforo per ogni tornello e guardia del corpo
        for (int i = 0; i < Ntornelli; i++) {
            this.tornelli[i] = new SemaphoreWithQueue(1);
            this.bodyguard[i] = new SemaphoreWithQueue(1);
        }
        this.cancello = new Semaphore(100);//il cancello d'uscita permette l'uscita di 100 persone contemporaneamente
    }


    //questo metodo simula l'entrata di uno spettatore
    public void entra(int idBiglietto){
        tornelli[0].tryAcquire();
        // Trova il tornello con il minor numero di thread in attesa
        int minWaitingThreads = tornelli[0].getWaitingThreads();
        List<Integer> bestTornelli = new ArrayList<>();
        bestTornelli.add(0); // Aggiungi il primo tornello alla lista dei migliori

        for (int i = 1; i < tornelli.length; i++) {
            int waitingThreads = tornelli[i].getWaitingThreads();// acquisisce il numero di thread in attesa al tornello
            if(tornelli[i].availablePermits()==0){
                waitingThreads = tornelli[i].getWaitingThreads() + 1;
            }
            // Se il tornello corrente ha meno thread in attesa, aggiornalo come migliore
            if (waitingThreads < minWaitingThreads) {
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
            // Passa alla fase di perquisizione
            perquisizione(idBiglietto, nBestTornello);
        }

        try {
            Thread.sleep(2000); //Durata concerto, da definire. Oppure sotto chiamata di Spettatore
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        deflusso(idBiglietto);


    }



    // Metodo per simulare la perquisizione da parte della guardia
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


    // Metodo per simulare l'uscita dallo stadio
    public void deflusso(int idBiglietto) {
        try {
            cancello.acquire();// Acquisisce il semaforo del cancello
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            Thread.sleep(5000);// Simula il tempo necessario per uscire
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        cancello.release(); // Rilascia il semaforo del cancello
        System.out.println("Spettatore con biglietto " + idBiglietto + " è uscito dallo stadio");
    }

}
