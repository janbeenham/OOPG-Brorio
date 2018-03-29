package nl.han.ica.brorio.entities;

import nl.han.ica.brorio.Launcher;
import nl.han.ica.brorio.entities.creatures.Enemy;
import nl.han.ica.brorio.entities.creatures.Player;
import nl.han.ica.brorio.entities.items.Item;

import java.util.ArrayList;

public class EntityHandler {

    private ArrayList<Entity> entities = new ArrayList<>();
    private int x, y;


    private Launcher launcher;


    public EntityHandler(Launcher launcher) {
        this.launcher = launcher;
    }


    public void addEntity(Entity e) {
        entities.add(e);
    }


    public void createEntities() {
        for (int i = 0; i < entities.size(); i++) {
            launcher.addGameObject(entities.get(i), 100 * i, 700);
        }
    }


    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public ArrayList<Item> getItems() {
        ArrayList<Item> items = new ArrayList<>();
        for (Entity e : entities) {
            if (e instanceof Item) {
                items.add((Item) e);

            }
        }
        return items;
    }

    public ArrayList<Item> getCollected() {
        ArrayList<Item> collected = new ArrayList<>();
        for (Entity e : entities) {
            if (e instanceof Item) {
                if (!((Item) e).getIsActive())
                    collected.add((Item) e);
            }
        }
        return collected;
    }

    public void initAI() {
        for (Entity e : entities) {
            if (e instanceof Enemy) {
                e.move();
            }
        }
    }
}
