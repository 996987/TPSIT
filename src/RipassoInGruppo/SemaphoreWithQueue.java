package src.RipassoInGruppo;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

//questa classe serve per gestire un semaforo con un numero di thread in attesa
public class SemaphoreWithQueue extends Semaphore {
    private final AtomicInteger waitingThreads;


    //metodo costruttore che setta il numerto di thread in attesa
    public SemaphoreWithQueue(int permits) {
        super(permits);
        this.waitingThreads = new AtomicInteger(0);
    }

    @Override
    public void acquire() throws InterruptedException {
        waitingThreads.incrementAndGet();  // Incrementa in modo atomico
        super.acquire();
        waitingThreads.decrementAndGet();  // Decrementa in modo atomico
    }

    @Override
    public void acquireUninterruptibly() {
        waitingThreads.incrementAndGet();  // Incrementa in modo atomico
        super.acquireUninterruptibly();
        waitingThreads.decrementAndGet();  // Decrementa in modo atomico
    }

    public int getWaitingThreads() {
        return waitingThreads.get();  // Restituisce il valore corrente in modo thread-safe
    }
}
