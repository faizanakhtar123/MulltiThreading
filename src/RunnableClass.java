class MyTask implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName()+" is running " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class RunnableClass {
    static void main() {
        MyTask task = new MyTask();
        Thread t1 =new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
    }
}
