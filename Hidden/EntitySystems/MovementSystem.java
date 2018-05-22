package Hidden.EntitySystems;

import Hidden.Components.PositionComponent;
import Hidden.Components.VelocityComponent;
import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;


import javax.swing.text.Position;

/**
 * Created by Aidan on 8/23/2017.
 */
public class MovementSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<VelocityComponent> vm = ComponentMapper.getFor(VelocityComponent.class);

    public MovementSystem(){}

    public void addedToEngine(Engine engine){
        entities = engine.getEntitiesFor(Family.all(PositionComponent.class, VelocityComponent.class).get());
    }

    public void update(float deltaTime){
        for(Entity entity : entities){
            PositionComponent position = pm.get(entity);
            VelocityComponent velocity = vm.get(entity);
            int eX = (int)(position.getX() + velocity.getX() * deltaTime);
            int eY = (int)(position.getY() + velocity.getY() * deltaTime);

            position.setX(eX);
            position.setY(eY);
        }
    }
}
