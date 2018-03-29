package nl.han.ica.brorio.entities.creatures;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.brorio.Launcher;

public class Enemy2 extends Enemy {
    public Enemy2(Launcher launcher) {
        super(launcher, new Sprite("src/main/java/nl/han/ica/brorio/resources/textures/Enemy/enemy2.png"), 3,3);

    }


    @Override
    public String enemyName() {
        return "Enemy2";
    }
}
