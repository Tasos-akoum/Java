package Model.card.MailCards;

import Controller.Controller;
import Model.Player.character;

//Class BillCard implements the bill card
public class BillCard extends MailCard{
    //Constructor: Constructs a new BillCard MailCard
    //Postcondition: Constructed BillCard with euro value
    public BillCard(int euro){
        super("Bill", euro);
    }

    //Transformer(mutative): The character pays the euro value
    //Postcondition: Character paid the value
    //@param g is the game controller
    public void action(Controller g){
        g.playSound("jackpot.wav");
        g.getCurrentPlayer().addBills(this.getEuro());
    }
}
