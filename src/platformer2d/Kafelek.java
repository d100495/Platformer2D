package platformer2d;

import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.*;


public class Kafelek //moze sie skladac z wiekszej ilosci blokow
{
    //bloki
    public static int[] powietrze = {-1,-1};
    public static int[] ziemia = {0,0};
    public static int [] trawa = {1,0};
       
       
    //postac
    public static int[] postacGraczaIdle = {0,19};
    public static int [] postacGraczaRun = {0,18};
    public static int [] postacGraczaJump = {0,17};
    public static int [] postacGraczaFallingDown = {0,16};
    public static int [] pustyTile = {8,19};
    
    //moby
     public static int [] smallGreenSlimeJumping = {0,12};
     public static int [] smallRedSlimeRolling = {0,11};
     public static int [] smallRedSlimeDying = {10,11};
    
    public static int [] bluePortal = {0,10};
     
    public static BufferedImage kafelki_TileSet;
    public static int kafelekSize = 32;
    

    
    public Kafelek()
    {
        try
        {
            File kafelkiPNGFile = new File(System.getProperty("user.dir")+"\\src\\resources\\KafelkiPNG.png");
            
            System.out.println("file: "+kafelkiPNGFile.getAbsoluteFile());
            
        Kafelek.kafelki_TileSet = ImageIO.read(kafelkiPNGFile);
        }
        catch(Exception e)
        {
        //throw new UnsupportedOperationException("Błąd ładowania obrazków"); 
        }
    }
    
    
   
    
    
}
