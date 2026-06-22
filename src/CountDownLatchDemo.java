import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    static void main() throws InterruptedException {
        // 1. create a latch that requires 3 task to finish
        CountDownLatch startupLatch = new CountDownLatch(3);
        // 2. start background services
        new Thread (new ServiceStarted("Databae" , startupLatch)).start();
        new Thread (new ServiceStarted("Redis Cache" , startupLatch)).start();
        new Thread (new ServiceStarted("Kafka Queue" , startupLatch)).start();
        System.out.println("Main Server: Waiting for all services to boot");
        //3. The wait: The main Thread freezes rights here until count hits 0;
        startupLatch.await();
        //4. The SUCCESS: This only prints when all 3 services are done
        System.out.println("Main Server: All services are UP. Accepting traffic");
    }
}
