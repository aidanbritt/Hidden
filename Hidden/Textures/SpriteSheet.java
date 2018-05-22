package Hidden.Textures;
import java.io.File;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
public class SpriteSheet
{
    private String path;
    private int width;
    private int height;
    public int pixels[]; 
    private int tileSize;

    public static SpriteSheet tiles = new SpriteSheet("/hidden/textures/tiles/tiles.png",208,208,16);
    public static SpriteSheet player = new SpriteSheet("/player.png",16,128,16);
    public static SpriteSheet player2 = new SpriteSheet("/player2.png",16,128,16);
    public static SpriteSheet player3 = new SpriteSheet("/player3.png",16,128,16);
    public static SpriteSheet player4 = new SpriteSheet("/player4.png",16,128,16);

    public SpriteSheet(String p,int w,int h,int s){
        tileSize = s;
        width = w;
        height = h;
        this.path = p;
        this.pixels = new int[width*height];    
        load();
    }

    public int getTileSize(){
        return tileSize;
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }

    private void load(){
        try{
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
            image.getRGB(0,0,width,height, pixels, 0, width);
            System.out.println(pixels[0]);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}