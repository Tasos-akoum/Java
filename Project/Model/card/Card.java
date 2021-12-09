package Model.Player.card;

public abstract class Card {
    private String type;
    private int cost;
    private int value;

    public Card(String type, int cost, int value){
        this.type = type;
        this.cost = cost;
        this.value = value;
    }

    public int getCost() {
        return this.cost;
    }

    public int getValue(){
        return this.value;
    }

    public String getType(){
        return this.type;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setCost(int cost){
        this.cost = cost;
    }

    public void setValue(int value){
        this.value = value;
    }

    public boolean canBeSold(){
        return type.equals("Deal");
    }
}
