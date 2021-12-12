package Model.Player.card.MailCards;

import Model.Player.character;

public class AdvertisementCard extends MailCard{
    //Constructor: Constructs a new AdvertisementCard MailCard
    //Postcondition: AdvertisementCard successfully constructed with a euro value
    public AdvertisementCard(int euro){
        super("Advertisement", euro);
    }

    //Transformer(mutative): Character sells the card for the euro value
    //Postcondition: Sold the card and got the money
    //@param c is the current character
    public void action(character c){
        
    }
}
