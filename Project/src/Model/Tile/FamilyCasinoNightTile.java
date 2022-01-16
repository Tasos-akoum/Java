package Model.Tile;

import Controller.Controller;
import Model.Player.character;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Class FamilyCasinoNightTile implements the family casino night tile
public class FamilyCasinoNightTile extends Tile{
    //Constructor: Constructs a new family casino night tile
    public FamilyCasinoNightTile(){
        super("Family Casino Night Tile");
    }

    @Override
    //Transformer(mutative): The player either pays the jackpot 500 or gets 500 from the bank depending on the dice value
    //Postcondition: The player paid 500 or got 500
    //@param c is the current player
    public void action(Controller g){


        g.playSound("casino.wav");
        if(g.getCurrentPlayer().getDice().getValue() % 2 == 1){
            g.board.addToJackpot(500);
            g.getCurrentPlayer().pay(500);
            this.showMessage("Έδωσες 500$ στο jackpot");
        } else {
            g.getCurrentPlayer().addMoney(500);
            this.showMessage("Πήρες 500$ από την τράπεζα");
        }

        g.getCurrentPlayer().setEndTurn(true);

    }

}
