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

public final class Level1 extends Level {

    public Level1(int W, int H) {
        super(W, H);
        CreateLevel();
    }

    @Override
    public void CreateLevel() {
        System.out.println("wchodze do stworzpozioom");
        System.out.println("Bloki.length: " + blocks.length);
        for (int x = 0; x < blocks.length; x++) {
            for (int y = 0; y < blocks[x].length; y++) {

                if (y == blocks[x].length - 2) {
                    blocks[x][y].blockID = Tile.grass;
                }

                if ((x == 0 || x == blocks.length - 1) && y == blocks[x].length - 2) {
                    blocks[x][y].blockID = Tile.ground;
                }

                for (int xx = 0; xx < 6; xx++) {
                    if (y == (blocks[x].length - 3) - xx + 1 && x > 20 + xx && x < 30) {
                        blocks[x][y].blockID = Tile.ground;
                    }
                }

                if (((x > 42 && x <= 44) && y == blocks[x].length - 5)) {
                    blocks[x][y].blockID = Tile.ground;
                }

                if (x == 0 || y == 0 || x == blocks.length - 1 || y == blocks[x].length - 1) {
                    blocks[x][y].blockID = Tile.ground;
                }

                if (((x > 40 && x <= 50) && (y == blocks[x].length - 2 || y == blocks[x].length - 1))) {
                    blocks[x][y].blockID = Tile.air;
                }

                if (((x > 70 && x < 75) && (y == blocks[x].length - 2 || y == blocks[x].length - 1))) {
                    blocks[x][y].blockID = Tile.air;
                }

                if (((x > 75 && x < 80) && (y == blocks[x].length - 3))) {
                    blocks[x][y].blockID = Tile.grass;
                }

                if (((x == 95) && (y == blocks[x].length - 4))) {
                    blocks[x][y].blockID = Tile.grass;
                }

                if (((x > 95 && x < 100) && (y == blocks[x].length - 6))) {
                    blocks[x][y].blockID = Tile.grass;
                }

                if (((x > 104 && x < 109) && (y == blocks[x].length - 6))) {
                    blocks[x][y].blockID = Tile.grass;
                }

                if (((x > 112 && x < 115) && (y == blocks[x].length - 4))) {
                    blocks[x][y].blockID = Tile.grass;
                }

                if (((x == 1) && (y == blocks[x].length - 5))) {
                    blocks[x][y].blockID = Tile.grass;
                }

                if (((x > 5 && x < 10) && (y == blocks[x].length - 6))) {
                    blocks[x][y].blockID = Tile.grass;
                }

                if (((x == 108) && (y < blocks[x].length - 6 && y > blocks[x].length - 20))) {
                    blocks[x][y].blockID = Tile.ground;
                }

            }
        }
    }

    @Override
    public void tick() {

    }

    Image cloud = new ImageIcon(System.getProperty("user.dir") + "\\src\\resources\\chmura1.png").getImage();
    Image tree = new ImageIcon(System.getProperty("user.dir") + "\\src\\resources\\tree.png").getImage();

    @Override
    public void render(Graphics grap_arg) {

        grap_arg.setColor(new Color(83, 157, 164));
        grap_arg.fillRect(0, 0, pixels.width, pixels.height);

        for (int x = 0; x < blocks.length; x++) {
            for (int y = 0; y < blocks[x].length; y++) {
                blocks[x][y].renderBlok(grap_arg);
            }
        }

        grap_arg.drawImage(cloud, 250 - scrollingX, 340 - Game.scrollingY, null);
        grap_arg.drawImage(tree, 350 - scrollingX, 328 - Game.scrollingY, null);
        grap_arg.drawImage(cloud, 550 - scrollingX, 240 - Game.scrollingY, null);
        grap_arg.drawImage(tree, 1000 - scrollingX, 328 - Game.scrollingY, null);
        grap_arg.drawImage(cloud, 950 - scrollingX, 150 - Game.scrollingY, null);
        grap_arg.drawImage(cloud, 1150 - scrollingX, 200 - Game.scrollingY, null);
        grap_arg.drawImage(cloud, 1600 - scrollingX, 400 - Game.scrollingY, null);
        grap_arg.drawImage(cloud, 1800 - scrollingX, 340 - Game.scrollingY, null);
        grap_arg.drawImage(cloud, 2300 - scrollingX, 350 - Game.scrollingY, null);

    }

}
