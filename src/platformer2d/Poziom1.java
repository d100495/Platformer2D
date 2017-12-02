package platformer2d;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;
import static platformer2d.Plansza.piksele;
import static platformer2d.Plansza.scrollingX;

/**
 *
 * @author Dunger
 */
public class Poziom1 
{
   
    public Blok[][] bloki = new Blok[100][15];//wiersze, kolumny planszy
    
    
    public Poziom1() 
    {
        for(int x =0; x<bloki.length;x++)
        {
            for(int y=0; y<bloki[x].length;y++)
            {
                bloki[x][y] =new Blok(
                                new Rectangle(
                                        x*Kafelek.kafelekSize,
                                        y*Kafelek.kafelekSize,
                                        Kafelek.kafelekSize,
                                        Kafelek.kafelekSize),
                                        Kafelek.powietrze);
            }
        }//wypelnianie powietrzem
        
        StworzPoziom();//wypelnianie kafelkami
    }
    
    public void StworzPoziom()
    {
        System.out.println("wchodze do stworzpozioom");
        System.out.println(bloki.length);
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
               
               if((x==0 || x==bloki.length-1) && y==bloki[x].length-2)
               {
                   bloki[x][y].blokID=Kafelek.ziemia;
               }
               
               for(int xx=0;xx<12;xx++)
               {
                    if(y==(bloki[x].length-3)-xx && x>20+xx && x<30)
                    {
                    bloki[x][y].blokID=Kafelek.ziemia;
                    } 
               }
               
                 
                  
            }
        }
    }
    
    public void tick()
    {
        
    }
    
    
    
    Image chmura = new ImageIcon(System.getProperty("user.dir")+"\\src\\resources\\chmura1.png").getImage();   
    public void render(Graphics grap_arg)
    {
       //Rysowanie tla
       grap_arg.setColor(new Color(83, 157, 164));
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
