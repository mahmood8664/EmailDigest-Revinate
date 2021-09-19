package com.revinate.emaildigest.lock;

/**
 * @author mahmood
 * @since 9/19/21
 */
public class LockProvider {

    public static void doWithLock(String lockName, Runnable runnable) {
        try {
            //todo: Here we need to provide distributed lock with key for example redis lock
            runnable.run();
        } finally {
            //todo: Release lock here
        }

    }

}
