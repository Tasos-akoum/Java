package Model.Tile;

import Controller.Controller;
import Model.Player.character;

//Class FamilyCasinoNightTile implements the family casino night tile
public class FamilyCasinoNightTile extends Tile{
    //Constructor: Constructs a new family casino night tile
    public FamilyCasinoNightTile(){
        super("Family Casino Night Tile");
    }

    @Override
    //Transformer(mutative): The player either pays the jackpot 500 or gets 500 from the bank depending on the dice value
    //Postcondition: The player paid 500 or got 500
    //@param c is the current player
    public void action(Controller g){

    }
}
