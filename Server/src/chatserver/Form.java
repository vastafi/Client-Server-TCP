package chatserver;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class Form extends javax.swing.JFrame {
    
    private MouseEvent getPositionEvent;
    private static ServerSocket serverSocket;
    private static Socket dataSocket;
    private boolean running;

    private javax.swing.JLabel jLabelServerStatus;
    private javax.swing.JPanel jPanelServerStatus;
    private javax.swing.JTextField jTextFieldPort;
    private static javax.swing.JTextPane jTextPaneLog;

    protected static ArrayList<Connection> serverConnection = new ArrayList<>();

    /**
     * Creates new form ClientForm
     */
    public Form() {
        initComponents();

        jPanelServerStatus.setBackground(Color.red);
        jLabelServerStatus.setText("OFF");
        jTextPaneLog.setText("\n\n");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
     private void initComponents() {

         javax.swing.JPanel jPanel2 = new javax.swing.JPanel();
        jTextFieldPort = new javax.swing.JTextField();
         javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        jPanelServerStatus = new javax.swing.JPanel();
        jLabelServerStatus = new javax.swing.JLabel();
         javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        jTextPaneLog = new javax.swing.JTextPane();
         javax.swing.JPanel jPanelDragWindow = new javax.swing.JPanel();
         javax.swing.JPanel jPanelCloseWindow = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new Color(114, 246, 252));

        jTextFieldPort.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldPort.setText("1111");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText("Port:");

        jPanelServerStatus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                serverStatus(evt);
            }
        });

        jLabelServerStatus.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabelServerStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelServerStatus.setText("Off");

        javax.swing.GroupLayout jPanelServerStatusLayout = new javax.swing.GroupLayout(jPanelServerStatus);
        jPanelServerStatus.setLayout(jPanelServerStatusLayout);
        jPanelServerStatusLayout.setHorizontalGroup(
            jPanelServerStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelServerStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
        );
        jPanelServerStatusLayout.setVerticalGroup(
            jPanelServerStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelServerStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldPort, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 321, Short.MAX_VALUE)
                .addComponent(jPanelServerStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPort, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(11, Short.MAX_VALUE))
            .addComponent(jPanelServerStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 800, 50));

        jTextPaneLog.setEditable(false);
        jTextPaneLog.setBackground(new Color(200, 173, 194));
        jTextPaneLog.setBorder(null);
        jTextPaneLog.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextPaneLog.setForeground(new Color(140, 40, 240));
        jScrollPane1.setViewportView(jTextPaneLog);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 800, 400));
         jPanelDragWindow.setBackground(new Color(156, 215, 236));

        jPanelDragWindow.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(MouseEvent evt) {
                dragWindowDragged(evt);
            }
        });
        jPanelDragWindow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                dragWindow(evt);
            }
        });
         jPanelCloseWindow.setBackground(new Color(66, 96, 128));

        jPanelCloseWindow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                closeWindow(evt);
            }
        });

        javax.swing.GroupLayout jPanelCloseWindowLayout = new javax.swing.GroupLayout(jPanelCloseWindow);
        jPanelCloseWindow.setLayout(jPanelCloseWindowLayout);
        jPanelCloseWindowLayout.setHorizontalGroup(
            jPanelCloseWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 53, Short.MAX_VALUE)
        );
        jPanelCloseWindowLayout.setVerticalGroup(
            jPanelCloseWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelDragWindowLayout = new javax.swing.GroupLayout(jPanelDragWindow);
        jPanelDragWindow.setLayout(jPanelDragWindowLayout);
        jPanelDragWindowLayout.setHorizontalGroup(
            jPanelDragWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDragWindowLayout.createSequentialGroup()
                .addGap(0, 447, Short.MAX_VALUE)
                .addComponent(jPanelCloseWindow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelDragWindowLayout.setVerticalGroup(
            jPanelDragWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelCloseWindow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanelDragWindow, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 50));

        pack();
    }// </editor-fold>                        

    private void closeWindow(MouseEvent evt) {
        System.exit(0);
    }

    private void dragWindowDragged(MouseEvent evt) {
        setLocation(evt.getXOnScreen() - getPositionEvent.getX(),
                evt.getYOnScreen() - getPositionEvent.getY());
    }

    private void dragWindow(MouseEvent evt) {
        getPositionEvent = evt;
    }

    private void serverStatus(MouseEvent evt) {
        if (jPanelServerStatus.getBackground() == Color.red) {
            jPanelServerStatus.setBackground(Color.green);
            int port = Integer.parseInt(jTextFieldPort.getText());
            startServer(port);
        } else {
            jPanelServerStatus.setBackground(Color.red);
            stopServer();
        }

        if (jLabelServerStatus.getText().equals("OFF")) {
            jLabelServerStatus.setText("ON");
        } else {
            jLabelServerStatus.setText("OFF");
        }
    }

    public void startServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
            running = true;

            addToLogPanel("Server", "Server started!");
            waitClient();
        } catch (IOException ex) {
            System.err.println("Could not listen on indicated port" + port);
        }
    }

    public void waitClient() {
        new Thread("Client listener") {
            @Override
            public void run() {
                try {
                    while (running) {
                        dataSocket = serverSocket.accept();
                        serverConnection.add(new Connection(dataSocket));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void stopServer() {
        try {
            serverSocket.close();
            running = false;
            
            addToLogPanel("Server", "Server closed!");
            System.exit(0);
        } catch (IOException ex) {
            Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected static void addToLogPanel(String name, String message) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String messageToAdd = "   " + dateFormat.format(date) + " [" + name + "]  :  " + message + "\n";
        jTextPaneLog.setText(jTextPaneLog.getText().concat(messageToAdd));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form().setVisible(true);
            }
        });
    }
}
