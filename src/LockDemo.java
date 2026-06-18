import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class CoffeeShopRestroom{
    private final Lock restroomLock = new ReentrantLock();
    public void useRestroom(String personName){
        System.out.println(personName + "is waiting outside the restroom...");
        restroomLock.lock();
        try{
            System.out.println(personName+"LOCKED The door and is inside...");
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            System.out.println(personName + " Unlock the door after use it..");
            restroomLock.unlock();
        }
    }
}
public class LockDemo {
    static void main() {
        CoffeeShopRestroom restroom = new CoffeeShopRestroom();
        Thread threadA = new Thread(()-> restroom.useRestroom("Thread-A"));
        Thread threadB = new Thread(()->restroom.useRestroom("Thread-B"));
        Thread threadC = new Thread(()->restroom.useRestroom("Thread-C"));
        threadA.start();
        threadB.start();
        threadC.start();
    }
}
