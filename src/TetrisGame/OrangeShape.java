package TetrisGame;

import java.awt.*;

import static TetrisGame.GamePanel.UNIT;

/**
 * Created by yayixu on 9/30/17.
 */
public class OrangeShape extends Shape {
    private static final Color ORANGE = Color.orange;

    public OrangeShape() {
        state = 0;
        width = 2 * UNIT;
        height = UNIT;
        cells.add(new Cell(2));
        cells.add(new Cell(3));
        cells.add(new Cell(0));
        cells.add(new Cell(1));
        for (Cell cell : cells) {
            add(cell);
            cell.setColor(ORANGE);
        }
        initialCellsBounds();
    }

    @Override
    public void rotate(boolean isClockwise) {
        state = (state + 1) % 2;
        setCellsBounds();

        Rectangle oldBounds = this.getBounds();
        if(state == 0)
        {
            this.setBounds(oldBounds.x, oldBounds.y, 2 * UNIT, UNIT);
        } else
        {
            this.setBounds(oldBounds.x, oldBounds.y, UNIT, 2 * UNIT);
        }
        repaint();
    }

    private void initialCellsBounds() {
        cells.get(0).setBounds(0, 0, UNIT, UNIT);
        cells.get(1).setBounds(0, 0, UNIT, UNIT);
        cells.get(2).setBounds(UNIT, 0, UNIT, UNIT);
        cells.get(3).setBounds(UNIT, 0, UNIT, UNIT);
    }

    private void setCellsBounds() {
        this.removeAll();
        switch (state) {
            case 0:
                cells.set(0, new Cell(2));
                cells.set(1, new Cell(3));
                cells.set(2, new Cell(0));
                cells.set(3, new Cell(1));
                cells.get(0).setBounds(0, 0, UNIT, UNIT);
                cells.get(1).setBounds(0, 0, UNIT, UNIT);
                cells.get(2).setBounds(UNIT, 0, UNIT, UNIT);
                cells.get(3).setBounds(UNIT, 0, UNIT, UNIT);
                break;
            case 1:
                cells.set(0, new Cell(0));
                cells.set(1, new Cell(3));
                cells.set(2, new Cell(1));
                cells.set(3, new Cell(2));
                cells.get(0).setBounds(0, 0, UNIT, UNIT);
                cells.get(1).setBounds(0, 0, UNIT, UNIT);
                cells.get(2).setBounds(0, UNIT, UNIT, UNIT);
                cells.get(3).setBounds(0, UNIT, UNIT, UNIT);
                break;
            default:

        }
        for (Cell cell : cells) {
            add(cell);
            cell.setColor(ORANGE);
        }
    }
}
