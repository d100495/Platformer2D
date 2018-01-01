/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformer2d.Poziomy;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.management.StringValueExp;
import javax.swing.ImageIcon;
import platformer2d.Blok;
import platformer2d.Kafelek;
import platformer2d.Plansza;
import static platformer2d.Plansza.piksele;
import static platformer2d.Plansza.scrollingX;
import static platformer2d.Plansza.timerValue;
import platformer2d.Postacie.BluePortal;
import platformer2d.Postacie.PostacGracza;

/**
 *
 * @author Dunger
 */
public final class ScoreRoom extends Poziom
{
    
    public ScoreRoom(int W, int H) {
        super(W, H);
        StworzPoziom();//wypelnianie innymi kafelkami
    }

    @Override
    public void StworzPoziom() 
    {
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
    }

    @Override
    public void tick() 
    {
        
    }

      Font myFont1 = new Font ("Tahoma", 1, 30);
      Font myFont2 = new Font ("Tahoma", 1, 20);
    @Override
    public void render(Graphics grap_arg)
    {
       //Rysowanie tla
       grap_arg.setColor(Color.black);
       grap_arg.fillRect(0, 0, piksele.width, piksele.height);
        
       
       //Rysowanie blokow
       for(int x =0; x<bloki.length;x++)
        {
            for (int y = 0; y<bloki[x].length; y++) {
                bloki[x][y].renderBlok(grap_arg);
            }
        }
    
       
       //Rysowanie dodatkow
      //grap_arg.drawImage(chmura, 250-scrollingX, 328-Plansza.scrollingY, null);  
      grap_arg.setColor(Color.white);
      grap_arg.setFont(myFont1);
      grap_arg.drawString("Twój czas przejścia: ", piksele.width/2-150, 35);
        grap_arg.setFont(myFont2);
      grap_arg.drawString("Poziomu 1: "+BluePortal.czasPoziomu_1+" sekund",  piksele.width/2-100, 85);
      grap_arg.drawString("Poziomu 2: "+BluePortal.czasPoziomu_2+" sekund", piksele.width/2-100, 120);
     
       
      
    }//renderowanie poziomu z obiektów graficznych
     
    
}
