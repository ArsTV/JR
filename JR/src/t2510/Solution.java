package t2510;

public class Solution extends Thread {
    Thread.UncaughtExceptionHandler uncaughtExceptionHandler = new ExceptionHandler();

    public Solution() {
       
    }
    public static class ExceptionHandler implements UncaughtExceptionHandler{

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            if(e instanceof Error){
                System.out.println("Error");
            } else if(e instanceof Exception){
                System.out.println("Exception");
            } else {
                System.out.println("Throwable");
            }
        }
    }
    
    public static void main(String[] args) {
    }
}
