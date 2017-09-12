package t2512;

public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
    }

    public static void main(String[] args) {
        Thread thread = new Thread(){
            public void run(){
                try{
                    throw new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI")));
                } catch (Exception e){
                    getUncaughtExceptionHandler().uncaughtException(currentThread(), e);
                }
            }
        };
        thread.setUncaughtExceptionHandler(new Solution());
        thread.start();
    }
}
