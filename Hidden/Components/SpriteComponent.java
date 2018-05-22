package Hidden.Components;

import Hidden.Textures.Sprite;
import com.badlogic.ashley.core.Component;

/**
 * Created by Aidan on 8/23/2017.
 */
public class SpriteComponent implements Component {
    private Sprite sprite = Sprite.empty;

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Sprite getSprite(){
        return sprite;
    }
}
