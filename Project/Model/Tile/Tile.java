package Model.Player.Tile;

import Model.Player.character;

public abstract class Tile {
    private final String type;

    //Constructor: Constructs a new tile with a type
    //Postcondition: Tile constructed
    public Tile(String type){
        this.type = type;
    }

    public abstract void action(character c);

}

