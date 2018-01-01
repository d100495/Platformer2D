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
public class MalyCzerwonySlime extends Postac
{

    
    private double kierunekPostaci=1;
    private final double grawitacja = 2;
    private final double predkoscPoruszania=0.3;
    
   
    private int animacja=0;
    private int klatkaAnimacji = 0;
    private final int czasAnimacji=10;
    
    private int health=20;
    
    private boolean czyNazdepniety = false;

   
    
    
    public MalyCzerwonySlime(int x, int y, int szer, int wys)
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
       
    
       if(this.kierunekPostaci==0)  //Logika poruszania sie slima
        {
            this.x+=this.predkoscPoruszania;
            
            Point punkt_3 = new Point((int)x+2,(int)(y)); //lewo gora
                Point punkt_4 = new Point((int)(this.x+this.szer-2),(int)(y)); //prawo gora
            
            if(kolizjaBlok(punkt_4,punkt_3))
            { 
                this.kierunekPostaci=1;
            }
        }
        
        if(this.kierunekPostaci==1)
        {
             this.x-=this.predkoscPoruszania;
             
            Point punkt_3 = new Point((int)x+2,(int)(y)); //lewo gora
                Point punkt_4 = new Point((int)(this.x+this.szer-2),(int)(y)); //prawo gora
            
            if(kolizjaBlok(punkt_4,punkt_3))
            { 
                this.kierunekPostaci=0;
            }
             
        }
     
        
        
        
        
        if(klatkaAnimacji>=czasAnimacji) //Animacje
        {
             if(animacja>=9) //Na razie 7 ,bo mi sie nie chce wincyj
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
        
         
        //Wykrywanie wypadniecia poza plansze
         if((this.x>=0&& this.y>=0 
                 && this.x< Plansza.level.bloki.length*Kafelek.kafelekSize 
                 && this.y<Plansza.level.bloki[0].length*Kafelek.kafelekSize)==false)
        {
            this.czyUmar=true;
        }
        
        
        if(kolizjaGracz(Plansza.postac, this)) //logika kolizji z graczem
        {
              if(PostacGracza.isAbleToGetHealth==true && PostacGracza.healthValue<100)
            {
                health--;
                PostacGracza.healthValue+=1;
                PostacGracza.isAbleToGetHealth = false;
                PostacGracza.healingTimer = System.nanoTime();
                System.out.println("Healing");
            }
           
            czyNazdepniety=true;
        }
        else
        {
            
            czyNazdepniety=false;
        }
        
        
        if(health<0)
        {
            this.czyUmar=true;
        }
            
           
    }
    

@Override
    public void render(Graphics graph_arg) 
    {
         //=============================
        //rendering poruszanie w lewo
        //=============================
                if(kierunekPostaci == 1)
                {
                     
                     if(czyNazdepniety==true)
                    {
                     graph_arg.drawImage(
                            Kafelek.kafelki_teren, 
                            (int)x - Plansza.scrollingX, 
                            (int)y - Plansza.scrollingY,
                            (int)(x+szer)- Plansza.scrollingX,
                            (int)(y+wys) - Plansza.scrollingY,
                            Kafelek.smallRedSlimeDying[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja),
                            Kafelek.smallRedSlimeDying[1]*Kafelek.kafelekSize,
                            Kafelek.smallRedSlimeDying[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja)+(int)szer,
                            Kafelek.smallRedSlimeDying[1]*Kafelek.kafelekSize+(int)wys,
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
                            Kafelek.smallRedSlimeRolling[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja),
                            Kafelek.smallRedSlimeRolling[1]*Kafelek.kafelekSize,
                            Kafelek.smallRedSlimeRolling[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja)+(int)szer,
                            Kafelek.smallRedSlimeRolling[1]*Kafelek.kafelekSize+(int)wys,
                            null);  
                         
                    }
                            
                            
                            
                            
                }
                else{
                    //=============================
                    //rendering poruszanie w prawo
                    //=============================

                    if(czyNazdepniety==true)
                    {
                        graph_arg.drawImage(
                        Kafelek.kafelki_teren, 
                        (int)x - Plansza.scrollingX, 
                        (int)y - Plansza.scrollingY,
                        (int)(x+szer)- Plansza.scrollingX,
                        (int)(y+wys) - Plansza.scrollingY,
                        Kafelek.smallRedSlimeDying[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja)+(int)szer,
                        Kafelek.smallRedSlimeDying[1]*Kafelek.kafelekSize,
                        Kafelek.smallRedSlimeDying[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja),
                        Kafelek.smallRedSlimeDying[1]*Kafelek.kafelekSize+(int)wys,
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
                        Kafelek.smallRedSlimeRolling[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja)+(int)szer,
                        Kafelek.smallRedSlimeRolling[1]*Kafelek.kafelekSize,
                        Kafelek.smallRedSlimeRolling[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja),
                        Kafelek.smallRedSlimeRolling[1]*Kafelek.kafelekSize+(int)wys,
                        null);
                         
                    }
    
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
    
    
    
    
    
}


