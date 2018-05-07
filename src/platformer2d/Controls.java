/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformer2d;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import platformer2d.Characters.CharacterPlayer;

/**
 *
 * @author Dunger
 */
public class Controls implements KeyListener
{

    @Override
    public void keyTyped(KeyEvent ke) 
    {
    }

    @Override
    public void keyPressed(KeyEvent ke) 
    {
        int key = ke.getKeyCode();
        
        if(Game.GameState==Game.STATE.GAME)
        {
            switch(key)
            {
                case KeyEvent.VK_D:
                    CharacterPlayer.isCharacterMoving=true;
                    CharacterPlayer.direction = Game.character.movingSpeed;
                break;

                case KeyEvent.VK_A:
                    CharacterPlayer.isCharacterMoving=true;
                    CharacterPlayer.direction = -Game.character.movingSpeed;
                break;

                 case KeyEvent.VK_SPACE:
                    Game.isJumping=true;
                break;
                
                 case KeyEvent.VK_ESCAPE:
                    Game.GameState=Game.STATE.MENU;
                break;


            }
        }
        else if(Game.GameState==Game.STATE.MENU)
        {
            switch(key)
          {
              case KeyEvent.VK_UP:
                Menu.chosenOption--;
		if(Menu.chosenOption == -1) 
                {
                    Menu.chosenOption = Menu.menuOptions.length - 1;
		}      
              break;

              case KeyEvent.VK_DOWN:
                Menu.chosenOption++;
		if(Menu.chosenOption == Menu.menuOptions.length) 
                {
                    Menu.chosenOption=0;
		}
              break;

              case KeyEvent.VK_ENTER:
                  Game.menu.select();
              break;
          }
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) 
    {
        int klawisz = ke.getKeyCode();
        
        if(Game.GameState==Game.STATE.GAME)
        {
            switch(klawisz)
            {
                case KeyEvent.VK_D:
                    if(CharacterPlayer.direction==Game.character.movingSpeed)
                    {
                        CharacterPlayer.isCharacterMoving =false;
                    }
                break;

                case KeyEvent.VK_A:
                   if(CharacterPlayer.direction==-Game.character.movingSpeed)
                    {
                        CharacterPlayer.isCharacterMoving =false;
                    }
                break;

                case KeyEvent.VK_SPACE:
                        Game.isJumping=false;
                        CharacterPlayer.isCharacterFallngDown=true;
                break;
            }
        }
    }
    
}
