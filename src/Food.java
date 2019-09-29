public class Food implements MapObject {
    private String objID;
    private int xCenter;
    private int yCenter;
    private int size;
    public Food(){

    }
    @Override
    public String generateID() {
        int prefix = GameMap.getFoodIdPrefix();
        return (prefix+"0");
    }
    @Override
    public String getObjID(){
        return objID;
    }
    @Override
    public int getXCenter() {
        return xCenter;
    }
    @Override
    public int getYCenter() {
        return yCenter;
    }
    @Override
    public int getSize() {
        return size;
    }

}
