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
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 10, 10);
        frame.setLayout(layout);
        frame.setResizable(false);

        JLabel image = new JLabel();
        image.setIcon(new ImageIcon(this.getImage()));
        JLabel message = new JLabel(this.getMessage());
        JButton button = new JButton(this.getButtonText());
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.add(image);
        frame.add(message);
        frame.add(button);
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

    //Transformer(mutative): Sets the euro value of the card
    //Postcondition: euro value set as euro
    //@param euro is the new euro value
    public void setEuro(int euro){
        this.euro = euro;
    }
}
