/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformer2d.Poziomy;

import java.awt.Graphics;
import java.awt.Rectangle;
import platformer2d.Blok;
import platformer2d.Kafelek;

/**
 *
 * @author Dunger
 */
public abstract class Poziom {
    
    public Blok[][] bloki; //wiersze, kolumny planszy
    
    public Poziom(int W, int H) {
        bloki=new Blok[W][H];
        
        for(int x =0; x<bloki.length;x++)
        {
            for(int y=0; y<bloki[x].length;y++)
            {
                bloki[x][y] =new Blok(
                                new Rectangle(
                                        x*Kafelek.kafelekSize,
                                        y*Kafelek.kafelekSize,
                                        Kafelek.kafelekSize,
                                        Kafelek.kafelekSize),
                                        Kafelek.powietrze);
            }
        }//wypelnianie powietrzem, ale moznaby np woda albo czyms innym
        
    }


    public Blok[][] getBloki()
    {
        return bloki;
    }


    public abstract void StworzPoziom();

    
    public abstract void tick();

    
    public abstract void render(Graphics grap_arg);
    
}
