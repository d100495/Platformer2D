/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformer2d;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import platformer2d.Postacie.PostacGracza;

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
        
        if(Plansza.GameState==Plansza.STATE.GAME)
        {
            switch(klawisz)
            {
                case KeyEvent.VK_D:
                    PostacGracza.isCharacterMoving=true;
                    PostacGracza.kierunekPostaci = Plansza.postac.predkoscPoruszania;
                break;

                case KeyEvent.VK_A:
                    PostacGracza.isCharacterMoving=true;
                    PostacGracza.kierunekPostaci = -Plansza.postac.predkoscPoruszania;
                break;

                 case KeyEvent.VK_SPACE:
                    Plansza.isJumping=true;
                break;


            }
        }
        else if(Plansza.GameState==Plansza.STATE.MENU)
        {
            switch(klawisz)
          {
              case KeyEvent.VK_UP:
                Menu.wybranaOpcja--;
		if(Menu.wybranaOpcja == -1) 
                {
                    Menu.wybranaOpcja = Menu.menuOptions.length - 1;
		}      
              break;

              case KeyEvent.VK_DOWN:
                Menu.wybranaOpcja++;
		if(Menu.wybranaOpcja == Menu.menuOptions.length) 
                {
                    Menu.wybranaOpcja=0;
		}
              break;

              case KeyEvent.VK_ENTER:
                  Plansza.menu.select();
              break;
          }
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) 
    {
        int klawisz = ke.getKeyCode();
        
        if(Plansza.GameState==Plansza.STATE.GAME)
        {
            switch(klawisz)
            {
                case KeyEvent.VK_D:
                    if(PostacGracza.kierunekPostaci==Plansza.postac.predkoscPoruszania)
                    {
                        PostacGracza.isCharacterMoving =false;
                    }
                break;

                case KeyEvent.VK_A:
                   if(PostacGracza.kierunekPostaci==-Plansza.postac.predkoscPoruszania)
                    {
                        PostacGracza.isCharacterMoving =false;
                    }
                break;

                case KeyEvent.VK_SPACE:
                        Plansza.isJumping=false;
                        PostacGracza.isCharacterFallngDown=true;
                break;
            }
        }
    }
    
}
