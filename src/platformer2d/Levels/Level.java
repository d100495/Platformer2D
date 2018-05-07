/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformer2d.Levels;

import java.awt.Graphics;
import java.awt.Rectangle;
import platformer2d.Block;
import platformer2d.Tile;

/**
 *
 * @author Dunger
 */
public abstract class Level {

    public Block[][] blocks;

    public Level(int W, int H) {
        blocks = new Block[W][H];

        for (int x = 0; x < blocks.length; x++) {
            for (int y = 0; y < blocks[x].length; y++) {
                blocks[x][y] = new Block(
                        new Rectangle(
                                x * Tile.tileSize,
                                y * Tile.tileSize,
                                Tile.tileSize,
                                Tile.tileSize),
                        Tile.air);
            }
        }

    }

    public Block[][] getBloki() {
        return blocks;
    }

    public abstract void CreateLevel();

    public abstract void tick();

    public abstract void render(Graphics grap_arg);

}
