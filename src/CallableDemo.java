import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableDemo {
    static void main()  throws ExecutionException , InterruptedException  {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        // submit a callable task (Notice  we use submit not execute()
        Future<Integer> futureResult = executor.submit(()->{
            System.out.println("Calculating massive dataset :..");
            try {
                Thread.sleep(2000); // Heavy work
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 1000; // return the actual value
        });
        System.out.println("Main thread is doing other thing while calculating run");
        // Warning calling .get() is Blocking call;
        // The main thread will freeze here unit the calculation is 100% finished.
        // this
        Integer finalResult = futureResult.get();
        System.out.println("The result is "+ finalResult);
        executor.shutdown();
    }
}
