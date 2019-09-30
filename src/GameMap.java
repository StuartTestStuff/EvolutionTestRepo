import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameMap {
    private static final int CREATURE_ID_PREFIX = 0;
    private static final int FOOD_ID_PREFIX = 1;
    private static final int FEEDINGAREA_ID_PREFIX = 2;
    private int width;
    private MapPixel[][] pixelArr;
    private HashMap<String, MapObject> mapObjects = new HashMap<String, MapObject>();
    public void settings(){
        MainClass.processing.size(width,width);
    }
    public static int getCreatureIdPrefix(){
        return CREATURE_ID_PREFIX;
    }
    public static int getFoodIdPrefix(){
        return FOOD_ID_PREFIX;
    }
    public static int getFeedingareaIdPrefix(){
        return FEEDINGAREA_ID_PREFIX;
    }
    /**
     * Constructor for game map, creates a square game map
     * @param width width of map
     */
    public GameMap(int width){
        this.width = width;
        pixelArr = new MapPixel[width][width];
        for(int i = 0; i < width; i++){
            for(int j = 0; j < width; j++){
                pixelArr[i][j] = new MapPixel(i,j);
            }
        }
    }


    private double distanceBetweenTwoPoints(int x1, int y1, int x2, int y2){
        //System.out.println("x1: " + x1  + " y1: " + y1 +  " x2: " + x2 + " y2: " + y2);
        int xDiff = Math.abs(x1-x2);
        int yDiff = Math.abs(y1-y2);
        //System.out.println("xDiff: " + xDiff + " yDiff: " + yDiff);
        double distance = Math.sqrt((xDiff*xDiff) + (yDiff*yDiff));
        //System.out.println("Distance: " + distance);
        return distance;
    }
    /**
     * Adds object to the mapObjects arraylist
     * @param toAdd the MapObject to add
     */
    public void addObject(MapObject toAdd){
        mapObjects.put(toAdd.getObjID(), toAdd);
        int xCenter = toAdd.getXCenter();
        int yCenter = toAdd.getYCenter();
        int size = toAdd.getSize();
        for(int i = (xCenter - size); i <= (xCenter + size); i++){
            for(int j = (yCenter - size); j <= (yCenter + size); j++){
                double distanceFromCenter = distanceBetweenTwoPoints(xCenter, yCenter, i,j);
                if(distanceFromCenter <= size){
                    pixelArr[i][j].addObjID(toAdd);
                }
            }
        }
    }

    public void moveCreature(Creature c, double direction){
        int xCenter = c.getXCenter();
        int yCenter = c.getYCenter();
        c.moveCreature(direction);
        int newXCenter = c.getXCenter();
        int newYCenter = c.getYCenter();
        int xVelocity = (int) Math.floor(c.getxVelocity());
        int yVelocity = (int) Math.floor(c.getyVelocity());

        System.out.println("x: " + xVelocity + " " + c.getxVelocity());
        System.out.println("y: " + yVelocity + " " + c.getyVelocity());
        int size = c.getSize();

        for(int i = (xCenter - size); i <= (xCenter + size); i++){
            for(int j = (yCenter - size); j <= (yCenter + size); j++){
                //System.out.println(xVelocity + " " + yVelocity);
                if(pixelArr[i][j].getBeingUpdated()){ continue;}
                ArrayList<String> mapObjectsHere = pixelArr[i][j].getMapObjectsHere();

                ArrayList<String> mapObjectsThere = pixelArr[i+xVelocity][j+yVelocity].getMapObjectsHere(); //map objects at new location
                //System.out.println(mapObjectsThere.contains(c.getObjID()));
                if(!mapObjectsThere.contains(c.getObjID()) && mapObjectsHere.contains(c.getObjID()) && !pixelArr[i][j].getBeingUpdated()){ //if new location doesnt have the object id
                    //System.out.println("Successful pixel check");

                    if(0 <= direction && direction <= ((Math.PI)/2)){ //bottom right quadrant
                        pixelArr[i+xVelocity][j+yVelocity].addObjID(c);
                        pixelArr[i+xVelocity][j+yVelocity].setBeingUpdated(true);
                        pixelArr[xCenter*2 - i][yCenter*2 - j].removeObjID(c.getObjID());
                        pixelArr[xCenter*2 - i][yCenter*2 - j].setBeingUpdated(true);
                    } else if ((Math.PI/2) < direction && direction <= (Math.PI)){ //bottom left quadrant
                        pixelArr[i+xVelocity][j+yVelocity].addObjID(c);
                        pixelArr[i+xVelocity][j+yVelocity].setBeingUpdated(true);
                        pixelArr[xCenter*2 - i][yCenter*2 - j].removeObjID(c.getObjID());
                        pixelArr[xCenter*2 - i][yCenter*2 - j].setBeingUpdated(true);
                    } else if ((Math.PI) < direction && direction <= (3*Math.PI/2)){ //top left quadrant
                        pixelArr[i+xVelocity][j+yVelocity].addObjID(c);
                        pixelArr[i+xVelocity][j+yVelocity].setBeingUpdated(true);
                        pixelArr[xCenter*2 - i][yCenter*2 - j].removeObjID(c.getObjID());
                        pixelArr[xCenter*2 - i][yCenter*2 - j].setBeingUpdated(true);
                    } else if ((3*Math.PI/2) < direction && direction < (2*Math.PI)){ //top right quadrant
                        pixelArr[i+xVelocity][j+yVelocity].addObjID(c);
                        pixelArr[i+xVelocity][j+yVelocity].setBeingUpdated(true);
                        pixelArr[xCenter*2 - i][yCenter*2 - j].removeObjID(c.getObjID());
                        pixelArr[xCenter*2 - i][yCenter*2 - j].setBeingUpdated(true);
                    }

                    //TODO: THIS IS NOT CURRENTLY WORKING AT ALL
                }
            }
        }
        for(int i = (xCenter - size-Math.abs(xVelocity)); i <= (xCenter + size+Math.abs(xVelocity)); i++) {
            for (int j = (yCenter - size-Math.abs(yVelocity)); j <= (yCenter + size+Math.abs(yVelocity)); j++) {
                pixelArr[i][j].setBeingUpdated(false);
            }
        }
    }
    public void updateMap(){
        for(Map.Entry<String, MapObject> entry : mapObjects.entrySet()){
            if(entry.getValue().getObjID().charAt(0) == '0'){
                moveCreature((Creature)entry.getValue(), ((Creature)entry.getValue()).getDirection());
                //TODO: does this typecasting work? Will it create a new object or not? I'm not sure.
            }
        }
        //System.out.println("Done with updateMap loop");
    }
    public void drawObjects(){
        updateMap();
        for(int i = 0; i < width; i++){
            for(int j = 0; j < width; j++){
                pixelArr[i][j].drawPixel();
            }
        }
    }

}
