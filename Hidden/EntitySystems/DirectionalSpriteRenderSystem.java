package Hidden.EntitySystems;

import Hidden.Components.DirectionalSpriteComponent;
import Hidden.Components.PositionComponent;
import Hidden.Components.SpriteComponent;
import Hidden.Graphics.Screen;
import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;

/**
 * Created by Aidan on 8/23/2017.
 */
public class DirectionalSpriteRenderSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<DirectionalSpriteComponent> sm = ComponentMapper.getFor(DirectionalSpriteComponent.class);

    private Screen screen;

    public DirectionalSpriteRenderSystem(Screen s){
        screen = s;
    }

    public void addedToEngine(Engine engine){
        entities = engine.getEntitiesFor(Family.all(PositionComponent.class, DirectionalSpriteComponent.class).get());
    }

    public void update(float deltaTime){
        for(Entity entity : entities){
            PositionComponent position = pm.get(entity);
            DirectionalSpriteComponent sprite = sm.get(entity);

            int x  =(int) position.getX();
            int y  =(int) position.getY();

            screen.renderEntity(entity);
        }
    }
}
