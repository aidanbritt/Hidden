package Hidden.EntitySystems;

import Hidden.Components.DirectionalSpriteComponent;
import Hidden.Components.MovementKeysComponent;
import Hidden.Components.PositionComponent;
import Hidden.Components.VelocityComponent;
import Hidden.Game;
import Hidden.Input.Keyboard;
import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import Hidden.Graphics.Screen;

/**
 * Created by Aidan on 5/20/2018.
 */
public class KeyboardControlSystem extends EntitySystem{
    private ImmutableArray<Entity> entities;
    private Screen screen;

    private ComponentMapper<MovementKeysComponent> km = ComponentMapper.getFor(MovementKeysComponent.class);

    public void addedToEngine(Engine engine){
        entities = engine.getEntitiesFor(Family.all(PositionComponent.class, VelocityComponent.class, MovementKeysComponent.class).get());
    }

    public KeyboardControlSystem(){
        screen = Game.getScreen();
    }

    public void update(float deltaTime){
        Keyboard keyboard = Game.getKey();
        boolean focusSet = false;

        for(Entity entity: entities) {
            VelocityComponent velocity = entity.getComponent(VelocityComponent.class);
            MovementKeysComponent controls = km.get(entity);

            boolean moved = false;

            velocity.setX(0);
            velocity.setY(0);

            if(keyboard.getKey(controls.getSwapKey())){
                screen.setFocus(entity);
            }

            if(entity.equals(screen.getFocus())) {
                if (keyboard.getKey(controls.getUpKey())) {
                    velocity.setY(-1.0f);
                    moved = true;
                }
                if (keyboard.getKey(controls.getDownKey())) {
                    velocity.setY(1.0f);
                    moved = true;
                }
                if (keyboard.getKey(controls.getLeftKey())) {
                    velocity.setX(-1.0f);
                    moved = true;
                }
                if (keyboard.getKey(controls.getRightKey())) {
                    velocity.setX(1.0f);
                    moved = true;
                }

                DirectionalSpriteComponent ds = entity.getComponent(DirectionalSpriteComponent.class);
                if(ds!=null && moved) {
                    if (keyboard.getKey(controls.getUpKey())) {
                        if (keyboard.getKey(controls.getLeftKey())) {
                            ds.setState(7);
                        } else if (keyboard.getKey(controls.getRightKey())) {
                            ds.setState(1);
                        } else {
                            ds.setState(0);
                        }
                    } else if (keyboard.getKey(controls.getDownKey())) {
                        if (keyboard.getKey(controls.getLeftKey())){
                            ds.setState(5);
                        } else if (keyboard.getKey(controls.getRightKey())) {
                            ds.setState(3);
                        } else {
                            ds.setState(4);
                        }
                    } else if (keyboard.getKey(controls.getRightKey())) {
                        ds.setState(2);
                    } else if (keyboard.getKey(controls.getLeftKey())) {
                        ds.setState(6);
                    }
                }

            }
        }
    }
}
