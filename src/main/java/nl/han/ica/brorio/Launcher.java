package nl.han.ica.brorio;

import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.FPSCounter;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.TextObject;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.brorio.entities.*;

import nl.han.ica.brorio.entities.creatures.*;
import nl.han.ica.brorio.entities.items.Item;
import nl.han.ica.brorio.entities.items.Kaas;
import nl.han.ica.brorio.entities.items.PizzaBodem;
import nl.han.ica.brorio.entities.items.Tomaat;
import nl.han.ica.brorio.gfx.Animations;
import nl.han.ica.brorio.gfx.Display;
import nl.han.ica.brorio.gfx.Timer;
import nl.han.ica.brorio.world.World;

import java.util.ArrayList;


public class Launcher extends GameEngine {

    private View view;
    //display settings
    private boolean viewPortEnabled = false;
    private int backgroundWidth = 1280, backgroundHeight = 720;
    private int width = 1280, height = 720;
    //dashboard
    private TextObject dashboardText;
    private TextObject dashboardObjectInfo;
    private TextObject dashboardLife;
    private Dashboard dashboard;
    private FPSCounter fpsCounter;

    //sounds
    private Sound backgroundSound;
    private EntityHandler entityHandler;
    private ArrayList<Entity> entities = new ArrayList<>();


    private Timer timer = new Timer(1f);


    private int counter = 0;
    private Player player = new Player(this, 10);

    private Display display = new Display(this);

    //Tiles
    private World world = new World(this);

    public static void main(String args[]) {
        Launcher launcher = new Launcher();
        launcher.runSketch();

    }

    @Override
    public void setupGame() {
        world.initializeMap();
        tileMap = world.getTileMap();
        view = new View(300, 300);
        setView(view);
        size(width, height);
        createDashboard(width, 200);
        initializeSound();
        createObjects();
    }

    @Override
    public void update() {
        if (viewPortEnabled) {
            display.createViewWithViewport(backgroundWidth, backgroundHeight, width, height, 1.1f);
        } else {
            display.createViewWithoutViewport(backgroundWidth, backgroundHeight);
        }
        world.updateMapTiles();

        for (GameObject go : getGameObjectItems()) {
            if (go instanceof Enemy) {
                go.move();
            }
        }
        refreshDasboardText();

    }

    private void initializeSound() {
//        backgroundSound = new Sound(this, "src/main/java/nl/han/ica/brorio/resources/sounds/geluid.mp3");

//        backgroundSound.loop(-1);
    }


    private void createDashboard(int dashboardWidth, int dashboardHeight) {

        Dashboard dashboard = new Dashboard(0, 30, dashboardWidth, dashboardHeight);


        dashboardText = new TextObject("Score:", 30);
        dashboardLife = new TextObject("Life " + player.getHealth(), 30);

        dashboardObjectInfo = new TextObject("", 30);
        dashboardObjectInfo.setX(width/2-100f);
        dashboardObjectInfo.setForeColor(0,255,0,255);
        dashboardLife.setX(width - 100f);
        dashboardLife.setForeColor(255, 0, 0, 255);
        dashboardText.setForeColor(255, 255, 255, 255);
        dashboard.addGameObject(dashboardObjectInfo);
        dashboard.addGameObject(dashboardText);
        dashboard.addGameObject(dashboardLife);
        addDashboard(dashboard);
    }

    private void refreshDasboardText() {
        int iterator = 0;


        dashboardText.setText("Score : ");
        dashboardLife.setText("Life " + player.getHealth());

        for (GameObject gameObject : getGameObjectItems()) {
            if(gameObject instanceof Enemy){
                if(((Creature) gameObject).isAttacked()){

                    dashboardObjectInfo.setText(((Enemy) gameObject).enemyName()+" "+((Creature) gameObject).getHealth());
                }
            }
            if (gameObject instanceof Item) {
                if (!((Item) gameObject).getIsActive()) {
                    iterator++;
                    if (gameObject.getY() >= 25) {
                        gameObject.setGravity(-1f);
                    } else if (gameObject.getY() <= 25) {
                        gameObject.setGravity(0);
                        gameObject.setX(100 * iterator);
                        gameObject.setY(25);
                    }
                    if (entityHandler.getCollected().size() == entityHandler.getItems().size()) {
                        dashboardText.setText("You Won Game Over!");
                        player.setCurrentFrameIndex(14);
                    }
                }
            }
        }
    }

    private void createObjects() {
        entityHandler = new EntityHandler(this);

        Item kaas2 = new Kaas(this);
        Item kaas = new Kaas(this);
        Item tomaat = new Tomaat(this);
        Item tomaat2 = new Tomaat(this);
        Item pizzaBodem = new PizzaBodem(this);
        Item pizzaBodem2 = new PizzaBodem(this);
        Enemy enemy1 = new Enemy1(this);
        Enemy enemy2 = new Enemy2(this);
        Enemy enemy3 = new Enemy3(this);

        entityHandler.addEntity(player);

        entityHandler.addEntity(enemy1);
        entityHandler.addEntity(enemy2);
        entityHandler.addEntity(enemy3);
        entityHandler.addEntity(kaas2);
        entityHandler.addEntity(tomaat);
        entityHandler.addEntity(tomaat2);
        entityHandler.addEntity(pizzaBodem);
        entityHandler.addEntity(pizzaBodem2);
        entityHandler.addEntity(kaas);


        entityHandler.createEntities();


    }


    public Player getPlayer() {
        return player;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }
}
