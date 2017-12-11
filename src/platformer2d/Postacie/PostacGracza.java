package platformer2d.Postacie;

import platformer2d.Postacie.Postac;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import platformer2d.Kafelek;
import platformer2d.Plansza;

/**
 *
 * @author Dunger
 */
public class PostacGracza extends Postac
{

    
    public static boolean isCharacterMoving=false;
    public static double kierunekPostaci=0;
    public static boolean isCharacterJumping=false;
    public static boolean isCharacterFallngDown=false;
    
    public final double grawitacja = 2;
    public final double predkoscPoruszania=2;
    public final double predkoscWznoszenia=grawitacja;
    
    public final double wysokoscSkoku=50;
    public int iloscSkokow=0;
    
    
    public int animacja=0;
    public int klatkaAnimacji = 0;
    public final int czasAnimacji=10;
    
    
     public PostacGracza(int szer, int wys)
    {
        this.x=((Plansza.piksele.width/2)-(szer/2));
        this.y=((Plansza.piksele.height/2)-(wys/2));       

        this.szer=szer;
        this.wys=wys;
    }
     
      public PostacGracza(int x, int y,int szer, int wys)
    {
        this.x=x;
        this.y=y;

        this.szer=szer;
        this.wys=wys;
    }
     

     
   
    
    
    @Override
    public void tick() 
    {
        Point puntk_1 = new Point((int)x+2,(int)(y+wys)); //lewo dol
        Point punkt_2 = new Point((int)(x+szer-2),(int)(y+wys)); //prawo dol
     
        if(!kolizjaBlok(puntk_1, punkt_2) && !isCharacterJumping) //kolizja dol
        {
            isCharacterFallngDown=true;

            y+=grawitacja;
            Plansza.scrollingY+=grawitacja;
        }
        else
        {
            isCharacterFallngDown=false;
               if(Plansza.isJumping)
            {
               isCharacterJumping=true;
            }
        }
        
        
        
        if(isCharacterMoving)
        {
            boolean zablokujRuch=false;
            
            if(kierunekPostaci == predkoscPoruszania) // kolizja z prawej
            {
                Point punkt_4 = new Point((int)(this.x+this.szer+1),(int)(y)); //prawo gora
                Point punkt_5 = new Point((int)(this.x+this.szer+1),(int)(y+wys)-2); //prawaao dol -fix na y
                zablokujRuch = kolizjaBlok(punkt_4,punkt_5);
            }
            
            else if(kierunekPostaci == (-predkoscPoruszania)) //kozlija z lewej
            {
                Point punkt_3 = new Point((int)x-1,(int)(y)); //lewo gora
                Point punkt_6 = new Point((int)x-1,(int)((y+wys)-2));  //lewo dol -fix na y
                zablokujRuch = kolizjaBlok(punkt_6,punkt_3);
            }

            
            if(!zablokujRuch)
            {
               x+=kierunekPostaci;
               Plansza.scrollingX+=kierunekPostaci; 
            }
        }
        
        
        if(isCharacterJumping)
        {
                Point punkt_3 = new Point((int)x+2,(int)(y)); //lewo gora
                Point punkt_4 = new Point((int)(this.x+this.szer-2),(int)(y)); //prawo gora
                
                
                if(!kolizjaBlok(punkt_3, punkt_4))
                {
                    if(iloscSkokow>=wysokoscSkoku) //koniec skoku
                    {
                        isCharacterJumping=false;
                        iloscSkokow=0;
                    }
                    else //skok
                    {
                        y-=predkoscWznoszenia;
                        Plansza.scrollingY-=predkoscWznoszenia;
                        iloscSkokow+=1;

                    }
                }
                else //brak skoku bo kolizja
                {
                    isCharacterJumping=false;
                    iloscSkokow=0;

                }
            
          
        }
       
      
        
        
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

        
        if(y>=Plansza.level.bloki[0].length*Kafelek.kafelekSize) //spadanie
        {
            Plansza.reload();
        }
    }



    @Override
    public void render(Graphics graph_arg) 
    {
        
        //=============================
        //rendering poruszanie w lewo
        //=============================
        if(kierunekPostaci == predkoscPoruszania)
        {
            if(isCharacterMoving==true && isCharacterJumping==false && isCharacterFallngDown==false) //poruszanie
            {
                graph_arg.drawImage(
                Kafelek.kafelki_teren, 
                (int)x - Plansza.scrollingX, 
                (int)y - Plansza.scrollingY,
                (int)(x+szer)- Plansza.scrollingX,
                (int)(y+wys) - Plansza.scrollingY,
                Kafelek.postacGraczaRun[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja),
                Kafelek.postacGraczaRun[1]*Kafelek.kafelekSize,
                Kafelek.postacGraczaRun[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja)+(int)szer,
                Kafelek.postacGraczaRun[1]*Kafelek.kafelekSize+(int)wys,
                null);
            }
            else if(isCharacterJumping==true && isCharacterMoving==true && isCharacterFallngDown==false) //skakanie z ruchem
            {
                 graph_arg.drawImage(
                Kafelek.kafelki_teren, 
                (int)x - Plansza.scrollingX, 
                (int)y - Plansza.scrollingY,
                (int)(x+szer)- Plansza.scrollingX,
                (int)(y+wys) - Plansza.scrollingY,
                Kafelek.postacGraczaJump[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja),
                Kafelek.postacGraczaJump[1]*Kafelek.kafelekSize,
                Kafelek.postacGraczaJump[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja)+(int)szer,
                Kafelek.postacGraczaJump[1]*Kafelek.kafelekSize+(int)wys,
                null);
            }
            else if(isCharacterJumping==false && isCharacterMoving==true && isCharacterFallngDown==true) //opadanie z ruchem
            {
//                 graph_arg.drawImage(
//                Kafelek.kafelki_teren, 
//                (int)x - Plansza.scrollingX, 
//                (int)y - Plansza.scrollingY,
//                (int)(x+szer)- Plansza.scrollingX,
//                (int)(y+wys) - Plansza.scrollingY,
//                Kafelek.postacGraczaFallingDown[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja),
//                Kafelek.postacGraczaFallingDown[1]*Kafelek.kafelekSize,
//                Kafelek.postacGraczaFallingDown[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja)+(int)szer,
//                Kafelek.postacGraczaFallingDown[1]*Kafelek.kafelekSize+(int)wys,
//                null);
                 
                   graph_arg.drawImage(
                Kafelek.kafelki_teren, 
                (int)x - Plansza.scrollingX, 
                (int)y - Plansza.scrollingY,
                (int)(x+szer)- Plansza.scrollingX,
                (int)(y+wys) - Plansza.scrollingY,
                Kafelek.postacGraczaJump[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja),
                Kafelek.postacGraczaJump[1]*Kafelek.kafelekSize,
                Kafelek.postacGraczaJump[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja)+(int)szer,
                Kafelek.postacGraczaJump[1]*Kafelek.kafelekSize+(int)wys,
                null);
                
            }
            else if(isCharacterJumping==false && isCharacterMoving==false && isCharacterFallngDown==true) //opadanie bez ruchu
            {
                graph_arg.drawImage(
                Kafelek.kafelki_teren, 
                (int)x - Plansza.scrollingX, 
                (int)y - Plansza.scrollingY,
                (int)(x+szer)- Plansza.scrollingX,
                (int)(y+wys) - Plansza.scrollingY,
                Kafelek.postacGraczaFallingDown[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja),
                Kafelek.postacGraczaFallingDown[1]*Kafelek.kafelekSize,
                Kafelek.postacGraczaFallingDown[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja)+(int)szer,
                Kafelek.postacGraczaFallingDown[1]*Kafelek.kafelekSize+(int)wys,
                null);
                
            }
             else if(isCharacterJumping==true && isCharacterMoving==false && isCharacterFallngDown==false) //skakanie bez ruchu
            {
                graph_arg.drawImage(
                Kafelek.kafelki_teren, 
                (int)x - Plansza.scrollingX, 
                (int)y - Plansza.scrollingY,
                (int)(x+szer)- Plansza.scrollingX,
                (int)(y+wys) - Plansza.scrollingY,
                Kafelek.postacGraczaJump[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja),
                Kafelek.postacGraczaJump[1]*Kafelek.kafelekSize,
                Kafelek.postacGraczaJump[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja)+(int)szer,
                Kafelek.postacGraczaJump[1]*Kafelek.kafelekSize+(int)wys,
                null);
                
            }
            else //stoi w lewo
            {
                graph_arg.drawImage(
                Kafelek.kafelki_teren, 
                (int)x - Plansza.scrollingX, 
                (int)y - Plansza.scrollingY,
                (int)(x+szer)- Plansza.scrollingX,
                (int)(y+wys) - Plansza.scrollingY,
                Kafelek.postacGraczaIdle[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja),
                Kafelek.postacGraczaIdle[1]*Kafelek.kafelekSize,
                Kafelek.postacGraczaIdle[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja)+(int)szer,
                Kafelek.postacGraczaIdle[1]*Kafelek.kafelekSize+(int)wys,
                null);
            }
             
        }
            
        //=============================
        //rendering poruszanie w prawo
        //=============================
        
        else 
        {
            if(isCharacterMoving==true && isCharacterJumping==false && isCharacterFallngDown==false) //poruszanie
            {
                graph_arg.drawImage(
                Kafelek.kafelki_teren, 
                (int)x - Plansza.scrollingX, 
                (int)y - Plansza.scrollingY,
                (int)(x+szer)- Plansza.scrollingX,
                (int)(y+wys) - Plansza.scrollingY,
                Kafelek.postacGraczaRun[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja)+(int)szer,
                Kafelek.postacGraczaRun[1]*Kafelek.kafelekSize,
                Kafelek.postacGraczaRun[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja),
                Kafelek.postacGraczaRun[1]*Kafelek.kafelekSize+(int)wys,
                null);
            }
            else if(isCharacterJumping==true && isCharacterMoving==true && isCharacterFallngDown==false) //skakanie z ruchem
            {
                graph_arg.drawImage(
                Kafelek.kafelki_teren, 
                (int)x - Plansza.scrollingX, 
                (int)y - Plansza.scrollingY,
                (int)(x+szer)- Plansza.scrollingX,
                (int)(y+wys) - Plansza.scrollingY,
                Kafelek.postacGraczaJump[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja)+(int)szer,
                Kafelek.postacGraczaJump[1]*Kafelek.kafelekSize,
                Kafelek.postacGraczaJump[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja),
                Kafelek.postacGraczaJump[1]*Kafelek.kafelekSize+(int)wys,
                null);
            }
            else if(isCharacterJumping==false && isCharacterMoving==true && isCharacterFallngDown==true) //opadanie z ruchem
            {
//               graph_arg.drawImage(
//                Kafelek.kafelki_teren, 
//                (int)x - Plansza.scrollingX, 
//                (int)y - Plansza.scrollingY,
//                (int)(x+szer)- Plansza.scrollingX,
//                (int)(y+wys) - Plansza.scrollingY,
//                Kafelek.postacGraczaFallingDown[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja)+(int)szer,
//                Kafelek.postacGraczaFallingDown[1]*Kafelek.kafelekSize,
//                Kafelek.postacGraczaFallingDown[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja),
//                Kafelek.postacGraczaFallingDown[1]*Kafelek.kafelekSize+(int)wys,
//                null);
                
                graph_arg.drawImage(
                Kafelek.kafelki_teren, 
                (int)x - Plansza.scrollingX, 
                (int)y - Plansza.scrollingY,
                (int)(x+szer)- Plansza.scrollingX,
                (int)(y+wys) - Plansza.scrollingY,
                Kafelek.postacGraczaJump[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja)+(int)szer,
                Kafelek.postacGraczaJump[1]*Kafelek.kafelekSize,
                Kafelek.postacGraczaJump[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja),
                Kafelek.postacGraczaJump[1]*Kafelek.kafelekSize+(int)wys,
                null);
            }
            else if(isCharacterJumping==false && isCharacterMoving==false && isCharacterFallngDown==true) //opadanie bez ruchu
            {
                graph_arg.drawImage(
                Kafelek.kafelki_teren, 
                (int)x - Plansza.scrollingX, 
                (int)y - Plansza.scrollingY,
                (int)(x+szer)- Plansza.scrollingX,
                (int)(y+wys) - Plansza.scrollingY,
                Kafelek.postacGraczaFallingDown[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja)+(int)szer,
                Kafelek.postacGraczaFallingDown[1]*Kafelek.kafelekSize,
                Kafelek.postacGraczaFallingDown[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja),
                Kafelek.postacGraczaFallingDown[1]*Kafelek.kafelekSize+(int)wys,
                null);
                
            }
            else if(isCharacterJumping==true && isCharacterMoving==false && isCharacterFallngDown==false) //skakanie bez ruchu
            {
                 graph_arg.drawImage(
                Kafelek.kafelki_teren, 
                (int)x - Plansza.scrollingX, 
                (int)y - Plansza.scrollingY,
                (int)(x+szer)- Plansza.scrollingX,
                (int)(y+wys) - Plansza.scrollingY,
                Kafelek.postacGraczaJump[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja)+(int)szer,
                Kafelek.postacGraczaJump[1]*Kafelek.kafelekSize,
                Kafelek.postacGraczaJump[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja),
                Kafelek.postacGraczaJump[1]*Kafelek.kafelekSize+(int)wys,
                null);
                
            }
            else //stoi w prawo
            {
                graph_arg.drawImage(
                Kafelek.kafelki_teren, 
                (int)x - Plansza.scrollingX, 
                (int)y - Plansza.scrollingY,
                (int)(x+szer)- Plansza.scrollingX,
                (int)(y+wys) - Plansza.scrollingY,
                Kafelek.postacGraczaIdle[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja)+(int)szer,
                Kafelek.postacGraczaIdle[1]*Kafelek.kafelekSize,
                Kafelek.postacGraczaIdle[0]*Kafelek.kafelekSize+(Kafelek.kafelekSize*animacja),
                Kafelek.postacGraczaIdle[1]*Kafelek.kafelekSize+(int)wys,
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
