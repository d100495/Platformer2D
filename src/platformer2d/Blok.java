package platformer2d;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Dunger
 */
public class Blok extends Rectangle 
{
   public int [] blokID={-1,-1}; //powietrze
   

    public Blok(Rectangle prostokatSize, int [] blokID)
    { 
        setBounds(prostokatSize);
        this.blokID=blokID;
    }
    
    
    
    public void renderBlok(Graphics graph_arg)
    {
        if(Plansza.postac_1.x<300)
        {
          Plansza.scrollingX=0;  
        }
        if(Plansza.postac_1.x>Plansza.level_1.bloki.length*Kafelek.kafelekSize-350)
        {
          Plansza.scrollingX=((int)Plansza.level_1.bloki.length)*Kafelek.kafelekSize-(Plansza.rozmiarOkna.width/2);
        } //Ustawianie scrollingu do nie wychodzenia poza obszar planszy
        
        
        if(blokID!=Kafelek.powietrze)
        {
        graph_arg.drawImage(
                Kafelek.kafelki_teren,
                x - Plansza.scrollingX,
                y - Plansza.scrollingY,
                x+width - Plansza.scrollingX,
                y+height - Plansza.scrollingY,
                blokID[0]*Kafelek.kafelekSize,
                blokID[1]*Kafelek.kafelekSize,
                blokID[0]*Kafelek.kafelekSize + Kafelek.kafelekSize,
                blokID[1]*Kafelek.kafelekSize + Kafelek.kafelekSize,
                null);
//        
//        graph_arg.setColor(Color.yellow);
//        graph_arg.fillRect(x,y,width,height);
        }
     
    
    }//renderowanie pojedyńczego bloku

    Blok() 
    {
    }
    
    
    
    
}
