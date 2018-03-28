package nl.han.ica.brorio.entities;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.brorio.Launcher;
import nl.han.ica.brorio.gfx.Timer;

public class Player extends Creature {
    /**
     * Create a new AnimatedSpriteObject with a Sprite and set the amount of total frames.
     *
     * @param sprite
     * @param totalFrames
     */

    private Launcher launcher;

    private int health;
    boolean pause = false;
    public Player(Launcher launcher) {
        this(launcher, Creature.DEFAULT_FRICTION, Creature.DEFAULT_GRAVITY, Creature.DEFAULT_HEALTH);
    }

    public Player(Launcher launcher, int health) {
        this(launcher, Creature.DEFAULT_FRICTION, Creature.DEFAULT_GRAVITY, health);
    }

    public Player(Launcher launcher, float friction, float gravity, int health) {
        super(launcher, new Sprite("src/main/java/nl/han/ica/brorio/resources/textures/brorio/brorio.png"), 6, health);
        setCurrentFrameIndex(9);
        setFriction(0.05f);
        setGravity(0.5f);
        this.launcher = launcher;

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
        //game pause
        if (key == 'p') {
            pause = !pause;
        }
        if (pause){
            launcher.pauseGame();
        }else if(!pause){
            launcher.resumeGame();
        }
    }

    @Override
    public void hurt(float tick, int damage) {
        Timer timer = new Timer(tick);
        if (timer.hasFinished()) {
            timer.resetTimer();
            health = health - damage;
            System.out.println(health);
        }
    }


    @Override
    public void die() {

        System.out.println("You died");
    }
}
