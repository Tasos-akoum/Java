package Model.card.MailCards;

import Controller.Controller;
import Model.Player.character;

//Class PayTheNeighborCard implements the pay the neighbor card
public class PayTheNeighborCard extends MailCard{
    //Constructor: Creates a new pay the neighbor card
    public PayTheNeighborCard(int euro){
        super("PayTheNeighbor", euro);
    }

    @Override
    //Transformer(mutative): Makes the player pay the enemy player money
    //Postcondition: Player paid the enemy player
    //@param g is the game controller
    public void action(Controller g){
        character currentPlayer = g.getCurrentPlayer();
        character inactivePlayer = g.getInactivePlayer();

        g.playSound("doom.wav");
        currentPlayer.pay(this.getEuro());
        inactivePlayer.addMoney(this.getEuro());
    }

}
