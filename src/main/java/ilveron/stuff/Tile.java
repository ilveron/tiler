package ilveron.stuff;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    BufferedImage myTile;
    Dimension tileSize;

    public Tile(BufferedImage myTile) {
        this.myTile = myTile;
        tileSize = new Dimension(myTile.getWidth(), myTile.getHeight());
    }

    public Dimension getTileSize() {
        return tileSize;
    }

    public BufferedImage getMyTile() {
        return myTile;
    }
}
