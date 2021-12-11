package Model.Player.card;

public abstract class Card {
    private String type;
    private int cost;
    private int value;

    //Constructor: Constructs a new card with a type, cost and value
    //Postcondition: A card has been constructed with a type, cost and value
    public Card(String type, int cost, int value){
        this.type = type;
        this.cost = cost;
        this.value = value;
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

    //Accessor(selector): Returns the type of the card
    //Postcondition: The type has been returned
    public String getType(){
        return this.type;
    }


    //Transformer(mutative): Sets the type of the card
    //Postcondition: The type has been set
    //@param type is the new type of the card
    public void setType(String type){
        this.type = type;
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

    //Observer:Returns true if the card can be sold(If the type is "Deal")
    //Postcondition: Returned true if it can be sold, false otherwise
    public boolean canBeSold(){
        return type.equals("Deal");
    }
}
