package Model.Tile;

import Controller.Controller;
import Model.Player.character;

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
    //@param g is the game controller
    public void action(Controller g){
        character currentPlayer = g.getCurrentPlayer();

        currentPlayer.addMoney(3500);

        currentPlayer.pay(currentPlayer.getBills());
        currentPlayer.setBills(0);
        this.showMessage("Πλήρωσες τους λογαριασμούς σου");

        currentPlayer.pay((currentPlayer.getLoan() * 10) / 100);
        this.showMessage("Πλήρωσες φόρο 10% του δανείου που έχεις");

        if(currentPlayer.getMoney() >= currentPlayer.getLoan() && currentPlayer.getLoan() != 0){
            int reply = JOptionPane.showConfirmDialog(null, "Θέλεις να πληρώσεις το δάνειο σου?", "Ερώτηση", JOptionPane.YES_NO_OPTION);
            if(reply == JOptionPane.YES_OPTION){
                try {
                    int amount = Integer.parseInt(JOptionPane.showInputDialog("Πόσο θέλεις να πληρώσεις? (Πρέπει να είναι πολλαπλάσιο του 1000)"));
                    while(amount < 0 || amount > currentPlayer.getMoney() || amount > currentPlayer.getLoan() || amount % 1000 != 0) {
                        amount = Integer.parseInt(JOptionPane.showInputDialog("Μη έγκυρο ποσό, προσπαθήστε ξανά"));
                    }

                    currentPlayer.addMoney(-amount);
                    currentPlayer.addLoan(-amount);

                } catch (Exception e){
                    this.showMessage("Μη έγκυρο input, το δάνειο δεν πληρώνεται αυτόν τον γύρο");
                    System.err.println(e.getMessage());
                }

                this.showMessage("Το δάνειο πληρώθηκε");
            }
            else if(reply == JOptionPane.NO_OPTION){
                this.showMessage("Το δάνειο δεν πληρώθηκε");
            }
        }
        else if( currentPlayer.getMoney() < currentPlayer.getLoan()){
            this.showMessage("Δεν έχεις αρκετά λεφτά για να πληρώσεις το δάνειο");
        }


        if(g.getTotalMonths() != currentPlayer.getMonth()) {
            this.showMessage("Παίρνεις 3500$ και μεταφέρεσαι στην αρχική θέση");
            currentPlayer.setPosition(0, 0);
            if (currentPlayer.getMonth() == g.getInactivePlayer().getMonth()){
                g.incrementMonth();
                g.board.assignDays();
            }

            currentPlayer.setMonth(currentPlayer.getMonth() + 1);
        } else {
            currentPlayer.pay(currentPlayer.getBills());
            currentPlayer.setFinished(true);
            if(g.getInactivePlayer().hasFinished()){
                g.setHasEnded(true);
            }
        }

        currentPlayer.setEndTurn(true);

    }
}
