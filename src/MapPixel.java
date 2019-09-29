import java.util.ArrayList;

public class MapPixel {
    private int xPos;
    private int yPos;
    private ArrayList<String>mapObjectsHere = new ArrayList<>();
    public MapPixel(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }
    public int getxPos(){
        return xPos;
    }
    public int getyPos(){
        return yPos;
    }
    public ArrayList<String> getMapObjectsHere(){
        return mapObjectsHere;
    }
    /**
     * adds object ID to mapObjectsHere arraylist
     * @param toAdd MapObject to add to the arraylist
     */
    public void addObjID(MapObject toAdd){
        mapObjectsHere.add(toAdd.getObjID());
    }
    public void removeObjID(String IDToRemove){
        mapObjectsHere.remove(IDToRemove);
    }
    public void drawPixel(){
        //TODO: change this so that it draws different things based on the type of object
        if(!mapObjectsHere.isEmpty()){
            MainClass.processing.point(xPos,yPos);
        }

    }
}
