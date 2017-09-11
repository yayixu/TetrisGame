package TetrisGame;

import com.sun.corba.se.impl.orbutil.graph.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.List;
import java.util.Set;
/**
 * Created by yayixu on 9/10/17.
 */
public class GamePanel extends BoardedPanel{
    public static final int UNIT = 50;
    private static final int WIDTH = 5, HEIGHT = 10;
    private static final String PAUSE = "PAUSE";

    public static int convert(double x) {
        return (int)(x * UNIT);
    }

    private JButton pauseButton;
    private List<Shape> shapes;
    //private Set<Cell> occupied;

    private static class Funk extends Component {

        public void paint(Graphics g) {
            super.paint(g);
            g.drawRect(10, 10, 200, 200);
            g.setColor(Color.RED);
        }
    }

    public GamePanel() {
        setLayout(null);
        setBackground(Color.white);
        // Added a pause button into Game Panel
        pauseButton = new JButton(PAUSE);

        add(pauseButton);
        pauseButton.setVisible(false);
        pauseButton.setBounds(70, 225, 100, 50);
        invalidate();
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
}
