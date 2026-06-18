import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class ThreadSafeCache{
    private final Map<String , String> cache = new HashMap<>();
    //1. creating read write lock
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    // 2 read method fast and concurrent
    public String get(String key){
        // grab the specific read lock
        rwLock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + " is reding...");
            Thread.sleep(50);
            return cache.get(key);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            // ALWAYS unlock in final
            rwLock.readLock().unlock();
        }

    }
    public void put(String key , String value){
        rwLock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + " is writing....");
            Thread.sleep(100);
            cache.put(key , value);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finally {
            rwLock.writeLock().unlock();
        }
    }

}

public class readWriteLock {

}
