class MyTask1 implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" is running with a priority " + Thread.currentThread().getPriority());
    }
}
public class PriorityDemo {
    static void main() {
        Thread t1 = new Thread(new MyTask1(), "Low Priority");
        Thread t2 = new Thread(new MyTask1(), "Mid Priority");
        Thread t3 = new Thread(new MyTask1(), "High Priority");
        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.NORM_PRIORITY);
        t3.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        t2.start();
        t3.start();
    }
}
