package Model.Tile;

import Controller.Controller;
import Model.Board;
import Model.Player.character;
import Model.card.MailCards.MoveToDealBuyer;

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
    public void action(Controller g) {
        character c = g.getCurrentPlayer();
        Board board = g.board;

        for(int i = 0; i < this.value; i++){
            boolean ok = false;
            while(!ok){
                if(board.getMailCards().get(board.getMailCards().size() - 1) instanceof MoveToDealBuyer){
                    board.getMailCards().remove(board.getMailCards().size() - 1);
                } else {
                    g.getCurrentPlayer().drawCard(board.getMailCards().get(board.getMailCards().size() - 1), g);
                    board.getMailCards().remove(board.getMailCards().size() - 1);
                    ok = true;
                }

                if(board.getMailCards().size() == 0)
                    board.replenishMailCards();
            }
        }

    }

    public static void main(String[] args) {
        MessageTile tile = new MessageTile(2);
        tile.action(new Controller());
    }
}
