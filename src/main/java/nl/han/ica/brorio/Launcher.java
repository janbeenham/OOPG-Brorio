package nl.han.ica.brorio;

import com.sun.prism.image.ViewPort;
import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.FPSCounter;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.TextObject;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;
import nl.han.ica.OOPDProcessingEngineHAN.View.EdgeFollowingViewport;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.brorio.entities.*;

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



        refreshDasboardText();

    }

    private void initializeSound() {
        backgroundSound = new Sound(this, "src/main/java/nl/han/ica/brorio/resources/sounds/geluid.mp3");
        backgroundSound.loop(-1);
    }


    private void createDashboard(int dashboardWidth, int dashboardHeight) {
        ArrayList<Item> dashboardItems = new ArrayList<>();
        Dashboard dashboard = new Dashboard(0, 30, dashboardWidth, dashboardHeight);

        dashboardText = new TextObject("Score:", 30);

        dashboard.addGameObject(dashboardText);
        addDashboard(dashboard);
    }

    private void refreshDasboardText() {
        int iterator = 0;
        boolean moveGameObject = false;
        dashboardText.setText("Score : ");
        for (GameObject gameObject : getGameObjectItems()) {
            if (gameObject instanceof Item) {
                iterator++;
                if (!((Item) gameObject).isActive) {
                    gameObject.setX(100 * iterator);
                    gameObject.setY(25);
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
        entityHandler.addEntity(player);
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
