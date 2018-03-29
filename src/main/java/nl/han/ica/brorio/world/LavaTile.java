package nl.han.ica.brorio.world;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.Tile;

public class LavaTile extends Tile implements DamagingTile {
    GameObject gameObject;

    /**
     * @param sprite The image which will be drawn whenever the draw method of the Tile is called.
     */
    public LavaTile(Sprite sprite) {
        super(sprite);
    }

    @Override
    public int dealDamage() {
        return 1;
    }
}
