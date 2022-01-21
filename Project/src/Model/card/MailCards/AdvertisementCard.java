package Model.card.MailCards;

import Controller.Controller;
import Model.Player.character;

//Class AdvertisementCard implements advertisement cards
public class AdvertisementCard extends MailCard{
    //Constructor: Constructs a new AdvertisementCard MailCard
    //Postcondition: AdvertisementCard successfully constructed with a euro value
    public AdvertisementCard(int euro){
        super("Advertisement", euro);
    }

    //Transformer(mutative): Character sells the card for the euro value
    //Postcondition: Sold the card and got the money
    //@param g is the game controller
    public void action(Controller g){
        g.playSound("add_money.wav");
        g.getCurrentPlayer().addMoney(this.getEuro());
    }
}
