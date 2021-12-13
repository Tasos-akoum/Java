package Model.Tile;

import Model.Player.character;

//Class SweepstakesTile implements the sweepstakes tile
public class SweepstakesTile extends Tile{
    //Constructor: Constructs a new sweepstakes tile
    //Postcondition: Tile constructed successfully
    public SweepstakesTile(){
        super("Sweepstakes Tile");
    }

    @Override
    //Transformer(mutative): Rolls the dice and wins 1000 * the value of the dice euros
    //Postcondition: Player wins 1000 * dice.getValue() euros
    public void action(character c){

    }
}
