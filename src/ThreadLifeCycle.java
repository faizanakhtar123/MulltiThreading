import com.sun.jdi.ThreadGroupReference;

public class ThreadLifeCycle {
    private static final Object lock = new Object();

    static void main() throws InterruptedException {
        Thread t1 =  new Thread(()->{
            synchronized (lock){
                try{
                    Thread.sleep(1000);
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println("1. State right after creation -> : "+ t1.getState());
        t1.start();
        System.out.println("2. State right after start() -> :"+t1.getState());
        Thread.sleep(200);
        System.out.println("3. State right after sleep -> : " +t1.getState());
        Thread.sleep(1000);
        System.out.println("4. State right after calls wait() "+t1.getState());
        Thread t2 =new Thread(()->{
            synchronized (lock){
                lock.notify();
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t2.start();
        Thread.sleep(100);
        System.out.println("5. State when t1 tries to get the lock back -> :"+ t1.getState());
         t1.join();
         t2.join();
        System.out.println("5. State after t1 finish execution " + t1.getState());

    }
}
