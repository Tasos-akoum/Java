package Model.Tile;

import Model.Player.character;

//Class YardSaleTile implements the yard sale tile
public class YardSaleTile extends Tile{
    //Constructor: Constructs a new yard sale tile
    //Postcondition: Yard sale tile constructed successfully
    public YardSaleTile(){
        super("YardSale Tile");
    }

    @Override
    //Transformer(mutative): Player rolls dice and pays 100 * dice value
    //Postcondition: Player paid the value
    //@param c is the current player
    public void action(character c){

    }
}
