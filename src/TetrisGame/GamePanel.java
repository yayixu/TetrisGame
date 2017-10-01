package TetrisGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by yayixu on 9/10/17.
 */
public class GamePanel extends BoardedPanel{
    public static final int UNIT = 50;
    private static final int WIDTH = 5, HEIGHT = 10;
    private static final String PAUSE = "PAUSE";
    private JButton pauseButton;
    private List<Shape> shapes;
    private Cell cur;

    public GamePanel() {
        setBackground(Color.white);

        // Added a pause button into Game Panel.
        pauseButton = new JButton(PAUSE);
        add(pauseButton);

        YellowTriangle triangle = new YellowTriangle();
        add(triangle);
        triangle.setBounds(UNIT, 0, 2 * UNIT, UNIT);

        Timer timer = new Timer();

        // Cell falls down.
        timer.schedule(new MoveAction(cur, 0), 0, 1000);

        pauseButton.setVisible(false);
        pauseButton.setBounds(70, 225, 100, 50);
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                pauseButton.setVisible(true);
            }
            public void mouseExited(MouseEvent e) {
                if (!contains(e.getPoint())) {
                    pauseButton.setVisible(false);
                }
            }
        });
        addMouseWheelListener(new MouseAdapter() {
            public void mouseWheelMoved(MouseWheelEvent e) {
                int notches = e.getWheelRotation();
                if (notches > 0) {
                    // mouse moves down
                    cur.rotate(true);
                } else {
                    // mouse moves up
                    cur.rotate(false);
                }
                cur.repaint();
            }
        });
    }

    public static int convert(double x) {
        return (int)(x * UNIT);
    }

    public static class MoveAction extends TimerTask {
        private Cell mCell;
        private int mDirec;

        public MoveAction(Cell cell, int direc) {
            mCell = cell;
            mDirec = direc;
        }

        @Override
        public void run() {
            if (canMove(mCell)) {
                mCell.move(mDirec);
            }
        }

        private boolean canMove(Cell cell) {
            if (cell.getType() == 0 || cell.getType() == 2) {
                return cell.getY() + UNIT < 10 * UNIT;
            } else {
                return cell.getY() + 0.5 * UNIT < 10 * UNIT;
            }
        }
    }
}
