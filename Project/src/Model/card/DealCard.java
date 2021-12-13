package Model.card;

import Model.Player.character;

//Class DealCard is the class responsible for creating Deal cards and their logic.
//They have a cost, a value and a choice, whether to get the deal or to ignore it
public class DealCard extends Card{
    private int cost;
    private int value;
    private int choice;

    //Constructor: Concstructs a new DealCard with a type, cost, value, and choice
    //Postcondition:Created a new DealCard with a cost, value and choice as 0(No choice)
    public DealCard(int cost, int value){
        super("Deal");
        this.cost = cost;
        this.value = value;
        this.choice = 0;
    }

    //Transformer(mutative):Decides if the player is going to accept the deal and follows through with what needs to happen
    //Postcondition: The deal is either dismissed or accepted
    //@param c is the player that gets the deal
    public void action(character c){

    }

    //Accessor(selector): Returns the choice of the player(0 for nothing, 1 for accepting the deal, 2 for ignoring the deal)
    //Postcondition: Choice returned
    public int getChoice(){
        return this.choice;
    }

    //Transformer(mutative): Sets the choice
    //Postcondition: Sets the choice variable as choice
    //@param choice is the new choice
    public void setChoice(int choice){
        this.choice = choice;
    }

    //Accessor(selector):Returns the cost of the card
    //Postcondition: The cost has been returned
    public int getCost() {
        return this.cost;
    }

    //Accessor(selector): Returns the value of the card
    //Postcondition: The value has been returned
    public int getValue(){
        return this.value;
    }

    //Transformer(mutative): Sets the cost of the card
    //Postcondition: The cost has been set
    //@param cost is the new cost of the card
    public void setCost(int cost){
        this.cost = cost;
    }

    //Transformer(mutative): Sets the value of the card
    //Postcondition: The value has been set
    //@param value is the new value of the card
    public void setValue(int value){
        this.value = value;
    }

}
