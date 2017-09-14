package t2514;

public class Solution {
    public static class YieldRunnable implements Runnable {
        private int index;

        public YieldRunnable(int index) {
            this.index = index;
        }

        public void run() {
            System.out.println("begin-" + index); 
            Thread.yield();
            System.out.println("end-" + index);
        }
    }

    public static void main(String[] args) {
        Thread t = new Thread(new YieldRunnable(1));
        t.start();
    }
}