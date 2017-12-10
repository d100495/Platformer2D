package platformer2d;

import platformer2d.Postacie.PostacGracza;
import platformer2d.Poziomy.Poziom2;
import platformer2d.Poziomy.Poziom1;
import platformer2d.Poziomy.Poziom;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import javax.imageio.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;



public class Plansza extends Applet implements Runnable //Applet, zeby mozna wstawic na strone www
{

    //Wyglad ....
    //klasa Dimension zawiera parametry szerokosc i wysokosc do okna
    protected static final Dimension rozmiarOkna = new Dimension(1280, 720);
    private static String tytul = "Platformer 2D";
    private static Image obrazekEkranu;
    
    private final int FPS =40;
    private double averageFPS;
    
    static final int rozmiarPiksela = 2; //czyli jakby przyblizenie ekranu
    public static final Dimension piksele = new Dimension(
            rozmiarOkna.width / rozmiarPiksela,
            rozmiarOkna.height / rozmiarPiksela);

    
    public Plansza()
    {
        setPreferredSize(rozmiarOkna);
        addKeyListener(new Sterowanie());
    }
    
     
     
    //Logika, poruszanie...
    public static int scrollingX=0, scrollingY=0;
    public static boolean gameIsRunning = false;
    public static boolean isJumping=false;

    
    //Poziomy,obiekty........
    public static Poziom level;
    public static int nrPoziomu=1;
    public static PostacGracza postac;
    

    
    
    
    @Override
    public void start() 
    {
            
    System.out.println("test");
    
        //Obiekty
        new Kafelek(); //ładowanie obrazków
        reload();

        //Pętla gry
        gameIsRunning = true; //rozpoczęcie gry
        new Thread(this).start(); //nowy watek dla Komponentu
    }

    
    @Override
    public void stop() 
    {
        gameIsRunning = false;
    }

    
    public static void reload()
    {
        
        postac = new PostacGracza(100,520,Kafelek.kafelekSize, Kafelek.kafelekSize);
         
        scrollingX=(int)postac.x;
        scrollingY=(int)postac.y-200;
        
        switch(nrPoziomu)
        {
            case 1:
            {
                level=new Poziom1(150, 20); // 2 wartosc nieparzysta (tymczasowo)
                break;
            }
            
            case 2:
            {
                level=new Poziom2(20, 20); 
                break;
            }
            
            default:
            {
                level=new Poziom1(150, 10); 
                break;
            }
        }
       
    }
    
    
    
    public void tick() //wszystkie metody tick z obiektow
    {
        level.tick();
        postac.tick();
    }

        //Zmienne do renderowania
            //public static BufferedImage tloPlanszy=null;
           // public static BufferedImage chmura=null;

        //    Random randomNumber = new Random();
        //    int chmuraY1 = randomNumber.nextInt(200)+150;
        //    int chmuraY2= randomNumber.nextInt(200)+150;
        //    int chmuraY3 = randomNumber.nextInt(200)+150;
        //    int chmuraY4 = randomNumber.nextInt(200)+150;
    
        Font myFont = new Font ("Courier New", 1, 9);
        Graphics graph1;
    public void render() 
    {
        //Obiekty graficzne planszy
                 graph1 = obrazekEkranu.getGraphics(); // plansza nr1
            
        //Rendering (wszystkie metody render)
                level.render(graph1);
                postac.render(graph1);

                
        //Fps counter        
        graph1.setColor(Color.yellow);
        graph1.setFont(myFont);
        graph1.drawString("FPS "+(int)averageFPS, 1, 6);
        graph1 = getGraphics(); 
       
        

        graph1.drawImage(obrazekEkranu, 0, 0, rozmiarOkna.width, rozmiarOkna.height, 0, 0, piksele.width, piksele.height, null);
        graph1.dispose();//zwalnianie pamieci
    }

    
    
    @Override
    public void run() 
    {
        System.out.println("wchodze do kafelek/run");
        obrazekEkranu = createVolatileImage(piksele.width, piksele.height); // podwójne bufforowanie, żeby obraz nie skakał
        
        long startTime;
        long URDTimeMillis;
        long waitTime;
        long totalTime=0;
        
        int frameCount=0; //to zliczamy
        int maxFrameCount=FPS; //to fpscap
        long targetTime=1000/FPS;
        while (gameIsRunning) 
        {
            startTime=System.nanoTime();
            tick();
            render();

            URDTimeMillis = ((System.nanoTime()-startTime)/10000000);
            waitTime=targetTime-URDTimeMillis;
            
            try  //predkosc gry
            {
                Thread.sleep(6); // niestety nie moge lockowac fpsow, bo gra dziala za wolno, a jak przestawie predkosc poruszania lub grawitacje, to psuja sie kolizje
            } 
            catch (InterruptedException ex) {
               ex.printStackTrace();
            }

            totalTime+=System.nanoTime()-startTime;
            frameCount++;
            if(frameCount==maxFrameCount)
            {
                averageFPS=1000.0 /((totalTime/frameCount)/1000000);
                frameCount=0;
                totalTime=0;
            }
        }
    }
    
    
    
    
    

    
    
    public static void main(String args[]) {

        //Point windowCenterPoint=new Point();
        //windowCenterPoint= GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        Plansza plansza1 = new Plansza();

        JFrame frame1 = new JFrame();

        frame1.add(plansza1);
        frame1.pack(); //ustawia w i h komponentów okna do rozdzialki gry
        frame1.setResizable(true);
        frame1.setLocationRelativeTo(null); //centrowanie okna
        frame1.setTitle(tytul);

        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true);

        plansza1.start();
    }

}
