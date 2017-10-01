package TetrisGame;

import java.awt.*;

import static TetrisGame.GamePanel.UNIT;

/**
 * Created by yayixu on 9/30/17.
 */
public class BlueSquare extends Shape {
    private static final Color BLUE = Color.blue;

    public BlueSquare() {
        state = 0;
        cells.add(new Cell(0));
        cells.add(new Cell(1));
        cells.add(new Cell(2));
        cells.add(new Cell(3));
        for (Cell cell : cells) {
            add(cell);
            cell.setColor(BLUE);
        }
        initialCellsBounds();
    }

    @Override
    public void rotate(boolean isClockwise) {

    }

    private void initialCellsBounds() {
        cells.get(0).setBounds(0, 0, UNIT, UNIT);
        cells.get(1).setBounds(0, 0, UNIT, UNIT);
        cells.get(2).setBounds(0, 0, UNIT, UNIT);
        cells.get(3).setBounds(0, 0, UNIT, UNIT);
    }
}
