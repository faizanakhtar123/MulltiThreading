import javax.swing.*;

class BankAccount {
    private int balance = 1000;
    public void withdrewUnsafe(int amount){
        System.out.println(Thread.currentThread().getName()+" is checking balance");
        if(balance>=amount) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            balance-=amount;
            System.out.println(Thread.currentThread().getName()+" successfully withdrew.."+amount);
        }
        else {
            System.out.println(Thread.currentThread().getName()+" failed : insufficient balance");
        }

     }
     public void withdrewSafe(int amount) {
         System.out.println(Thread.currentThread().getName() + " is attempting to secure withdrew ");
         synchronized (this) {
             if (balance >= amount) {
                 try {
                     Thread.sleep(100);
                 } catch (InterruptedException e) {
                     throw new RuntimeException(e);
                 }
                 balance -= amount;
                 System.out.println(Thread.currentThread().getName() + " successfully withdrew.." + amount);
             } else {
                 System.out.println(Thread.currentThread().getName() + " Failed : insufficient balance");
             }
         }
     }
    public int getBalance(){
        return balance;
    }
}
public  class Synchronization {
    static void main() {
        BankAccount sharedAccount = new BankAccount();
        Runnable withdrewTask = () -> {
            sharedAccount.withdrewUnsafe(800);
        };
        Thread threadA = new Thread(withdrewTask , "Thread-A (Mobile App)");
        Thread threadB = new Thread(withdrewTask, "Thread-B (WEB-Dashboard)");
        threadA.start();
        threadB.start();
    }
}
