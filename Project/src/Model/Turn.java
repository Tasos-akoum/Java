package Model;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;
import Model.Player.character;

//Class Turn keeps tracks of who needs to play and how many turns the game will last
public class Turn implements Serializable {
    private character currentPlayer, inactivePlayer;
    private int currentMonth;
    private final int totalMonths;

    //Constructor: Constructs a new Turn
    //Postcondition: Constructs a new Turn and sets the month to 1 and decides how many months the game will be
    public Turn(){
        this.currentMonth = 1;
        this.currentPlayer = null;
        this.inactivePlayer = null;
        this.totalMonths = ThreadLocalRandom.current().nextInt(1,4);
    }

    //Accessor(selector): Returns the value of currentMonth
    //Postcondition: Returned the value of currentMonth
    public int getCurrentMonth(){
        return this.currentMonth;
    }

    public int getTotalMonths(){
        return this.totalMonths;
    }

    //Transformer(mutative): Sets a value to currentMonth
    //Postcondition: Set the value of currentTurn to currentMonth
    //@param currentTurn the new value of currentTurn
    public void setCurrentMonth(int currentMonth){
        this.currentMonth = currentMonth;
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
        p.setRoll(true);
        p.setMove(true);
        p.setEndTurn(false);

        this.currentPlayer = p;
    }
    //Transformer(mutative): Sets the character as the inactive player
    //Postcondition: A new inactive player has been set
    //@param character p is the new inactive player
    public void setInactivePlayer(character p) {
        p.setRoll(false);
        p.setMove(false);
        p.setEndTurn(false);
        this.inactivePlayer = p;
    }

    //Observer: Returns true if this month is the final month, otherwise returns false
    //Postcondition: Returned true if it's the final month
    public boolean isFinalMonth(){
        return currentMonth == totalMonths;
    }

    //Observer: Returns how many months the game has left
    //Postcondition: Returned how many months are left
    public int monthsLeft(){
        return totalMonths - currentMonth;
    }

    //Observer: Returns true if the turn has finished, else returns false
    //Postcondition: Returned true if the turn finished, else false
    public boolean turnHasFinished(){
        return false;
    }
}
