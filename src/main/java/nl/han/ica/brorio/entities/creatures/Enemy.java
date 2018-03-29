package nl.han.ica.brorio.entities.creatures;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.brorio.Launcher;
import nl.han.ica.brorio.entities.Entity;
import nl.han.ica.brorio.gfx.Timer;

public abstract class Enemy extends Creature{
    private Timer timer;
    private Launcher launcher;

    public Enemy(Launcher launcher, Sprite sprite, int totalFrames) {
        this(launcher, sprite, totalFrames,Creature.DEFAULT_HEALTH);
    }
    public Enemy(Launcher launcher,Sprite sprite,int totalFrames,int health){
        super(launcher, sprite,Creature.DEFAULT_FRICTION,Creature.DEFAULT_GRAVITY, totalFrames,health);
        timer=new Timer(0.1f);
        this.launcher=launcher;

        setZ(-1f);
    }
    public void move(){
        if(timer.hasFinished()){
            timer.resetTimer();
            nextFrame();
        }
    }

    @Override
    public void die() {
        launcher.deleteGameObject(this);
    }

    @Override
    public int getHealth() {
        return super.getHealth();
    }

    public abstract String enemyName();


}
