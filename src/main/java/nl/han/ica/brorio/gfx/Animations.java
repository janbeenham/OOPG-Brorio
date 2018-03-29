package nl.han.ica.brorio.gfx;

import nl.han.ica.brorio.Launcher;
import processing.core.PImage;

import java.util.ArrayList;

public class Animations {
    private Timer timer = new Timer(1f);
    private Launcher launcher;
    private String path;
    private int totalFrames, currentX;
    private PImage currentFrame;
    private PImage initialFrame;
    private int counter = 0;
    private int initFrame;
    private int stopFrame;

    private ArrayList<PImage> animations = new ArrayList<>();

    public Animations(Launcher launcher, String path, int totalFrames) {

        this.launcher = launcher;
        this.path = path;
        this.totalFrames = totalFrames;
        initialFrame = launcher.loadImage(path);
        currentFrame = launcher.loadImage(path);


    }


    private int frameWidth() {
        return currentFrame.width / totalFrames;
    }



    private ArrayList<PImage> loopAnimations() {

        int currentY = 0;
        int currentWidth = frameWidth();
        int currentHeight = currentFrame.height;
        for (int i = 0; i < totalFrames; i++) {
            currentFrame = initialFrame.get(currentX(), currentY, currentWidth, currentHeight);
            animations.add(currentFrame);
        }
        return animations;


    }

    public PImage getAnimation(int initFrame, int stopFrame) {
        PImage animation = new PImage();
        for (int i = initFrame; i < stopFrame; i++) {
            animation = loopAnimations().get(i);
        }
        return animation;
    }

    public PImage animation() {
        return loopAnimations().get(counter());
    }

    private int currentX() {

        currentX = currentX + frameWidth() * counter();
        return currentX;
    }

    private int counter() {

        if (counter > totalFrames - 1) {
            counter = 0;
        }
        if (timer.hasFinished()) {

            timer.resetTimer();
            return counter++;
        }
        return counter;
    }


}