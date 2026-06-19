import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ModernDataBuffer {
    private final Queue<String> buffer = new LinkedList<>();
    private final int CAPACITY = 5;
    private final Lock lock = new ReentrantLock();
    // create specific waiting room
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public void produce(String data) throws InterruptedException {
        lock.lock();
        try {
            // ALWAYS use while loop
            while (buffer.size() == CAPACITY) {
                System.out.println("Buffer full. Producer is waiting...");
                notFull.await();
            }

        buffer.add(data);
        System.out.println("Producer " + data);

        // laser target only wake up the consumer
        notEmpty.signal();
    } finally {
        lock.unlock();
        }
    }
    public void consume() throws Exception{
        lock.unlock();
        try {
            while (buffer.isEmpty()) {
                System.out.println("Buffer is empty...consumer is waiting");
                notEmpty.await();
            }

            String data = buffer.poll();
            System.out.println("Consumed "+ data);
            notFull.signal();
        } finally {
            lock.unlock();
        }

    }
}
public class ThreadCommunication {
}
