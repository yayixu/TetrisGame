package TetrisGame;

/**
 * Created by yayixu on 9/10/17.
 */
import java.awt.*;
import javax.swing.*;

public class TetrisApp {
    private static final String APPNAME = "yayiTetrisGame";
    private static final int WIDTH = 600, HEIGHT = 600;


    public static void addComponentsToPane(Container pane) {
        pane.setLayout(null);
        GamePanel gamePanel = new GamePanel();
        pane.add(gamePanel);
        gamePanel.setBounds(50, 50, 250, 500);
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame(APPNAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());

        //Size and display the window.
        Insets insets = frame.getInsets();
        frame.setSize(WIDTH + insets.left + insets.right,
                HEIGHT + insets.top + insets.bottom);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}