package t2805;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by DELL on 11/12/2017.
 */
public class MyThread extends Thread {
    public static AtomicInteger priority = new AtomicInteger(1);
    public MyThread() {
        if(priority.get()<=10){
            setPriority(priority.getAndIncrement());
            if(priority.get() > 10)
                priority.set(1);
        }
    }

    public MyThread(Runnable target) {
        super(target);
        if(priority.get()<=10){
            setPriority(priority.getAndIncrement());
            if(priority.get() > 10)
                priority.set(1);
        }
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        if(priority.get()<=10){
            setPriority(priority.getAndIncrement());
            if(priority.get() > 10)
                priority.set(1);
        }
    }

    public MyThread(String name) {
        super(name);
        if(priority.get()<=10){
            setPriority(priority.getAndIncrement());
            if(priority.get() > 10)
                priority.set(1);
        }
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        if(priority.get()<=10){
            setPriority(priority.getAndIncrement());
            if(priority.get() > 10)
                priority.set(1);
        }
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        if(priority.get()<=10){
            setPriority(priority.getAndIncrement());
            if(priority.get() > 10)
                priority.set(1);
        }
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        if(priority.get()<=10){
            setPriority(priority.getAndIncrement());
            if(priority.get() > 10)
                priority.set(1);
        }
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        if(priority.get()<=10){
            setPriority(priority.getAndIncrement());
            if(priority.get() > 10)
                priority.set(1);
        }
    }
}
