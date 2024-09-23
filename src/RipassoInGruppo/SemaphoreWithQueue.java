package src.RipassoInGruppo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class SemaphoreWithQueue extends Semaphore {
    private final AtomicInteger waitingThreads;

    public SemaphoreWithQueue(int permits) {
        super(permits);
        waitingThreads = new AtomicInteger(0);
    }

    @Override
    public void acquire() throws InterruptedException {
        waitingThreads.incrementAndGet();
        super.acquire();
        waitingThreads.decrementAndGet();
    }

    @Override
    public void acquireUninterruptibly() {
        waitingThreads.incrementAndGet();
        super.acquireUninterruptibly();
        waitingThreads.decrementAndGet();
    }

    public int getWaitingThreads() {
        return waitingThreads.get();
    }
}
