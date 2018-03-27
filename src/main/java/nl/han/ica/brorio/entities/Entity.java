package nl.han.ica.brorio.entities;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Exceptions.TileNotFoundException;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.brorio.BoardsTile;
import nl.han.ica.brorio.Launcher;
import processing.core.PVector;

import java.util.List;

public abstract class Entity extends AnimatedSpriteObject implements ICollidableWithTiles, ICollidableWithGameObjects {
    private int x, y, width, height;
    private Launcher launcher;
    private boolean interaction = false;

    public Entity(Launcher launcher, Sprite sprite, int totalFrames) {
        super(sprite, totalFrames);
        this.launcher = launcher;
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        PVector vector;
        for (GameObject go : collidedGameObjects) {
            if (go.getX() == (go.getX()) && go.getY() == go.getY()) {
                interaction = true;
                die();

            }
        }
    }

    public boolean getInteraction() {
        return interaction;
    }

    @Override
    public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
        PVector vector;

        for (CollidedTile ct : collidedTiles) {
            if (ct.theTile instanceof BoardsTile) {

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
