package chatserver;

import javax.swing.*;
import java.awt.*;

/**
 * @author DaleRoyFall
 */
public class AlignmentWindows {
  public static void center(JFrame thisWindows) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        Point coordinates = new Point((int)(dimension.getWidth() - thisWindows.getWidth()) / 2,
                                (int)(dimension.getHeight() - thisWindows.getHeight()) / 2);
        thisWindows.setLocation(coordinates);
    }
}
