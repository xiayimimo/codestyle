package com.tycho.codestyle.six;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class LiftOff implements Runnable {
	 
    Logger logger = LoggerFactory.getLogger(LiftOff.class);
 
    protected int countDown = 10; // Default
    private static int taskCount = 0;
 
    private final String tag = "liftoff";
 
    public String getTag() {
        return tag;
    }
 
    private final int id = taskCount++;
    public LiftOff() {}
    public LiftOff(int countDown) {
        this.countDown = countDown;
    }
    public String status() {
        return "#" + id + "(" +
                (countDown > 0 ? countDown : "Liftoff!") + "), ";
    }
    @Override
    public void run() {
        while(countDown-- > 0) {
            logger.info(status());
            Thread.yield();
        }
    }
}
