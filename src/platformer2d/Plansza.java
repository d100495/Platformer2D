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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;
import platformer2d.Postacie.BluePortal;
import platformer2d.Postacie.MalyCzerwonySlime;
import platformer2d.Postacie.MalyZielonySlime;
import platformer2d.Postacie.Postac;
import platformer2d.Poziomy.ScoreRoom;



public class Plansza extends Applet implements Runnable //Applet, zeby mozna wstawic na strone www
{

    //Wyglad ....
    //klasa Dimension zawiera parametry szerokosc i wysokosc do okna
    protected static final Dimension rozmiarOkna = new Dimension(1280, 720);
    private static String tytul = "Platformer 2D";
    private static Image obrazekEkranu;
    
    private final int FPS =40;
    private double averageFPS;
    
    private int timerDelay;
    private Timer myTimer;
    public static int timerValue=0;
    
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
    private static boolean gameIsRunning = false;
    public static boolean isJumping=false;

    
    //Poziomy,obiekty........
    public static Poziom level;
    public static int nrPoziomu=1;
    public static PostacGracza postac;
    public static BluePortal portal;
    private static ArrayList<Postac> mobArrayList;
    

    
    //Menu
    public static enum STATE
    {
        MENU,GAME
    };
    public static STATE GameState=STATE.MENU;
    
    public static Menu menu = new Menu();
    
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
        timerDelay = 1000;
        myTimer = new Timer(timerDelay,gameTimer);
        myTimer.start();
        
    }

    ActionListener gameTimer = new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            timerValue++;
        }
    };
    
    
    @Override
    public void stop() 
    {
        gameIsRunning = false;
    }

    
    public static void reload()
    {
        timerValue=0;
        postac = new PostacGracza(100,520,Kafelek.kafelekSize, Kafelek.kafelekSize);
        PostacGracza.healthValue=100; 
        
        scrollingX=(int)postac.x;
        scrollingY=(int)postac.y-200;
        
        switch(nrPoziomu)
        {
            case 1:
            {
                level=new Poziom1(150, 20); // 2 wartosc nieparzysta (tymczasowo)
               portal=new BluePortal(
                       (150*Kafelek.kafelekSize)-(2*Kafelek.kafelekSize), //kafelek x portalu
                       (20*Kafelek.kafelekSize)-(3*Kafelek.kafelekSize), //kafelek y portalu
                        Kafelek.kafelekSize,
                        Kafelek.kafelekSize);

                        
                mobArrayList=new ArrayList<>();
                
                for(int i=0; i<20;i++)//Zielone SLIMY
                {
                    mobArrayList.add(new MalyZielonySlime(200+(new Random().nextInt(((level.bloki.length*Kafelek.kafelekSize)-200 ) + 1))
                            ,100
                            ,Kafelek.kafelekSize/2
                            ,Kafelek.kafelekSize/2
                            ,new Random().nextDouble()*(0.1 + (1.5 - 0.1)) 
                    ));
                }
                
                 for(int i=0; i<40;i++)//Czerwone SLIMY
                {
                    mobArrayList.add(new MalyCzerwonySlime(200+(new Random().nextInt(((level.bloki.length*Kafelek.kafelekSize)-200 ) + 1))
                            ,100
                            ,Kafelek.kafelekSize/2
                            ,Kafelek.kafelekSize/2));
                }

                
                
                
                break;
            }
            
            case 2:
            {
                level=new Poziom2(40, 20); 
                portal=new BluePortal(
                       (40*Kafelek.kafelekSize)-(2*Kafelek.kafelekSize), //kafelek x portalu
                       (20*Kafelek.kafelekSize)-(3*Kafelek.kafelekSize), //kafelek y portalu
                        Kafelek.kafelekSize,
                        Kafelek.kafelekSize);
                
                mobArrayList=new ArrayList<>();
                
                 for(int i=0; i<50;i++) //Czerwone SLIMY
                {
                    mobArrayList.add(new MalyCzerwonySlime(200+(new Random().nextInt(((level.bloki.length*Kafelek.kafelekSize)-200 ) + 1))
                            ,100
                            ,Kafelek.kafelekSize/2
                            ,Kafelek.kafelekSize/2));
                }
                
                   for(int i=0; i<3;i++) //Zielone SLIMY
                {
                    mobArrayList.add(new MalyZielonySlime(200+(new Random().nextInt(((level.bloki.length*Kafelek.kafelekSize)-200 ) + 1))
                            ,100
                            ,Kafelek.kafelekSize/2
                            ,Kafelek.kafelekSize/2
                            ,new Random().nextDouble()*(0.1 + (1.5 - 0.1)) 
                    ));
                }
                 
                break;
            }
            
            default:
            {
               level=new ScoreRoom(20, 20); // 2 wartosc nieparzysta (tymczasowo)
               portal=new BluePortal(
                       (20*Kafelek.kafelekSize)-(2*Kafelek.kafelekSize), //kafelek x portalu
                       (20*Kafelek.kafelekSize)-(3*Kafelek.kafelekSize), //kafelek y portalu
                       Kafelek.kafelekSize,
                       Kafelek.kafelekSize);
               mobArrayList=new ArrayList<>();
       
               break;
            }
        }
       
        
    }
    
    
    
    public void tick() //wszystkie metody tick z obiektow
    {
        if(GameState==STATE.GAME)
        {
        level.tick();
        postac.tick();
        portal.tick();
        myTimer.start();
        
        
            for(int i =0;i<mobArrayList.size();i++)
            {
                mobArrayList.get(i).tick();
                if(mobArrayList.get(i).czyUmar==true)
                {
                    System.out.println("mob " + i +" z klasy " + mobArrayList.get(i).getClass() + " umar ");
                    System.out.println("Pozostało: " + mobArrayList.size()+ " mobow");
                    mobArrayList.remove(i);
                }
            }

            
            
        }
        else
        {
          myTimer.stop();
        }
        
        
    }

        //Zmienne do renderowania
        Font myFont = new Font ("Arial", 1, 9);
        Font myFont2 = new Font ("Tahoma", 1, 9);
        Graphics graph1;
        
        Image healthBarImage = new ImageIcon(System.getProperty("user.dir") + "\\src\\resources\\HealthBar-Bar.png").getImage();
        Image healthCrossImage = new ImageIcon(System.getProperty("user.dir") + "\\src\\resources\\HealthBar-Cross.png").getImage();
    public void render() 
    {
        //Obiekty graficzne planszy
        graph1 = obrazekEkranu.getGraphics(); // plansza nr1
            
        if(GameState==STATE.GAME)
        {
            //Rendering (wszystkie metody render)
           level.render(graph1);
           postac.render(graph1);
           portal.render(graph1);
          
           //Timer
           graph1.setColor(Color.black);
           graph1.fillRect(piksele.width-120, 25, 65, 15);
           graph1.setColor(Color.white);
           graph1.setFont(myFont2);
           graph1.drawString("Czas: "+timerValue+"s", piksele.width-115, 35);
           
           
           //HealthBar
           graph1.drawImage(healthBarImage, piksele.width-120, 6, 110, 20, null);
           graph1.setColor(Color.RED);
           graph1.fillRect(piksele.width-115, 9, PostacGracza.healthValue, 14);
           graph1.drawImage(healthCrossImage, piksele.width-155, 6, 30, 20, null);
           graph1.setColor(Color.white);
           graph1.drawString(PostacGracza.healthValue+"%", piksele.width-80, 20);
           
           
            for(Postac x : mobArrayList)
            {
                x.render(graph1);
            }
            
            
        }
        else if(GameState==STATE.MENU)
        {
            menu.render(graph1);
        }
      
                
        //Fps counter        
        graph1.setColor(Color.yellow);
        graph1.setFont(myFont);
        graph1.drawString("FPS "+(int)averageFPS, 1, 9);
        
        
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
                Thread.sleep(7); //nie moge lockowac fpsow, bo gra dziala za wolno, a jak przestawie predkosc poruszania lub grawitacje, to psuja sie kolizje
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
