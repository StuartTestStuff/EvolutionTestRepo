import processing.core.PApplet;

public class MainClass extends PApplet {
    public static int iterator = 0;
    public static PApplet processing;
    GameMap gameMap = new GameMap(1000);
    Creature creatureTest = new Creature(500,500, Math.PI/4);
    Creature creatureTest2 = new Creature(500,500, 3*Math.PI/4);
    Creature creatureTest3 = new Creature(500,500, 5*Math.PI/4);
    Creature creatureTest4 = new Creature(500,500, 7*Math.PI/4);

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
        gameMap.addObject(creatureTest2);
        gameMap.addObject(creatureTest3);
        gameMap.addObject(creatureTest4);
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

