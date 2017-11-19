package t2903;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ThreadLocalRandom;

public class Solution {
    public static final ThreadLocalRandom random = ThreadLocalRandom.current();

    public static void main(String[] args) {
        ConcurrentMap<Integer, String> concurrentMap = new ConcurrentHashMap<>();
        for (int i = 0; i < 100; i++) {
            new Thread(getRunnable(i, concurrentMap)).start();
        }
        sleepASecond();
    }

    private static void sleepASecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Runnable getRunnable(final int i, final ConcurrentMap<Integer, String> concurrentMap) {
        return new Runnable() {
            @Override
            public void run() {
                final String name = "Thread #" + i;
                int randomInt = random.nextInt(20);
                String text = name + " putted text for " + randomInt;

                
                String previousEntry = null;
                concurrentMap.putIfAbsent(randomInt, text);

                if (previousEntry != null) {
                    System.out.println(name + " wants to update " + randomInt + ", but already " + previousEntry);
                } else {
                    System.out.println(text);
                }
            }
        };
    }
}
