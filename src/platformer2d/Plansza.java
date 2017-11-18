package platformer2d;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.*;
import javax.swing.JFrame;



public class Plansza extends Applet implements Runnable //Applet, zeby mozna wstawic na strone www
{

    //Wyglad ....
    //klasa Dimension zawiera parametry szerokosc i wysokosc do okna
    protected static final Dimension rozmiarOkna = new Dimension(1280, 720);
    public static String tytul = "Przygody Wnęko - Platformer 2D";
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
    public static int scrollingX=0, scrollingY=0;
    public static double kierunekPostaci=0;
    
    
    public static boolean isCharacterMoving=false;
    public static boolean gameIsRunning = false;
    

    
    //Poziomy,obiekty........
    public static Poziom level_1;
    public static PostacGracza postac_1;
    

    
    
    
    @Override
    public void start() 
    {
            
    System.out.println("asfg");
        //Obiekty
        new Kafelek(); //ładowanie obrazków
        level_1 = new Poziom();
        postac_1 = new PostacGracza(Kafelek.kafelekSize, Kafelek.kafelekSize);

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

    
    public static BufferedImage tloPlanszy;
    public void render() 
    {
        Graphics graph1 = obrazekEkranu.getGraphics();

        //Rysowanie
        graph1.setColor(Color.CYAN);
        graph1.fillRect(0, 0, piksele.width, piksele.height);//tworzenie tła
        
       
         File background_dayFile = new File(System.getProperty("user.dir")+"\\src\\resources\\background_day.jpg");  
         try
         {
         tloPlanszy = ImageIO.read(background_dayFile);
         }
         catch(Exception e)
        {
        //throw new UnsupportedOperationException("Błąd ładowania obrazków"); 
        }
       
         
        graph1.drawImage(tloPlanszy, 0, 0, this);
        
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

            
            try 
            {
                Thread.sleep(10);
            } 
            catch (InterruptedException ex) {
                throw new UnsupportedOperationException("Thread.Sleep Exception.");
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
