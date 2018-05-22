//package Hidden.LivingEntity;
//import Hidden.Textures.Sprite;
//import Hidden.Textures.SpriteSheet;
//import Hidden.Graphics.Screen;
//import Hidden.Graphics.Map;
//public class Player
//{
//    private int xPos = 0;
//    private int yPos = 0;
//
//    private int speed = 1;
//
//    private Sprite[] sprites = new Sprite[8];
//
//    private Map map;
//
//    private int state;
//
//    private boolean updateX=false,updateY=false;
//    public Player(int x, int y,Map m)
//    {
//        xPos = x;
//        yPos = y;
//        map = m;
//        for(int i = 0; i < 8; i++){
//            sprites[i] = new Sprite(0,i,SpriteSheet.player);
//        }
//    }
//
//    public void render(Screen screen){
//        if(updateX){
//            screen.updateCameraX(xPos);
//            updateX = false;
//        }
//        if(updateY){
//            screen.updateCameraY(yPos);
//            updateY = false;
//        }
//        screen.renderEntity(xPos,yPos,sprites[state]);
//    }
//
//    public void moveLeft(){
//        if(xPos-speed<0)
//            xPos = 0;
//        else
//            xPos = xPos - speed;
//        updateX = true;
//    }
//
//    public void moveRight(){
//        if((xPos+speed/*+width*/)>(map.getWidth()*sprites[0].getSize()))
//            xPos = (map.getWidth()-1)*sprites[0].getSize()/*-width*/;
//        else
//            xPos = xPos + speed;
//        updateX = true;
//    }
//
//    public void moveUp(){
//        if(yPos-speed<0)
//            yPos = 0;
//        else
//            yPos = yPos - speed;
//        updateY = true;
//    }
//
//    public void moveDown(){
//        if((yPos+speed/*+height*/)>(map.getHeight()*sprites[0].getSize()))
//            yPos= (map.getHeight()-1)*sprites[0].getSize()/*-height*/;
//        else
//            yPos = yPos + speed;
//        updateY = true;
//    }
//
//    public void setState(int s){
//        if(s<sprites.length){state = s;}
//    }
//
//    public int getXPos(){return xPos;}
//
//    public int getYPos(){return yPos;}
//
//    public void setXPos(int x){ xPos = x;}
//
//    public void setYPos(int y){ yPos = y;}
//
//    public void setSpeed(int s){ speed = s;}
//
//    public int getSpeed(){ return speed;}
//
//    public Sprite getSprite(){
//        return sprites[state];
//    }
//
//}
