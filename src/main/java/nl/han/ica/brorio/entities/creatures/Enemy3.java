package nl.han.ica.brorio.entities.creatures;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.brorio.Launcher;

public class Enemy3 extends Enemy {
    public Enemy3(Launcher launcher) {
        super(launcher, new Sprite("src/main/java/nl/han/ica/brorio/resources/textures/Enemy/enemy3.png"), 8,3);
        move();
    }


    @Override
    public void move() {
        super.move();
//        System.out.println(getHealth());
    }

    @Override
    public String enemyName() {
        return "Enemy3";
    }
}
