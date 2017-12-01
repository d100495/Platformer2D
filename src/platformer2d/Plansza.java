package platformer2d;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
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
    public static String tytul = "Platformer 2D";
    private static Image obrazekEkranu;
    
    private static final int rozmiarPiksela = 2; //czyli jakby przyblizenie ekranu
    protected static final Dimension piksele = new Dimension(
            rozmiarOkna.width / rozmiarPiksela,
            rozmiarOkna.height / rozmiarPiksela);

    
     public Plansza()
    {
        setPreferredSize(rozmiarOkna);
        addKeyListener(new Sterowanie());
    }
    
     
     
    //Logika, poruszanie...
    public static int scrollingX=0, scrollingY=-20;
    public static double kierunekPostaci=0;
    
    
    public static boolean isCharacterMoving=false;
    public static boolean gameIsRunning = false;
    

    
    //Poziomy,obiekty........
    public static Poziom1 level_1;
    public static PostacGracza postac_1;
    

    
    
    
    @Override
    public void start() 
    {
            
    System.out.println("test");
        //Obiekty
        new Kafelek(); //ładowanie obrazków
        level_1 = new Poziom1();
        postac_1 = new PostacGracza(100,250,Kafelek.kafelekSize, Kafelek.kafelekSize);

        //Pętla gry
        gameIsRunning = true; //rozpoczęcie gry
        new Thread(this).start(); //nowy watek dla Komponentu
    }

    
    @Override
    public void stop() 
    {
        gameIsRunning = false;
    }

    
    
    public void tick() //wszystkie metody tick z obiektow
    {
        level_1.tick();
        postac_1.tick();
    }

    //Zmienne do renderowania
    //public static BufferedImage tloPlanszy=null;
   // public static BufferedImage chmura=null;
    
//    Random randomNumber = new Random();
//    int chmuraY1 = randomNumber.nextInt(200)+150;
//    int chmuraY2= randomNumber.nextInt(200)+150;
//    int chmuraY3 = randomNumber.nextInt(200)+150;
//    int chmuraY4 = randomNumber.nextInt(200)+150;
        
    
    public void render() 
    {
        //Obiekty graficzne planszy
                Graphics graph1 = obrazekEkranu.getGraphics(); // plansza nr1


        //Pliki tła planszy
                //File background_dayFile = new File(System.getProperty("user.dir")+"\\src\\resources\\background_day.jpg");  
                Image chmura = new ImageIcon(System.getProperty("user.dir")+"\\src\\resources\\chmura1.png").getImage();   
                //Image icon = new ImageIcon(System.getProperty("user.dir")+"\\src\\resources\\giphy.gif").getImage();  
                try
                {
               // tloPlanszy = ImageIO.read(background_dayFile);
                   // chmura = ImageIO.read(chmura_png);
                    
                }
                catch(Exception e)
               {
               e.printStackTrace();
               }

               //graph1.drawImage(tloPlanszy, -scrollingX, 0, null);
               
               
         //Rysowanie
                graph1.setColor(new Color(83, 157, 164));
                graph1.fillRect(0, 0, piksele.width, piksele.height);//tworzenie tła
                graph1.drawImage(chmura, 2300-scrollingX, 150, this);  
                graph1.drawImage(chmura, 650-scrollingX, 50, this);  
                graph1.drawImage(chmura, 1200-scrollingX, 100, this);  
                graph1.drawImage(chmura, 250-scrollingX, 140, this);       
            
            
        //Rendering (wszystkie metody render)
                level_1.renderPoziom(graph1);
                postac_1.render(graph1);

        
        
        graph1 = getGraphics();
        
        

        graph1.drawImage(obrazekEkranu, 0, 0, rozmiarOkna.width, rozmiarOkna.height, 0, 0, piksele.width, piksele.height, null);
        graph1.dispose();//zwalnianie pamieci
    }

    
    
    @Override
    public void run() 
    {
        System.out.println("wchodze do kafelek/run");
        obrazekEkranu = createVolatileImage(piksele.width, piksele.height); // podwójne bufforowanie, żeby obraz nie skakał
        
        
        while (gameIsRunning) 
        {
            tick();
            render();

            
            try  //predkosc gry
            {
                Thread.sleep(25);
            } 
            catch (InterruptedException ex) {
               ex.printStackTrace();
            }

        }
    }
    
    
    
    
    

    
    
    public static void main(String args[]) {

        //Point windowCenterPoint=new Point();
        //windowCenterPoint= GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        Plansza component1 = new Plansza();

        JFrame frame1 = new JFrame();

        frame1.add(component1);
        frame1.pack(); //ustawia w i h komponentów okna do rozdzialki gry
        frame1.setResizable(true);
        frame1.setLocationRelativeTo(null); //centrowanie okna
        frame1.setTitle(tytul);

        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true);

        component1.start();
    }

}
