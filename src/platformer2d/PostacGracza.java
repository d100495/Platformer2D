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
        this.y=((Plansza.piksele.height/2)-(wys)/2);       

        this.szer=szer;
        this.wys=wys;
    }
     
      public PostacGracza(int x, int y,int szer, int wys)
    {
        this.x=x;
        this.y=y;

        this.szer=szer;
        this.wys=wys;
    }
     

     
    public double grawitacja = 15;
    public double predkoscPoruszania=7;
    
    public int animacja=0;
    public int klatkaAnimacji = 0, czasAnimacji=2;
    
    
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
      
        
        
        if(klatkaAnimacji>=czasAnimacji) //Animacje
        {
             if(animacja>=7) //Na razie 4 ,bo mi sie nie chce wincyj
            {
                animacja=0;
            }
            else
            {
               animacja+=1;  
            }
            
                klatkaAnimacji=0;   
            }
        else
        {
            klatkaAnimacji+=1;
        }

        
        if(y>=Plansza.level.bloki[0].length*Kafelek.kafelekSize)
        {
            Plansza.reload();
        }
    }



    @Override
    public void render(Graphics graph_arg) 
    {
        if(Plansza.kierunekPostaci == predkoscPoruszania)//rendering poruszania w lewo
        {
            if(Plansza.isCharacterMoving==true) 
            {
                graph_arg.drawImage(
                Kafelek.kafelki_teren, 
                (int)x - Plansza.scrollingX, 
                (int)y - Plansza.scrollingY,
                (int)(x+szer)- Plansza.scrollingX,
                (int)(y+wys) - Plansza.scrollingY,
                Kafelek.postacGraczaRun[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja),
                Kafelek.postacGraczaRun[1]*Kafelek.kafelekSize,
                Kafelek.postacGraczaRun[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja)+(int)szer,
                Kafelek.postacGraczaRun[1]*Kafelek.kafelekSize+(int)wys,
                null);
            }
            else
            {
                graph_arg.drawImage(
                Kafelek.kafelki_teren, 
                (int)x - Plansza.scrollingX, 
                (int)y - Plansza.scrollingY,
                (int)(x+szer)- Plansza.scrollingX,
                (int)(y+wys) - Plansza.scrollingY,
                Kafelek.postacGraczaIdle[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja),
                Kafelek.postacGraczaIdle[1]*Kafelek.kafelekSize,
                Kafelek.postacGraczaIdle[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja)+(int)szer,
                Kafelek.postacGraczaIdle[1]*Kafelek.kafelekSize+(int)wys,
                null);
            }
             
        }
        else // poruszanie w prawo
        {
            if(Plansza.isCharacterMoving==true) 
            {
                graph_arg.drawImage(
                Kafelek.kafelki_teren, 
                (int)x - Plansza.scrollingX, 
                (int)y - Plansza.scrollingY,
                (int)(x+szer)- Plansza.scrollingX,
                (int)(y+wys) - Plansza.scrollingY,
                Kafelek.postacGraczaRun[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja)+(int)szer,
                Kafelek.postacGraczaRun[1]*Kafelek.kafelekSize,
                Kafelek.postacGraczaRun[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja),
                Kafelek.postacGraczaRun[1]*Kafelek.kafelekSize+(int)wys,
                null);
            }
            else
            {
                graph_arg.drawImage(
                Kafelek.kafelki_teren, 
                (int)x - Plansza.scrollingX, 
                (int)y - Plansza.scrollingY,
                (int)(x+szer)- Plansza.scrollingX,
                (int)(y+wys) - Plansza.scrollingY,
                Kafelek.postacGraczaIdle[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja)+(int)szer,
                Kafelek.postacGraczaIdle[1]*Kafelek.kafelekSize,
                Kafelek.postacGraczaIdle[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja),
                Kafelek.postacGraczaIdle[1]*Kafelek.kafelekSize+(int)wys,
                null);
            }
        }
        
        
   
    }
    
    
    public boolean kolizjaBlok(Point pkt1, Point pkt2)
    {
        for(int x = (int)(this.x/Kafelek.kafelekSize);x<(int)(this.x/Kafelek.kafelekSize+2);x++)
        {
            for(int y = (int)(this.y/Kafelek.kafelekSize);y<(int)(this.y/Kafelek.kafelekSize+2);y++)
            {
                if(x>=0&& y>=0 && x< Plansza.level.bloki.length && y<Plansza.level.bloki[0].length)
                if(Plansza.level.bloki[x][y].blokID!=Kafelek.powietrze)
                {
                     if(Plansza.level.bloki[x][y].contains(pkt1) || Plansza.level.bloki[x][y].contains(pkt2))
                    {
                    return true;
                    }
                }
               
            }
            
        }
        return false;
    }
    
}
