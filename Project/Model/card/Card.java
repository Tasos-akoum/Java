package Model.Player.card;

import Model.Player.character;

public abstract class Card {
    private final String type;

    //Constructor: Constructs a new card with a type
    //Postcondition: A card has been constructed with a type
    public Card(String type){
        this.type = type;
    }

    //Transformer(mutative): Does some action
    //Postcondition: action complete
    //@param c is the current character
    public abstract void action(character c);

    //Accessor(selector): Returns the type of the card
    //Postcondition: The type has been returned
    public String getType(){
        return this.type;
    }


    //Observer:Returns true if the card can be sold(If the type is "Deal")
    //Postcondition: Returned true if it can be sold, false otherwise
    public boolean canBeSold(){
        return type.equals("Deal");
    }
}
