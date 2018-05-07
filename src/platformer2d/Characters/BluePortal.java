/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformer2d.Characters;

import java.awt.Graphics;
import platformer2d.Tile;
import platformer2d.Game;

/**
 *
 * @author Dunger
 */
public class BluePortal extends Character {

    private int animation = 0;
    private int animationFrame = 0;
    private final int animationTime = 10;

    public static int level1_Time;
    public static int level2_Time;

    public BluePortal(int x, int y, int szer, int wys) {
        this.x = x;
        this.y = y;
        this.width = szer;
        this.height = wys;
    }

    @Override
    public void tick() {

        if (animationFrame >= animationTime) {
            if (animation >= 7) {
                animation = 0;
            } else {
                animation += 1;
            }

            animationFrame = 0;

        } else {
            animationFrame += 1;
        }

        if (kolizjaGracz(Game.character, this)) {
            if (Game.curLevel == 1) {
                level1_Time = Game.timerValue;
            }
            if (Game.curLevel == 2) {
                level2_Time = Game.timerValue;
            }

            if (Game.curLevel >= 3) {
                Game.curLevel = 1;

                level1_Time = 0;
                level2_Time = 0;
            } else {
                Game.curLevel++;
            }

            Game.reload();
        }

    }

    @Override
    public void render(Graphics graph_arg) {

        graph_arg.drawImage(Tile.tiles_TileSet,
                (int) x - Game.scrollingX,
                (int) y - Game.scrollingY,
                (int) (x + width) - Game.scrollingX,
                (int) (y + height) - Game.scrollingY,
                Tile.bluePortal[0] * Tile.tileSize + (Tile.tileSize * animation),
                Tile.bluePortal[1] * Tile.tileSize,
                Tile.bluePortal[0] * Tile.tileSize + (Tile.tileSize * animation) + (int) width,
                Tile.bluePortal[1] * Tile.tileSize + (int) height,
                null);
    }

}
