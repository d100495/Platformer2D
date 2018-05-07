/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformer2d.Characters;

import java.awt.Graphics;
import java.awt.Point;
import platformer2d.Tile;
import platformer2d.Game;

/**
 *
 * @author Dunger
 */
public class SmallRedSlime extends Character {

    private double characterDirection = 1;
    private final double gravity = 2;
    private final double movingSpeed = 0.3;

    private int animation = 0;
    private int animationFrame = 0;
    private final int animationTime = 10;

    private int health = 20;

    private boolean isTrampled = false;

    public SmallRedSlime(int x, int y, int szer, int wys) {
        this.x = x;
        this.y = y;
        this.width = szer;
        this.height = wys;
    }

    @Override
    public void tick() {

        Point puntk_1 = new Point((int) this.x + 2, (int) (this.y + this.height));
        Point punkt_2 = new Point((int) (this.x + this.width - 2), (int) (y + this.height));

        if (!kolizjaBlok(puntk_1, punkt_2)) {
            y += gravity;
        }

        if (this.characterDirection == 0) {
            this.x += this.movingSpeed;

            Point punkt_3 = new Point((int) x + 2, (int) (y));
            Point punkt_4 = new Point((int) (this.x + this.width - 2), (int) (y));

            if (kolizjaBlok(punkt_4, punkt_3)) {
                this.characterDirection = 1;
            }
        }

        if (this.characterDirection == 1) {
            this.x -= this.movingSpeed;

            Point punkt_3 = new Point((int) x + 2, (int) (y));
            Point punkt_4 = new Point((int) (this.x + this.width - 2), (int) (y));

            if (kolizjaBlok(punkt_4, punkt_3)) {
                this.characterDirection = 0;
            }

        }

        if (animationFrame >= animationTime) {
            if (animation >= 9) {
                animation = 0;
            } else {
                animation += 1;
            }

            animationFrame = 0;

        } else {
            animationFrame += 1;
        }

        if ((this.x >= 0 && this.y >= 0
                && this.x < Game.level.blocks.length * Tile.tileSize
                && this.y < Game.level.blocks[0].length * Tile.tileSize) == false) {
            this.isDead = true;
        }

        if (kolizjaGracz(Game.character, this)) {
            if (CharacterPlayer.isAbleToGetHealth == true && CharacterPlayer.healthValue < 100) {
                health--;
                CharacterPlayer.healthValue += 1;
                CharacterPlayer.isAbleToGetHealth = false;
                CharacterPlayer.healingTimer = System.nanoTime();
                System.out.println("Healing");
            }

            isTrampled = true;
        } else {

            isTrampled = false;
        }

        if (health < 0) {
            this.isDead = true;
        }

    }

    @Override
    public void render(Graphics graph_arg) {

        if (characterDirection == 1) {

            if (isTrampled == true) {
                graph_arg.drawImage(Tile.tiles_TileSet,
                        (int) x - Game.scrollingX,
                        (int) y - Game.scrollingY,
                        (int) (x + width) - Game.scrollingX,
                        (int) (y + height) - Game.scrollingY,
                        Tile.smallRedSlimeDying[0] * Tile.tileSize + (Tile.tileSize * animation),
                        Tile.smallRedSlimeDying[1] * Tile.tileSize,
                        Tile.smallRedSlimeDying[0] * Tile.tileSize + (Tile.tileSize * animation) + (int) width,
                        Tile.smallRedSlimeDying[1] * Tile.tileSize + (int) height,
                        null);
            } else {
                graph_arg.drawImage(Tile.tiles_TileSet,
                        (int) x - Game.scrollingX,
                        (int) y - Game.scrollingY,
                        (int) (x + width) - Game.scrollingX,
                        (int) (y + height) - Game.scrollingY,
                        Tile.smallRedSlimeRolling[0] * Tile.tileSize + (Tile.tileSize * animation),
                        Tile.smallRedSlimeRolling[1] * Tile.tileSize,
                        Tile.smallRedSlimeRolling[0] * Tile.tileSize + (Tile.tileSize * animation) + (int) width,
                        Tile.smallRedSlimeRolling[1] * Tile.tileSize + (int) height,
                        null);

            }

        } else {

            if (isTrampled == true) {
                graph_arg.drawImage(Tile.tiles_TileSet,
                        (int) x - Game.scrollingX,
                        (int) y - Game.scrollingY,
                        (int) (x + width) - Game.scrollingX,
                        (int) (y + height) - Game.scrollingY,
                        Tile.smallRedSlimeDying[0] * Tile.tileSize + (Tile.tileSize * animation) + (int) width,
                        Tile.smallRedSlimeDying[1] * Tile.tileSize,
                        Tile.smallRedSlimeDying[0] * Tile.tileSize + (Tile.tileSize * animation),
                        Tile.smallRedSlimeDying[1] * Tile.tileSize + (int) height,
                        null);
            } else {
                graph_arg.drawImage(Tile.tiles_TileSet,
                        (int) x - Game.scrollingX,
                        (int) y - Game.scrollingY,
                        (int) (x + width) - Game.scrollingX,
                        (int) (y + height) - Game.scrollingY,
                        Tile.smallRedSlimeRolling[0] * Tile.tileSize + (Tile.tileSize * animation) + (int) width,
                        Tile.smallRedSlimeRolling[1] * Tile.tileSize,
                        Tile.smallRedSlimeRolling[0] * Tile.tileSize + (Tile.tileSize * animation),
                        Tile.smallRedSlimeRolling[1] * Tile.tileSize + (int) height,
                        null);

            }

        }
    }

    public boolean kolizjaBlok(Point pkt1, Point pkt2) {
        for (int x = (int) (this.x / Tile.tileSize); x < (int) (this.x / (Tile.tileSize) + 2); x++) {
            for (int y = (int) (this.y / Tile.tileSize); y < (int) (this.y / (Tile.tileSize) + 2); y++) {
                if (x >= 0 && y >= 0 && x < Game.level.blocks.length && y < Game.level.blocks[0].length) {
                    if (Game.level.blocks[x][y].blockID != Tile.air) {
                        if (Game.level.blocks[x][y].contains(pkt1) || Game.level.blocks[x][y].contains(pkt2)) {
                            return true;
                        }
                    }
                }

            }

        }
        return false;
    }

}
