package nl.han.ica.brorio.entities.creatures;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.brorio.Launcher;
import nl.han.ica.brorio.entities.items.Item;
import nl.han.ica.brorio.gfx.Animations;
import nl.han.ica.brorio.gfx.Timer;

import java.util.List;

public class Player extends Creature implements ICollidableWithGameObjects{
    /**
     * Create a new AnimatedSpriteObject with a Sprite and set the amount of total frames.
     *
     * @param sprite
     * @param totalFrames
     */
    private int countPressedUp = 0;
    private boolean right= true;
    private boolean isAttacking = false;
    private Launcher launcher;
    private Timer timer;
    private int health;
    boolean pause = false;



    public Player(Launcher launcher) {
        this(launcher, DEFAULT_FRICTION, Creature.DEFAULT_GRAVITY, Creature.DEFAULT_HEALTH);
    }

    public Player(Launcher launcher, int health) {
        this(launcher, DEFAULT_FRICTION, Creature.DEFAULT_GRAVITY, health);
    }

    public Player(Launcher launcher, float friction, float gravity, int health) {

        super(launcher, new Sprite("src/main/java/nl/han/ica/brorio/resources/textures/brorio/brorio2.png"), friction, gravity, 16, health);


        this.launcher = launcher;
        timer = new Timer(0.125f);
        setCurrentFrameIndex(15);


    }

    private void animation(int startFrame, int endFrame) {
        if (timer.hasFinished()) {
            timer.resetTimer();
            if (getCurrentFrameIndex() >= endFrame || getCurrentFrameIndex() < startFrame) {
                setCurrentFrameIndex(startFrame);
            }
            nextFrame();
        }
    }

    @Override
    public void keyPressed(int keyCode, char key) {
        final int speed = 3;


        if (keyCode == launcher.LEFT) {
            animation(6, 9);
            right=false;
            setDirectionSpeed(270, speed);
//            setCurrentFrameIndex(4);

        }
        if (keyCode == launcher.RIGHT) {
            right=true;
            animation(9, 13);

            setDirectionSpeed(90, speed);
//            setCurrentFrameIndex(7);

        }

        if( keyCode == launcher.UP){
            setDirectionSpeed(0, speed);
            setCurrentFrameIndex(15);
        }
        if(key == ' ' ){
            isAttacking=true;
            if(right) {
                animation(0, 2);
            }else {
                animation(3,5);
            }
        }else{
            isAttacking=false;
        }
        //game pause
        if (key == 'p') {
            pause = !pause;
        }
        if (pause) {
            launcher.pauseGame();
        } else if (!pause) {
            launcher.resumeGame();
        }
    }


    @Override
    public void die() {

        System.out.println("You died");

    }


    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {

            for (GameObject go : collidedGameObjects) {
                if (go instanceof Item) {
                    ((Item) go).die();
                }


                if(go instanceof Creature){
//                    hurt(10,1);
                    if(isAttacking) {
                        ((Creature) go).hurt(1, 1);
                    }
                }
            }
    }
}
