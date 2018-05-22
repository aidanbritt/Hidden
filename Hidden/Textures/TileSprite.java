package Hidden.Textures;
import Hidden.Textures.SpriteSheet;
public class TileSprite
{
    private int size;
    private int x,y;
    public int pixels[][];
    private SpriteSheet sheet;

    private static int[] indexes = {11,22,31,104,107,127,208,214,223,248,251,254,255};
    public static TileSprite peak = new TileSprite(0,0,SpriteSheet.tiles);
    /**
     * X and Y are row and column of the texture in spritesheet
     */
    public TileSprite(int x,int y, SpriteSheet s){
        sheet = s;
        size = sheet.getTileSize();
        this.x = size*x;
        this.y = size*y;     
        pixels = new int[256][size*size];
        load();        
    }

    public int getSize(){
        return size;}

    public int[] getSprite(int i){
        return pixels[i];
    }
        
    private void load(){
        for(int i = 0; i < sheet.getHeight()/16;i++){
            for( int y =0; y < size; y++){
                for(int x = 0; x < size; x++){
                    pixels[indexes[i]][x+(y*size)] = sheet.pixels[(x+this.x) + (y+this.y)*sheet.getWidth()];
                }
            }
        }
    }

}
