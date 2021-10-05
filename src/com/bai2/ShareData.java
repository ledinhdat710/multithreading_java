package com.bai2;

public class ShareData {
	int n;
    int count;


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public static final int THREAD_1 = 1;
    public static final int THREAD_2 = 2;
    public static final int THREAD_3 = 3;

    int currentThread = THREAD_1;

    public int getCurrentThread() {
        return currentThread;
    }

    public void setCurrentThread(int currentThread) {
        this.currentThread = currentThread;
    }

    private static ShareData instance = null;

    public synchronized static ShareData getInstance() {
        if(instance == null ) {
            instance = new ShareData();
        }
        return instance;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }
    public synchronized int index (int value) {
        return value;
    }
    public synchronized boolean isAlive() {
        return count >= 0;
    }

}
