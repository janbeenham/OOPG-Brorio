package nl.han.ica.brorio;

import com.sun.prism.image.ViewPort;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.Tile;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileMap;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileType;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import processing.core.PImage;

import java.awt.*;


public class Launcher extends GameEngine {

    View view;
    ViewPort viewPort;
    private int width = 1000, height = 700;

    public static void main(String args[]) {
        Launcher launcher = new Launcher();
        launcher.runSketch();

    }

    @Override
    public void setupGame() {
        view = new View(width, height);
        setView(view);
        size(width, height);
        initializeMap();
        createObjects();

    }

    @Override
    public void update() {
        view.setBackground(0, 0, 255);



    }
    private void createObjects(){
       Player player = new Player(this);
       addGameObject(player,200,600);
    }

    private void initializeMap() {
        Sprite sprite_sheet = new Sprite("src/main/java/nl/han/ica/brorio/resources/textures/boards.jpg");
        Tile tile = new Tile(sprite_sheet);
        tile.setSpriteSize(64);
        TileType<BoardsTile> boardTileType = new TileType<>(BoardsTile.class, sprite_sheet);

        TileType[] tileTypes = { boardTileType };
        int tileSize=64;
        int tilesMap[][]={
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,0,0,0,1,1,1,1,1},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
        };
        tileMap = new TileMap(tileSize, tileTypes, tilesMap);
    }


    }
