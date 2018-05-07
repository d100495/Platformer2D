/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformer2d.Characters;

import java.awt.Graphics;
import platformer2d.Tile;

/**
 *
 * @author Dunger
 */
public abstract class Character 
{        
    public double x,y,width,height;
     public boolean isDead=false;
     
     public abstract void tick();
     public abstract void render(Graphics graph_arg);
     
      public boolean kolizjaGracz(Character mainCharacter , Character otherCharacter)
      {
      
       if (mainCharacter.x > otherCharacter.x + otherCharacter.width ||
           mainCharacter.x + mainCharacter.width < otherCharacter.x ||
          mainCharacter.y > otherCharacter.y + otherCharacter.height || 
            mainCharacter.y + mainCharacter.height < otherCharacter.y)
       {
           return false;
       }
    else
        return true;
    
    
    }
     
     
 
}
