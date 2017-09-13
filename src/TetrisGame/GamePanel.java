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
        setBackground(Color.white);
        // Added a pause button into Game Panel
        pauseButton = new JButton(PAUSE);

        add(pauseButton);
        pauseButton.setVisible(false);
        pauseButton.setBounds(70, 225, 100, 50);

        Funk f = new Funk();
        add(f);
        f.setBounds(0, 0, 200, 300);

        Funk f1 = new Funk();
        add(f1);
        f1.setBounds(100, 100, 200, 300);

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

    private static class Funk extends JComponent {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D gg = (Graphics2D) g;
            gg.setColor(Color.RED);
            gg.fillRect(0, 0, 100, 100);
        }
    }
}
