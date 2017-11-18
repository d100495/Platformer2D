package platformer2d;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author Dunger
 */
public class PostacGracza extends Postac
{

     public PostacGracza(int szer, int wys)
    {
        this.x=((Plansza.piksele.width/2)-(szer/2));
        this.y=((Plansza.piksele.height)-(3*wys));       

        this.szer=szer;
        this.wys=wys;
    }
     
     

     
    public double grawitacja = 15;
    public double predkoscPoruszania=7;
    
    
    @Override
    public void tick() 
    {
        Point puntk_1 = new Point((int)x,(int)(y+wys));
        Point punkt_2 = new Point((int)(x+szer),(int)(y+wys));
       
        if(!kolizjaBlok(puntk_1, punkt_2))
        {
            y+=grawitacja;
            Plansza.scrollingY+=grawitacja;
        }
        
     
        if(Plansza.isCharacterMoving==true)
        {
            x+=Plansza.kierunekPostaci;
            Plansza.scrollingX+=Plansza.kierunekPostaci;
        }
        
    }



    @Override
    public void render(Graphics graph_arg) 
    {
        graph_arg.drawImage(
                Kafelek.kafelki_teren, 
                (int)x - Plansza.scrollingX, 
                (int)y - Plansza.scrollingY,
                (int)(x+szer)- Plansza.scrollingX,
                (int)(y+wys) - Plansza.scrollingY,
                Kafelek.postacGracza[0]*Kafelek.kafelekSize,
                Kafelek.postacGracza[1]*Kafelek.kafelekSize,
                Kafelek.postacGracza[0]*Kafelek.kafelekSize+(int)szer,
                Kafelek.postacGracza[1]*Kafelek.kafelekSize+(int)wys,
                null);
        
   
    }
    
    
    public boolean kolizjaBlok(Point pkt1, Point pkt2)
    {
        for(int x = (int)(this.x/Kafelek.kafelekSize);x<(int)(this.x/Kafelek.kafelekSize+3);x++)
        {
            for(int y = (int)(this.y/Kafelek.kafelekSize);y<(int)(this.y/Kafelek.kafelekSize+3);y++)
            {
                if(x>=0&& y>=0 && x< Plansza.level_1.bloki.length && y<Plansza.level_1.bloki[0].length)
                if(Plansza.level_1.bloki[x][y].blokID!=Kafelek.powietrze)
                {
                     if(Plansza.level_1.bloki[x][y].contains(pkt1) || Plansza.level_1.bloki[x][y].contains(pkt2))
                    {
                    return true;
                    }
                }
               
            }
            
        }
        return false;
    }
    
}
