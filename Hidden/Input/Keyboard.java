package Hidden.Input;
import java.awt.event.*;
public class Keyboard implements KeyListener
{
    private boolean[] keys = new boolean[65536];
    public boolean up,down,left,right,plus,minus,lastPlus,lastMinus,q,lastQ,reset,grid,lastGrid,enter,lastEnter,z,lastZ,swap1,swap2,swap3,swap4;

    public boolean getKey(int k){
        return keys[k];
    }

    public void update(){
        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
        q = keys[KeyEvent.VK_Q];
        reset = keys[KeyEvent.VK_R];

        enter = (!lastEnter) && keys[KeyEvent.VK_ENTER];
        plus = (!lastPlus) && keys[KeyEvent.VK_EQUALS];
        minus = (!lastMinus) && keys[KeyEvent.VK_MINUS];
        grid = (!lastGrid) && keys[KeyEvent.VK_G];
        z = (!lastZ) && keys[KeyEvent.VK_Z];
        q = (!lastQ) && keys[KeyEvent.VK_Q];
        //OLD VERSION
        //         if(lastMinus==false){
        //             minus = keys[KeyEvent.VK_MINUS];
        //         }
        //         else
        //             minus = false;

        lastPlus = keys[KeyEvent.VK_EQUALS];
        lastMinus = keys[KeyEvent.VK_MINUS];
        lastGrid = keys[KeyEvent.VK_G];
        lastEnter = keys[KeyEvent.VK_ENTER];
        lastZ = keys[KeyEvent.VK_Z];
        lastQ = keys[KeyEvent.VK_Q];
    }

    public void keyPressed(KeyEvent e){
        keys[e.getKeyCode()]=true;        
    }

    public void keyReleased(KeyEvent e){
        keys[e.getKeyCode()]=false;
    }

    public void keyTyped(KeyEvent e){

    }
}
