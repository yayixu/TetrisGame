package TetrisGame;

import java.awt.*;
import javax.swing.*;

import static TetrisGame.GamePanel.UNIT;
import static TetrisGame.GamePanel.convert;

/**
 * Created by yayixu on 9/11/17.
 */
public class Cell extends JComponent{
    private int x, y, z;

    public Cell(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        int[] xs = new int[3];
        int[] ys = new int[3];
        g.setColor(Color.black);
        switch (z) {
            case 0:
                xs[0] = convert(x);
                xs[1] = convert(x);
                xs[2] = convert(x + 0.5);
                ys[0] = convert(y);
                ys[1] = convert(y + 1);
                ys[2] = convert(y + 0.5);
                g.fillPolygon(xs, ys, 3);
                g.drawPolygon(xs, ys, 3);
                break;
            case 1:
                xs[0] = convert(x);
                xs[1] = convert(x + 1);
                xs[2] = convert(x + 0.5);
                ys[0] = convert(y);
                ys[1] = convert(y);
                ys[2] = convert(y + 0.5);
                g.fillPolygon(xs, ys, 3);
                g.drawPolygon(xs, ys, 3);
                break;
            case 2:
                xs[0] = convert(x + 1);
                xs[1] = convert(x + 1);
                xs[2] = convert(x + 0.5);
                ys[0] = convert(y);
                ys[1] = convert(y + 1);
                ys[2] = convert(y + 0.5);
                g.fillPolygon(xs, ys, 3);
                g.drawPolygon(xs, ys, 3);
                break;
            case 3:
                xs[0] = convert(x);
                xs[1] = convert(x + 1);
                xs[2] = convert(x + 0.5);
                ys[0] = convert(y + 1);
                ys[1] = convert(y + 1) ;
                ys[2] = convert(y + 0.5);
                g.fillPolygon(xs, ys, 3);
                g.drawPolygon(xs, ys, 3);
                break;
            default:
        }
    }

    public int getZ() {
        return this.z;
    }

    public void setStartPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setZ(int z) {
        this.z = z;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Cell)) return false;
        Cell cell = (Cell) obj;
        if (this.x == cell.x && this.y == cell.y && this.z == cell.z) return true;
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 7 * hash + this.x;
        hash = 7 * hash + this.y;
        hash = 7 * hash + this.z;
        return hash;
    }

    public void move(int direction) {
        Rectangle oldBounds = this.getBounds();
        if (direction == 0) {
            this.setBounds(oldBounds.x, oldBounds.y + UNIT, oldBounds.width, oldBounds.height);
        } else if (direction == 1) {
            this.setBounds(oldBounds.x - UNIT, oldBounds.y, oldBounds.width, oldBounds.height);
        }else if (direction == 2) {
            this.setBounds(oldBounds.x + UNIT, oldBounds.y, oldBounds.width, oldBounds.height);
        }
        repaint();
    }

    public void rotate(boolean isClockwise) {
        if (isClockwise) {
            setZ((this.z + 1) % 4);
        } else {
            setZ((this.z + 3) % 4);
        }
        setBounds(x, y, UNIT, UNIT);
        repaint();
    }
}
