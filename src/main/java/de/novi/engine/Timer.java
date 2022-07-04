package de.novi.engine;

public class Timer {
    private double lastLoopTime;

    public static final int TARGET_FPS = 75;
    public static final int TARGET_UPS = 30;

    
    public void initTime() {
        lastLoopTime = getTime();
    }

    public double getTime() {
        return System.nanoTime() / 1_000_000_000.0;
    }

    public float getElapsedTime() {
        double time = getTime();
        float elapsedTime = (float) (time - lastLoopTime);
        lastLoopTime = time;
        return elapsedTime;
    }

    public double getLastLoopTime() {
        return lastLoopTime;
    }

    public void sync() {
        float loopSlot = 1f / TARGET_FPS;
        double endTime = getLastLoopTime() + loopSlot; 
        while(getTime() < endTime) {
 
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {

            }
        }
     }

}
