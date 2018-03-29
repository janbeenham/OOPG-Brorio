package nl.han.ica.brorio.entities.creatures;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.brorio.Launcher;
import nl.han.ica.brorio.entities.Entity;
import nl.han.ica.brorio.gfx.Animations;
import nl.han.ica.brorio.gfx.Timer;
import processing.core.PImage;

public class Enemy1 extends Enemy {
    private Launcher launcher;



    public Enemy1(Launcher launcher) {
        super(launcher, new Sprite("src/main/java/nl/han/ica/brorio/resources/textures/Enemy/enemy1.png"), 4, 3);
        this.launcher = launcher;




    }


    @Override
    public void update() {
        if (getX()+getWidth()<=0) {
            setX(launcher.getWidth());
        }

    }


    @Override
    public String enemyName() {
        return "Enemy1";
    }
}
