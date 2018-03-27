package nl.han.ica.brorio.entities;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.brorio.Launcher;
import nl.han.ica.brorio.entities.Entity;

public abstract class Item extends Entity {

    private Launcher launcher;

    public Item(Launcher launcher, int itemId) {
        super(launcher,new Sprite("src/main/java/nl/han/ica/brorio/resources/textures/items/itemsSprite.png"), 3);
        setCurrentFrameIndex(itemId);
        this.launcher = launcher;
        setGravity(0.05f);
    }


    @Override
    public void update() {

    }

    @Override
    public abstract void die();
    public abstract boolean remove();

}