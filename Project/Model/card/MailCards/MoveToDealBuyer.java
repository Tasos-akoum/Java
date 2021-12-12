package Model.Player.card.MailCards;

import Model.Player.character;

public class MoveToDealBuyer extends MailCard{
    //Constructor: Constructs a new MoveToDealBuyer MailCard
    //Postcondition: Constructed card successfully
    public MoveToDealBuyer(){
        super("MoveToDeal", 0);
    }

    //Transformer(mutative): Move the character to the next buyer tile or deal tile
    //Postcondition: Moved character to the next buyer or deal tile
    //@param c is the character
    public void action(character c){

    }
}
