package nl.han.ica.brorio.entities;

import nl.han.ica.brorio.Launcher;
import nl.han.ica.brorio.entities.Item;

public class PizzaBodem extends Item {
    private boolean remove = false;

    public PizzaBodem(Launcher launcher) {
        super(launcher, 0);
    }

    @Override
    public void die() {
        remove = true;
    }

    @Override
    public boolean remove() {
        return remove;
    }


}
