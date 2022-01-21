package Model.Tile;

import Controller.Controller;
import Model.Board;
import Model.Player.character;
import Model.card.MailCards.MailCard;
import Model.card.MailCards.MoveToDealBuyer;

import java.util.ArrayList;

//Class MessageTile implements the message tile
public class MessageTile extends Tile{
    private final int value;

    //Constructor: Constructs a new Message tile with a value(How many cards the player will draw)
    //Postcondition: Constructed a new message tile with a value
    //@param value is the value of the tile
    public MessageTile(int value){
        super("Message Tile");
        this.value = value;
    }

    //Accessor(selector): Returns the value of the tile
    //Postcondition: Returned value
    public int getValue(){
        return this.value;
    }

    @Override
    //Transformer(mutative):Makes the player draw 1 or 2 mail cards and complete their actions
    //Postcondition: Cards drawn and actions completed
    //@param g is the game controller
    public void action(Controller g) {
        character currentPlayer = g.getCurrentPlayer();
        Board board = g.board;
        ArrayList<MailCard> mailCards = board.getMailCards();


        for(int i = 0; i < this.value; i++){
            if(this.value == 2 && i == 0){
                while(mailCards.get(mailCards.size() - 1) instanceof MoveToDealBuyer){
                    board.getDisposedMailCards().add(mailCards.get(mailCards.size() - 1));
                    mailCards.remove(mailCards.size() - 1);

                    if(mailCards.size() == 0){
                        board.replenishMailCards();
                    }
                }
            }
                g.playSound("draw.wav");
                currentPlayer.drawCard(mailCards.get(mailCards.size() - 1), g);
                board.getDisposedMailCards().add(mailCards.get(mailCards.size() - 1));
                mailCards.remove(mailCards.size() - 1);

                if(mailCards.size() == 0)
                    board.replenishMailCards();
        }

        currentPlayer.setEndTurn(true);

    }
}
