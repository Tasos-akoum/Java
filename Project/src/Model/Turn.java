package Model;

import java.util.concurrent.ThreadLocalRandom;
import Model.Player.character;

//Class Turn keeps tracks of who needs to play and how many turns the game will last
public class Turn {
    private character currentPlayer, inactivePlayer;
    private int currentTurn;
    private final int totalMonths;

    //Constructor: Constructs a new Turn
    //Postcondition: Constructs a new Turn and sets the round to 0 and decides how many months the game will be
    public Turn(){
        this.currentTurn = 0;
        this.currentPlayer = null;
        this.inactivePlayer = null;
        this.totalMonths = ThreadLocalRandom.current().nextInt(1,4);
    }

    //Accessor(selector): Returns the value of currentTurn
    //Postcondition: Returned the value of currentTurn
    public int getCurrentTurn(){
        return this.currentTurn;
    }

    //Transformer(mutative): Sets a value to currentTurn
    //Postcondition: Set the value of currentTurn to currentTurn
    //@param currentTurn the new value of currentTurn
    public void setCurrentTurn(int currentTurn){
        this.currentTurn = currentTurn;
    }

    //Accessor(selector): Returns the player who is playing now
    //Postcondition: Returned current player
    public character getCurrentPlayer(){
        return this.currentPlayer;
    }

    public character getInactivePlayer(){
        return this.inactivePlayer;
    }

    //Transformer(mutative): Sets the character as the current player
    //Postcondition: A new current player has been set
    //@param character p is the new current player
    public void setCurrentPlayer(character p){
        this.currentPlayer = p;
    }

    public void setInactivePlayer(character c) {
        this.inactivePlayer = c;
    }

    //Observer: Returns true if this turn is the final turn, otherwise returns false
    //Postcondition: Returned true if it's the final turn
    public boolean isFinalTurn(){
        return currentTurn == totalMonths * 31;
    }

    //Observer: Returns true if the turn has finished, else returns false
    //Postcondition: Returned true if the turn finished, else false
    public boolean turnHasFinished(){
        return false;
    }
}
