package nl.han.ica.brorio.entities;

import nl.han.ica.brorio.Launcher;
import nl.han.ica.brorio.entities.Item;

public class Tomaat extends Item {
boolean remove = false;
    public Tomaat(Launcher launcher) {
        super(launcher, 2);
    }

    @Override
    public void die() {
    remove=true;
    }

    @Override
    public boolean remove() {
        return remove;
    }

}
