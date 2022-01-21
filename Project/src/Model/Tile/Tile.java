package Model.Tile;

import Controller.Controller;

import javax.swing.*;

//Abstract class Tile implements the basic functionality of the tiles
public abstract class Tile{
    private final String type;
    private String day;

    //Constructor: Constructs a new tile with a type
    //Postcondition: Tile constructed
    public Tile(String type){
        this.type = type;
    }

    //Transformer(mutative): Does some action
    //Postcondition: Action complete
    public abstract void action(Controller g);

    public void showMessage(String message){
        JOptionPane.showMessageDialog(null, message, this.type, JOptionPane.INFORMATION_MESSAGE);
    }

    //Accessor(selector): Returns the type of the tile
    //Postcondition: Type returned
    public String getType(){
        return this.type;
    }

    public void setDay(String day){
        this.day = day;
    }

    public String getDay(){
        return this.day;
    }

}

