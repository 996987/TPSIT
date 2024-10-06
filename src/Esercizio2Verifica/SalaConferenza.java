package src.Esercizio2Verifica;import java.util.concurrent.Semaphore;

public class SalaConferenza {
    private int postiOccupati;
    private Semaphore salaConferenza;
    private Semaphore mutex; // Controlla l'accesso alle variabili condivise
    private int contatoreTecnici;
    private int contatoreMarketing;
    private int chistausando; // 0 = tecnico, 1 = marketing, -1 = nessuno

    public SalaConferenza(int capacitaMax) {
        this.salaConferenza = new Semaphore(capacitaMax); // Limita la capacità della sala
        this.mutex = new Semaphore(1); // Protegge l'accesso a contatori e logica di priorità
        this.chistausando = -1; // Nessun team sta usando la sala all'inizio
    }

    public void accediAllaSala(String tipoTeam) throws InterruptedException {
        mutex.acquire();

        if (tipoTeam.equals("tecnico")) {
            // Se c'è un team di marketing nella sala, i tecnici devono attendere
            while (contatoreMarketing > 0 || (chistausando == 1 && postiOccupati > 0)) {
                System.out.println("Team tecnico in attesa. La sala è occupata dal team marketing.");
                mutex.release();
                Thread.sleep(100); // Attesa attiva, potrebbe essere migliorata
                mutex.acquire();
            }
            // Ora i tecnici possono entrare
            chistausando = 0; // Indica che la sala è usata dai tecnici
            contatoreTecnici++;
        } else {
            // Se ci sono tecnici nella sala, il team di marketing deve attendere
            while (contatoreTecnici > 0 || (chistausando == 0 && postiOccupati > 0)) {
                System.out.println("Team marketing in attesa. La sala è occupata dal team tecnico.");
                mutex.release();
                Thread.sleep(100); // Attesa attiva, potrebbe essere migliorata
                mutex.acquire();
            }
            // Ora i team di marketing possono entrare
            chistausando = 1; // Indica che la sala è usata dal marketing
            contatoreMarketing++;
        }

        // Controlla se ci sono posti disponibili nella sala
        salaConferenza.acquire();
        postiOccupati++;
        System.out.println(tipoTeam + " è entrato. Persone attualmente nella sala: " + postiOccupati);

        mutex.release();
    }

    public void esciDallaSala(String tipoTeam) throws InterruptedException {
        mutex.acquire();

        // Decrementa i contatori per il tipo di team che sta uscendo
        if (tipoTeam.equals("tecnico")) {
            contatoreTecnici--;
        } else {
            contatoreMarketing--;
        }

        postiOccupati--;
        salaConferenza.release(); // Libera un posto nella sala

        // Se non ci sono più persone nella sala, nessun team sta utilizzando la sala
        if (postiOccupati == 0) {
            chistausando = -1;
        }

        System.out.println(tipoTeam + " è uscito. Persone attualmente nella sala: " + postiOccupati);
        mutex.release();
    }
}
