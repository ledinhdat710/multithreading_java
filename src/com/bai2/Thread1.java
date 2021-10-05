package com.bai2;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Thread1 extends Thread {
	ShareData shareData;
    public Thread1() {
        this.shareData = ShareData.getInstance();
    }
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Thread1.class.getName()).log(Level.SEVERE, null, ex);
        }
        FileReader reader = null;
        BufferedReader bufferedReader = null;

        try {
            reader = new FileReader("number.txt");
            bufferedReader = new BufferedReader(reader);


            synchronized (shareData) {
                String line = null;
                while ((line = bufferedReader.readLine())!= null) {
                    line = line.trim();
                    int n = Integer.parseInt(line);
                    shareData.setN(n);
                    if(n%2==0) {
                        shareData.setCurrentThread(ShareData.THREAD_2);
                    } else {
                        shareData.setCurrentThread(ShareData.THREAD_3);
                    }
                    shareData.notifyAll();
                    while (shareData.getCurrentThread() != ShareData.THREAD_1 && shareData.isAlive()) {
                        shareData.wait();
                        Thread.sleep(1000);
                    }
                }
            }
            System.out.println("T1 stop");
            synchronized (shareData) {
                shareData.notifyAll();
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        } finally {
            if(bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

}
