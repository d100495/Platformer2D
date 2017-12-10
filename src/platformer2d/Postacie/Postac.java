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

    
    public Postac()
    {
        this.x = 100;
        this.y = 100;
        this.szer=Kafelek.kafelekSize;
        this.wys=Kafelek.kafelekSize;
    }
    
    
    public Postac(double x, double y, double szer, double wys)
    {
        this.x=x;
        this.y=y;
        this.szer=szer;
        this.wys=wys;
    }
    
        public Postac(int x, int y, int szer, int wys)
    {
        this.x=x;
        this.y=y;
        this.szer=szer;
        this.wys=wys;
    }
    
     public abstract void tick();
     public abstract void render(Graphics graph_arg);
    
 
}
