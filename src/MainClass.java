import processing.core.PApplet;

public class MainClass extends PApplet {
    public static PApplet processing;
    GameMap gameMap = new GameMap(1000);
    Creature creatureTest = new Creature(500,500);

    public static void main(String[] args) {
        PApplet.main("MainClass", args);
    }
    public void settings(){
        processing = this;
        gameMap.settings();
    }
    public void setup(){
        processing = this;
        gameMap.addObject(creatureTest);
/*
        creatureTest.testEPP(1);
        creatureTest.testEPP(3);
        creatureTest.testEPP(6);
        Food foodTest = new Food();
        System.out.println(foodTest.generateID());
*/
    }

    public void draw() {
        background(255,255,255);
        gameMap.drawObjects();
        //point(5,frameCount);

    }


}

