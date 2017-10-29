package TetrisGame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static TetrisGame.GamePanel.UNIT;

/**
 * Created by yayixu on 9/21/17.
 */
public abstract class Shape extends JComponent{
    // the rotation state, default state is 0.
    protected int state;
    protected int width, height;

    protected List<Cell> cells = new ArrayList<>();

    public abstract void rotate(boolean isClockwise);

    public void move(int direction) {
        Rectangle oldBounds = this.getBounds();
        if (direction == 0) {  // move down
            this.setBounds(oldBounds.x, oldBounds.y + UNIT, oldBounds.width, oldBounds.height);
        } else if (direction == 1) { // move to left
            this.setBounds(oldBounds.x - UNIT, oldBounds.y, oldBounds.width, oldBounds.height);
        }else if (direction == 2) { // move to right
            this.setBounds(oldBounds.x + UNIT, oldBounds.y, oldBounds.width, oldBounds.height);
        }
        repaint();
    }
}
