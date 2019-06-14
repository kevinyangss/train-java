package com.kevin.base.thread;

/**
 * @author kevin
 */
public class TraditionalSynchronizedTest {
    public static void main(String[] args) {
        final PrintLner printLner = new PrintLner();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 5; i++) {
                    printLner.sub(i);
                }
            }
        }).start();

        for (int i = 1; i <= 5; i++) {
            printLner.main(i);
        }
    }
}

class PrintLner {
    private boolean bShouldSub = true;

    public synchronized void sub(int j) {
        while (!bShouldSub) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 100; i++) {
            System.out.println("sub Thread sequence of :" + j + " do loop:" + i);
        }
        bShouldSub = false;
        this.notify();
    }

    public synchronized void main(int j) {
        while (bShouldSub) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 10; i++) {
            System.out.println("main Thread sequence of :" + j + " do loop:" + i);
        }
        bShouldSub = true;
        this.notify();
    }
}
