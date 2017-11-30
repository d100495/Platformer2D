package platformer2d;

import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.*;


public class Kafelek //moze sie skladac z wiekszej ilosci blokow
{
    public static int[] powietrze = {-1,-1};
    public static int[] ziemia = {0,0};
    public static int[] postacGraczaIdle = {0,19};
    public static int [] postacGraczaRun = {0,18};
    public static int [] trawa = {1,0};
    
    public static BufferedImage kafelki_teren;
    public static int kafelekSize = 32;
    

    
    public Kafelek()
    {
        try
        {
            File kafelkiPNGFile = new File(System.getProperty("user.dir")+"\\src\\resources\\KafelkiPNG.png");
            
            System.out.println("file: "+kafelkiPNGFile.getAbsoluteFile());
            
        Kafelek.kafelki_teren = ImageIO.read(kafelkiPNGFile);
        }
        catch(Exception e)
        {
        //throw new UnsupportedOperationException("Błąd ładowania obrazków"); 
        }
    }
    
    
   
    
    
}
