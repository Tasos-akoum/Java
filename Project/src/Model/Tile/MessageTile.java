package Model.Tile;

import Model.Player.character;

//Class MessageTile implements the message tile
public class MessageTile extends Tile{
    private final int value;

    //Constructor: Constructs a new Message tile with a value(How many cards the player will draw)
    //Postcondition: Constructed a new message tile with a value
    //@param value is the value of the tile
    public MessageTile(int value){
        super("Message Tile");
        this.value = value;
    }

    //Accessor(selector): Returns the value of the tile
    //Postcondition: Returned value
    public int getValue(){
        return this.value;
    }

    @Override
    //Transformer(mutative):Makes the player draw 1 or 2 mail cards and complete their actions
    //Postcondition: Cards drawn and actions completed
    public void action(character c) {

    }
}
