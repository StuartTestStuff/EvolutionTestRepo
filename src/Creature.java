


public class Creature implements MapObject{

    private static final int DEFAULT_SPEED = 3;
    private static final int DEFAULT_SIZE = 25; //subject to change
    private static final int DEFAULT_STARTING_ENERGY_STORED = 800; //subject to change
    private static final float SIZE_FACTOR = (float) (DEFAULT_SIZE * DEFAULT_SIZE /3.14); //default size makes 0 change in energy
    private static final float SPEED_FACTOR = (float) (2* DEFAULT_SPEED * DEFAULT_SPEED);
    private static final int IN_BOUNDS = 1;
    private static final int OUT_OF_BOUNDS = 0;
    private static final int X_COORD = 0;
    private static final int Y_COORD = 1;

    private String objID;
    private int xCenter;
    private int yCenter;
    private int energyStored;
    private int speed; //pixels per frame
    private double xVelocity;
    private double yVelocity;
    private float energyPerFrame;
    private int size; //radius
    private double direction;


    private int sightLength;
    private int sightWidth; //angle it sees in a cone

    /**
     * constructor for creature using all defaults, and a specified direction for testing
     * @param xCenter must be at least size away from the boundary
     * @param yCenter must be at least size away from the boundary
     */
    public Creature(int xCenter, int yCenter,double direction){
        //TODO: add checks to make sure it will not be out of bounds
        this.objID = generateID();
        this.xCenter = xCenter;
        this.yCenter = yCenter;
        this.energyStored = DEFAULT_STARTING_ENERGY_STORED;
        this.size = DEFAULT_SIZE;
        this.speed = DEFAULT_SPEED;
        this.energyPerFrame = calcEPP(this.speed, this.size);
        this.direction = direction; //THIS WILL CHANGE WITH BEHAVIOR
    }
    public Creature(int xCenter, int yCenter){
        //TODO: add checks to make sure it will not be out of bounds
        this.objID = generateID();
        this.xCenter = xCenter;
        this.yCenter = yCenter;
        this.energyStored = DEFAULT_STARTING_ENERGY_STORED;
        this.size = DEFAULT_SIZE;
        this.speed = DEFAULT_SPEED;
        this.energyPerFrame = calcEPP(this.speed, this.size);
        this.direction = determineDirection(); //THIS WILL CHANGE WITH BEHAVIOR
    }
    public double determineDirection(){
        //TODO: find closest food and go to that direction. for now, default direction
        boolean[] boundaryCheck = boundaryCheck();

    }

    /**
     * determines if the next frame will move the creature out of bounds
     * @return each index returns 1 if in boundaries, 0 if out of boundaries. index 0 is x coordinate, index 1 if y coordinate
     */
    public boolean[] boundaryCheck(){
        boolean [] boundaryCheck =
        int [] nextFrame = nextFrameCenter();
        int furthestX;
        int furthestY;
    }
    /**
     * Determines coordinates on next frame given current direciton
     * @return index 0 id x coordinate, index 1 is y coordinate
     */
    public int[] nextFrameCenter(){
        int [] nextFrame = new int[2];
        nextFrame[X_COORD] = xCenter + (int) Math.floor(xVelocity);
        nextFrame[Y_COORD] = yCenter + (int) Math.floor(yVelocity);
        return nextFrame;
    }
    public void setDirection(int newDirection){
        direction = newDirection;
    }
    public double getDirection(){
        return direction;
    }
    @Override
    public String generateID() {
        MainClass.iterator++;
        return "00"+MainClass.iterator;

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
    public void setxCenter(int newX){
        xCenter = newX;
    }
    public void setyCenter(int newY){
        yCenter = newY;
    }
    public int getSpeed(){
        return speed;
    }
    public double getxVelocity() {
        return xVelocity;
    }
    public double getyVelocity(){
        return yVelocity;
    }

    /**
     * moves the creature on the map
     * @param direction direction to move. corresponds with cardinal directions (N is up aka negative Y direction)
     */
    public void moveCreature(String direction) {
        if(direction.equals("N")){
            yCenter -= speed;
        } else if(direction.equals("E")){
            xCenter += speed;
        } else if(direction.equals("S")){
            yCenter += speed;
        } else if(direction.equals("W")){
            xCenter -= speed;
        } //TODO: deal with bad inputs here
    }

    /**
     * moves the creature on the map
     * @param direction between 0 and 360 degrees
     */
    public void moveCreature(double direction){
        //direction = direction % 2*Math.PI;
        //TODO: test to make sure that the directions work properly
        System.out.println(direction);
        if(0 <= direction && direction <= ((Math.PI)/2)){ //bottom right quadrant
            //System.out.println(direction);
            System.out.println(Math.cos(direction));
            System.out.println(Math.sin(direction));
            yVelocity = (Math.sin(direction)*speed);
            xVelocity = (Math.cos(direction)*speed);
            yCenter += yVelocity;
            xCenter += xVelocity;
        } else if ((Math.PI/2) < direction && direction <= (Math.PI)){ //bottom left quadrant
            //System.out.println(direction);
            //System.out.println(Math.cos(direction));
            yVelocity = (Math.sin(direction)*speed);
            xVelocity =  (Math.cos(direction)*speed); //times -1 maybe
            yCenter += yVelocity;
            xCenter += xVelocity;
        } else if ((Math.PI) < direction && direction <= (3*Math.PI/2)){ //top left quadrant
            System.out.println(direction);
            System.out.println(Math.cos(direction));
            yVelocity = (Math.sin(direction)*speed); //times -1 maybe
            xVelocity =  (Math.cos(direction)*speed); //times -1 maybe
            yCenter += yVelocity;
            xCenter += xVelocity;
        } else if ((3*Math.PI/2) < direction && direction < (2*Math.PI)){ //top right quadrant
            //System.out.println(direction);
            //System.out.println(Math.cos(direction));
            yVelocity = (Math.sin(direction)*speed); //times -1 maybe
            xVelocity =  (Math.cos(direction)*speed);
            yCenter += yVelocity;
            xCenter += xVelocity;
        }
    }

    private float calcEPP(float PPF, float size) {
        float EPP = (float) ((PPF*PPF*size*size)/(SIZE_FACTOR * SPEED_FACTOR *3.14) + 0.5);

        return EPP;
    }
    private float calcEPPNoSizeCalc(float PPF) {
        float EPP = (float) ((PPF*PPF)/(SPEED_FACTOR) + 0.5);

        return EPP;
    }
    public void testEPP(float PPF){

        System.out.println("PPF: " + PPF);
        System.out.println("Default size EPP: " + calcEPP(PPF, DEFAULT_SIZE));
        System.out.println("No Size calc EPP: " + calcEPPNoSizeCalc(PPF));
        System.out.println("Half Size EPP: " + calcEPP(PPF, DEFAULT_SIZE /2));
        System.out.println("Twice Size EPP: " + calcEPP(PPF, DEFAULT_SIZE *2));
        System.out.println("");
    }

}

