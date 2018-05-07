package platformer2d.Levels;

import platformer2d.Levels.Level;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;
import platformer2d.Block;
import platformer2d.Tile;
import platformer2d.Game;
import static platformer2d.Game.scrollingX;
import static platformer2d.Game.pixels;

/**
 *
 * @author Dunger
 */
public final class Level2 extends Level {

    public Level2(int W, int H) {
        super(W, H);
        CreateLevel();
    }

    @Override
    public void CreateLevel() {
        System.out.println("wchodze do stworzpozioom");
        System.out.println("Bloki.length: " + blocks.length);
        for (int x = 0; x < blocks.length; x++) {
            for (int y = 0; y < blocks[x].length; y++) {
                if (x == 0 || y == 0 || x == blocks.length - 1 || y == blocks[x].length - 1) {
                    blocks[x][y].blockID = Tile.ground;
                }

                if (y == blocks[x].length - 2) {
                    blocks[x][y].blockID = Tile.grass;
                }

                if (((x > 2 && x <= 45) && (y < blocks[x].length - 3 && y > blocks[x].length - 10))) {
                    blocks[x][y].blockID = Tile.ground;
                }

                if ((x % 4 == 0 || x % 3 == 0) && (y < blocks[x].length - 3 && y > blocks[x].length - 20) && (x > 2 && x <= 45)) {
                    blocks[x][y].blockID = Tile.air;
                }

                if ((x % 2 == 0 || x % 3 == 0) && (y < blocks[x].length - 5 && y > blocks[x].length - 9) && (x > 2 && x <= 45)) {
                    blocks[x][y].blockID = Tile.air;
                }

            }
        }
    }

    @Override
    public void tick() {

    }

    Image chmura = new ImageIcon(System.getProperty("user.dir") + "\\src\\resources\\chmura1.png").getImage();
    Image drzewo = new ImageIcon(System.getProperty("user.dir") + "\\src\\resources\\tree.png").getImage();

    @Override
    public void render(Graphics grap_arg) {

        grap_arg.setColor(Color.orange);
        grap_arg.fillRect(0, 0, pixels.width, pixels.height);

        for (int x = 0; x < blocks.length; x++) {
            for (int y = 0; y < blocks[x].length; y++) {
                blocks[x][y].renderBlok(grap_arg);
            }
        }

        grap_arg.drawImage(chmura, 250 - scrollingX, 328 - Game.scrollingY, null);
        grap_arg.drawImage(chmura, 550 - scrollingX, 328 - Game.scrollingY, null);
        grap_arg.drawImage(chmura, 950 - scrollingX, 150 - Game.scrollingY, null);
        grap_arg.drawImage(chmura, 1150 - scrollingX, 200 - Game.scrollingY, null);
        grap_arg.drawImage(chmura, 1600 - scrollingX, 400 - Game.scrollingY, null);
        grap_arg.drawImage(chmura, 1800 - scrollingX, 340 - Game.scrollingY, null);
        grap_arg.drawImage(chmura, 2300 - scrollingX, 350 - Game.scrollingY, null);

    }

}
