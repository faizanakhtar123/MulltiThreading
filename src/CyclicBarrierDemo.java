import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    static void main() {
        // 1. create the barrier action(runs when everyone arrives)
        Runnable mergeDAtaAction = () ->{
            System.out.println(">>> ALL REGIONS ARRIVED ! Merging Phase 1 Data....\n");
        };
        // 2. Create the Barrier for 3 task
        CyclicBarrier barrier = new CyclicBarrier(3,mergeDAtaAction);
        new Thread(new RiskAnalyzer("US Region", barrier)).start();
        new Thread(new RiskAnalyzer("EU Region", barrier)).start();
        new Thread(new RiskAnalyzer("ASIA Region", barrier)).start();
    }
}
class RiskAnalyzer implements Runnable{
    private final String region;
    private final CyclicBarrier barrier;
    public RiskAnalyzer(String region , CyclicBarrier barrier){
        this.region = region;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try{
            //========Phase 1 =======
            System.out.println(region + " is calculating phase 1....");
            Thread.sleep((long) (Math.random()*3000)); // simulate work
            System.out.println(region + "reached the barrier. Waiting");
            // The Wait: Thread stop here until all 3 reach this exact limit..
            barrier.await();
         //======m Phase 2 =====
            // The barrier reset automatically! we can resue it
            System.out.println(region + " is starting phase 1....");
            Thread.sleep((long) (Math.random()*3000));
            System.out.println(region + "reached the second barrier. Waiting");
            barrier.await(); // Reusing the exact same barrier
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
