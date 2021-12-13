package Model.Tile;

import Model.Player.character;

//Class DealTile implements the deal tiles
public class DealTile extends Tile{
    //Constructor: Constructs a new deal tile
    //Postcondition: Deal tile constructed
    public DealTile(){
        super("Deal Tile");
    }

    @Override
    //Transformer(mutative): Draws a deal card
    //Postcondition: Deal card drawn and is accepted or ignored
    //@param c is the current player
    public void action(character c) {

    }
}
