package TetrisGame;

import java.awt.*;

import static TetrisGame.GamePanel.UNIT;

/**
 * Created by yayixu on 9/30/17.
 */
public class RedTriangle extends Shape {
    private static final Color RED = Color.red;

    public RedTriangle() {
        state = 0;
        width =  UNIT;
        height = UNIT;
        cells.add(new Cell(0));
        cells.add(new Cell(3));
        for (Cell cell : cells) {
            add(cell);
            cell.setColor(RED);
        }
        initialCellsBounds();
    }

    @Override
    public void rotate(boolean isClockwise) {
        if (isClockwise) {
            state = (state + 1) % 4;
        } else {
            state = (state + 3) % 4;
        }
        for (Cell c : cells) {
            c.rotate(isClockwise);
        }
    }

    private void initialCellsBounds() {
        cells.get(0).setBounds(0, 0, UNIT, UNIT);
        cells.get(1).setBounds(0, 0, UNIT, UNIT);
    }
}
