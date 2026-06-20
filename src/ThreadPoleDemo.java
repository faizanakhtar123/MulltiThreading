import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoleDemo {
    static void main() {
        // 1. create a manager and hired 3 workers
        ExecutorService executor = Executors.newFixedThreadPool(3);
        //2. submit 5 task to the manager
        for (int i = 0; i <5; i++) {
            int taskNumber = i;
            // using lambda to define the runnable task
            executor.submit(()-> {
                System.out.println(Thread.currentThread().getName() + " is processing task..");
                try{
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

        }
        executor.shutdown();

    }
}
