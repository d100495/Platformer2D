/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformer2d.Postacie;

import java.awt.Graphics;
import platformer2d.Kafelek;
import platformer2d.Plansza;

/**
 *
 * @author Dunger
 */
public class BluePortal extends Postac 
{

    
    private int animacja=0;
    private int klatkaAnimacji = 0;
    private final int czasAnimacji=10;
    
    public static int czasPoziomu_1;
    public static int czasPoziomu_2;
    
    
      public BluePortal(int x, int y, int szer, int wys)
    {
        this.x=x;
        this.y=y;
        this.szer=szer;
        this.wys=wys;
    }
    
      
      
    @Override
    public void tick() 
    {
        
        if(klatkaAnimacji>=czasAnimacji) //Animacje
        {
             if(animacja>=7) //Na razie 7 ,bo mi sie nie chce wincyj
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
            if(Plansza.nrPoziomu==1)
            {
                czasPoziomu_1=Plansza.timerValue;
            }
             if(Plansza.nrPoziomu==2)
            {
                czasPoziomu_2=Plansza.timerValue;
            }
            
            
            
           if(Plansza.nrPoziomu>=3)
           {
               Plansza.nrPoziomu=1;
               
               czasPoziomu_1=0;
               czasPoziomu_2=0;
           }
           else
           {
               Plansza.nrPoziomu++; 
           }
            
           Plansza.reload();
        }
        
        
    }
    

    @Override
    public void render(Graphics graph_arg) {

        graph_arg.drawImage(
                Kafelek.kafelki_teren,
                (int) x - Plansza.scrollingX,
                (int) y - Plansza.scrollingY,
                (int) (x + szer) - Plansza.scrollingX,
                (int) (y + wys) - Plansza.scrollingY,
                Kafelek.bluePortal[0] * Kafelek.kafelekSize + (Kafelek.kafelekSize * animacja),
                Kafelek.bluePortal[1] * Kafelek.kafelekSize,
                Kafelek.bluePortal[0] * Kafelek.kafelekSize + (Kafelek.kafelekSize * animacja) + (int) szer,
                Kafelek.bluePortal[1] * Kafelek.kafelekSize + (int) wys,
                null);
    }

}
