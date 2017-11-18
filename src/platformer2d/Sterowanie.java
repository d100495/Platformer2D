/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformer2d;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Dunger
 */
public class Sterowanie implements KeyListener
{

    @Override
    public void keyTyped(KeyEvent ke) 
    {
    }

    @Override
    public void keyPressed(KeyEvent ke) 
    {
        int klawisz = ke.getKeyCode();
        
        switch(klawisz)
        {
            case KeyEvent.VK_D:
                Plansza.isCharacterMoving=true;
                Plansza.kierunekPostaci = Plansza.postac_1.predkoscPoruszania;
            break;
            
            case KeyEvent.VK_A:
                Plansza.isCharacterMoving=true;
                Plansza.kierunekPostaci = -Plansza.postac_1.predkoscPoruszania;
            break;
            
            
                
            
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) 
    {
        int klawisz = ke.getKeyCode();
        
        switch(klawisz)
        {
            case KeyEvent.VK_D:
                if(Plansza.kierunekPostaci==Plansza.postac_1.predkoscPoruszania)
                {
                    Plansza.isCharacterMoving =false;
                }
            break;
            
            case KeyEvent.VK_A:
               if(Plansza.kierunekPostaci==-Plansza.postac_1.predkoscPoruszania)
                {
                    Plansza.isCharacterMoving =false;
                }
            break;
        }
    }
    
}
