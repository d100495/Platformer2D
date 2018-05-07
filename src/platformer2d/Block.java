package platformer2d;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Dunger
 */
public class Block extends Rectangle {

    public int[] blockID = {-1, -1};

    public Block(Rectangle rectangleSize, int[] blockId) {
        setBounds(rectangleSize);
        this.blockID = blockId;
    }

    public void renderBlok(Graphics graph_arg) {
        if (Game.character.x < 300) {
            Game.scrollingX = 0;
        }
        if (Game.character.x > Game.level.blocks.length * Tile.tileSize - 350) {
            Game.scrollingX = ((int) Game.level.blocks.length) * Tile.tileSize - (Game.windowSize.width / 2);
        }

        if (blockID != Tile.air) {
            graph_arg.drawImage(Tile.tiles_TileSet,
                    x - Game.scrollingX,
                    y - Game.scrollingY,
                    x + width - Game.scrollingX,
                    y + height - Game.scrollingY,
                    blockID[0] * Tile.tileSize,
                    blockID[1] * Tile.tileSize,
                    blockID[0] * Tile.tileSize + Tile.tileSize,
                    blockID[1] * Tile.tileSize + Tile.tileSize,
                    null);
        }

    }

    Block() {
    }
}
