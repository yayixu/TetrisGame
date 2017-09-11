package TetrisGame;

import javax.swing.*;
import java.awt.*;
import java.util.List;
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
        setLayout(null);
        setBackground(Color.white);
        // Added a pause button into Game Panel
        pauseButton = new JButton(PAUSE);

        add(pauseButton);
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

    private static class Funk extends Component {

        public void paint(Graphics g) {
            super.paint(g);
            g.drawRect(10, 10, 200, 200);
            g.setColor(Color.RED);
        }
    }
}
