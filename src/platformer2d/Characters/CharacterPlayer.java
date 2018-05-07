package platformer2d.Characters;

import platformer2d.Characters.Character;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import platformer2d.Tile;
import platformer2d.Game;

/**
 *
 * @author Dunger
 */
public class CharacterPlayer extends Character {

    public static boolean isCharacterMoving = false;
    public static double direction = 0;
    public static boolean isCharacterJumping = false;
    public static boolean isCharacterFallngDown = false;
    public static boolean isCharacterFlinching = false;
    public static long flinchTimer = 0;
    public static boolean isAbleToGetHealth = true;
    public static long healingTimer = 0;

    private final double gravity = 2;
    public final double movingSpeed = 2;
    private final double jumpSpeed = gravity;

    private final double jumpHeight = 54;
    private int numberOfJumps = 0;

    public static int healthValue = 100;

    private int animation = 0;
    private int animationFrame = 0;
    private final int animationTime = 10;

    public CharacterPlayer(int szer, int wys) {
        this.x = ((Game.pixels.width / 2) - (szer / 2));
        this.y = ((Game.pixels.height / 2) - (wys / 2));

        this.width = szer;
        this.height = wys;
    }

    public CharacterPlayer(int x, int y, int szer, int wys) {
        this.x = x;
        this.y = y;

        this.width = szer;
        this.height = wys;
    }

    @Override
    public void tick() {
        Point point_1 = new Point((int) x + 2, (int) (y + height));
        Point point_2 = new Point((int) (x + width - 2), (int) (y + height));

        if (!collisionWithBlock(point_1, point_2) && !isCharacterJumping) {
            isCharacterFallngDown = true;

            y += gravity;
            Game.scrollingY += gravity;
        } else {
            isCharacterFallngDown = false;
            if (Game.isJumping) {
                isCharacterJumping = true;
            }
        }

        if (isCharacterMoving) {
            boolean blockMoves = false;

            if (direction == movingSpeed) {
                Point point_4 = new Point((int) (this.x + this.width + 1), (int) (y));
                Point point_5 = new Point((int) (this.x + this.width + 1), (int) (y + height) - 2);
                blockMoves = collisionWithBlock(point_4, point_5);
            } else if (direction == (-movingSpeed)) {
                Point point_3 = new Point((int) x - 1, (int) (y));
                Point point_6 = new Point((int) x - 1, (int) ((y + height) - 2));
                blockMoves = collisionWithBlock(point_6, point_3);
            }

            if (!blockMoves) {
                x += direction;
                Game.scrollingX += direction;
            }
        }

        if (isCharacterJumping) {
            Point point_3 = new Point((int) x + 2, (int) (y));
            Point point_4 = new Point((int) (this.x + this.width - 2), (int) (y));

            if (!collisionWithBlock(point_3, point_4)) {
                if (numberOfJumps >= jumpHeight) {
                    isCharacterJumping = false;
                    numberOfJumps = 0;
                } else {
                    y -= jumpSpeed;
                    Game.scrollingY -= jumpSpeed;
                    numberOfJumps += 1;

                }
            } else {
                isCharacterJumping = false;
                numberOfJumps = 0;

            }

        }

        if (isCharacterFlinching == true) {
            long elapsed = (System.nanoTime() - flinchTimer) / 1000000;

            if (elapsed > 1000) {
                isCharacterFlinching = false;
            }
        }

        if (isAbleToGetHealth == false) {
            long elapsed = (System.nanoTime() - healingTimer) / 1000000;

            if (elapsed > 100) {
                isAbleToGetHealth = true;
            }
        }

        if (healthValue < 0) {
            Game.reload();
            healthValue = 100;
        }

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

        if (y >= Game.level.blocks[0].length * Tile.tileSize) {
            Game.reload();
            healthValue = 100;
        }
    }

    @Override
    public void render(Graphics graph_arg) {

        if (direction == movingSpeed) {
            if (isCharacterMoving == true && isCharacterJumping == false && isCharacterFallngDown == false) {
                graph_arg.drawImage(Tile.tiles_TileSet,
                        (int) x - Game.scrollingX,
                        (int) y - Game.scrollingY,
                        (int) (x + width) - Game.scrollingX,
                        (int) (y + height) - Game.scrollingY,
                        Tile.characterRun[0] * Tile.tileSize + (Tile.tileSize * animation),
                        Tile.characterRun[1] * Tile.tileSize,
                        Tile.characterRun[0] * Tile.tileSize + (Tile.tileSize * animation) + (int) width,
                        Tile.characterRun[1] * Tile.tileSize + (int) height,
                        null);
            } else if (isCharacterJumping == true && isCharacterMoving == true && isCharacterFallngDown == false) {
                graph_arg.drawImage(Tile.tiles_TileSet,
                        (int) x - Game.scrollingX,
                        (int) y - Game.scrollingY,
                        (int) (x + width) - Game.scrollingX,
                        (int) (y + height) - Game.scrollingY,
                        Tile.characterJump[0] * Tile.tileSize + (Tile.tileSize * animation),
                        Tile.characterJump[1] * Tile.tileSize,
                        Tile.characterJump[0] * Tile.tileSize + (Tile.tileSize * animation) + (int) width,
                        Tile.characterJump[1] * Tile.tileSize + (int) height,
                        null);
            } else if (isCharacterJumping == false && isCharacterMoving == true && isCharacterFallngDown == true) {
                graph_arg.drawImage(Tile.tiles_TileSet,
                        (int) x - Game.scrollingX,
                        (int) y - Game.scrollingY,
                        (int) (x + width) - Game.scrollingX,
                        (int) (y + height) - Game.scrollingY,
                        Tile.characterFallingDown[0] * Tile.tileSize + (Tile.tileSize * animation),
                        Tile.characterFallingDown[1] * Tile.tileSize,
                        Tile.characterFallingDown[0] * Tile.tileSize + (Tile.tileSize * animation) + (int) width,
                        Tile.characterFallingDown[1] * Tile.tileSize + (int) height,
                        null);

            } else if (isCharacterJumping == false && isCharacterMoving == false && isCharacterFallngDown == true) {
                graph_arg.drawImage(Tile.tiles_TileSet,
                        (int) x - Game.scrollingX,
                        (int) y - Game.scrollingY,
                        (int) (x + width) - Game.scrollingX,
                        (int) (y + height) - Game.scrollingY,
                        Tile.characterFallingDown[0] * Tile.tileSize + (Tile.tileSize * animation),
                        Tile.characterFallingDown[1] * Tile.tileSize,
                        Tile.characterFallingDown[0] * Tile.tileSize + (Tile.tileSize * animation) + (int) width,
                        Tile.characterFallingDown[1] * Tile.tileSize + (int) height,
                        null);

            } else if (isCharacterJumping == true && isCharacterMoving == false && isCharacterFallngDown == false) {
                graph_arg.drawImage(Tile.tiles_TileSet,
                        (int) x - Game.scrollingX,
                        (int) y - Game.scrollingY,
                        (int) (x + width) - Game.scrollingX,
                        (int) (y + height) - Game.scrollingY,
                        Tile.characterJump[0] * Tile.tileSize + (Tile.tileSize * animation),
                        Tile.characterJump[1] * Tile.tileSize,
                        Tile.characterJump[0] * Tile.tileSize + (Tile.tileSize * animation) + (int) width,
                        Tile.characterJump[1] * Tile.tileSize + (int) height,
                        null);

            } else {
                graph_arg.drawImage(Tile.tiles_TileSet,
                        (int) x - Game.scrollingX,
                        (int) y - Game.scrollingY,
                        (int) (x + width) - Game.scrollingX,
                        (int) (y + height) - Game.scrollingY,
                        Tile.characterIdle[0] * Tile.tileSize + (Tile.tileSize * animation),
                        Tile.characterIdle[1] * Tile.tileSize,
                        Tile.characterIdle[0] * Tile.tileSize + (Tile.tileSize * animation) + (int) width,
                        Tile.characterIdle[1] * Tile.tileSize + (int) height,
                        null);
            }

        } else {
            if (isCharacterMoving == true && isCharacterJumping == false && isCharacterFallngDown == false) {
                graph_arg.drawImage(Tile.tiles_TileSet,
                        (int) x - Game.scrollingX,
                        (int) y - Game.scrollingY,
                        (int) (x + width) - Game.scrollingX,
                        (int) (y + height) - Game.scrollingY,
                        Tile.characterRun[0] * Tile.tileSize + (Tile.tileSize * animation) + (int) width,
                        Tile.characterRun[1] * Tile.tileSize,
                        Tile.characterRun[0] * Tile.tileSize + (Tile.tileSize * animation),
                        Tile.characterRun[1] * Tile.tileSize + (int) height,
                        null);
            } else if (isCharacterJumping == true && isCharacterMoving == true && isCharacterFallngDown == false) {
                graph_arg.drawImage(Tile.tiles_TileSet,
                        (int) x - Game.scrollingX,
                        (int) y - Game.scrollingY,
                        (int) (x + width) - Game.scrollingX,
                        (int) (y + height) - Game.scrollingY,
                        Tile.characterJump[0] * Tile.tileSize + (Tile.tileSize * animation) + (int) width,
                        Tile.characterJump[1] * Tile.tileSize,
                        Tile.characterJump[0] * Tile.tileSize + (Tile.tileSize * animation),
                        Tile.characterJump[1] * Tile.tileSize + (int) height,
                        null);
            } else if (isCharacterJumping == false && isCharacterMoving == true && isCharacterFallngDown == true) {
                graph_arg.drawImage(Tile.tiles_TileSet,
                        (int) x - Game.scrollingX,
                        (int) y - Game.scrollingY,
                        (int) (x + width) - Game.scrollingX,
                        (int) (y + height) - Game.scrollingY,
                        Tile.characterFallingDown[0] * Tile.tileSize + (Tile.tileSize * animation) + (int) width,
                        Tile.characterFallingDown[1] * Tile.tileSize,
                        Tile.characterFallingDown[0] * Tile.tileSize + (Tile.tileSize * animation),
                        Tile.characterFallingDown[1] * Tile.tileSize + (int) height,
                        null);

            } else if (isCharacterJumping == false && isCharacterMoving == false && isCharacterFallngDown == true) {
                graph_arg.drawImage(Tile.tiles_TileSet,
                        (int) x - Game.scrollingX,
                        (int) y - Game.scrollingY,
                        (int) (x + width) - Game.scrollingX,
                        (int) (y + height) - Game.scrollingY,
                        Tile.characterFallingDown[0] * Tile.tileSize + (Tile.tileSize * animation) + (int) width,
                        Tile.characterFallingDown[1] * Tile.tileSize,
                        Tile.characterFallingDown[0] * Tile.tileSize + (Tile.tileSize * animation),
                        Tile.characterFallingDown[1] * Tile.tileSize + (int) height,
                        null);

            } else if (isCharacterJumping == true && isCharacterMoving == false && isCharacterFallngDown == false) {
                graph_arg.drawImage(Tile.tiles_TileSet,
                        (int) x - Game.scrollingX,
                        (int) y - Game.scrollingY,
                        (int) (x + width) - Game.scrollingX,
                        (int) (y + height) - Game.scrollingY,
                        Tile.characterJump[0] * Tile.tileSize + (Tile.tileSize * animation) + (int) width,
                        Tile.characterJump[1] * Tile.tileSize,
                        Tile.characterJump[0] * Tile.tileSize + (Tile.tileSize * animation),
                        Tile.characterJump[1] * Tile.tileSize + (int) height,
                        null);

            } else {
                graph_arg.drawImage(Tile.tiles_TileSet,
                        (int) x - Game.scrollingX,
                        (int) y - Game.scrollingY,
                        (int) (x + width) - Game.scrollingX,
                        (int) (y + height) - Game.scrollingY,
                        Tile.characterIdle[0] * Tile.tileSize + (Tile.tileSize * animation) + (int) width,
                        Tile.characterIdle[1] * Tile.tileSize,
                        Tile.characterIdle[0] * Tile.tileSize + (Tile.tileSize * animation),
                        Tile.characterIdle[1] * Tile.tileSize + (int) height,
                        null);
            }

        }

        if (isCharacterFlinching == true) {
            graph_arg.drawImage(Tile.tiles_TileSet,
                    (int) x - Game.scrollingX,
                    (int) y - Game.scrollingY,
                    (int) (x + width) - Game.scrollingX,
                    (int) (y + height) - Game.scrollingY,
                    Tile.emptyTile[0] * Tile.tileSize + (Tile.tileSize * animation),
                    Tile.emptyTile[1] * Tile.tileSize,
                    Tile.emptyTile[0] * Tile.tileSize + (Tile.tileSize * animation) + (int) width,
                    Tile.emptyTile[1] * Tile.tileSize + (int) height,
                    null);
        }

    }

    public boolean collisionWithBlock(Point pkt1, Point pkt2) {
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
