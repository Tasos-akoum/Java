package Model.card;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Class DealCard is the class responsible for creating Deal cards and their logic.
//They have a cost, a value and a choice, whether to get the deal or to ignore it
public class DealCard extends Card{
    private int cost;
    private int value;

    //Constructor: Constructs a new DealCard with a type, cost, value, and choice
    //Postcondition:Created a new DealCard with a cost, value and choice as 0(No choice)
    public DealCard(int cost, int value){
        super("Deal");
        this.cost = cost;
        this.value = value;
    }

    //Transformer(mutative): Displays the card along with its cost and value to the user and lets him select an action
    //Postcondition: Card displayed and action performed
    public void showCard(Controller g){
        JFrame parent = new JFrame();

        JDialog frame = new JDialog(parent, this.getType(), true);
        frame.setSize(670,250);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        frame.setLayout(layout);
        frame.setResizable(false);

        JLabel image = new JLabel();
        image.setIcon(new ImageIcon(this.getImage()));
        JLabel message = new JLabel("<html>" + this.getMessage() + "<br>Τιμή αγοράς: " + this.cost + "<br>Τιμή πώλησης: " + this.value + "</html>");

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
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 25, 0, 5);
        frame.add(message,gbc);


        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        gbc.insets = new Insets(0, 0, 10, 0);
        frame.add(choice1,gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 10, 10, 25);
        frame.add(choice2,gbc);

        frame.pack();
        frame.setVisible(true);
    }

    //Transformer(mutative): Adds deal card to the current player
    //Postcondition: Current player bought the deal card
    //@param g is the game controller
    public void action(Controller g){
        g.getCurrentPlayer().pay(this.cost);
        g.getCurrentPlayer().addCard(this);
        g.playSound("add_money.wav");
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
