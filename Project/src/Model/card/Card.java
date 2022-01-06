package Model.card;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

//Abstract class Card is the class that gives basic functionality to all the card instances.
//It has an abstract method "action", because all cards need to have a different action which affects the current player.
public abstract class Card {
    private final String type;
    private String message;
    private Image image;


    //Constructor: Constructs a new card with a type
    //Postcondition: A card has been constructed with a type
    public Card(String type){
        this.type = type;
    }

    //Transformer(mutative): Does some action
    //Postcondition: action complete
    //@param c is the current character
    public abstract void action(Controller g);

    //Accessor(selector): Returns the type of the card
    //Postcondition: The type has been returned
    public String getType(){
        return this.type;
    }


    //Observer:Returns true if the card can be sold(If the type is "Deal")
    //Postcondition: Returned true if it can be sold, false otherwise
    public boolean canBeSold(){
        return type.equals("Deal");
    }

    public String getMessage(){
        return this.message;
    }



    public Image getImage(){
        return this.image;
    }

    public void setMessage(String message){
        this.message = message;
    }



    public void setImage(String path){
        ClassLoader cldr = this.getClass().getClassLoader();
        URL imageURL = cldr.getResource(path);
        this.image = new ImageIcon(imageURL).getImage();
        this.image = image.getScaledInstance(250,150,Image.SCALE_SMOOTH);
    }

}
