import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class SecureTransaction{
    private final Lock lock = new ReentrantLock();
    public void stepOne(){
        // Thread acquires the lock , hold count = 1;
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+" is running step 1 ");
            stepTwo();
        } finally {
            // hold count drop to 0 , lock fully released
            lock.unlock();
            System.out.println(Thread.currentThread().getName()+" fully released lock");
        }
    }
    public void stepTwo(){
        // since the same thread asking , it succeeds ! hold count = 2;
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+" is running step 2");
        } finally {
            // The hold count drop and back to 1
            lock.unlock();
        }
    }
}
public class ReentrantDemo {
    static void main() {
        SecureTransaction transaction = new SecureTransaction();
        Thread t1 = new Thread(transaction::stepOne,
                "Thread-1");
        t1.start();
    }
}
