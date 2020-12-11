package com.kevin.base.thread;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author kevin.yang
 */
public class TraditionalTimerTest {
    private static int count = 0;

    public static void main(String[] args) throws Exception {
        new Timer().schedule(bombing(), 2000);
        while (true) {
            System.out.println(new Date().getSeconds());
            Thread.sleep(1000);
        }
    }

    public static TimerTask bombing() {
        return new TimerTask() {
            @Override
            public void run() {
                count = (count + 1) % 2;
                System.out.println("bombing!");
                new Timer().schedule(bombing(), 2000 + 2000 * count);
            }
        };
    }
}
