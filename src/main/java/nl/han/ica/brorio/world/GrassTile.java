package nl.han.ica.brorio.world;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.Tile;

public class GrassTile extends Tile implements SolidTile {
    /**
     * @param sprite The image which will be drawn whenever the draw method of the Tile is called.
     */
    public GrassTile(Sprite sprite) {
        super(sprite);
    }
}
