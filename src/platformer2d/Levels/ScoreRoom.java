/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformer2d.Levels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import platformer2d.Tile;
import platformer2d.Characters.BluePortal;
import static platformer2d.Game.pixels;

/**
 *
 * @author Dunger
 */
public final class ScoreRoom extends Level {

    public ScoreRoom(int W, int H) {
        super(W, H);
        CreateLevel();
    }

    @Override
    public void CreateLevel() {
        {
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

                }
            }
        }
    }

    @Override
    public void tick() {

    }

    Font myFont1 = new Font("Tahoma", 1, 30);
    Font myFont2 = new Font("Tahoma", 1, 20);

    @Override
    public void render(Graphics grap_arg) {

        grap_arg.setColor(Color.black);
        grap_arg.fillRect(0, 0, pixels.width, pixels.height);

        for (int x = 0; x < blocks.length; x++) {
            for (int y = 0; y < blocks[x].length; y++) {
                blocks[x][y].renderBlok(grap_arg);
            }
        }

        grap_arg.setColor(Color.white);
        grap_arg.setFont(myFont1);
        grap_arg.drawString("Twój czas przejścia: ", pixels.width / 2 - 150, 35);
        grap_arg.setFont(myFont2);
        grap_arg.drawString("Poziomu 1: " + BluePortal.level1_Time + " sekund", pixels.width / 2 - 100, 85);
        grap_arg.drawString("Poziomu 2: " + BluePortal.level2_Time + " sekund", pixels.width / 2 - 100, 120);

    }

}
