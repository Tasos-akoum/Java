package Model.Tile;

import Controller.Controller;
import Model.card.Card;
import Model.card.MailCards.BillCard;

import javax.swing.*;

//Class PayDayTile implements the payday tile
public class PaydayTile extends Tile{
    //Constructor: Constructs the payday tile
    public PaydayTile(){
        super("Payday Tile");
    }

    @Override
    //Transformer(mutative): Pays the player 3500, player pays his bills and discards the cards
    //Moves the player to the next month and if it's the final month, the player stops playing
    //and completes all the actions that are necessary
    //Postcondition: Actions complete
    public void action(Controller g){
        g.getCurrentPlayer().addMoney(3500);

        g.getCurrentPlayer().pay(g.getCurrentPlayer().getBills());
        g.getCurrentPlayer().setBills(0);

        g.getCurrentPlayer().pay((g.getCurrentPlayer().getLoan() * 10) / 100);

        if(g.getCurrentPlayer().getMoney() >= g.getCurrentPlayer().getLoan() && g.getCurrentPlayer().getLoan() != 0){
            int reply = JOptionPane.showConfirmDialog(null, "Would you like to pay your loan?", "Question", JOptionPane.YES_NO_OPTION);
            if(reply == JOptionPane.YES_OPTION){
                try {
                    int amount = Integer.parseInt(JOptionPane.showInputDialog("How much do you want to pay? (Must be multiple of 1000)"));
                    while(amount < 0 || amount > g.getCurrentPlayer().getMoney() || amount > g.getCurrentPlayer().getLoan() || amount % 1000 != 0) {
                        amount = Integer.parseInt(JOptionPane.showInputDialog("Invalid amount, please try again"));
                    }

                    g.getCurrentPlayer().addMoney(-amount);
                    g.getCurrentPlayer().addLoan(-amount);

                } catch (Exception e){
                    System.err.println(e.getMessage());
                }

                JOptionPane.showMessageDialog(null, "Το δάνειο πληρώθηκε");
            }
            else if(reply == JOptionPane.NO_OPTION){
                JOptionPane.showMessageDialog(null, "Το δάνειο δεν πληρώθηκε");
            }
        }
        else if( g.getCurrentPlayer().getMoney() < g.getCurrentPlayer().getLoan()){
            JOptionPane.showMessageDialog(null, "Δεν έχεις αρκετά λεφτά για να πληρώσεις το δάνειο");
        }

        if(g.getTotalMonths() != g.getCurrentPlayer().getMonth()){
            g.getCurrentPlayer().setPosition(0,0);
            g.getCurrentPlayer().setMonth(g.getCurrentPlayer().getMonth() + 1);
            if(g.getCurrentPlayer().getMonth() != g.getInactivePlayer().getMonth())
                g.incrementMonth();
            g.board.assignDays();
        } else {
            g.getCurrentPlayer().pay(g.getCurrentPlayer().getBills());
            g.getCurrentPlayer().setFinished(true);
            if(g.getInactivePlayer().hasFinished()){
                g.setHasEnded(true);
            }
        }

        g.getCurrentPlayer().setEndTurn(true);

    }

}
