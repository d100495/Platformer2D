/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformer2d;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import static platformer2d.Game.pixels;

/**
 *
 * @author Dunger
 */
public class Menu {

    public static int chosenOption = 0;
    private int showHelp = -1;

    public static String[] menuOptions = {
        "Graj",
        "Pomoc",
        "Poziomy",
        "Wyjdź"
    };

    public static String[] levelOptions = {
        "Poziom 1",
        "Poziom 2",
        "Poziom 3"
    };

    public void render(Graphics grap_arg) {
        Graphics2D g2d = (Graphics2D) grap_arg;

        Image menuBackground = new ImageIcon(System.getProperty("user.dir") + "\\src\\resources\\tlo2.gif").getImage();

        grap_arg.setColor(Color.PINK);
        grap_arg.fillRect(0, 0, pixels.width, pixels.height);
        grap_arg.drawImage(menuBackground, 0, 0, pixels.width, pixels.height, null);

        grap_arg.setColor(Color.MAGENTA);
        Font font2 = new Font("arial", Font.BOLD, 40);
        grap_arg.setFont(font2);
        grap_arg.setColor(Color.WHITE);

        switch (chosenOption) {
            case 0:
                grap_arg.setColor(Color.WHITE);
                grap_arg.drawString("Graj", pixels.width / 2 - 40, pixels.height / 2 + 10);
                break;

            case 1:
                grap_arg.drawString("Pomoc", pixels.width / 2 - 70, pixels.height / 2 + 10);
                if (showHelp == 1) {
                    Font font3 = new Font("arial", Font.BOLD, 20);
                    grap_arg.setFont(font3);
                    grap_arg.drawString("A - ruch w lewo", pixels.width / 2 - 80, pixels.height / 2 + 50);
                    grap_arg.drawString("D - ruch w prawo", pixels.width / 2 - 80, pixels.height / 2 + 80);
                    grap_arg.drawString("Spacja - skok", pixels.width / 2 - 80, pixels.height / 2 + 110);
                    grap_arg.drawString("Enter - zatwierdź", pixels.width / 2 - 80, pixels.height / 2 + 140);
                }

                break;

            case 2:
                grap_arg.drawString("Poziomy", pixels.width / 2 - 80, pixels.height / 2 + 10);
                break;

            case 3:
                grap_arg.drawString("Wyjdź", pixels.width / 2 - 55, pixels.height / 2 + 10);
                break;

            default:
                break;
        }
    }

    public void select() {

        if (chosenOption == 0) {
            Game.GameState = Game.STATE.GAME;
        }
        if (chosenOption == 1) {
            showHelp *= -1;
        }
        if (chosenOption == 2) {

        }
        if (chosenOption == 3) {
            System.exit(0);
        }
    }

}
