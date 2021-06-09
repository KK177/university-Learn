package snakeGame;
import java.awt.*;
import javax.swing.JPanel;

public class backGroundImage  extends  JPanel{
    private Image image = null;
    public backGroundImage(Image image) {
        this.image = image;
    }
     @Override
     public void paint(Graphics g) {
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
   }
}
