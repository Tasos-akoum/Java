package Model.Player.card.MailCards;

import Model.Player.character;

public class BillCard extends MailCard{
    //Constructor: Constructs a new BillCard MailCard
    //Postcondition: Constructed BillCard with euro value
    public BillCard(int euro){
        super("Bill", euro);
    }

    //Transformer(mutative): The character pays the euro value
    //Postcondition: Character paid the value
    //@param c is the current player
    public void action(character c){

    }
}
