package Model.Tile;

import Model.Player.character;

//Class PayDayTile implements the payday tile
public class PaydayTile extends Tile{
    //Constructor: Constructs the payday tile
    public PaydayTile(){
        super("Payday Tile");
    }

    @Override
    //Transformer(mutative): Pays the player 3500, player pays his bills and discards the cards
    //Moves the player to the next month and if it's the final month, the player stops playing
    //and completes all the actions that are necessary
    //Postcondition: Actions complete
    public void action(character c){

    }
}
