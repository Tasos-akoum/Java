package Model.card.MailCards;

import Controller.Controller;
import Model.card.Card;
import Model.Player.character;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Abstract class MailCard implements the basic functionality of mail cards
public abstract class MailCard extends Card {
    private int euro;
    private String choice;
    //Constructor: Constructs a new MailCard with a type and a euro value
    //Postcondition: MailCard successfully constructed
    public MailCard(String type, int euro){
        super(type);
        this.euro = euro;
    }

    public void showCard(){
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
        JLabel message = new JLabel(this.getMessage());
        JButton button = new JButton(this.getChoice());
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0,10,20,0);
        frame.add(image, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.insets = new Insets(0,25,0,0);
        frame.add(message, gbc);

        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, -60,0,0);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0;
        frame.add(button, gbc);
        frame.setVisible(true);
    }

    //Transformer(mutative): Does some action
    //Postcondition: action finished
    //@param c is the current player
    public abstract void action(Controller g);

    //Accessor(selector): Returns the euro value of the card
    //Postcondition: Returned the euro value
    public int getEuro(){
        return this.euro;
    }


    public String getChoice(){
        return this.choice;
    }

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
