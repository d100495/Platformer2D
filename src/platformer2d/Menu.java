/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformer2d;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import static platformer2d.Plansza.piksele;


/**
 *
 * @author Dunger
 */
public class Menu 
{
    public static int wybranaOpcja = 0;
    private int wyswietlPomoc=1;
    
    public static String[] menuOptions = {
		"Graj",
                "Pomoc",
		"Poziomy",
		"Wyjdź"
	};
    
    public static String[] levelOptions = {
		"Poziom 1",
		"Poziom 2",
		"Poziom 3"
	};
    
//   public Rectangle przyciskPlay = new Rectangle(piksele.width/2-100,100,200,50);
//   public Rectangle przyciskLevel = new Rectangle(piksele.width/2-100,180,200,50);
//   public Rectangle przyciskQuit = new Rectangle(piksele.width/2-100,260,200,50);

     public void render(Graphics grap_arg)
    {
        Graphics2D g2d = (Graphics2D) grap_arg;
          
    
        Image tloMenu = new ImageIcon(System.getProperty("user.dir") + "\\src\\resources\\tlo2.gif").getImage();
       
     
        
       //Rysowanie tla
       grap_arg.setColor(Color.PINK);
       grap_arg.fillRect(0, 0, piksele.width, piksele.height);
       grap_arg.drawImage(tloMenu,0,0,piksele.width,piksele.height,null);
        
//       Font font1 = new Font("arial",Font.BOLD,50);
//       grap_arg.setFont(font1);
//       grap_arg.setColor(Color.cyan);
//       grap_arg.drawString("Platformer2D", piksele.width/2-150, 50);
      
       
       grap_arg.setColor(Color.MAGENTA);
//       g2d.draw(przyciskPlay);
//       g2d.draw(przyciskLevel);
//       g2d.draw(przyciskQuit);
       Font font2 = new Font("arial",Font.BOLD,40);
       grap_arg.setFont(font2);
       grap_arg.setColor(Color.WHITE);
       
       
        
       switch(wybranaOpcja)
       {
           case 0:
              grap_arg.setColor(Color.WHITE);
              grap_arg.drawString("Graj", piksele.width/2-40,piksele.height/2+10); 
           break;
           
            case 1:
                grap_arg.drawString("Pomoc", piksele.width/2-70,piksele.height/2+10); 
                if(wyswietlPomoc==1)
                {
                    Font font3 = new Font("arial",Font.BOLD,20);
                    grap_arg.setFont(font3);
                    grap_arg.drawString("A - ruch w lewo", piksele.width/2-80,piksele.height/2+50); 
                    grap_arg.drawString("D - ruch w prawo", piksele.width/2-80,piksele.height/2+80);
                    grap_arg.drawString("Spacja - skok", piksele.width/2-80,piksele.height/2+110); 
                    grap_arg.drawString("Enter - zatwierdź", piksele.width/2-80,piksele.height/2+140); 
                }
                
           break;
           
            case 2:
                grap_arg.drawString("Poziomy", piksele.width/2-80,piksele.height/2+10); 
           break;
           
            case 3:
              grap_arg.drawString("Wyjdź", piksele.width/2-55,piksele.height/2+10); 
           break;
           
           default:
               break;
       }
       
//	if(wybranaOpcja==0) 
//        {
//            grap_arg.drawString("Graj", przyciskPlay.x+55, przyciskPlay.y+40);
//            grap_arg.drawString("Poziomy", przyciskLevel.x+20, przyciskLevel.y+40);
//            grap_arg.drawString("Wyjdź", przyciskQuit.x+40, przyciskQuit.y+40);
//	}
//	else 
//        {
//            grap_arg.setColor(Color.WHITE);
//	}
//        grap_arg.drawString(menuOptions[i], piksele.width/2-70, 140 + i * 80);
        
    }
    
     
     	public void select() 
        {
            
		if(wybranaOpcja == 0) 
                {
                    Plansza.GameState=Plansza.STATE.GAME;
		}
		if(wybranaOpcja == 1) 
                {
                    wyswietlPomoc*=-1;
		}
                if(wybranaOpcja == 2) 
                {
                    // Poziomy
		}
		if(wybranaOpcja == 3) 
                {
                    System.exit(0);
		}
	}
	
        
     
}
