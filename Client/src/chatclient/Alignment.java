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
    public static void center(JFrame currentWindow) {
        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        Point point = new Point((int)(screenDimension.getWidth() - currentWindow.getWidth()) / 2,
                                (int)(screenDimension.getHeight() - currentWindow.getHeight()) / 2);
                currentWindow.setLocation(point);
    }
    
    public static void alignWindowToPoint(JFrame currentWindow, Point point) {
        currentWindow.setLocation(point);
    }
}
