package Hidden.Graphics;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.*;
import java.io.IOException;
import Hidden.Textures.Sprite;
public class Tile
{
    public Sprite sprite;
    private String terrain;
    private int type;

    public static final Tile deepWater = new Tile(Sprite.deepWater,"DEEPWATER",0);
    public static final Tile water = new Tile(Sprite.water,"WATER",0);
    public static final Tile lowland = new Tile(Sprite.lowland,"LOWLAND",0);
    public static final Tile grass = new Tile(Sprite.grass,"GRASS",0);  
    public static final Tile mountainLow = new Tile(Sprite.mountainLow,"MOUNTAINLOW",0);
    public static final Tile mountain = new Tile(Sprite.mountain,"MOUNTAIN",0);
    public static final Tile peak = new Tile(Sprite.peak,"PEAK",0);   
    public static final Tile undefined = new Tile(Sprite.empty,"?",0);

    public static final Tile[] DEFAULTS = {deepWater,water,lowland,grass,mountain,peak,undefined};

    private boolean forest = false;
    public Tile(Sprite sprite,String terrain, int type){
        this.sprite = sprite;
        this.terrain = terrain;
    }

    public Tile(Tile other){
        this.sprite = other.sprite;
        this.terrain = other.terrain;
        this.type = other.type;
    }

    public void setForest(boolean f){
        forest = f;
        if(forest){
            if(terrain.equals("LOWLAND")){
                sprite = Sprite.lowlandForest;
                System.out.println("LF");
            }else if(terrain.equals("GRASS")){
                System.out.println("GF");
                sprite = Sprite.grassForest;

            }
        }
    }

    public boolean above(Tile t){        
        return this.getTerrain().equals(t.getBelow().getTerrain());
    }

    public Tile getAbove(){        
        if(this.isDeepWater()){return Tile.water;}
        else if(this.isWater()){return Tile.lowland;}
        else if(this.isLowland()){return Tile.grass;}
        else if(this.isGrass()){return Tile.mountainLow;}
        else if(this.isMountainLow()){return Tile.mountain;}
        else if(this.isMountain()){return Tile.peak;}
        return Tile.undefined;
    }

    public Tile getBelow(){                
        if(this.isWater()){return Tile.deepWater;}
        else if(this.isLowland()){return Tile.water;}
        else if(this.isGrass()){return Tile.lowland;}
        else if(this.isMountainLow()){return Tile.grass;}
        else if(this.isMountain()){return Tile.mountainLow;}
        else if(this.isPeak()){return Tile.mountain;}
        return Tile.undefined;
    }

    public boolean below(Tile t){
        return this.getTerrain().equals(t.getAbove().getTerrain());
    }

    public int[] getTexture(){
        return sprite.pixels;
    }

    public int getSize(){
        return sprite.getSize();
    }

    public String getTerrain(){
        return terrain;
    }

    public void setTerrain(String t){
        terrain = t;
        loadTexture();
    }

    public boolean isPeak(){
        if(terrain.equals("PEAK")){return true;}
        return false;
    }

    public boolean isMountain(){
        if(terrain.equals("MOUNTAIN")){return true;}
        return false;
    }

    public boolean isMountainLow(){
        if(terrain.equals("MOUNTAINLOW")){return true;}
        return false;
    }

    public boolean isGrass(){
        if(terrain.equals("GRASS")){return true;}
        return false;
    }

    public boolean isLowland(){
        if(terrain.equals("LOWLAND")){return true;}
        return false;
    }

    public boolean isWater(){
        if(terrain.equals("WATER")){return true;}
        return false;
    }

    public boolean isDeepWater(){
        if(terrain.equals("DEEPWATER")){return true;}
        return false;
    }

    /**
     * Represents the different types of tiles for each terrain type.
     * Value from 0 - 12
     * 
     * Returns true if successful
     */
    public boolean setType(int t){
        if(t < 13 && t > -1){
            type = t;
            loadTexture();
            return true;            
        }
        return false;
    }

    public int getType(){return type;}

    private void loadTexture(){
        if(terrain.equals("PEAK")){
            switch(type){
                case 0: sprite = Sprite.peak;
                break;
                case 1:  sprite = Sprite.peakC1;
                break;
                case 2:  sprite = Sprite.peakC2;
                break;
                case 3:  sprite = Sprite.peakC3;
                break;
                case 4:  sprite = Sprite.peakC4;
                break;
                case 5:  sprite = Sprite.peakC5;
                break;
                case 6:  sprite = Sprite.peakC6;
                break;
                case 7:  sprite = Sprite.peakC7;
                break;
                case 8:  sprite = Sprite.peakC8;
                break;
                case 9:  sprite = Sprite.peakS1;
                break;
                case 10: sprite = Sprite.peakS2;
                break;
                case 11: sprite = Sprite.peakS3;
                break;
                case 12: sprite = Sprite.peakS4;
                break;
            }
        }else if(terrain.equals("MOUNTAIN")){
            switch(type){
                case 0: sprite = Sprite.mountain;
                break;
                case 1:  sprite = Sprite.mountainC1;
                break;
                case 2:  sprite = Sprite.mountainC2;
                break;
                case 3:  sprite = Sprite.mountainC3;
                break;
                case 4:  sprite = Sprite.mountainC4;
                break;
                case 5:  sprite = Sprite.mountainC5;
                break;
                case 6:  sprite = Sprite.mountainC6;
                break;
                case 7:  sprite = Sprite.mountainC7;
                break;
                case 8:  sprite = Sprite.mountainC8;
                break;
                case 9:  sprite = Sprite.mountainS1;
                break;
                case 10: sprite = Sprite.mountainS2;
                break;
                case 11: sprite = Sprite.mountainS3;
                break;
                case 12: sprite = Sprite.mountainS4;
                break;
            }
        }else if(terrain.equals("MOUNTAINLOW")){
            switch(type){
                case 0: sprite = Sprite.mountainLow;
                break;
                case 1:  sprite = Sprite.mountainLowC1;
                break;
                case 2:  sprite = Sprite.mountainLowC2;
                break;
                case 3:  sprite = Sprite.mountainLowC3;
                break;
                case 4:  sprite = Sprite.mountainLowC4;
                break;
                case 5:  sprite = Sprite.mountainLowC5;
                break;
                case 6:  sprite = Sprite.mountainLowC6;
                break;
                case 7:  sprite = Sprite.mountainLowC7;
                break;
                case 8:  sprite = Sprite.mountainLowC8;
                break;
                case 9:  sprite = Sprite.mountainLowS1;
                break;
                case 10: sprite = Sprite.mountainLowS2;
                break;
                case 11: sprite = Sprite.mountainLowS3;
                break;
                case 12: sprite = Sprite.mountainLowS4;
                break;
            }
        }else if(terrain.equals("GRASS")){
            switch(type){
                case 0: 
                if(forest)
                    sprite=Sprite.grassForest;
                else
                    sprite = Sprite.grass;
                break;
                case 1:  sprite = Sprite.grassC1;
                break;
                case 2:  sprite = Sprite.grassC2;
                break;
                case 3:  sprite = Sprite.grassC3;
                break;
                case 4:  sprite = Sprite.grassC4;
                break;
                case 5:  sprite = Sprite.grassC5;
                break;
                case 6:  sprite = Sprite.grassC6;
                break;
                case 7:  sprite = Sprite.grassC7;
                break;
                case 8:  sprite = Sprite.grassC8;
                break;
                case 9:  sprite = Sprite.grassS1;
                break;
                case 10: sprite = Sprite.grassS2;
                break;
                case 11: sprite = Sprite.grassS3;
                break;
                case 12: sprite = Sprite.grassS4;
                break;
            }
        }else if(terrain.equals("LOWLAND")){
            switch(type){
                case 0: sprite = Sprite.lowland;
                break;
                case 1:  sprite = Sprite.lowlandC1;
                break;
                case 2:  sprite = Sprite.lowlandC2;
                break;
                case 3:  sprite = Sprite.lowlandC3;
                break;
                case 4:  sprite = Sprite.lowlandC4;
                break;
                case 5:  sprite = Sprite.lowlandC5;
                break;
                case 6:  sprite = Sprite.lowlandC6;
                break;
                case 7:  sprite = Sprite.lowlandC7;
                break;
                case 8:  sprite = Sprite.lowlandC8;
                break;
                case 9:  sprite = Sprite.lowlandS1;
                break;
                case 10: sprite = Sprite.lowlandS2;
                break;
                case 11: sprite = Sprite.lowlandS3;
                break;
                case 12: sprite = Sprite.lowlandS4;
                break;
            }
        }else if(terrain.equals("WATER")){
            switch(type){
                case 0: sprite = Sprite.water;
                break;
                case 1:  sprite = Sprite.waterC1;
                break;
                case 2:  sprite = Sprite.waterC2;
                break;
                case 3:  sprite = Sprite.waterC3;
                break;
                case 4:  sprite = Sprite.waterC4;
                break;
                case 5:  sprite = Sprite.waterC5;
                break;
                case 6:  sprite = Sprite.waterC6;
                break;
                case 7:  sprite = Sprite.waterC7;
                break;
                case 8:  sprite = Sprite.waterC8;
                break;
                case 9:  sprite = Sprite.waterS1;
                break;
                case 10: sprite = Sprite.waterS2;
                break;
                case 11: sprite = Sprite.waterS3;
                break;
                case 12: sprite = Sprite.waterS4;
                break;
            }
        }else if(terrain.equals("DEEPWATER")){
            switch(type){
                case 0: sprite = Sprite.deepWater;
                break;
            }
        }
        else{
            sprite = Sprite.empty;
        }
    }

    public BufferedImage drawTile(BufferedImage i,int xPos, int yPos){
        int p[]= sprite.pixels;
        for (int y = 0; y < sprite.getSize(); y++)
        {
            for (int x = 0; x < sprite.getSize(); x++)
            {
                i.setRGB((xPos*sprite.getSize())+x,(yPos*sprite.getSize())+y,p[x + y*sprite.getSize()]);
            }
        }
        return i;
    }    

    public void renderTile(int x, int y, Screen screen){
        screen.renderTile(x<<4,y<<4,this);
    }

    public void renderTileMini(int x, int y, Screen screen){
        screen.renderTileMini(x,y,this);
    }    

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Tile){
            Tile o = (Tile)obj;
            if(this.getTerrain().equals(o.getTerrain())){
                if(this.getType()==o.getType()){
                    return true;
                }
            }
        }
        return false;
    }
}