/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformer2d;

import java.awt.Graphics;

/**
 *
 * @author Dunger
 */
public abstract class Postac 
{        
    public double x,y,szer,wys;

    
    public Postac()
    {
        this.x = 0;
        this.y = 0;
        this.szer=0;
        this.wys=0;
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
