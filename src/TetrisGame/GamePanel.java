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
    private Shape cur;

    public GamePanel() {
        setBackground(Color.white);

        // Added a pause button into Game Panel.
        pauseButton = new JButton(PAUSE);
        add(pauseButton);

        cur = new YellowTriangle();
        add(cur);
        cur.setBounds(UNIT, 0, 2 * UNIT, UNIT);

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

            public void mouseClicked(MouseEvent e) {
                // mouse left click
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (canMoveLeft(cur)) {
                        cur.move(1);
                    }
                    // mouse right click
                } else if(e.getButton() == MouseEvent.BUTTON3) {
                    if(canMoveRight(cur)) {
                        cur.move(2);
                    }
                }
            }
        });

        addMouseWheelListener(new MouseAdapter() {
            public void mouseWheelMoved(MouseWheelEvent e) {
                int notches = e.getWheelRotation();
                if (notches > 0) {
                    // mouse moves down
                    if (canRotate(cur)) {
                        cur.rotate(true);
                    }
                } else {
                    // mouse moves up
                    if (canRotate(cur)) {
                        cur.rotate(false);
                    }
                }
                cur.repaint();
            }
        });
    }

    public static int convert(double x) {
        return (int)(x * UNIT);
    }

    public  class MoveAction extends TimerTask {
        private Shape mShape;
        private int mDirec;

        public MoveAction(Shape shape, int direc) {
            mShape = shape;
            mDirec = direc;
        }

        @Override
        public void run() {
            if (canMoveDown(mShape)) {
                mShape.move(mDirec);
            }
        }
    }

    public boolean canMoveDown(Shape shape) {
        boolean canMoveDown, canMoveLeft, canMoveRight;
        if (shape.state == 0 || shape.state == 2) {
            return shape.getY() + UNIT < 10 * UNIT;
        } else {
            return shape.getY() + 2 * UNIT < 10 * UNIT;
        }
    }

    public boolean canMoveLeft(Shape shape) {
        return shape.getX() > 0 && !hasArrivedBottom(shape) ;
    }

    public boolean canMoveRight(Shape shape) {
        if (shape.state == 0 || shape.state == 2) {
            return shape.getX() + 2 * UNIT < 5 * UNIT && !hasArrivedBottom(shape);
        } else {
            return shape.getX() + UNIT < 5 * UNIT &&  !hasArrivedBottom(shape);
        }
    }

    public boolean canRotate(Shape shape) {
        if (shape.state == 0 || shape.state == 2) {
            return shape.getY() + 2 * UNIT < 10 * UNIT;
        } else {
            return shape.getX() + UNIT < 5 * UNIT;
        }
    }

    public boolean hasArrivedBottom(Shape shape) {
        return shape.getY() + shape.getHeight() >= 10 * UNIT;
    }
}
