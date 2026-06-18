public class DeadLockDemo {
    // our two shared lock
    private static final Object lock1 = new Object();
    private  static final Object lock2 = new Object();
    static void main() {
     // Thread A lock 1 , then want 2;
        Thread threadA = new Thread(()->{
            synchronized (lock1){
                System.out.println("Thread A is holding a lock 1: ");
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Thread A is waiting for lock 2");
                synchronized (lock2){
                    System.out.println("Thread A : acquires lock 1 & 2");
                }
            }
        });
        // Thread B lock 2 , want 1 ;
        Thread threadB = new Thread(()->{
            synchronized (lock1){
                System.out.println("Thread B : is Holding back lock 2");
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Thread B is waiting for lock 1");
                synchronized (lock1){
                    System.out.println("Thread B is acquiring lock 1 and 2");
                }
            }
        });
        threadA.start();
        threadA.start();
    }
}
