package platformer2d.Poziomy;

import platformer2d.Poziomy.Poziom;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;
import platformer2d.Blok;
import platformer2d.Kafelek;
import static platformer2d.Plansza.piksele;
import static platformer2d.Plansza.scrollingX;

/**
 *
 * @author Dunger
 */
public final class Poziom2 extends Poziom
{

    public Poziom2(int W, int H) {
        super(W, H);
        StworzPoziom();//wypelnianie innymi kafelkami
    }
    
    
    @Override
    public Blok[][] getBloki()
    {
        return bloki;
    }
    

    @Override
    public void StworzPoziom()
    {
        System.out.println("wchodze do stworzpozioom");
        System.out.println("Bloki.length: " + bloki.length);
         for(int x =0; x<bloki.length;x++)
        {
            for(int y=0; y<bloki[x].length;y++)
            {
               if(x==0 || y ==0 || x==bloki.length-1 || y==bloki[x].length-1)
               {
                   bloki[x][y].blokID=Kafelek.ziemia;
               }
               
               if(y==bloki[x].length-2)
               {
                   bloki[x][y].blokID=Kafelek.trawa;
               }
               
         
               
                 
                  
            }
        }
    }

    
    @Override
    public void tick()
    {
        
    }
    
    
    Image chmura = new ImageIcon(System.getProperty("user.dir") + "\\src\\resources\\chmura1.png").getImage();
    
    @Override
    public void render(Graphics grap_arg)
    {
       //Rysowanie tla
       grap_arg.setColor(Color.orange);
       grap_arg.fillRect(0, 0, piksele.width, piksele.height);
        
       
       //Rysowanie blokow
       for(int x =0; x<bloki.length;x++)
        {
            for (int y = 0; y<bloki[x].length; y++) {
                bloki[x][y].renderBlok(grap_arg);
            }
        }
    
       
       //Rysowanie dodatkow
        grap_arg.drawImage(chmura, 2300-scrollingX, 150, null);  
        grap_arg.drawImage(chmura, 650-scrollingX, 50, null);  
        grap_arg.drawImage(chmura, 1200-scrollingX, 100, null);  
        grap_arg.drawImage(chmura, 250-scrollingX, 140, null);  
      
       
      
    }//renderowanie poziomu z obiektów graficznych
     
}
