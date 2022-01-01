package View;

import javax.swing.*;
import java.awt.*;

public class ImagePane extends JLayeredPane{
    private Image image;

    public ImagePane(Image img){
        this.image = img;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0,0,this);
    }
}
