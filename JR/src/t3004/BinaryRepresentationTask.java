package t3004;

import java.util.concurrent.RecursiveTask;

/**
 * Created by DELL on 1/18/2018.
 */

public class BinaryRepresentationTask extends RecursiveTask<String> {
    int x;

    public BinaryRepresentationTask(int x) {
        this.x = x;
    }

    @Override
    protected String compute() {
        int a = x % 2;
        int b = x / 2;
        String result = String.valueOf(a);
        if (b > 0) {
            BinaryRepresentationTask task = new BinaryRepresentationTask(b);
            task.fork();
            return task.join() + a;
        }
        return result;
    }
}
