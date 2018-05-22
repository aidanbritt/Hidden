package Hidden;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;
import javax.swing.text.Position;
import java.awt.image.BufferedImage;
import java.awt.image.BufferStrategy;
import java.awt.event.MouseAdapter;
import java.awt.event.*;
import java.security.Key;

import Hidden.Components.*;
import Hidden.EntitySystems.DirectionalSpriteRenderSystem;
import Hidden.EntitySystems.KeyboardControlSystem;
import Hidden.EntitySystems.MovementSystem;
import Hidden.EntitySystems.SpriteRenderSystem;
import Hidden.Graphics.Screen;
import Hidden.Graphics.Map;
import Hidden.Graphics.Tile;
import Hidden.Input.*;
//import Hidden.LivingEntity.*;
import Hidden.Textures.SpriteSheet;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;


public class Game extends Canvas implements Runnable
{
    public static int width = 1024;
    public static int height = 512;;
    //public static int scale = 13;

    public static int fps = 0;
    public static int ups = 0;

    public static String title = "Hidden MapGen";

    private Thread thread;
    private JFrame frame;
    private boolean running = false;
    private static Screen screen;
    private static Keyboard key;
    private Mouse mouse;
    //private Player p;

    private BufferedImage image = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

    Map m;
    int mapSize;
    float waterLevel;

    //Component System
    Engine engine;
    Entity testEntity;//temporary

    public Game(){
        //Family family = Family.all(PositionComponent.class).get();  //create a family for all entities with a position
        engine = new Engine();                                          //create engine that controls all components 1 per instance of program
        engine.addSystem(new MovementSystem());     //add movement system to engine

        //MovementSystem movementSystem = engine.getSystem(MovementSystem.class); //retrieve movement system from engine
        //movementSystem.setProcessing(false);                                    //turn movement system off

        /********** Setup Window **********/  	
        Dimension size = new Dimension(width,height);   //create dimensions of window
        setPreferredSize(size);                         //set size of window
        screen = new Screen(width,height);              //create screen
        frame = new JFrame();                           //create jframe        

        engine.addSystem(new SpriteRenderSystem(screen));
        engine.addSystem(new DirectionalSpriteRenderSystem(screen));
        engine.addSystem(new KeyboardControlSystem());

        /********** Setup Map **********/        
        mapSize = 512;                                  //set map size
        waterLevel = .6f;                               //set map waterlevel
        m = new Map(-1,mapSize,mapSize,waterLevel);     //create map with rand seed

        /********** Add Listeners **********/
        key = new Keyboard();                           //create keyboard
        mouse = new Mouse();                            //create mouse        
        addKeyListener(key);
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
        addMouseWheelListener(mouse);

        /********** Create Player **********/
        //p = new Player(mapSize*8,mapSize*8,m);                        //create player
        //screen.centerCamera(p);                                       //center camera around player

        testEntity = new Entity();                                      //entity for testing
        engine.addEntity(testEntity);                                   //add entity to the engine
        testEntity.add(new PositionComponent((mapSize*16)/2,(mapSize*16)/2));     //add pos component to test
        testEntity.add(new VelocityComponent(0,0));                      //add vel component to test
        testEntity.add(new DirectionalSpriteComponent(SpriteSheet.player));
        testEntity.add(new MovementKeysComponent(true,KeyEvent.VK_1));

        Entity testEntity2 = new Entity();
        engine.addEntity(testEntity2);
        testEntity2.add(new PositionComponent(((mapSize*16)/2)+64,(mapSize*16)/2));     //add pos component to test
        testEntity2.add(new VelocityComponent(0,0));                      //add vel component to test
        testEntity2.add(new DirectionalSpriteComponent(SpriteSheet.player2));
        testEntity2.add(new MovementKeysComponent(false,KeyEvent.VK_2));

        Entity testEntity3 = new Entity();
        engine.addEntity(testEntity3);
        testEntity3.add(new PositionComponent(((mapSize*16)/2),((mapSize*16)/2)+64));     //add pos component to test
        testEntity3.add(new VelocityComponent(0,0));                      //add vel component to test
        testEntity3.add(new DirectionalSpriteComponent(SpriteSheet.player3));
        testEntity3.add(new MovementKeysComponent(false,KeyEvent.VK_3));

        Entity testEntity4 = new Entity();
        engine.addEntity(testEntity4);
        testEntity4.add(new PositionComponent(((mapSize*16)/2)+64,((mapSize*16)/2)+64));     //add pos component to test
        testEntity4.add(new VelocityComponent(0,0));                      //add vel component to test
        testEntity4.add(new DirectionalSpriteComponent(SpriteSheet.player4));
        testEntity4.add(new MovementKeysComponent(false,KeyEvent.VK_4));

        screen.centerCamera(testEntity);
        screen.setFocus(testEntity);


    }

    
    public static void main(String[] args){
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.setTitle(title);
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null); //center frame
        game.frame.setVisible(true);
        game.start();
    }
    

    public synchronized void start(){
        running = true;
        thread = new Thread(this,"Display");
        thread.start();
    }

    public synchronized void stop(){
        running = false;
        try{
            thread.join();
        }
        catch(InterruptedException e){
            System.out.println(e);
        }
    }

    public void run(){
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000D / 60D; //ratio of nanoseconds per update
        double delta = 0;
        int frames = 0;
        int updates = 0;
        while(running){
            long now = System.nanoTime();            
            delta += (now - lastTime)/ns; //delta is equal to one 60x every second

            lastTime = now; //reset the last time
            while(delta >= 1){
                update();
                updates++;
                delta--;
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer = System.currentTimeMillis();
                ups = updates;
                fps = frames;
                updates = 0;
                frames = 0;
            }
        }
    }

    Tile t = Tile.undefined;
    int tileX,tileY,cameraX,cameraY = 0;
    boolean showGrid = false,selectTile = false,clicked = false,showMap=false;
    int listIndex = 1;
    int gridColor=0;
    public void update(){
        key.update();
//        if(key.plus){
//            p.setSpeed(p.getSpeed()+1);
//        }
//        if(key.minus){
//            p.setSpeed(p.getSpeed()-1);
//        }

        if(key.grid){
            showGrid = true;
            gridColor ++;
            if(gridColor>3){
                showGrid = false;
                gridColor=0;
            }
        }
        if(key.z){
            m.generateTileTypes();
        }

        PositionComponent p = testEntity.getComponent(PositionComponent.class);

        //if(cameraY<0)p.setX(0);
        //if(cameraY>(mapSize*16) - height)p.setY((mapSize*16)-height);
        //if(cameraX<0)p.setY(0);
        //if(cameraX>(mapSize*16) - width)p.setX((mapSize*16)-width);

        if(mouse.hasLClicked()){
            selectTile=true;                       
        }

        if(selectTile){
            if(mouse.mouseUp()){
                listIndex++;
                mouse.mouseUp = false;
            }
            if(mouse.mouseDown()){
                listIndex--;
                mouse.mouseDown = false;
            }
            if(listIndex>Tile.DEFAULTS.length){
                listIndex = 1;
            }
            if(listIndex<1){
                listIndex = Tile.DEFAULTS.length;
            }
            if(key.enter){
                m.setTile(tileX, tileY, Tile.DEFAULTS[listIndex-1]);
            }
            if(mouse.hasLClicked()){
                m.setTile(mouse.getLastLClickedX(),mouse.getLastLClickedY(),Tile.DEFAULTS[listIndex-1]);
                //mouse.setLClicked(false);
            }
        }

        if(mouse.hasRClicked()){
            int oldX=tileX;
            int oldY=tileY;
            tileX = mouse.getLastRClickedX();
            tileY = mouse.getLastRClickedY();

            t = m.getTile(tileX,tileY);
            if(oldX==tileX&&oldY==tileY){
                selectTile = !selectTile;
            }
            else{selectTile = true;}

            //System.out.println("Boop");
            mouse.setRClicked(false);            
        }
        cameraX = screen.getCameraX();
        cameraY = screen.getCameraY();

        if(key.q){
            showMap = !showMap;           
        }
        if(key.reset){
//            p.setXPos(0);
//            p.setYPos(0);

            m = new Map(-1,mapSize,mapSize,waterLevel);
            screen.updateCameraX(0);
            screen.updateCameraY(0);
        }

    }

    public void render(){
        BufferStrategy bs = getBufferStrategy();
        if(bs==null){
            createBufferStrategy(3); //triple buffer;
            return;
        }

        screen.clear();
        //screen.render(x,y);
        m.render(screen);

        engine.update(1);
        //p.render(screen);
        if(showMap){
            m.renderMinimap(screen,(mapSize/2),0,mapSize,screen.getFocus());
        }
        m.renderMinimapCircle(screen,0,height-150,150,screen.getFocus());
        for(int i = 0; i < pixels.length; i++){
            pixels[i] = screen.pixels[i];
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0,0,getWidth(),getHeight());
        g.drawImage(image,0,0,getWidth(),getHeight(),null);

        g.setColor(new Color(0,0,0,128));
        g.fillRect(0,0,200,145);

        if(showGrid)
            drawGrid(g);

        g.setColor(Color.WHITE);
        g.drawString("UPS: "+ups,0,10);
        g.drawString("FPS: "+fps,0,25);

        g.drawString("Tile: "+t.getTerrain()+t.getType(),0,55);
        g.drawString("Coordinates: ("+tileX+","+tileY+")",0,70);

        PositionComponent p = testEntity.getComponent(PositionComponent.class);

        g.drawString("p Coordinates: ("+p.getX()+","+p.getY()+")",0,100);
        g.drawString("Camera Coordinates: (" + screen.getCameraX() + "," + screen.getCameraY() + ")", 0, 115);

        //g.drawString("Speed: "+p.getSpeed(),0,130);

        if(selectTile){
            g.drawRect(tileX*16 - cameraX, tileY*16 - cameraY, 15, 15);

            g.setColor(new Color(0,0,0,128));
            g.fillRect(getWidth()-100,0, 100, getHeight());

            int count=1;
            for(Tile t:Tile.DEFAULTS){
                if(count==listIndex)
                    g.setColor(Color.GREEN);
                else
                    g.setColor(Color.WHITE);
                g.drawString(count+"-"+t.getTerrain(), getWidth()-90,count*15);
                count++;
            }
        }else{listIndex = 1;}

        g.dispose();

        bs.show();
    }

    private void drawGrid(Graphics g){
        //horizontal
        int c = cameraY/16;
        g.setColor(Color.BLACK);
        int y = 15 - cameraY%16;
        while(y<getHeight()){            
            c++;
            if(c%4==0)
                g.setColor(new Color(255,255,255,gridColor*85));
            else
                g.setColor(new Color(0,0,0,gridColor*85));
            g.drawLine(0, y, getWidth(), y);
            y+=16;
        }
        c = cameraX/16;
        int x= 15 - cameraX%16;
        while(x<getWidth()){        
            c++;
            if(c%4==0)
                g.setColor(new Color(255,255,255,gridColor*85));
            else
                g.setColor(new Color(0,0,0,gridColor*85));
            g.drawLine(x, 0, x, getWidth());
            x+=16;
        }
    }

    public static Screen getScreen(){return screen;}

    public static Keyboard getKey(){return key;}
}