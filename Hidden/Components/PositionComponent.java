package Hidden.Components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Aidan on 8/23/2017.
 */
public class PositionComponent implements Component{
    private int x;
    private int y;

    public PositionComponent(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setX(int newX){
        x = newX;
    }

    public void setY(int newY){
        y = newY;
    }
}
