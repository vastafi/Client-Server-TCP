package chatserver;

import javax.swing.*;
import java.awt.*;

/**
 * @author DaleRoyFall
 */
public class Alignment {
  public static void center(JFrame currentWindow) {
        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        Point point = new Point((int)(screenDimension.getWidth() - currentWindow.getWidth()) / 2,
                                (int)(screenDimension.getHeight() - currentWindow.getHeight()) / 2);
        currentWindow.setLocation(point);
    }
}
