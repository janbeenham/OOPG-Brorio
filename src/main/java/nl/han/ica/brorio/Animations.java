package nl.han.ica.brorio;

import processing.core.PImage;

import java.util.ArrayList;

public class Animations {
    private Timer timer;
    private Launcher launcher;
    private String path;
    private int totalFrames, currentX;
    private PImage currentFrame;
    private PImage initialFrame;

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

    public ArrayList<PImage> loopAnimations() {

        int currentY = 0;
        int currentWidth = frameWidth();
        int currentHeight = currentFrame.height;
        for(int i = 0; i<totalFrames; i++) {
            currentFrame = initialFrame.get(currentX(i), currentY, currentWidth, currentHeight);
            animations.add(currentFrame);
        }
        return animations;



    }

    private int currentX(int counter) {

        currentX = currentX + frameWidth() * counter;
        return currentX;
    }


}