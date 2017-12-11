/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformer2d.Postacie;

import java.awt.Graphics;
import java.awt.Point;
import platformer2d.Kafelek;
import platformer2d.Plansza;



/**
 *
 * @author Dunger
 */
public class MalyZielonySlime extends Postac
{

    
    public static double kierunekPostaci=0;
    public final double grawitacja = 2;
    public final double predkoscPoruszania=0.3;
    
   
    public int animacja=0;
    public int klatkaAnimacji = 0;
    public final int czasAnimacji=10;
    
    
    public MalyZielonySlime(int x, int y, int szer, int wys)
    {
        this.x=x;
        this.y=y;
        this.szer=szer;
        this.wys=wys;
    }
    
    @Override
    public void tick() 
    {
        
        Point puntk_1 = new Point((int)this.x+2,(int)(this.y+this.wys)); //lewo dol
        Point punkt_2 = new Point((int)(this.x+this.szer-2),(int)(y+this.wys)); //prawo dol
     
        if(!kolizjaBlok(puntk_1, punkt_2)) //kolizja dol
        {
            y+=grawitacja;
        }
       
    
        if(kierunekPostaci==0)
        {
            x+=predkoscPoruszania;
            if(x>400)
            {
                kierunekPostaci=1;
            }
        }
        if(kierunekPostaci==1)
        {
             x-=predkoscPoruszania;
            if(x<100)
            {
                kierunekPostaci=0;
            }
        }
     
        
        if(klatkaAnimacji>=czasAnimacji) //Animacje
        {
             if(animacja>=8) //Na razie 7 ,bo mi sie nie chce wincyj
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
        
        
        
        
        if(kolizjaGracz(Plansza.postac, this)) //logika kolizji z graczem
        {
            Plansza.reload();
        }
    }
    

@Override
    public void render(Graphics graph_arg) 
    {
         //=============================
        //rendering poruszanie w lewo
        //=============================
                if(kierunekPostaci == predkoscPoruszania)
                {
                     
                            graph_arg.drawImage(
                            Kafelek.kafelki_teren, 
                            (int)x - Plansza.scrollingX, 
                            (int)y - Plansza.scrollingY,
                            (int)(x+szer)- Plansza.scrollingX,
                            (int)(y+wys) - Plansza.scrollingY,
                            Kafelek.smallGreenSlimeJumping[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja),
                            Kafelek.smallGreenSlimeJumping[1]*Kafelek.kafelekSize,
                            Kafelek.smallGreenSlimeJumping[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja)+(int)szer,
                            Kafelek.smallGreenSlimeJumping[1]*Kafelek.kafelekSize+(int)wys,
                            null);
                }

                    //=============================
                    //rendering poruszanie w prawo
                    //=============================

                    else 
                    {
                        graph_arg.drawImage(
                        Kafelek.kafelki_teren, 
                        (int)x - Plansza.scrollingX, 
                        (int)y - Plansza.scrollingY,
                        (int)(x+szer)- Plansza.scrollingX,
                        (int)(y+wys) - Plansza.scrollingY,
                        Kafelek.smallGreenSlimeJumping[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja)+(int)szer,
                        Kafelek.smallGreenSlimeJumping[1]*Kafelek.kafelekSize,
                        Kafelek.smallGreenSlimeJumping[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja),
                        Kafelek.smallGreenSlimeJumping[1]*Kafelek.kafelekSize+(int)wys,
                        null);
                    }
    
                }
    
    
    
    
    public boolean kolizjaBlok(Point pkt1, Point pkt2)
    {
        for(int x = (int)(this.x/Kafelek.kafelekSize);x<(int)(this.x/(Kafelek.kafelekSize)+2);x++)
        {
            for(int y = (int)(this.y/Kafelek.kafelekSize);y<(int)(this.y/(Kafelek.kafelekSize)+2);y++)
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
    
    
    public boolean kolizjaGracz(Postac postac1 , Postac malySlime)
    {
      
       if (postac1.x > malySlime.x + malySlime.szer ||
           postac1.x + postac1.szer < malySlime.x ||
          postac1.y > malySlime.y + malySlime.wys || 
            postac1.y + postac1.wys < malySlime.y)
       {
           return false;
       }
        
 
    else
        return true;
    
    
    }
 
 
    
    
    
    
    
    
}


