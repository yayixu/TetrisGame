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

    protected List<Cell> cells = new ArrayList<>();

    public abstract void move(int direction);

    public abstract void rotate(boolean isClockwise);
}
