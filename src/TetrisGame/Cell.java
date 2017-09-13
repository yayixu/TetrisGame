package TetrisGame;

import java.awt.*;
import javax.swing.*;
import static TetrisGame.GamePanel.convert;

/**
 * Created by yayixu on 9/11/17.
 */
public class Cell extends Component{
    private int x, y, z;

    public Cell(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        System.out.println("Cell's paint() is called!");
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
                System.out.println("No.1 cell was drew!");

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
                System.out.println("No.4 cell was drew!");
                break;
            default:
        }
    }

    public void setPos(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
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
}
