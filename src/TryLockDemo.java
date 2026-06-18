import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class serverTask{
  private final Lock lock = new ReentrantLock();
  public void performTask(String ThreadName){
      if(lock.tryLock(2, TimeUnit.SECONDS)){
          try{
              System.out.println(ThreadName + " acquired the lock !... working");
              Thread.sleep(5000);
          } catch (InterruptedException e) {
              throw new RuntimeException(e);
          }
          finally {
              lock.unlock();
              System.out.println(ThreadName + "release the lock...");
          }
      }
      else {
          System.out.println(ThreadName + "we failed to acquiring the lock...");
      }
  }
}

public class TryLockDemo {

}
