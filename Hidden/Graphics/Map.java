package Hidden.Graphics;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.text.Position;
import java.awt.image.BufferedImage;
import java.io.IOException;

import Hidden.Components.PositionComponent;
import Hidden.Graphics.Tile;
import Hidden.Util.Noise;
//import Hidden.LivingEntity.*;
import Hidden.Game;
import Hidden.Textures.Sprite;
import com.badlogic.ashley.core.Entity;

import java.awt.Color;
public class Map
{
    private float[][] heightMap;
    private Tile[][] tileMap;
    private long seed;
    public int height,width;

    public Sprite minimap;
    /**
     *  s = Seed -1 For Random, Width & Height of Map, waterLevel 0.0 - 1.0
     */
    public Map(long s, int width, int height, float waterLevel){
        //Make sure that waterlevel is a value from 0-1
        while(waterLevel>1){
            waterLevel/=10;
        }
        seed = s;//set seed
        this.height = height;
        this.width = width;
        heightMap = new float[width][height]; // heightmap blank array
        heightMap = generateHeightMap(width,height); // generate heightmap
        tileMap = new Tile[width][height]; // tilemap blank array
        generateTileMap(width, height, heightMap, waterLevel); //generate tilemap
        while(smoothTerrain()!=0){}
        generateTileTypes();
        generateMinimap();
    }

    public Map(int width,int height){
        this.height = height;
        this.width = width;
        tileMap = new Tile[width][height];
        int c = 0;
        for(int i = 0 ; i < width; i++){
            c++;
            for(int j = 0; j < height; j++){
                c++;
                tileMap[i][j] = (c%2==0)? Tile.water:Tile.undefined;
            }
        }
    }

    private float[][] generateHeightMap(int width, int height){
        float hm[][] = new float[width][height];
        hm = Noise.noise(width, height, seed);//Make basic noise
        hm = Noise.generatePerlinNoise(hm,7); // Smooth into Perlin noise

        return hm;
    }

    private void generateTileMap(int width, int height, float[][] hm, float wl){
        float land = 1-wl;
        for(int i = 0 ; i < width; i++){
            for(int j = 0; j < height; j++){
                float val = hm[i][j];                
                if(val < (wl * .8)){tileMap[i][j]= new Tile(Tile.deepWater);}
                else if(val < wl){tileMap[i][j]= new Tile(Tile.water);}
                else if(val < (wl + land*.25)){tileMap[i][j]= new Tile(Tile.lowland);}
                else if(val < (wl + land*.5)){tileMap[i][j]= new Tile(Tile.grass);}
                else if(val < (wl + land*.65)){tileMap[i][j]= new Tile(Tile.mountainLow);}
                else if(val < (wl + land*.8)){tileMap[i][j]= new Tile(Tile.mountain);}
                else if(val < 1){tileMap[i][j]= new Tile(Tile.peak);}
            }
        }

        //tm = generateTileTypes(tm);
        //return tm;
    }

    public void generateTileTypes(){
        Tile nextLevel = new Tile(Tile.grass),previousLevel = new Tile(Tile.water);        
        boolean nw,n,ne,e,w,sw,s,se;
        boolean surrounding[][];
        for (int y = 0; y < tileMap[0].length; y++){
            for (int x = 0; x < tileMap.length; x++){                
                surrounding = getBelow(tileMap,x-1,y-1);
                nw = surrounding [0][0];
                n = surrounding [1][0];
                ne = surrounding [2][0];
                w = surrounding [0][1];
                e = surrounding [2][1];
                sw = surrounding [0][2];
                s = surrounding [1][2];
                se = surrounding [2][2];                
                if(!nw && !n && !ne && !w && !e && !sw && !s && !se){tileMap[x][y].setType(0);
                    tileMap[x][y].setForest((Math.random()>.7));}
                else if(!nw && !n && !ne && !w && !e && !sw && !s && se){tileMap[x][y].setType(1);}                    
                else if(!nw && !n && ne && !w && !e && !sw && !s && !se){tileMap[x][y].setType(2);}    
                else if(!nw && !n && !ne && !w && !e && sw && !s && !se){tileMap[x][y].setType(3);}  
                else if(nw && !n && !ne && !w && !e && !sw && !s && !se){tileMap[x][y].setType(4);}  
                else if(nw && n && w && !e && !s && !se){tileMap[x][y].setType(5);}  
                else if(!n && !ne && w && !e && sw && s){tileMap[x][y].setType(6);}
                else if(n && ne && !w && e && !sw && !s){tileMap[x][y].setType(7);}
                else if(!nw && !n &&!w && e && s){ tileMap[x][y].setType(8);}
                else if(!w && !n &&!s && e){tileMap[x][y].setType(9);}
                else if(w && !n &&!s && !e){tileMap[x][y].setType(10);}
                else if(!w && !s && !e && n){tileMap[x][y].setType(11);}
                else if(!w && !n && !e && s){tileMap[x][y].setType(12);}
            }
        }
    }

    //     public void generateTileTypes(){
    //         Tile nextLevel = new Tile(Tile.grass),previousLevel = new Tile(Tile.water);        
    //         boolean nw,n,ne,e,w,sw,s,se;
    //         boolean surrounding[][];
    //         for (int y = 0; y < tileMap[0].length; y++){
    //             for (int x = 0; x < tileMap.length; x++){                
    //                 surrounding = getBelow(tileMap,x-1,y-1);
    //                 nw = surrounding [0][0];
    //                 n = surrounding [1][0];
    //                 ne = surrounding [2][0];
    //                 w = surrounding [0][1];
    //                 e = surrounding [2][1];
    //                 sw = surrounding [0][2];
    //                 s = surrounding [1][2];
    //                 se = surrounding [2][2];                
    //                 if(!nw && !n && !ne && !w && !e && !sw && !s && !se){tileMap[x][y].setType(0);
    //                 tileMap[x][y].setForest((Math.random()>.5));}
    //                 else if(!nw && !n && !ne && !w && !e && !sw && !s && se){tileMap[x][y].setType(1);}                    
    //                 else if(!nw && !n && ne && !w && !e && !sw && !s && !se){tileMap[x][y].setType(2);}    
    //                 else if(!nw && !n && !ne && !w && !e && sw && !s && !se){tileMap[x][y].setType(3);}  
    //                 else if(nw && !n && !ne && !w && !e && !sw && !s && !se){tileMap[x][y].setType(4);}  
    //                 else if(nw && n && w && !e && !s && !se){tileMap[x][y].setType(5);}  
    //                 else if(!n && !ne && w && !e && sw && s){tileMap[x][y].setType(6);}
    //                 else if(n && ne && !w && e && !sw && !s){tileMap[x][y].setType(7);}
    //                 else if(!nw && !n &&!w && e && s){ tileMap[x][y].setType(8);}
    //                 else if(!w && !n &&!s && e){tileMap[x][y].setType(9);}
    //                 else if(w && !n &&!s && !e){tileMap[x][y].setType(10);}
    //                 else if(!w && !s && !e && n){tileMap[x][y].setType(11);}
    //                 else if(!w && !n && !e && s){tileMap[x][y].setType(12);}
    //             }
    //         }
    //     }

    public void generatePNG(String s){
        BufferedImage image = new BufferedImage(tileMap.length * 16 ,tileMap[0].length * 16, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < tileMap[0].length; y++)
        {
            for (int x = 0; x < tileMap.length; x++)
            {
                //System.out.print(tileMap[x][y].getTerrain()+"\t");
                image = tileMap[x][y].drawTile(image, x, y);
            }
            //System.out.println();
        }
        try {
            File outputfile = new File(s+".png");
            outputfile.createNewFile();

            ImageIO.write(image, "png", outputfile);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public boolean[][] getBelow(Tile[][] t,int xStart,int yStart){ //returns true if tiles below center
        boolean st[][] = new boolean[3][3];
        Tile center = t[xStart+1][yStart+1];
        for (int y = yStart; y < yStart+3; y++){
            for (int x = xStart; x < xStart+3; x++) {
                if(y >= t[0].length || y < 0 || x >= t.length || x < 0 ){
                    st[x-xStart][y-yStart] = false;
                }else{st[x-xStart][y-yStart] = center.below(t[x][y]);}
            }
        }    
        return st;
    }

    public Tile[][] getSurrounding(Tile[][] t,int xStart,int yStart){ //returns true if tiles below center
        Tile st[][] = new Tile[3][3];
        Tile center = t[xStart+1][yStart+1];
        for (int y = yStart; y < yStart+3; y++){
            for (int x = xStart; x < xStart+3; x++) {
                if(y >= t[0].length || y < 0 || x >= t.length || x < 0 ){st[x-xStart][y-yStart] = center;}
                else{st[x-xStart][y-yStart]=t[x][y];}                
            }
        }
        return st;
    }

    public int smoothTerrain(){ //problem - doesnt take into account things above / multiple below
        Tile st[][] = new Tile[3][3];
        Tile center;
        Tile above,below;
        int aCount = 0, bCount = 0;
        int sCount = 0;
        for (int y = 0; y < tileMap[0].length; y++){
            for (int x = 0; x < tileMap.length; x++){
                st = getSurrounding(tileMap,x-1,y-1);
                center = tileMap[x][y];
                below = new Tile(center.getBelow());
                above = new Tile(center.getAbove());
                aCount = 0;
                bCount = 0;
                for(int i = 0; i < 3; i++){
                    for(int j = 0; j < 3; j++){
                        if(st[j][i].equals(above)){
                            aCount++;
                        }
                        else if (st[j][i].equals(below)){
                            bCount++;
                        }
                    }
                }

                if(aCount>=5){
                    tileMap[x][y]=above;
                    sCount ++;
                }
                if(bCount>=5){
                    tileMap[x][y]=below;
                    sCount ++;
                }
            }
        }
        return sCount;
    }

    public void render(Screen screen){
        int xScroll = screen.getCameraX();
        int yScroll = screen.getCameraY();

        int x0 = xScroll >> 4; //x minimum tile
        int x1 = (xScroll + screen.width + 16) >> 4; //x maximum tile
        int y0 = yScroll >> 4; //y minimum tile
        int y1 = (yScroll + screen.height +  16)>> 4; //y maximum tile

        for(int y = y0; y < y1; y++){
            for(int x = x0; x < x1; x++){
                getTile(x,y).renderTile(x,y,screen);
            }
        }
    }

    public void generateMinimap(){
        int m[] = new int[width*height];
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                m[x+y*width] = tileMap[x][y].sprite.pixels[0];         
            }            
        }
        minimap = new Sprite(m,width*height);
    }

    public void renderMinimap(){

    }

    public void renderMinimap(Screen screen,int xPosition,int yPosition,int size,Entity e){
        PositionComponent p = e.getComponent(PositionComponent.class);

        int cX = (screen.getCameraX()/16);
        int cY = (screen.getCameraY()/16);
        int mX=(p.getX()/16)-(size/2); //equal to x bound of first minimap tile
        int mY=(p.getY()/16)-(size/2);
        if(mX<0){
            mX=0;
        }else if(mX>width-size){
            mX=width-size;
        }
        if(mY<0){
            mY=0;
        }else if(mY>height-size){
            mY=height-size;
        }        
        //System.out.println(mX+"Swag");
        //System.out.println(mY);
        for(int y = mY; y <size + mY; y++){
            for(int x = mX; x <size + mX; x++){
                screen.renderPixel(x+xPosition-mX,y+yPosition-mY,minimap.pixels[x+(y*width)]);
                //getTile(x /*-(size/2)*/,y/*-(size/2)*/).renderTileMini(x+xPosition-mX,y+yPosition-mY,screen);                
            }
        }

        int playerBlipX = ((p.getX()/16))-mX;
        int playerBlipY = ((p.getY()/16))-mY;

        //         playerBlipX += (size/2);
        //         playerBlipY += (size/2);
        screen.renderPixel(playerBlipX+xPosition,playerBlipY+yPosition, 0xFF0000);
    }

    public void renderMinimapCircle(Screen screen,int xPosition,int yPosition,int size,Entity e){
        PositionComponent p = e.getComponent(PositionComponent.class);

        int cX = (screen.getCameraX()/16);
        int cY = (screen.getCameraY()/16);
        int mX=(p.getX()/16)-(size/2); //equal to x bound of first minimap tile
        int mY=(p.getY()/16)-(size/2);

        int r = size/2;
        int r2 = r - 3;
        //System.out.println(mX+"Swag");
        //System.out.println(mY);
        for(int y = 0; y < size; y++){
            for(int x = 0; x < size; x++){
                //System.out.print((x+xPosition)+","+(y+yPosition));
                double dist = Math.sqrt(Math.pow(y-r,2)+Math.pow(x-r,2));
                if(dist<=r){
                    screen.renderPixel(x+xPosition,y+yPosition,0x00000);
                }
                if(dist<r2){
                    if((mX+x+(mY+y)*width)<(width*height) && (mX+x+(mY+y)*width) >= 0){
                        if(mX+x<width && mX+x>=0){
                            screen.renderPixel(x+xPosition, y+yPosition, minimap.pixels[mX+x+(mY+y)*width]);
                        }
                    }
                }
                //getTile(mX+x,mY+y).
                //renderTileMini(x+xPosition,y+yPosition,screen);

            }
        }

        //         for(int y = cY; y < cY/16 + size + cY; y++){
        //             for(int x = cX; x < cX/16 + size + cX; x++){
        //                 getTile(x /*-(size/2)*/,y/*-(size/2)*/).renderTileMini(x+xPosition,y+yPosition,screen);                
        //             }
        //         }
        //         playerBlipX += (size/2);
        //         playerBlipY += (size/2);
        screen.renderPixel(r+xPosition,r+yPosition, 0xFF0000);
    }

    public int getWidth(){return width;}

    public int getHeight(){return height;}

    public Tile getTile(int x, int y){
        //System.out.println("Generating Tile "+x+","+y);
        if(x<width&&y<height&&x>=0&&y>=0)
            return tileMap[x][y];
        return Tile.undefined;
    }

    public void setTile(int x, int y,Tile t){
        if(x<width&&y<height&&x>=0&&y>=0){
            tileMap[x][y].setTerrain(t.getTerrain());
            tileMap[x][y].setType(0);
            minimap.pixels[x+y*width] = t.sprite.pixels[0];
        }
    }
}
