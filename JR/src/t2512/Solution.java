package t2512;

import java.util.ArrayList;
import java.util.List;

public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
    	t.interrupt();
        List<Throwable> list = new ArrayList<>();

        do {
            list.add(e);
            e = e.getCause();
        } while (e != null);

        for (int i = list.size()-1; i >= 0; i--) {
            System.out.println(list.get(i).fillInStackTrace());
        }
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
