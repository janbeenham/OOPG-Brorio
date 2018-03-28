package nl.han.ica.brorio.entities;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Exceptions.TileNotFoundException;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.brorio.world.BoardsTile;
import nl.han.ica.brorio.Launcher;
import nl.han.ica.brorio.world.DamagingTile;
import nl.han.ica.brorio.world.SolidTile;
import processing.core.PVector;

import java.util.List;

public abstract class Entity extends AnimatedSpriteObject implements ICollidableWithTiles,ICollidableWithGameObjects{
    private int x, y, width, height;
    private Launcher launcher;
    private boolean objectInteraction = false;
    private boolean tileInteraction = false;

    public Entity(Launcher launcher, Sprite sprite, int totalFrames) {
        super(sprite, totalFrames);
        this.launcher = launcher;
    }



    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        PVector vector;
        for (GameObject go : collidedGameObjects) {
            if (go.getX() == (go.getX()) && go.getY() == go.getY()) {
                //Wanneer gameobject een Speler is slaat hij dit stuk over
                if(go instanceof Player) {
                    objectInteraction = true;
                    die();
                }
            }
        }
    }

    public boolean getObjectInteraction() {
        return objectInteraction;
    }
    public boolean getTileInteracton(){
        return tileInteraction;
    }

    @Override
    public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
        PVector vector;

        for (CollidedTile ct : collidedTiles) {
            if (ct.theTile instanceof SolidTile) {

                if (ct.collisionSide >= ct.TOP) {
                    try {
                        vector = launcher.getTileMap().getTilePixelLocation(ct.theTile);
                        setY(vector.y - getHeight());
                    } catch (TileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    @Override
    public void update() {

    }

    public abstract void die();
}
