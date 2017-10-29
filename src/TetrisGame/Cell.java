package TetrisGame;

import java.awt.*;
import javax.swing.*;

import static TetrisGame.GamePanel.UNIT;
import static TetrisGame.GamePanel.convert;

/**
 * Created by yayixu on 9/11/17.
 */
public class Cell extends JComponent{
    // cell's type
    private int type;
    private Color color;

    public Cell(int type) {
        this.type = type;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int[] xs = new int[3];
        int[] ys = new int[3];
        g.setColor(color);
        switch (type) {
            case 0:
                xs[0] = 0;
                xs[1] = 0;
                xs[2] = convert(0.5);
                ys[0] = 0;
                ys[1] = convert(1);
                ys[2] = convert(0.5);
                g.fillPolygon(xs, ys, 3);
                g.drawPolygon(xs, ys, 3);
                break;
            case 1:
                xs[0] = 0;
                xs[1] = convert(1);
                xs[2] = convert(0.5);
                ys[0] = 0;
                ys[1] = 0;
                ys[2] = convert( 0.5);
                g.fillPolygon(xs, ys, 3);
                g.drawPolygon(xs, ys, 3);
                break;
            case 2:
                xs[0] = convert(1);
                xs[1] = convert(1);
                xs[2] = convert(0.5);
                ys[0] = 0;
                ys[1] = convert(1);
                ys[2] = convert(0.5);
                g.fillPolygon(xs, ys, 3);
                g.drawPolygon(xs, ys, 3);
                break;
            case 3:
                xs[0] = 0;
                xs[1] = convert( 1);
                xs[2] = convert(0.5);
                ys[0] = convert(1);
                ys[1] = convert(1) ;
                ys[2] = convert(0.5);
                g.fillPolygon(xs, ys, 3);
                g.drawPolygon(xs, ys, 3);
                break;
            default:
        }
    }

    public int getType() {
        return this.type;
    }


    public void setType(int type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Cell)) return false;
        Cell cell = (Cell) obj;
        if (this.type == cell.type) return true;
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 7 * hash + this.type;
        return hash;
    }

    public void rotate(boolean isClockwise) {
        if (isClockwise) {
            setType((this.type + 1) % 4);
        } else {
            setType((this.type + 3) % 4);
        }
        repaint();
    }

    public void setColor(Color color) {
        this.color = color;
    }
}