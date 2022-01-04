package Model.card.MailCards;

import Controller.Controller;
import Model.Player.character;

//Class MoveToDealBuyer implements the move to deal buyer card
public class MoveToDealBuyer extends MailCard{
    //Constructor: Constructs a new MoveToDealBuyer MailCard
    //Postcondition: Constructed card successfully
    public MoveToDealBuyer(){
        super("MoveToDeal", 0);
    }

    //Transformer(mutative): Move the character to the next buyer tile or deal tile
    //Postcondition: Moved character to the next buyer or deal tile
    //@param c is the character
    public void action(Controller g){

    }
}
