package Model.Tile;

import Model.Player.character;
import Model.card.MailCards.PayTheNeighborCard;

//Abstract class Tile implements the basic functionality of the tiles
public abstract class Tile{
    private final String type;

    //Constructor: Constructs a new tile with a type
    //Postcondition: Tile constructed
    public Tile(String type){
        this.type = type;
    }

    //Transformer(mutative): Does some action
    //Postcondition: Action complete
    public abstract void action(character c);

    //Accessor(selector): Returns the type of the tile
    //Postcondition: Type returned
    public String getType(){
        return this.type;
    }

}

