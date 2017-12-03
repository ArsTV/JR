package t2806;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Solution {

    public static void main(String[] args) throws InterruptedException {
        //Add your code here

        ExecutorService service = Executors.newFixedThreadPool(5);

        for (int i = 1; i < 11; i++) {
            final int j = i;
            Thread t = new Thread(new Runnable() {

                @Override
                public void run() {
                    doExpensiveOperation(j);
                }
            });

            service.submit(t);
        }

        service.awaitTermination(5,TimeUnit.SECONDS);
        service.shutdown();

        /* output example
pool-1-thread-2, localId=2
pool-1-thread-1, localId=1
pool-1-thread-3, localId=3
pool-1-thread-1, localId=7
pool-1-thread-1, localId=9
pool-1-thread-4, localId=4
pool-1-thread-5, localId=5
pool-1-thread-2, localId=6
pool-1-thread-1, localId=10
pool-1-thread-3, localId=8
         */
    }

    private static void doExpensiveOperation(int localId) {
        System.out.println(Thread.currentThread().getName() + ", localId="+localId);
    }
}