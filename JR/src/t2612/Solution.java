package t2612;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Solution {
    private Lock lock = new ReentrantLock();

    public void someMethod() {
        //implement logic here, use the lock field
    }

    public void ifLockIsFree() {
    }

    public void ifLockIsBusy() {
    }
}
