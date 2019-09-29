public class FeedingArea implements MapObject {
    private String objID;
    private int xCenter;
    private int yCenter;
    private int size;
    @Override
    public String generateID() {
        return null;
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


    public FeedingArea(){

    }

}
