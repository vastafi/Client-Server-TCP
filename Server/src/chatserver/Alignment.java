/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserver;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author DaleRoyFall
 */
public class Alignment {
    // Align window to center of the screen
    public static void centerTheWindow(JFrame currentWindow) {
        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        Point point = new Point((int)(screenDimension.getWidth() - currentWindow.getWidth()) / 2,
                                (int)(screenDimension.getHeight() - currentWindow.getHeight()) / 2);
        
        currentWindow.setLocation(point);
    }
}
