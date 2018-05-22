package Hidden.Input;

import Hidden.Game;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseEvent;
import javax.swing.*;
//import Hidden.LivingEntity.*;

public class Mouse implements MouseListener,MouseMotionListener, MouseWheelListener{       
    int lastRClickedX=-1;
    int lastRClickedY=-1;
    int lastLClickedX=-1;
    int lastLClickedY=-1;
    public boolean hasRClicked=false,mouseUp,mouseDown,hasLClicked=false;    

    int currentX=-1;
    int currentY=-1;
    public void mousePressed(MouseEvent e) {
        if(e.getModifiers() == MouseEvent.BUTTON3_MASK){
            lastRClickedX = (int)((Game.getScreen().getCameraX() + e.getPoint().getX())/16);
            lastRClickedY = (int)((Game.getScreen().getCameraY() + e.getPoint().getY())/16);

            System.out.println("RIGHT\t"+lastRClickedX+","+lastRClickedY);

            hasRClicked = true;       
        }

        if(e.getModifiers() == MouseEvent.BUTTON1_MASK){
            lastLClickedX = (int)((Game.getScreen().getCameraX() + e.getPoint().getX())/16);
            lastLClickedY = (int)((Game.getScreen().getCameraY() + e.getPoint().getY())/16);

            System.out.println("LEFT\t"+lastLClickedX+","+lastLClickedY);

            hasLClicked = true;       
        }
    }     

    public boolean hasRClicked(){
        return hasRClicked;
    }

    public void setRClicked(boolean c){hasRClicked = c;}

    public boolean hasLClicked(){
        return hasLClicked;
    }

    public void setLClicked(boolean c){hasLClicked = c;}

    public boolean mouseUp(){return mouseUp;}

    public boolean mouseDown(){return mouseDown;}

    public int getCurrentX(){return currentX;}

    public int getCurrentY(){return currentY;}

    public int getLastRClickedX(){return lastRClickedX;}

    public int getLastRClickedY(){return lastRClickedY;}

    public int getLastLClickedX(){return lastLClickedX;}

    public int getLastLClickedY(){return lastLClickedY;}

    public void mouseReleased(MouseEvent e) {
        if(e.getModifiers() == MouseEvent.BUTTON1_MASK){
            setLClicked(false);
            System.out.println("LEFT RELEASE");
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {        
        currentX = (int)e.getPoint().getX();
        currentY = (int)e.getPoint().getY();

        //System.out.println(e.getPoint().toString()+"!");
    }

    public void mouseDragged(MouseEvent e) {
        if(e.getModifiers() == MouseEvent.BUTTON1_MASK){
            lastLClickedX = (int)((Game.getScreen().getCameraX() + e.getPoint().getX())/16);
            lastLClickedY = (int)((Game.getScreen().getCameraY() + e.getPoint().getY())/16);
        }
    }

    public void mouseWheelMoved(MouseWheelEvent e) {
        int notches = e.getWheelRotation();
        mouseUp = (notches>0);
        mouseDown = (notches<0);
        notches = 0;
    }
}