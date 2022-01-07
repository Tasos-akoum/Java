package Model.card;

import Controller.Controller;
import Model.Player.character;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Class DealCard is the class responsible for creating Deal cards and their logic.
//They have a cost, a value and a choice, whether to get the deal or to ignore it
public class DealCard extends Card{
    private int cost;
    private int value;

    //Constructor: Concstructs a new DealCard with a type, cost, value, and choice
    //Postcondition:Created a new DealCard with a cost, value and choice as 0(No choice)
    public DealCard(int cost, int value){
        super("Deal");
        this.cost = cost;
        this.value = value;
    }

    public void showCard(Controller g){
        JFrame frame = new JFrame(this.getType());
        frame.setSize(600,250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        frame.setLayout(layout);
        frame.setResizable(false);

        JLabel image = new JLabel();
        image.setIcon(new ImageIcon(this.getImage()));
        JLabel message = new JLabel("<html>" + this.getMessage() + "<br>Τιμή αγοράς: " + this.cost + "<br>Τιμή πώλησης: " + this.value + "</html>");
        JLabel cost_label = new JLabel("Τιμή αγοράς: " + this.cost);
        JLabel value_label = new JLabel("Τιμή πώλησης: " + this.value);

        JButton choice1 = new JButton("Αγόρασε");
        choice1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action(g);
                frame.dispose();
            }
        });

        JButton choice2 = new JButton("Αγνόησε την συμφωνια");
        choice2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        gbc.weighty = 0;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 10, 0);
        frame.add(image,gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.insets = new Insets(0, 25, 0, 0);
        frame.add(message,gbc);


        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        frame.add(choice1,gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 10, 0, 0);
        frame.add(choice2,gbc);

        frame.setVisible(true);
    }

    //Transformer(mutative):Decides if the player is going to accept the deal and follows through with what needs to happen
    //Postcondition: The deal is either dismissed or accepted
    //@param c is the player that gets the deal
    public void action(Controller g){
        if(g.getCurrentPlayer().getMoney() + g.getCurrentPlayer().getLoan() >= this.cost){
            g.getCurrentPlayer().addMoney(-this.cost);
            g.getCurrentPlayer().replenishMoney();
            g.getCurrentPlayer().addCard(this);
        }
        else{
            g.getCurrentPlayer().addLoan(g.getCurrentPlayer().calculateLoan(this.cost));
            this.action(g);
        }

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
