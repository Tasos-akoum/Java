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
    //@param c is the current player
    public void action(Controller g){
        character currentPlayer = g.getCurrentPlayer();
        character inactivePlayer = g.getInactivePlayer();

        if(currentPlayer.getMoney() + currentPlayer.getLoan() >= this.getEuro()){
            currentPlayer.addMoney(-this.getEuro());
            if(currentPlayer.getMoney() < 0){
                currentPlayer.addLoan(currentPlayer.getMoney());
                currentPlayer.setMoney(0);
            }

            inactivePlayer.addMoney(this.getEuro());
        }
        else{
            currentPlayer.addLoan(currentPlayer.calculateLoan(this.getEuro()));
            this.action(g);
        }
    }

}
