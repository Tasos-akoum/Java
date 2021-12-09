package Model.Player;

import java.util.concurrent.ThreadLocalRandom;

public class Turn {
    private Character currentPlayer;
    private int currentTurn;
    private final int totalMonths;

    public Turn(){
        this.currentTurn = 0;
        this.currentPlayer = null;
        this.totalMonths = ThreadLocalRandom.current().nextInt(1,4);
    }

    public int getCurrentTurn(){
        return this.currentTurn;
    }

    public void setCurrentTurn(int currentTurn){
        this.currentTurn = currentTurn;
    }

    public Character getCurrentPlayer(){
        return this.currentPlayer;
    }
    public void setCurrentPlayer(Character p){
        this.currentPlayer = p;
    }

    public boolean checkIfPlayerFinished(Character p){
        return false;
    }

    public boolean isFinalTurn(){
        return currentTurn == totalMonths * 31;
    }

    public boolean turnHasFinished(){
        return false;
    }
}
