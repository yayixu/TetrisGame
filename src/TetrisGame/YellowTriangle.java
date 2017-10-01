package TetrisGame;

import java.awt.*;
import java.util.ArrayList;

import static TetrisGame.GamePanel.UNIT;

/**
 * Created by yayixu on 9/21/17.
 */
public class YellowTriangle extends Shape {
    private static final Color YELLOW = Color.yellow;

    public YellowTriangle() {
        state = 0;
        cells.add(new Cell(2));
        cells.add(new Cell(3));
        cells.add(new Cell(0));
        cells.add(new Cell(3));
        for (Cell cell : cells) {
            add(cell);
            cell.setColor(YELLOW);
        }
        initialCellsBounds();
    }

    @Override
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

    @Override
    public void rotate(boolean isClockwise) {
        if (isClockwise) {
            state = (state + 1) % 4;
        } else {
            state = (state + 3) % 4;
        }
        repaint();
    }

    // set the triangle's initial bounds to top center of the game panel.
    public void initialCellsBounds() {
        cells.get(0).setBounds(0, 0, UNIT, UNIT);
        cells.get(1).setBounds(0, 0, UNIT, UNIT);
        cells.get(2).setBounds(UNIT, 0, UNIT, UNIT);
        cells.get(3).setBounds(UNIT, 0, UNIT, UNIT);
    }
}
