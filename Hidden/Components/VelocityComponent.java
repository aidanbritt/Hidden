package Hidden.Components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Aidan on 8/23/2017.
 */
public class VelocityComponent implements Component{
    private float x;
    private float y;

    public VelocityComponent(int x, int y){
        this.x = x;
        this.y =y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
}
