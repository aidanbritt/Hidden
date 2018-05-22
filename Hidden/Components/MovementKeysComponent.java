package Hidden.Components;

import com.badlogic.ashley.core.Component;
import java.awt.event.*;

/**
 * Created by Aidan on 5/20/2018.
 */
public class MovementKeysComponent implements Component {
    private boolean isFocused = false;

    private int upKey = KeyEvent.VK_W;
    private int downKey = KeyEvent.VK_S;
    private int leftKey = KeyEvent.VK_A;
    private int rightKey = KeyEvent.VK_D;
    private int swapKey;

    public MovementKeysComponent(boolean f,int sk){
        isFocused = f;
        swapKey = sk;
    }

    public boolean isFocused(){
        return isFocused;
    }

    public void setFocused(boolean f){
        isFocused = f;
    }

    public void setSwapKey(int swapKey){
        this.swapKey = swapKey;
    }

    public int getSwapKey(){
        return swapKey;
    }

    public void setDownKey(int downKey) {
        this.downKey = downKey;
    }

    public void setLeftKey(int leftKey) {
        this.leftKey = leftKey;
    }

    public void setRightKey(int rightKey) {
        this.rightKey = rightKey;
    }

    public void setUpKey(int upKey) {
        this.upKey = upKey;
    }

    public int getDownKey() {
        return downKey;
    }

    public int getLeftKey() {
        return leftKey;
    }

    public int getRightKey() {
        return rightKey;
    }

    public int getUpKey() {
        return upKey;
    }
}
