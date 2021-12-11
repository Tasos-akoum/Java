package Model.Player;

import java.util.concurrent.ThreadLocalRandom;

public class Dice {
    private int value;

    //Constructor: Constructs a new Dice with a value of 0
    //Postcondition: A new dice has been constructed
    public Dice(){
        value = 0;
    }

    //Transformer(mutative): rolls dice
    //Postcondition: The dice has a random value from 1 to 6
    public void rollDice(){
        this.value = ThreadLocalRandom.current().nextInt(1,7);
    }

    //Accessor(selector): Returns the value of the variable value
    //Postcondition: Value has been returned
    public int getValue(){
        return value;
    }

    //Transformer(mutative): Sets the value of the variable value
    //Postcondition: The value has been set
    //@param value is the new value
    public void setValue(int value){
        this.value = value;
    }
}
