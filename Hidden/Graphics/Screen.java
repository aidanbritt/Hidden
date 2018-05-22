package Hidden.Graphics;
import java.util.Random;

import Hidden.Components.DirectionalSpriteComponent;
import Hidden.Components.PositionComponent;
import Hidden.Components.SpriteComponent;
import Hidden.Textures.Sprite;
//import Hidden.LivingEntity.Player;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Component;

import javax.swing.text.Position;

public class Screen
{
    public int width,height;
    public int[] pixels;
    private final int tileSize = 16;
    private final int bitSize =(int)( Math.log(tileSize)/Math.log(2));
    private int cameraX;
    private int cameraY;
    private Entity focus;

    public int zoom = 1; 

    private Random random = new Random();

    public Screen(int width,int height){
        this.width = width;
        this.height = height;
        pixels = new int [width*height];
        cameraX = 0;
        cameraY = 0;
    }        

    public void clear(){
        for(int i = 0; i < pixels.length; i++){
            pixels[i] = 0;
        }
    }

    //     public void render(int xOff, int yOff){
    //         for(int y = 0; y < height; y++){
    //             int y2 = y+yOff;
    //             if(y2 < 0 || y2 >= height){continue;}
    //             for(int x = 0; x < width; x++){
    //                 int x2 = x+xOff;
    //                 if(x2 < 0 || x2 >= width){continue;}
    //                 int tileIndex = (x2 >> bitSize & MAPMASK) + (y2 >> bitSize & MAPMASK)*MAPSIZE;
    //                 pixels[x + y*width] = Sprite.sandC1.pixels[tileIndex];
    //             }
    //         }
    //     }

    /**
     * Render Specific Tile
     */
    public void renderTile(int xPos, int yPos, Tile tile){
        xPos -= cameraX; // - offset because the map moves opposite of player
        yPos -= cameraY; // - offset because the map moves opposite of player
        for(int y = 0; y < tile.getSize(); y++){ //pixels of tile
            int ya = y+yPos;
            for(int x = 0; x < tile.getSize(); x++){ //pixels of tile
                if(tile.sprite.pixels[x+y*tile.getSize()]!=-65281){                                        
                    int xa= x+xPos;
                    if(xa < -tile.getSize() || xa >= width ||ya < -tile.getSize() || ya >= height) break;
                    if(xa<0)
                        xa=0;
                    if(ya<0)
                        ya=0;
                    //System.out.println("Position of pixel"+x+","+y+"\tPosition of Pixel in screen"+(xa+ya*width)+"\tPosition of Pixel in texture"+(x*y*tile.getSize()));
                    pixels[xa+ya*width] = tile.sprite.pixels[x+y*tile.getSize()];
                }
            }
        }
    }

    public void renderTileMini(int x, int y, Tile tile){
        if(tile.sprite.pixels[0]!=-65281){  
            //             x -= cameraX/16; // - offset because the map moves opposite of player
            //             y -= cameraY/16; // - offset because the map moves opposite of player
            pixels[x+y*width] = tile.sprite.pixels[0];               
        }
    }

    public void renderEntity(Entity entity){
        PositionComponent posC = entity.getComponent(PositionComponent.class);

        SpriteComponent spriteC = entity.getComponent(SpriteComponent.class);
        DirectionalSpriteComponent dSpriteC = entity.getComponent(DirectionalSpriteComponent.class);

        Sprite s;
        if(spriteC != null) {
            s = spriteC.getSprite();
        }else if(dSpriteC !=null){
            s = dSpriteC.getSprite();
        }else{
            System.out.println("Couldnt find sprite for entity");
            return;
        }

        int size = s.getSize();
        int xPos = posC.getX() - cameraX; // - offset because the map moves opposite of player
        int yPos = posC.getY() - cameraY; // - offset because the map moves opposite of player
//        System.out.println(posC.getX()+"<"+(cameraX + width)+" and "+ posC.getX()+" > "+cameraX);
//        System.out.println(posC.getY()+"<"+(cameraY + height)+" and "+posC.getY()+" > "+cameraY+"\n");

        if(((posC.getX()) < cameraX + width) && (posC.getX() > cameraX) && ((posC.getY()) < cameraY + height) && (posC.getY() + size > cameraY)){
            for(int y = 0; y < s.getSize(); y++){ //pixels of tile
                for(int x = 0; x < s.getSize(); x++) { //pixels of tile

                    int spriteIndex = x + y * s.getSize();
                    int screenIndex = (x + xPos) + (y + yPos) * width;

                    if (s.pixels[spriteIndex] != -65281) {//color for transparency
                        try {
                            pixels[screenIndex] = s.pixels[spriteIndex];
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("pixels["+((x+xPos)+(y+yPos)*width)+"]\t"+pixels.length);
                            System.out.println("("+xPos+","+yPos+")");
                        }
                    }
                }
            }
        }
        if(entity.equals(focus)){
            //System.out.println("focused");
            updateCameraX(posC.getX());
            updateCameraY(posC.getY());
        }

    }

    public void updateCameraX(int x){
        if((x - cameraX)>(width*.8)){ //if difference is at least 80% screen width
            if((x - (int)(width*.8)) < (8192 - width)){
                cameraX = x - (int)(width*.8);
            }else
                cameraX = (8192)-width;       
        }else if((x - cameraX)<(width*.2)){// if the difference is less than 20% screen width
            if(x - (int)(width*.2) > 0){
                cameraX = x - (int)(width*.2); 
            }else
                cameraX = 0;            
        }        
    }

    public void updateCameraY(int y){
        if((y - cameraY)>(height*.8)){ //if difference is at least 80% screen width (GOING DOWN)
            if((y - (int)(height*.8))  < (8192 - height)){
                cameraY = y - (int)(height*.8);
            }else
                cameraY = (8192)-height;       
        }else if((y - cameraY)<(height*.2)){// if the difference is less than 20% screen width
            if(y - (int)(height*.2) > 0){
                cameraY = y - (int)(height*.2); 
            }else
                cameraY = 0;            
        }        
    }

    public void renderPixel(int x,int y,int c){      
        pixels[x + y*width]=c;
    }

    public void centerCamera(Entity e){
        PositionComponent position = e.getComponent(PositionComponent.class);

        cameraX = position.getX()-width/2;
        cameraY = position.getY()-height/2;

        updateCameraX(position.getX());
        updateCameraY(position.getY());
    }

    public Entity getFocus(){
        return focus;
    }

    public void setFocus(Entity e){
        focus = e;
    }

    public int getCameraX(){return cameraX;}

    public int getCameraY(){return cameraY;}

    //     public void setOffset(int x, int y){
    //         this.xOffset=x;
    //         this.yOffset=y;
    //     }
}