/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformer2d.Postacie;

import java.awt.Graphics;
import platformer2d.Kafelek;

/**
 *
 * @author Dunger
 */
public abstract class Postac 
{        
    public double x,y,szer,wys;
     public boolean czyUmar=false;
     
     public abstract void tick();
     public abstract void render(Graphics graph_arg);
     
      public boolean kolizjaGracz(Postac postacGracza , Postac innaPostac)
      {
      
       if (postacGracza.x > innaPostac.x + innaPostac.szer ||
           postacGracza.x + postacGracza.szer < innaPostac.x ||
          postacGracza.y > innaPostac.y + innaPostac.wys || 
            postacGracza.y + postacGracza.wys < innaPostac.y)
       {
           return false;
       }
    else
        return true;
    
    
    }
     
     
 
}
