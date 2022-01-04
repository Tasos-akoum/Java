package Model.Tile;

import Controller.Controller;
import Model.Player.character;

//Class BuyerTile implements the buyer tile
public class BuyerTile extends Tile{
    //Constructor: Constructs a new Buyer Tile
    //Postcondition: Buyer tile constructed
    public BuyerTile(){
        super("Buyer Tile");
    }

    @Override
    //Transformer(mutative): The player can sell a Deal Card, if he has more than one he needs to be given a choice
    //Postcondition: Selected card sold
    //@param c is the current player
    public void action(Controller g){

    }

}
