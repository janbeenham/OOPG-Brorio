package nl.han.ica.brorio;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Exceptions.TileNotFoundException;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import processing.core.PVector;

import java.util.List;

import static javafx.util.Duration.millis;

public class Player extends AnimatedSpriteObject implements ICollidableWithTiles,ICollidableWithGameObjects{
    /**
     * Create a new AnimatedSpriteObject with a Sprite and set the amount of total frames.
     *
     * @param sprite
     * @param totalFrames
     */

    private Launcher launcher;
    public Player(Launcher launcher) {
        super(new Sprite("src/main/java/nl/han/ica/brorio/resources/textures/newPlayerSheet.png"),18);
        this.launcher=launcher;
        setCurrentFrameIndex(9);
        setFriction(0.05f);
        setGravity(0.05f);
    }

    @Override
    public void update() {

    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {

    }

    @Override
    public void tileCollisionOccurred(List<CollidedTile> collidedTiles)  {
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
                if (ct.collisionSide == ct.RIGHT) {
                    try {
                        vector = launcher.getTileMap().getTilePixelLocation(ct.theTile);
                        launcher.getTileMap().setTile((int) vector.x / 50, (int) vector.y / 50, -1);
                    } catch (TileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    private void animation(int startIndex,int aantalFrames){
        //test
        for(int i = startIndex;i<aantalFrames; i ++  ){
        setCurrentFrameIndex(i);
        }
    }

    @Override
    public void keyPressed(int keyCode, char key) {
        final int speed = 5;
        if (keyCode == launcher.LEFT) {
            setDirectionSpeed(270, speed);
            setCurrentFrameIndex(0);
        }
        if (keyCode == launcher.UP) {
            setDirectionSpeed(0, speed);
            setCurrentFrameIndex(0);
        }
        if (keyCode == launcher.RIGHT) {
            setDirectionSpeed(90, speed);
            setCurrentFrameIndex(1);
        }
        if (keyCode == launcher.DOWN) {
            setDirectionSpeed(180, speed);
            setCurrentFrameIndex(0);
        }
        if (key == ' ') {
            System.out.println("Spatie!");

            setCurrentFrameIndex(0);
        }
    }
}
