
package nl.han.ica.brorio.entities;

import nl.han.ica.brorio.Launcher;
import nl.han.ica.brorio.entities.Item;

public class Kaas extends Item {
private boolean remove = false;
    public Kaas(Launcher launcher) {
        super(launcher, 1);


    }



    public void die() {
    remove=true;


    }

    @Override
    public boolean remove() {
        return remove;
    }

}