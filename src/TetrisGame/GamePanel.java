package TetrisGame;

import javax.swing.*;
import java.awt.*;
import java.sql.Time;
import java.util.*;
import java.util.List;
import java.util.Timer;

import static java.lang.Thread.sleep;

/**
 * Created by yayixu on 9/10/17.
 */
public class GamePanel extends BoardedPanel{
    public static final int UNIT = 50;
    private static final int WIDTH = 5, HEIGHT = 10;
    private static final String PAUSE = "PAUSE";
    private JButton pauseButton;
    private List<Shape> shapes;

    public GamePanel() {
        setBackground(Color.white);

        // Added a pause button into Game Panel.
        pauseButton = new JButton(PAUSE);
        add(pauseButton);

        // Added a cell into Game Panel.
        Cell c = new Cell(0, 0, 1);
        add(c);
        c.setBounds(2 * UNIT, 0, UNIT, UNIT);

        Timer timer = new Timer();

        // Cell falls down.
        if (c.getY() < 10 * UNIT) {
            timer.schedule(new MoveAction(c, 0), 0, 1000);
        }

        // Cell rotates.
        //timer.schedule(new RotateAction(c, true), 0, 1000);

        pauseButton.setVisible(false);
        pauseButton.setBounds(70, 225, 100, 50);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pauseButton.setVisible(true);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (!contains(evt.getPoint())) {
                    pauseButton.setVisible(false);
                }
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
            mCell.move(mDirec);
        }
    }

    public static class RotateAction extends TimerTask {
        private Cell mCell;
        private boolean mIsClockwise;

        public RotateAction(Cell cell, boolean isClockwise) {
            mCell = cell;
            mIsClockwise = isClockwise;
        }

        @Override
        public void run() {
            mCell.rotate(mIsClockwise);
        }
    }
}
