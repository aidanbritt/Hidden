package Hidden.Components;

import Hidden.Textures.Sprite;
import Hidden.Textures.SpriteSheet;
import com.badlogic.ashley.core.Component;

/**
 * Created by Aidan on 5/21/2018.
 */
public class DirectionalSpriteComponent  implements Component {

    private Sprite[] sprites = new Sprite[8];
    private int state = 0;

    public DirectionalSpriteComponent(SpriteSheet s){
        super();
        for(int i = 0; i < 8; i++){
            sprites[i] = new Sprite(0,i,s);
        }
    }

    public void setState(int s){
        state = s;
    }

    public int getState(){
        return state;
    }

    public Sprite getSprite(){
        return sprites[state];
    }


}