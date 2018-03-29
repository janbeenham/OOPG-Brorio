package nl.han.ica.brorio.world;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileMap;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileType;
import nl.han.ica.brorio.gfx.Animations;
import nl.han.ica.brorio.Launcher;

public class World{
    private Sprite lavaSprite;
    private Launcher launcher;
    private Animations animatedLavaTiles;
    private TileMap tileMap;
    public World(Launcher launcher){

        this.launcher=launcher;
        animatedLavaTiles= new Animations(launcher,"src/main/java/nl/han/ica/brorio/resources/textures/broriotiles/lava.png",3);

    }
    public void updateMapTiles(){
        lavaSprite.setSprite(animatedLavaTiles.animation());
    }

    public void initializeMap() {
        Sprite sprite_sheet = new Sprite("src/main/java/nl/han/ica/brorio/resources/textures/broriotiles/Image20000.png");
        Animations lava = new Animations(launcher,"src/main/java/nl/han/ica/brorio/resources/textures/broriotiles/lava.png",3);

        lavaSprite = new Sprite(animatedLavaTiles.animation());


        TileType<LavaTile> lavaTileType = new TileType<>(LavaTile.class, lavaSprite);
        TileType<BoardsTile> boardsTileTileType = new TileType<>(BoardsTile.class, sprite_sheet);
        TileType<GrassTile> grassTileTileType = new TileType<>(GrassTile.class, sprite_sheet);
        TileType[] tileTypes = {lavaTileType,grassTileTileType};
        int tileSize = 64;
        int tilesMap[][] = {
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, 1, 0, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, 1, 1, 0, 1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };
         tileMap = new TileMap(tileSize, tileTypes, tilesMap);
    }

    public TileMap getTileMap() {
        return tileMap;
    }



}
