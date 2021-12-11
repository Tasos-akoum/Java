package controller;

import Model.Player.Board;
import Model.Player.Dice;
import Model.Player.character;
import Model.Player.Turn;

import java.util.concurrent.ThreadLocalRandom;

public class Controller {
    private boolean hasStarted, emptyTable;
    private final character c1, c2;
    private Board board;
    private Turn turn = new Turn();
    private Dice dice;

    public Controller(){
        board = new Board();
        dice = new Dice();

        c1 = new character(1);
        c2 = new character(2);

        c1.setPosition(1);
        c2.setPosition(1);

        c1.setMoney(3500);
        c2.setMoney(3500);

        if(ThreadLocalRandom.current().nextInt(1,3) == 1)
            turn.setCurrentPlayer(c1);
        else
            turn.setCurrentPlayer(c2);

        this.hasStarted = false;
        this.emptyTable = true;
    }

    public void init_cards(){

    }

    public boolean boardIsEmpty(){
        return this.emptyTable;
    }

    public boolean hasStarted(){
        return this.hasStarted;
    }

    //Transformer(mutative): Sets the value of the hasStarted variable
    //Postcondition: Sets the hasStarted value as b
    //@param b is the new value of hasStarted
    public void setHasStarted(boolean b){
        this.hasStarted = b;
    }

    public void changePlayerPosition(character c){
        c.setPosition(c.getPosition() + dice.getValue());
        if(c.getPosition() > 31)
            c.setPosition(1);
    }

    public void changeCurrentPlayer(){
        turn.setCurrentPlayer((turn.getCurrentPlayer().getId() == 1 ? c2 : c1));
    }

    public void updateBoard(){

    }

    //Accessor
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
