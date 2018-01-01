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
import platformer2d.Plansza;
import static platformer2d.Plansza.piksele;
import static platformer2d.Plansza.scrollingX;

/**
 *
 * @author Dunger
 */
public final class Poziom1 extends Poziom
{

    public Poziom1(int W, int H) {
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
               
               
               if(y==bloki[x].length-2)
               {
                   bloki[x][y].blokID=Kafelek.trawa;
               }
               
               
               
               if((x==0 || x==bloki.length-1) && y==bloki[x].length-2)
               {
                   bloki[x][y].blokID=Kafelek.ziemia;
               }
               
               for(int xx=0;xx<6;xx++) //schodki
               {
                    if(y==(bloki[x].length-3)-xx+1 && x>20+xx && x<30)
                    {
                    bloki[x][y].blokID=Kafelek.ziemia;
                    } 
               }

                 if(((x>42 && x<=44) && y==bloki[x].length-5)) //pierwsza kładka
               {
                   bloki[x][y].blokID=Kafelek.ziemia;
               }
               
                 
               if(x==0 || y ==0 || x==bloki.length-1 || y==bloki[x].length-1) //OBRAMOWANIE =====================
               {
                   bloki[x][y].blokID=Kafelek.ziemia;
               }
               
               if(((x>40 && x<=50) && (y==bloki[x].length-2 || y==bloki[x].length-1))) //pierwsza przepasc
               {
                   bloki[x][y].blokID=Kafelek.powietrze;
               }
               
                  if(((x>70 && x<75) && (y==bloki[x].length-2 || y==bloki[x].length-1))) //druga przepasc
               {
                   bloki[x][y].blokID=Kafelek.powietrze;
               }
                  
                  
            }
        }
    }

    
    @Override
    public void tick()
    {
        
    }
    
    
    Image chmura = new ImageIcon(System.getProperty("user.dir") + "\\src\\resources\\chmura1.png").getImage();
     Image drzewo = new ImageIcon(System.getProperty("user.dir") + "\\src\\resources\\tree.png").getImage();
    
    @Override
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
        grap_arg.drawImage(chmura, 250-scrollingX, 340-Plansza.scrollingY, null);  
        grap_arg.drawImage(drzewo, 350-scrollingX, 328-Plansza.scrollingY, null);  
        grap_arg.drawImage(chmura, 550-scrollingX, 240-Plansza.scrollingY, null);  
        grap_arg.drawImage(drzewo, 1000-scrollingX, 328-Plansza.scrollingY, null);  
        grap_arg.drawImage(chmura, 950-scrollingX, 150-Plansza.scrollingY, null);  
        grap_arg.drawImage(chmura, 1150-scrollingX, 200-Plansza.scrollingY, null);  
        grap_arg.drawImage(chmura, 1600-scrollingX, 400-Plansza.scrollingY, null);  
        grap_arg.drawImage(chmura, 1800-scrollingX, 340-Plansza.scrollingY, null);  
        grap_arg.drawImage(chmura, 2300-scrollingX, 350-Plansza.scrollingY, null);  

      
       
      
    }//renderowanie poziomu z obiektów graficznych
     
}
