package chatclient;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author DaleRoyFall
 */
public class Alignment {
    public static void center(JFrame thisWindow) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        Point coordinates = new Point((int)(dimension.getWidth() - thisWindow.getWidth()) / 2,
                                (int)(dimension.getHeight() - thisWindow.getHeight()) / 2);
                thisWindow.setLocation(coordinates);
    }
    
    public static void alignWindowToPoint(JFrame currentWindow, Point coordinates) {
        currentWindow.setLocation(coordinates);
    }
}
