import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GraceFullyShutDownDemo {
    static void main() {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        // submit a bunch of task to the pool....
        //1. stop() accepting a new task.....
        pool.shutdown();
        try {
            // wait upto 60 sec for current task to finish
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                System.out.println("Task took too long...forcing shutDown");
                // 3. Time ran out... force everything to stop immediately;
                pool.shutdown();
                // wait another 60 se for the forced shutDown to take effects
                if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                    System.err.println("Pool did not terminated completely");
                }
            }
        } catch (InterruptedException e) {
            pool.shutdownNow();
            Thread.currentThread().interrupt();
        }
        System.out.println("Is pool fully terminated ? " + pool.isTerminated());
    }
}
