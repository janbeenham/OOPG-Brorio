package nl.han.ica.brorio;

import com.sun.prism.image.ViewPort;
import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.FPSCounter;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.TextObject;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.Tile;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileMap;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileType;
import nl.han.ica.OOPDProcessingEngineHAN.View.EdgeFollowingViewport;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.brorio.entities.*;

import nl.han.ica.brorio.world.World;
import processing.core.PImage;

import java.util.ArrayList;


public class Launcher extends GameEngine {
    //display settings
    private boolean viewPortEnabled = false;
    private int backgroundWidth = 1280, backgroundHeight = 720;
    private int width = 1280, height = 720;
    //dashboard
    private TextObject dashboardText;
    private Dashboard dashboard;
    private FPSCounter fpsCounter;

    //sounds
    private Sound backgroundSound;

    private ArrayList<Entity> entities = new ArrayList<>();

    private View view;
    private ViewPort viewPort;

    private Timer timer = new Timer(1f);


    private int counter = 0;
    private Player player = new Player(this);

    private Animations animatedBackground = new Animations(this, "src/main/java/nl/han/ica/brorio/resources/textures/background/backgroundSprite.png", 12);

    //Tiles
    World world = new World(this);
    Sprite lavaSprite;
    private Animations animatedLavaTiles = new Animations(this,"src/main/java/nl/han/ica/brorio/resources/textures/broriotiles/lava.png",3);
    public static void main(String args[]) {
        Launcher launcher = new Launcher();
        launcher.runSketch();

    }

    @Override
    public void setupGame() {
        world.initializeMap();
        tileMap = world.getTileMap();
        view = new View(backgroundWidth, backgroundHeight);


        setView(view);
        size(width, height);




        createDashboard(width, 100);



        initializeSound();
        createObjects();


    }

    @Override
    public void update() {
        if (viewPortEnabled) {
            createViewWithViewport(backgroundWidth, backgroundHeight, width, height, 1.1f);
        } else {
            createViewWithoutViewport(backgroundWidth, backgroundHeight);
        }
        world.updateMapTiles();
        deleteObjects();
        refreshDasboardText();
    }

    private void initializeSound() {
        backgroundSound = new Sound(this, "src/main/java/nl/han/ica/brorio/resources/sounds/geluid.mp3");
        backgroundSound.loop(-1);
    }

    private void createViewWithoutViewport(int screenWidth, int screenHeight) {
        View view = new View(screenWidth, screenHeight);
        view.setBackground(animatedBackground.animation(),screenWidth,screenHeight);
        setView(view);
        size(screenWidth, screenHeight);
    }

    private void createViewWithViewport(int worldWidth, int worldHeight, int screenWidth, int screenHeight, float zoomFactor) {
        EdgeFollowingViewport viewPort = new EdgeFollowingViewport(player, (int) Math.ceil(screenWidth / zoomFactor), (int) Math.ceil(screenHeight / zoomFactor), 0, 0);
        viewPort.setTolerance(50, 50, 50, 50);
        View view = new View(viewPort, worldWidth, worldHeight);
        setView(view);
        size(screenWidth, screenHeight);
        view.setBackground(animatedBackground.animation(), screenWidth, screenHeight);

    }

    private void createDashboard(int dashboardWidth, int dashboardHeight) {
        Dashboard dashboard = new Dashboard(0, 0, dashboardWidth, dashboardHeight);
        dashboardText = new TextObject("hello", 11);

        dashboard.addGameObject(dashboardText);
        addDashboard(dashboard);
    }

    private void refreshDasboardText() {
        dashboardText.setText("Bfoewflewemflwe'mf");
    }


    private void createObjects() {


        Item kaas2 = new Kaas(this);
        Item kaas = new Kaas(this);
        Item tomaat = new Tomaat(this);
        Item tomaat2 = new Tomaat(this);
        Item pizzaBodem = new PizzaBodem(this);
        Item pizzaBodem2 = new PizzaBodem(this);
        entities.add(player);
        entities.add(kaas);
        entities.add(tomaat);
        entities.add(pizzaBodem);
        entities.add(kaas2);
        entities.add(tomaat2);
        entities.add(pizzaBodem2);

        for (int i = 0; i < entities.size(); i++) {
            addGameObject(entities.get(i), 100 * i, 700);
        }
    }

    private void deleteObjects() {
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i) instanceof Item) {
                if (((Item) entities.get(i)).remove()) {
                    deleteGameObject(entities.get(i));
                }
            }


        }
    }
//    private void updateMapTiles(){
//        lavaSprite.setSprite(animatedLavaTiles.animation());
//    }
//
//    private void initializeMap() {
//        Sprite sprite_sheet = new Sprite("src/main/java/nl/han/ica/brorio/resources/textures/broriotiles/Image20000.png");
//        Animations lava = new Animations(this,"src/main/java/nl/han/ica/brorio/resources/textures/broriotiles/lava.png",3);
//
//        lavaSprite = new Sprite(animatedLavaTiles.animation());
//
//        Tile tile = new Tile(sprite_sheet);
//        tile.setSpriteSize(64);
//        TileType<BoardsTile> lavaTileType = new TileType<>(BoardsTile.class, lavaSprite);
//        TileType<BoardsTile> boardsTileTileType = new TileType<>(BoardsTile.class, sprite_sheet);
//
//        TileType[] tileTypes = {lavaTileType,boardsTileTileType};
//        int tileSize = 64;
//        int tilesMap[][] = {
//                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
//                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
//                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
//                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
//                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
//                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
//                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
//                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
//                {-1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
//                {-1, -1, -1, -1, -1, -1, -1, -1, 1, 1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
//                {1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
//                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
//        };
//        tileMap = new TileMap(tileSize, tileTypes, tilesMap);
//    }


}
