package com.bai2;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Thread3 extends Thread {
	ShareData shareData;

    public Thread3() {
        this.shareData = ShareData.getInstance();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Thread1.class.getName()).log(Level.SEVERE, null, ex);
        }
       for(int i= shareData.index(11); i> 0;i--) {
           synchronized (shareData) {
               shareData.notifyAll();
               try {
                   while (shareData.getCurrentThread()!= ShareData.THREAD_3 && shareData.isAlive()) {
                       shareData.wait();
                   }
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }

               int n = shareData.getN();
               if(n%2 !=0) {
                   System.out.println("T3 >> BP " + n + " = " + n * n);
                   System.out.println("T3 stop");
               }
               shareData.setCurrentThread(ShareData.THREAD_1);
           }

       }

    }

}
