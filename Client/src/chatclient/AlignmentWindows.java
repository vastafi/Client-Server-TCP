package chatclient;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author DaleRoyFall
 */
public class AlignmentWindows {
    public static void center(JFrame thisWindows) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        Point coordinates = new Point((int)(dimension.getWidth() - thisWindows.getWidth()) / 2,
                                (int)(dimension.getHeight() - thisWindows.getHeight()) / 2);
                thisWindows.setLocation(coordinates);
    }
    
    public static void alignWindowToPoint(JFrame thisWindows, Point coordinates) {
        thisWindows.setLocation(coordinates);
    }
}
