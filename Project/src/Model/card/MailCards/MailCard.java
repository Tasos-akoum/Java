package Model.card.MailCards;

import Model.card.Card;
import Model.Player.character;

//Abstract class MailCard implements the basic functionality of mail cards
public abstract class MailCard extends Card {
    private int euro;

    //Constructor: Constructs a new MailCard with a type and a euro value
    //Postcondition: MailCard successfully constructed
    public MailCard(String type, int euro){
        super(type);
        this.euro = euro;
    }

    //Transformer(mutative): Does some action
    //Postcondition: action finished
    //@param c is the current player
    public abstract void action(character c);

    //Accessor(selector): Returns the euro value of the card
    //Postcondition: Returned the euro value
    public int getEuro(){
        return this.euro;
    }

    //Transformer(mutative): Sets the euro value of the card
    //Postcondition: euro value set as euro
    //@param euro is the new euro value
    public void setEuro(int euro){
        this.euro = euro;
    }
}
