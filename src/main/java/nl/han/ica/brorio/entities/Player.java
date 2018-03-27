package nl.han.ica.brorio.entities;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.brorio.Launcher;
import nl.han.ica.brorio.entities.Entity;

import java.util.ArrayList;

public class Player extends Entity {
    /**
     * Create a new AnimatedSpriteObject with a Sprite and set the amount of total frames.
     *
     * @param sprite
     * @param totalFrames
     */

    private Launcher launcher;
    public Player(Launcher launcher) {
        super(launcher,new Sprite("src/main/java/nl/han/ica/brorio/resources/textures/brorio/brorio.png"),6);
        this.launcher=launcher;

        setCurrentFrameIndex(9);
        setFriction(0.05f);
        setGravity(0.5f);
    }




    @Override
    public void keyPressed(int keyCode, char key) {
        final int speed = 5;
        if (keyCode == launcher.LEFT) {
            setDirectionSpeed(270, speed);
            setCurrentFrameIndex(4);
        }
        if (keyCode == launcher.RIGHT) {
            setDirectionSpeed(90, speed);
            setCurrentFrameIndex(0);
        }
        if (key == ' ') {
            setDirectionSpeed(0, speed);

            setCurrentFrameIndex(2);
        }
    }

    @Override
    public void die() {

    }
}