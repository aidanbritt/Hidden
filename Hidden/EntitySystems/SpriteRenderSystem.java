package Hidden.EntitySystems;

import Hidden.Components.MovementKeysComponent;
import Hidden.Components.PositionComponent;
import Hidden.Components.SpriteComponent;
import Hidden.Components.VelocityComponent;
import Hidden.Graphics.Screen;
import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;

import javax.swing.text.Position;

/**
 * Created by Aidan on 8/23/2017.
 */
public class SpriteRenderSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<SpriteComponent> sm = ComponentMapper.getFor(SpriteComponent.class);

    private Screen screen;

    public SpriteRenderSystem(Screen s){
        screen = s;
    }

    public void addedToEngine(Engine engine){
        entities = engine.getEntitiesFor(Family.all(PositionComponent.class, SpriteComponent.class).get());
    }

    public void update(float deltaTime){
        for(Entity entity : entities){
            PositionComponent position = pm.get(entity);
            SpriteComponent sprite = sm.get(entity);

            int x  =(int) position.getX();
            int y  =(int) position.getY();

            screen.renderEntity(entity);
        }
    }
}
