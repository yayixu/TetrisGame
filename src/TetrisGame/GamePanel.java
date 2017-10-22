package TetrisGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.*;
import java.util.List;
import java.util.Timer;

/**
 * Created by yayixu on 9/10/17.
 */
public class GamePanel extends BoardedPanel{
    public static final int UNIT = 50;
    private static final int WIDTH = 5, HEIGHT = 10;
    private static final String PAUSE = "PAUSE";
    private static final int SHAPE_COUNT = 5;
    private boolean[][][] isValid = new boolean[5][10][4];

    private JButton pauseButton;
    private Shape cur;
    private Random shapeType = new Random();
    private int curType;
    private List<Shape> shapes = new ArrayList<>();

    public GamePanel() {
        // Initialize cells valid matrix.
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 4; k++) {
                    isValid[i][j][k] = true;
                }
            }
        }
        setBackground(Color.white);

        // Added a pause button into Game Panel.
        pauseButton = new JButton(PAUSE);
        add(pauseButton);

        // Initialize a shape.
        if(shouldGenerate()) {
            cur = generateRandomShape();
            add(cur);
            cur.setBounds(UNIT, 0, cur.width, cur.height);
        }

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

    public class MoveAction extends TimerTask {
        private Shape mShape;
        private int mDirect;

        public MoveAction(Shape shape, int direct) {
            mShape = shape;
            mDirect = direct;
        }

        @Override
        public void run() {
            if (canMoveDown(mShape)) {
                mShape.move(mDirect);
            }
            if (shouldGenerate()) {
                int x = mShape.getX() / UNIT;
                int y = mShape.getY() / UNIT;
                int cellX, cellY, cellZ;
                for (Cell cell : mShape.cells) {
                    cellX = x + cell.getX() / UNIT;
                    cellY = y + cell.getY() / UNIT;
                    cellZ = cell.getType();
                    isValid[cellX][cellY][cellZ] = false;
                }
                cur = generateRandomShape();
                mShape = cur;
                add(cur);
                cur.setBounds(UNIT, 0, cur.width, cur.height);
            }
        }
    }

    public boolean canMoveDown(Shape shape) {
        boolean res = true;
        int x = shape.getX() / UNIT;
        int y = shape.getY() / UNIT;
        int cellX, cellY, cellZ;
        for (Cell cell : shape.cells) {
            cellX = x + cell.getX() / UNIT;
            cellY = y + cell.getY() / UNIT;
            cellZ = cell.getType();
            if (cellY >= 9 || !isValid[cellX][cellY + 1][cellZ]) {
                res = false;
            }
        }
        return shape.getY() + shape.getHeight() < 10 * UNIT && res;
    }

    public boolean canMoveLeft(Shape shape) {
        boolean res = true;
        int x = shape.getX() / UNIT;
        int y = shape.getY() / UNIT;
        int cellX, cellY, cellZ;
        for (Cell cell : shape.cells) {
            cellX = x + cell.getX() / UNIT;
            cellY = y + cell.getY() / UNIT;
            cellZ = cell.getType();
            if (cellX < 1 || !isValid[cellX - 1][cellY][cellZ]) {
                res = false;
            }
        }
        return shape.getX() > 0 && !hasArrivedBottom(shape) && res;
    }

    public boolean canMoveRight(Shape shape) {
        boolean res = true;
        int x = shape.getX() / UNIT;
        int y = shape.getY() / UNIT;
        int cellX, cellY, cellZ;
        for (Cell cell : shape.cells) {
            cellX = x + cell.getX() / UNIT;
            cellY = y + cell.getY() / UNIT;
            cellZ = cell.getType();
            if (cellX >= 4 || !isValid[cellX + 1][cellY][cellZ]) {
                res = false;
            }
        }
        return shape.getX() + shape.getWidth() < 5 * UNIT && !hasArrivedBottom(shape) && res;
    }

    // need rewrite since each shape's canRotate is different
    public boolean canRotate(Shape shape) {
        //boolean res = true;
        if(curType == 2 || curType == 3 || curType == 4) {
            if (shape.state == 0 || shape.state == 2) {
                return shape.getY() + 2 * UNIT < 10 * UNIT;
            } else {
                return shape.getX() + UNIT < 5 * UNIT;
            }
        } else {
            return true;
        }
    }

    public boolean hasArrivedBottom(Shape shape) {
        return shape.getY() + shape.getHeight() >= 10 * UNIT;
    }

    public boolean shouldGenerate() {
        return cur == null || !canMoveDown(cur);
    }

    public Shape generateRandomShape() {
        curType = shapeType.nextInt(SHAPE_COUNT);
        switch(curType) {
            case 0:
                return new RedTriangle();
            case 1:
                return new BlueSquare();
            case 2:
                return new YellowTriangle();
            case 3:
                return new GreenShape();
            case 4:
                return new OrangeShape();
        }
        return null;
    }

}
