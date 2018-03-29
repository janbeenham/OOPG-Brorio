package nl.han.ica.brorio.gfx;

import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;

public class Timer {
    private float second;
    private final long WAIT_TIME;
    private long startTime;
    public Timer(float second){
        this.second=second;
        WAIT_TIME = (int) (second * 1000); // 3.5 seconds

    }

    public void resetTimer() {

        if (hasFinished()) {
//            System.out.println(WAIT_TIME / 1e3 + " seconds have transpired!");
            startTime = System.currentTimeMillis();
        }

    }

    public boolean hasFinished() {

        return System.currentTimeMillis() - startTime > WAIT_TIME;
    }

    public void setTimer(float second){
        this.second=second;
    }
}
