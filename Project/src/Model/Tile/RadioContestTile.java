package Model.Tile;

import Model.Player.character;

//Class RadioContestTile implements the radio contest tile
public class RadioContestTile extends Tile{
    //Constructor: Constructs a new radio contest tile
    //Postcondition: Radio contest tile created successfully
    public RadioContestTile(){
        super("RadioContest Tile");
    }

    @Override
    //Transformer(mutative): Gives 1000 euros to the player who will roll the most value on the dice
    //Postcondition: The player with the most value on the dice gets the 1000 euros
    //@param c is the current player
    public void action(character c){

    }
}
