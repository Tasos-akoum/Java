package Model.card.MailCards;

import Controller.Controller;

public class Charity extends MailCard{
    //Constructor: Constructs a new Charity MailCard
    //Postcondition: Charity successfully constructed with a euro value
    public Charity(int euro){
        super("Charity", euro);
    }

    //Transformer(mutative): Character adds card's euro value amount to jackpot
    //Postcondition: Amount added to jackpot
    //@param g is the controller
    public void action(Controller g){
        g.getCurrentPlayer().addMoney(-this.getEuro());
        g.board.addToJackpot(this.getEuro());
    }
}
