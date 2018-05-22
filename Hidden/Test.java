package Hidden;
import Hidden.Textures.TileSprite;
import Hidden.Textures.SpriteSheet;
import Hidden.Graphics.Tile;

/**
 * Write a description of class Test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Test
{

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public static void main(String args[])
    {
        int s = TileSprite.peak.getSize();

        int p[] = (TileSprite.peak.getSprite(255));

        for(int i = 0; i < s*s; i++){
            if(i%s==0){
                System.out.println();
            }
            System.out.print(p[i]+" ");
        }
    }
}
