package platformer2d;

import platformer2d.Characters.CharacterPlayer;
import platformer2d.Levels.Level2;
import platformer2d.Levels.Level1;
import platformer2d.Levels.Level;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;
import platformer2d.Characters.BluePortal;
import platformer2d.Characters.SmallRedSlime;
import platformer2d.Characters.SmallGreenSlime;
import platformer2d.Characters.Character;
import platformer2d.Levels.ScoreRoom;

public class Game extends Applet implements Runnable {

    protected static final Dimension windowSize = new Dimension(1280, 720);
    private static String title = "Platformer 2D";
    private static Image screenImage;

    private final int FPS = 40;
    private double averageFPS;

    private int timerDelay;
    private Timer myTimer;
    public static int timerValue = 0;

    static final int pixelSize = 2;
    public static final Dimension pixels = new Dimension(
            windowSize.width / pixelSize,
            windowSize.height / pixelSize);

    public Game() {
        setPreferredSize(windowSize);
        addKeyListener(new Controls());
    }

    public static int scrollingX = 0, scrollingY = 0;
    private static boolean gameIsRunning = false;
    public static boolean isJumping = false;

    public static Level level;
    public static int curLevel = 1;
    public static CharacterPlayer character;
    public static BluePortal portal;
    private static ArrayList<Character> mobArrayList;

    public static enum STATE {
        MENU, GAME
    };
    public static STATE GameState = STATE.MENU;

    public static Menu menu = new Menu();

    @Override
    public void start() {

        System.out.println("test");

        new Tile();
        reload();

        gameIsRunning = true;
        new Thread(this).start();
        timerDelay = 1000;
        myTimer = new Timer(timerDelay, gameTimer);
        myTimer.start();

    }

    ActionListener gameTimer = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            timerValue++;
        }
    };

    @Override
    public void stop() {
        gameIsRunning = false;
    }

    public static void reload() {
        timerValue = 0;
        character = new CharacterPlayer(100, 520, Tile.tileSize, Tile.tileSize);
        CharacterPlayer.healthValue = 100;

        scrollingX = (int) character.x;
        scrollingY = (int) character.y - 200;

        switch (curLevel) {
            case 1: {
                level = new Level1(150, 20);
                portal = new BluePortal(
                        (150 * Tile.tileSize) - (2 * Tile.tileSize),
                        (20 * Tile.tileSize) - (3 * Tile.tileSize),
                        Tile.tileSize,
                        Tile.tileSize);

                mobArrayList = new ArrayList<>();

                for (int i = 0; i < 20; i++) {
                    mobArrayList.add(new SmallGreenSlime(200 + (new Random().nextInt(((level.blocks.length * Tile.tileSize) - 200) + 1)),
                            100,
                            Tile.tileSize / 2,
                            Tile.tileSize / 2,
                            new Random().nextDouble() * (0.1 + (1.5 - 0.1))
                    ));
                }

                for (int i = 0; i < 40; i++) {
                    mobArrayList.add(new SmallRedSlime(200 + (new Random().nextInt(((level.blocks.length * Tile.tileSize) - 200) + 1)),
                            100,
                            Tile.tileSize / 2,
                            Tile.tileSize / 2));
                }

                break;
            }

            case 2: {
                level = new Level2(50, 20);
                portal = new BluePortal(
                        (50 * Tile.tileSize) - (2 * Tile.tileSize),
                        (20 * Tile.tileSize) - (3 * Tile.tileSize),
                        Tile.tileSize,
                        Tile.tileSize);

                mobArrayList = new ArrayList<>();

                for (int i = 0; i < 50; i++) {
                    mobArrayList.add(new SmallRedSlime(200 + (new Random().nextInt(((level.blocks.length * Tile.tileSize) - 200) + 1)),
                            100,
                            Tile.tileSize / 2,
                            Tile.tileSize / 2));
                }

                for (int i = 0; i < 10; i++) {
                    mobArrayList.add(new SmallGreenSlime(200 + (new Random().nextInt(((level.blocks.length * Tile.tileSize) - 200) + 1)),
                            100,
                            Tile.tileSize / 2,
                            Tile.tileSize / 2,
                            new Random().nextDouble() * (0.1 + (1.5 - 0.1))
                    ));
                }

                break;
            }

            default: {
                level = new ScoreRoom(20, 20);
                portal = new BluePortal(
                        (20 * Tile.tileSize) - (2 * Tile.tileSize),
                        (20 * Tile.tileSize) - (3 * Tile.tileSize),
                        Tile.tileSize,
                        Tile.tileSize);
                mobArrayList = new ArrayList<>();

                break;
            }
        }

    }

    public void tick() {
        if (GameState == STATE.GAME) {
            level.tick();
            character.tick();
            portal.tick();
            myTimer.start();

            for (int i = 0; i < mobArrayList.size(); i++) {
                mobArrayList.get(i).tick();
                if (mobArrayList.get(i).isDead == true) {
                    System.out.println("mob " + i + " z klasy " + mobArrayList.get(i).getClass() + " umar ");
                    System.out.println("Pozostało: " + mobArrayList.size() + " mobow");
                    mobArrayList.remove(i);
                }
            }

        } else {
            myTimer.stop();
        }

    }

    Font myFont = new Font("Arial", 1, 9);
    Font myFont2 = new Font("Tahoma", 1, 9);
    Graphics graph1;

    Image healthBarImage = new ImageIcon(System.getProperty("user.dir") + "\\src\\resources\\HealthBar-Bar.png").getImage();
    Image healthCrossImage = new ImageIcon(System.getProperty("user.dir") + "\\src\\resources\\HealthBar-Cross.png").getImage();

    public void render() {

        graph1 = screenImage.getGraphics();

        if (GameState == STATE.GAME) {

            level.render(graph1);
            character.render(graph1);
            portal.render(graph1);

            graph1.setColor(Color.black);
            graph1.fillRect(pixels.width - 120, 25, 65, 15);
            graph1.setColor(Color.white);
            graph1.setFont(myFont2);
            graph1.drawString("Czas: " + timerValue + "s", pixels.width - 115, 35);

            graph1.drawImage(healthBarImage, pixels.width - 120, 6, 110, 20, null);
            graph1.setColor(Color.RED);
            graph1.fillRect(pixels.width - 115, 9, CharacterPlayer.healthValue, 14);
            graph1.drawImage(healthCrossImage, pixels.width - 155, 6, 30, 20, null);
            graph1.setColor(Color.white);
            graph1.drawString(CharacterPlayer.healthValue + "%", pixels.width - 80, 20);

            for (Character x : mobArrayList) {
                x.render(graph1);
            }

        } else if (GameState == STATE.MENU) {
            menu.render(graph1);
        }

        graph1.setColor(Color.yellow);
        graph1.setFont(myFont);
        graph1.drawString("FPS " + (int) averageFPS, 1, 9);

        graph1 = getGraphics();

        graph1.drawImage(screenImage, 0, 0, windowSize.width, windowSize.height, 0, 0, pixels.width, pixels.height, null);
        graph1.dispose();
    }

    @Override
    public void run() {

        System.out.println("wchodze do kafelek/run");
        screenImage = createVolatileImage(pixels.width, pixels.height);

        long startTime;
        long URDTimeMillis;
        long waitTime;
        long totalTime = 0;

        int frameCount = 0;
        int maxFrameCount = FPS;
        long targetTime = 1000 / FPS;
        while (gameIsRunning) {
            startTime = System.nanoTime();
            tick();
            render();

            URDTimeMillis = ((System.nanoTime() - startTime) / 10000000);
            waitTime = targetTime - URDTimeMillis;

            try {
                Thread.sleep(7);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if (frameCount == maxFrameCount) {
                averageFPS = 1000.0 / ((totalTime / frameCount) / 1000000);
                frameCount = 0;
                totalTime = 0;
            }
        }
    }

    public static void main(String args[]) {

        Game gamePanel = new Game();

        JFrame frame1 = new JFrame();

        frame1.add(gamePanel);
        frame1.pack();
        frame1.setResizable(true);
        frame1.setLocationRelativeTo(null);
        frame1.setTitle(title);

        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true);

        gamePanel.start();
    }

}
