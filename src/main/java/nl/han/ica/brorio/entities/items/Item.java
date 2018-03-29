package nl.han.ica.brorio.entities.items;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;
import nl.han.ica.brorio.Launcher;
import nl.han.ica.brorio.entities.Entity;

public abstract class Item extends Entity {
private Sound pickUpSound;
    private Launcher launcher;

    private boolean isActive = true;

    public Item(Launcher launcher, int itemId) {
        super(launcher, new Sprite("src/main/java/nl/han/ica/brorio/resources/textures/items/itemsSprite.png"), 3);
        setCurrentFrameIndex(itemId);
        this.launcher = launcher;
        pickUpSound = new Sound(launcher, "src/main/java/nl/han/ica/brorio/resources/sounds/17750__krisboruff__picking-up-tacklebox.mp3");



    }



    @Override
    public void update() {


    }



    @Override
    public void die() {

               pickUpSound.play();
        isActive = false;
        launcher.getEntities().remove(this);
//        launcher.deleteGameObject(this);


    }

    public boolean getIsActive() {
        return isActive;
    }


}
