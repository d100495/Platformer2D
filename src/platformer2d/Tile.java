package platformer2d;

import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.*;

public class Tile {

    public static int[] air = {-1, -1};
    public static int[] ground = {0, 0};
    public static int[] grass = {1, 0};

    public static int[] characterIdle = {0, 19};
    public static int[] characterRun = {0, 18};
    public static int[] characterJump = {0, 17};
    public static int[] characterFallingDown = {0, 16};
    public static int[] emptyTile = {8, 19};

    public static int[] smallGreenSlimeJumping = {0, 12};
    public static int[] smallRedSlimeRolling = {0, 11};
    public static int[] smallRedSlimeDying = {10, 11};

    public static int[] bluePortal = {0, 10};

    public static BufferedImage tiles_TileSet;
    public static int tileSize = 32;

    public Tile() {
        try {
            File kafelkiPNGFile = new File(System.getProperty("user.dir") + "\\src\\resources\\KafelkiPNG.png");

            System.out.println("file: " + kafelkiPNGFile.getAbsoluteFile());

            Tile.tiles_TileSet = ImageIO.read(kafelkiPNGFile);
        } catch (IOException e) {
        }
    }

}
