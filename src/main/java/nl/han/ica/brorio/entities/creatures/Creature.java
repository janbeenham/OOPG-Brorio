package nl.han.ica.brorio.entities.creatures;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.brorio.Launcher;
import nl.han.ica.brorio.entities.Entity;
import nl.han.ica.brorio.gfx.Timer;

public abstract class Creature extends Entity {
    protected static final int DEFAULT_HEALTH = 10;
    protected static final float DEFAULT_FRICTION = 0.05f;
    protected static final float DEFAULT_GRAVITY = 0.25f;
    private int health;
    private boolean isAttacked;

    private Timer timer = new Timer(1f);


    public Creature(Launcher launcher, Sprite sprite, int totalFrames) {
        this(launcher, sprite, DEFAULT_FRICTION, DEFAULT_GRAVITY, totalFrames, DEFAULT_HEALTH);
    }

    public Creature(Launcher launcher, Sprite sprite, float friction, float gravity, int totalFrames, int health) {
        super(launcher, sprite, totalFrames);
        this.health = health;
        setFriction(friction);
        setGravity(gravity);

    }

    public int getHealth() {
        return health;
    }

    @Override
    public abstract void die();

    public void hurt(float tick, int damage) {
        timer.setTimer(tick);
        if (health <= 0) {

            die();
        } else {

            if (timer.hasFinished()) {
                isAttacked = true;
                timer.resetTimer();
                health = health - damage;
                System.out.println(health);
            }
        }
    }


    public boolean isAttacked() {
        return isAttacked;
    }
}
