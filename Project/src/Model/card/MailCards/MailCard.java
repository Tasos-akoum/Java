package Model.card.MailCards;

import Controller.Controller;
import Model.card.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Abstract class MailCard implements the basic functionality of mail cards
public abstract class MailCard extends Card{
    private int euro;
    private String choice;

    //Constructor: Constructs a new MailCard with a type and a euro value
    //Postcondition: MailCard successfully constructed
    public MailCard(String type, int euro){
        super(type);
        this.euro = euro;
    }

    //Transformer(mutative): Shows card's details and lets the player select an action
    //Postcondition: Card displayed and player selects action
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
        JLabel message = new JLabel(this.getMessage());
        JButton button = new JButton(this.getChoice());
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action(g);
                frame.dispose();
            }
        });

        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0,10,20,0);
        frame.add(image, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0,25,0,10);
        frame.add(message, gbc);

        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0,10,0);
        gbc.gridx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridy = 1;
        gbc.weightx = 0;
        frame.add(button, gbc);

        frame.pack();
        frame.setVisible(true);
    }

    //Transformer(mutative): Does some action
    //Postcondition: action finished
    //@param g is the game controller
    public abstract void action(Controller g);

    //Accessor(selector): Returns the euro value of the card
    //Postcondition: Returned the euro value
    public int getEuro(){
        return this.euro;
    }

    //Accessor(selector) Returns the choice text of the card
    //Postcondition: Returned the choice
    public String getChoice(){
        return this.choice;
    }

    //Transformer(mutative): Sets the choice text of the card
    //Postcondition: Choice text set
    //@param choice is the new choice text of the card
    public void setChoice(String choice){
        this.choice = choice;
    }

    //Transformer(mutative): Sets the euro value of the card
    //Postcondition: euro value set as euro
    //@param euro is the new euro value
    public void setEuro(int euro){
        this.euro = euro;
    }

}
