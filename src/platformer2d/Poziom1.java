package platformer2d;

import java.awt.Graphics;
import java.awt.Rectangle;

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
            }
        }
    }
    
    public void tick()
    {
        
    }
    
    public void renderPoziom(Graphics grap_arg)
    {
       for(int x =0; x<bloki.length;x++)
        {
            for (int y = 0; y<bloki[x].length; y++) {
                bloki[x][y].renderBlok(grap_arg);
            }
        }
    }//renderowanie poziomu z obiektów graficznych
     
}
