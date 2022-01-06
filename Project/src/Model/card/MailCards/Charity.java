package Model.card.MailCards;

import Controller.Controller;

public class Charity extends MailCard{
    //Constructor: Constructs a new AdvertisementCard MailCard
    //Postcondition: AdvertisementCard successfully constructed with a euro value
    public Charity(int euro){
        super("Advertisement", euro);
    }

    //Transformer(mutative): Character sells the card for the euro value
    //Postcondition: Sold the card and got the money
    //@param c is the current character
    public void action(Controller g){
        g.getCurrentPlayer().addMoney(this.getEuro());
    }
}
