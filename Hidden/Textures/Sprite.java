package Hidden.Textures;

public class Sprite
{
    private int size;
    private int x,y;
    public int pixels[];
    private SpriteSheet sheet;

    //Init Peak Tiles:
    public static Sprite peak = new Sprite(0,0,SpriteSheet.tiles);
    public static Sprite peakC1 = new Sprite(0,1,SpriteSheet.tiles); //bot right corner
    public static Sprite peakC2 = new Sprite(0,2,SpriteSheet.tiles); //top right corner
    public static Sprite peakC3 = new Sprite(0,3,SpriteSheet.tiles); //bot left corner
    public static Sprite peakC4 = new Sprite(0,4,SpriteSheet.tiles); //top left corner
    public static Sprite peakC5 = new Sprite(0,5,SpriteSheet.tiles); //inverse bot right corner
    public static Sprite peakC6 = new Sprite(0,6,SpriteSheet.tiles); //inverse top right corner
    public static Sprite peakC7 = new Sprite(0,7,SpriteSheet.tiles); //inverse bot left corner
    public static Sprite peakC8 = new Sprite(0,8,SpriteSheet.tiles); //inverse top left corner
    public static Sprite peakS1 = new Sprite(0,9,SpriteSheet.tiles); //left
    public static Sprite peakS2 = new Sprite(0,10,SpriteSheet.tiles);//right
    public static Sprite peakS3 = new Sprite(0,11,SpriteSheet.tiles);//down
    public static Sprite peakS4 = new Sprite(0,12,SpriteSheet.tiles);//up

    //Init Mountain Tiles:
    public static Sprite mountain = new Sprite(1,0,SpriteSheet.tiles);
    public static Sprite mountainC1 = new Sprite(1,1,SpriteSheet.tiles); //bot right corner
    public static Sprite mountainC2 = new Sprite(1,2,SpriteSheet.tiles); //top right corner
    public static Sprite mountainC3 = new Sprite(1,3,SpriteSheet.tiles); //bot left corner
    public static Sprite mountainC4 = new Sprite(1,4,SpriteSheet.tiles); //top left corner
    public static Sprite mountainC5 = new Sprite(1,5,SpriteSheet.tiles); //inverse bot right corner
    public static Sprite mountainC6 = new Sprite(1,6,SpriteSheet.tiles); //inverse top right corner
    public static Sprite mountainC7 = new Sprite(1,7,SpriteSheet.tiles); //inverse bot left corner
    public static Sprite mountainC8 = new Sprite(1,8,SpriteSheet.tiles); //inverse top left corner
    public static Sprite mountainS1 = new Sprite(1,9,SpriteSheet.tiles); //left
    public static Sprite mountainS2 = new Sprite(1,10,SpriteSheet.tiles);//right
    public static Sprite mountainS3 = new Sprite(1,11,SpriteSheet.tiles);//down
    public static Sprite mountainS4 = new Sprite(1,12,SpriteSheet.tiles);//up

    //Init MountainLow Tiles:
    public static Sprite mountainLow = new Sprite(2,0,SpriteSheet.tiles);
    public static Sprite mountainLowC1 = new Sprite(2,1,SpriteSheet.tiles); //bot right corner
    public static Sprite mountainLowC2 = new Sprite(2,2,SpriteSheet.tiles); //top right corner
    public static Sprite mountainLowC3 = new Sprite(2,3,SpriteSheet.tiles); //bot left corner
    public static Sprite mountainLowC4 = new Sprite(2,4,SpriteSheet.tiles); //top left corner
    public static Sprite mountainLowC5 = new Sprite(2,5,SpriteSheet.tiles); //inverse bot right corner
    public static Sprite mountainLowC6 = new Sprite(2,6,SpriteSheet.tiles); //inverse top right corner
    public static Sprite mountainLowC7 = new Sprite(2,7,SpriteSheet.tiles); //inverse bot left corner
    public static Sprite mountainLowC8 = new Sprite(2,8,SpriteSheet.tiles); //inverse top left corner
    public static Sprite mountainLowS1 = new Sprite(2,9,SpriteSheet.tiles); //left
    public static Sprite mountainLowS2 = new Sprite(2,10,SpriteSheet.tiles);//right
    public static Sprite mountainLowS3 = new Sprite(2,11,SpriteSheet.tiles);//down
    public static Sprite mountainLowS4 = new Sprite(2,12,SpriteSheet.tiles);//up

    //Init Grass Tiles:
    public static Sprite grass = new Sprite(3,0,SpriteSheet.tiles); //Solid Grass
    public static Sprite grassC1 = new Sprite(3,1,SpriteSheet.tiles); //bot right corner
    public static Sprite grassC2 = new Sprite(3,2,SpriteSheet.tiles); //top right corner
    public static Sprite grassC3 = new Sprite(3,3,SpriteSheet.tiles); //bot left corner
    public static Sprite grassC4 = new Sprite(3,4,SpriteSheet.tiles); //top left corner
    public static Sprite grassC5 = new Sprite(3,5,SpriteSheet.tiles); //inverse bot right corner
    public static Sprite grassC6 = new Sprite(3,6,SpriteSheet.tiles); //inverse top right corner
    public static Sprite grassC7 = new Sprite(3,7,SpriteSheet.tiles); //inverse bot left corner
    public static Sprite grassC8 = new Sprite(3,8,SpriteSheet.tiles); //inverse top left corner
    public static Sprite grassS1 = new Sprite(3,9,SpriteSheet.tiles); //left
    public static Sprite grassS2 = new Sprite(3,10,SpriteSheet.tiles);//right
    public static Sprite grassS3 = new Sprite(3,11,SpriteSheet.tiles);//down
    public static Sprite grassS4 = new Sprite(3,12,SpriteSheet.tiles);//up

    public static Sprite grassForest = new Sprite(6,2,SpriteSheet.tiles);

    //Init lowland Tiles:
    public static Sprite lowland = new Sprite(4,0,SpriteSheet.tiles);
    public static Sprite lowlandC1 = new Sprite(4,1,SpriteSheet.tiles); //bot right corner
    public static Sprite lowlandC2 = new Sprite(4,2,SpriteSheet.tiles); //top right corner
    public static Sprite lowlandC3 = new Sprite(4,3,SpriteSheet.tiles); //bot left corner
    public static Sprite lowlandC4 = new Sprite(4,4,SpriteSheet.tiles); //top left corner
    public static Sprite lowlandC5 = new Sprite(4,5,SpriteSheet.tiles); //inverse bot right corner
    public static Sprite lowlandC6 = new Sprite(4,6,SpriteSheet.tiles); //inverse top right corner
    public static Sprite lowlandC7 = new Sprite(4,7,SpriteSheet.tiles); //inverse bot left corner
    public static Sprite lowlandC8 = new Sprite(4,8,SpriteSheet.tiles); //inverse top left corner
    public static Sprite lowlandS1 = new Sprite(4,9,SpriteSheet.tiles); //left
    public static Sprite lowlandS2 = new Sprite(4,10,SpriteSheet.tiles);//right
    public static Sprite lowlandS3 = new Sprite(4,11,SpriteSheet.tiles);//down
    public static Sprite lowlandS4 = new Sprite(4,12,SpriteSheet.tiles);//up

    public static Sprite lowlandForest = new Sprite(6,1,SpriteSheet.tiles);

    //Init Water Tiles:
    public static Sprite water = new Sprite(5,0,SpriteSheet.tiles);
    public static Sprite waterC1 = new Sprite(5,1,SpriteSheet.tiles); //bot right corner
    public static Sprite waterC2 = new Sprite(5,2,SpriteSheet.tiles); //top right corner
    public static Sprite waterC3 = new Sprite(5,3,SpriteSheet.tiles); //bot left corner
    public static Sprite waterC4 = new Sprite(5,4,SpriteSheet.tiles); //top left corner
    public static Sprite waterC5 = new Sprite(5,5,SpriteSheet.tiles); //inverse bot right corner
    public static Sprite waterC6 = new Sprite(5,6,SpriteSheet.tiles); //inverse top right corner
    public static Sprite waterC7 = new Sprite(5,7,SpriteSheet.tiles); //inverse bot left corner
    public static Sprite waterC8 = new Sprite(5,8,SpriteSheet.tiles); //inverse top left corner
    public static Sprite waterS1 = new Sprite(5,9,SpriteSheet.tiles); //left
    public static Sprite waterS2 = new Sprite(5,10,SpriteSheet.tiles);//right
    public static Sprite waterS3 = new Sprite(5,11,SpriteSheet.tiles);//down
    public static Sprite waterS4 = new Sprite(5,12,SpriteSheet.tiles);//up

    public static Sprite player = new Sprite(0,0,SpriteSheet.player); //down

    public static Sprite deepWater = new Sprite(6,0,SpriteSheet.tiles);

    public static Sprite empty = new Sprite(0,0,SpriteSheet.player); //down
    //new Sprite(6,0,SpriteSheet.tiles);
    /**
     * X and Y are row and column of the texture in spritesheet
     */
    public Sprite(int x,int y, SpriteSheet s){
        sheet = s;
        size = sheet.getTileSize();
        this.x = size*x;
        this.y = size*y;     
        pixels = new int[size*size];
        load();        
    }

    public Sprite(int[] s,int sz){
        sheet = null;
        size = sz;
        this.x = size*x;
        this.y = size*y;
        pixels = s;
    }

    public int getSize(){
        return size;}

    private void load(){
        for( int y =0; y < size; y++){
            for(int x = 0; x < size; x++){
                pixels[x+(y*size)] = sheet.pixels[(x+this.x) + (y+this.y)*sheet.getWidth()];
            }
        }
    }
}
