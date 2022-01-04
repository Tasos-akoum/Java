package Controller;

import Model.Board;
import Model.Player.character;
import Model.Turn;

import java.util.concurrent.ThreadLocalRandom;

//Class Controller is the class that connects all the assets of the game and controls the game flow
public class Controller {
    private boolean hasStarted, emptyBoard;
    private final character c1, c2;
    private Board board;
    private Turn turn = new Turn();

    //Constructor: Constructs a game controller
    //Postcondition: Constructed a controller and sets the game up to start
    public Controller(){
        board = new Board();

        c1 = new character(1);
        c2 = new character(2);

        c1.setPosition(1);
        c2.setPosition(1);

        c1.setMoney(3500);
        c2.setMoney(3500);

        if(ThreadLocalRandom.current().nextInt(1,3) == 1) {
            turn.setCurrentPlayer(c1);
            turn.setInactivePlayer(c2);
        }
        else {
            turn.setCurrentPlayer(c2);
            turn.setInactivePlayer(c1);
        }

        this.hasStarted = false;
        this.emptyBoard = true;
    }

    //Observer: Returns true if the board is empty, otherwise returns false
    //Postcondition: Returned if the board is empty
    public boolean boardIsEmpty(){
        return this.emptyBoard;
    }

    //Observer: Returns true if the game has started, otherwise returns false
    //Postcondition: Return if the game has started
    public boolean hasStarted(){
        return this.hasStarted;
    }

    //Transformer(mutative): Sets the value of the hasStarted variable
    //Postcondition: Sets the hasStarted value as b
    //@param b is the new value of hasStarted
    public void setHasStarted(boolean b){
        this.hasStarted = b;
    }

    public character getPlayer(int id){
        if(id == 1)
            return c1;
        else if (id == 2)
            return c2;
        else
            System.err.println("Invalid ID");

        return null;
    }

    public character getCurrentPlayer(){
        return this.turn.getCurrentPlayer();
    }

    public character getInactivePlayer(){
        return this.turn.getInactivePlayer();
    }

    //Transformer(mutative): Changes player position on the board
    //Postcondition: Player position changed
    //@param c is the character who will have his position changed
    public void changePlayerPosition(character c){
        if(c.canMove()) {
            c.setPosition(c.getPosition() + c.getDice().getValue());
            if (c.getPosition() > 31)
                c.setPosition(1);
        }
    }

    //Transformer(mutative): Changes who the current player is
    //Postcondition: Current player is changed
    public void changeCurrentPlayer(){
        turn.setInactivePlayer(turn.getCurrentPlayer());
        turn.setCurrentPlayer((turn.getCurrentPlayer().getId() == 1 ? c2 : c1));
    }

    //Transformer(mutative): Updates the board with the new information
    //Postcondition: Board updated with new info
    public void updateBoard(){

    }

    //Accessor(selector): Returns the winner of the game or declares draw
    //Postcondition: Returned the winner or draw (0 for draw)
    public int getWinner(){
        if(c1.getScore() > c2.getScore())
            return 1;
        else if(c1.getScore() < c2.getScore())
            return 2;
        else
            return 0;
    }

    //Observer: Returns true if the game has finished, false otherwise
    //Postcondition: Returns true if the game has finished
    public boolean gameHasFinished(){
        return false;
    }

    //Observer: Returns true if the player has finished playing, false otherwise
    //Postcondition: Returns true if the player has finished
    public boolean playerHasFinished(){
        return false;
    }


}
