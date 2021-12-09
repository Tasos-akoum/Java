package controller;

import Model.Player.Character;
import Model.Player.Turn;

import java.util.concurrent.ThreadLocalRandom;

public class Controller {
    private boolean hasStarted, emptyTable;
    private final Character p1, p2;
    private Turn turn = new Turn();

    public Controller(){
        p1 = new Character(1); //The players start with an amount of 3500$
        p2 = new Character(2);

        p1.setPosition(1);
        p2.setPosition(1);

        if(ThreadLocalRandom.current().nextInt(1,3) == 1)
            turn.setCurrentPlayer(p1);
        else
            turn.setCurrentPlayer(p2);

        this.hasStarted = false;
        this.emptyTable = true;
    }

    public void init_tiles(){

    }

    public void init_cards(){

    }



    public void changePlayerPosition(Character p){
        p.setPosition(p.getPosition() + p.rollDice());
        if(p.getPosition() > 31)
            p.setPosition(1);
    }

    public void changePlayer(){
        turn.setCurrentPlayer((turn.getCurrentPlayer().getId() == 1 ? p2 : p1));
    }

    public int getWinner(){
        if(p1.getScore() > p2.getScore())
            return 1;
        else if(p1.getScore() < p2.getScore())
            return 2;
        else
            return 0;
    }


}
