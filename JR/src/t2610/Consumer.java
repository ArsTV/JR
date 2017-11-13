package t2610;

import java.util.concurrent.BlockingQueue;

/**
 * Created by DELL on 11/12/2017.
 */
public class Consumer implements Runnable {
    private BlockingQueue queue;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            int i = 0;
            while (true) {
                System.out.print(queue.take() + " ");
                Thread.sleep(300);
            }
        } catch (InterruptedException e) {
            System.out.println();
            System.out.println(String.format("[%s] thread was terminated", Thread.currentThread().getName()));
        }
    }
}
