package TetrisGame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by yayixu on 9/10/17.
 */
public class BoardedPanel extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Rectangle rec = this.getBounds();
        g.setColor(Color.black);
        g.drawRect(0, 0, rec.width, rec.height);
    }


}
