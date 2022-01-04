package Model.Tile;

import Controller.Controller;
import Model.Player.character;

//Class LotteryTile implements the lottery tile
public class LotteryTile extends Tile{
    //Constructor: Constructs a new lottery tile
    //Postcondition: Lottery tile constructed
    public LotteryTile(){
        super("Lottery Tile");
    }

    @Override
    //Transformer(mutative): Makes the 2 players choose a number and if the dice rolls the number the player gets 1000 euros
    //Postcondition: One random player got the money
    //@param c is the current player
    public void action(Controller g){

    }
}
