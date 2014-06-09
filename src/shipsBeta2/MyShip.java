/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shipsBeta2;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Jakub
 */
public class MyShip implements Serializable{
    
    private ArrayList<MyPoint> points = new ArrayList<MyPoint>();
    
    private boolean destroyed = false;
    
    public MyShip(){
        
    }
    
    public void addPoint(MyPoint p){
        this.points.add(p);
    }
    
    public ArrayList<MyPoint> getPoints(){
        return this.points;
    }
    
    public boolean isDestroyed(){
        return this.destroyed;
    }
    
    public void destroy(){
        this.destroyed = true;
    }
    
}
