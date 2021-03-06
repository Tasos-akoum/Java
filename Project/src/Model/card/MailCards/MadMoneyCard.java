package Model.card.MailCards;

import Controller.Controller;
import Model.Player.character;

//Class MadMoneyCard implements the mad money card
public class MadMoneyCard extends MailCard{
    //Constructor: Constructs a new MadMoney MailCard
    //Postcondition: Constructed MadMoney card and assigns a euro value
    public MadMoneyCard(int euro){
        super("MadMoney", euro);
    }

    //Transformer(mutative):Takes money from the inactive character and gives it to the current
    //Postcondition: Money transfer successful
    //@param g is the game controller
    public void action(Controller g){
        character currentPlayer = g.getCurrentPlayer();
        character inactivePlayer = g.getInactivePlayer();

        g.playSound("add_money.wav");
        inactivePlayer.pay(this.getEuro());
        currentPlayer.addMoney(this.getEuro());
    }
}

