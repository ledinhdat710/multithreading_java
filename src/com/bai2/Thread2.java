package com.bai2;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Thread2 extends Thread {
	ShareData shareData;

    public Thread2() {
        this.shareData = ShareData.getInstance();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Thread1.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i = shareData.index(11); i>0 ; i--) {
            synchronized (shareData) {
                shareData.notifyAll();
                try {
                    while (shareData.getCurrentThread()!= ShareData.THREAD_2 && shareData.isAlive()) {
                        shareData.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int n = shareData.getN();
                if(n%2==0) {
                    System.out.print("T2 >> "+ n  +"= ");
                    for(int j =1; j<=n;j++) {
                        if(n%j==0) {
                            System.out.print(j + " ");
                        }
                    }
                    System.out.println();
                    System.out.println("T2 stop");
                }
                shareData.setCurrentThread(ShareData.THREAD_1);
            }

        }


    }

}
