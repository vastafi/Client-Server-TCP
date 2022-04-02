package chatserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author User
 */
public class Connection extends Thread {

    private Thread thread;
    private Socket socket;
    private DataInputStream dIS;
    private DataOutputStream dOS;
    private String nickname;
    private String listUsers;

    private boolean running = true;

    Connection(Socket socket) {
        this.socket = socket;
        this.start();
    }

    @Override
    public void start() {
        if (thread == null) {
            thread = new Thread(this, "Client");
            thread.start();
        }
    }

    public void sendMessage(String message) {
        for (int i = 0; i < Form.serverConnection.size(); i++) {
            try {
                Connection serverConnection = Form.serverConnection.get(i);
                serverConnection.dOS.writeUTF(message);
            } catch (IOException ex) {
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void run() {
        try {
            dIS = new DataInputStream(socket.getInputStream());
            dOS = new DataOutputStream(socket.getOutputStream());
            while (running) {
               String message = dIS.readUTF();
                if (!message.isEmpty()) {
                    if (!isCommand(message)) {
                        Form.addToLogPanel(nickname, message);
                        sendMessage("    [" + nickname + "]:" + message);
                    } else {
                        sendMessage(message);
                    }
                }
            }
            dIS.close();
            dOS.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean isCommand(String message) {
        if (message.startsWith("\\connect:")) {

            String connectName = message.substring(message.indexOf(":") + 1);
            if (this.nickname == null) {
                this.nickname = connectName;
            }
            addUser();
            Form.addToLogPanel("Server", "User [" + connectName + "] connected.");
            return true;

        } else if (message.startsWith("\\disconnect:")) {
            String disconnectName = message.substring(message.indexOf(":") + 1);
            disconnect(disconnectName);
            deleteUser(nickname);
            Form.addToLogPanel("Server", "User [" + nickname + "] disconnected.");
            return true;
        }
        return false;
    }

    private void addUser() {
        listUsers = "";

        listUsers = listUsers.concat("\\userlist");
        for (int i = 0; i < Form.serverConnection.size(); i++) {
            Connection serverConnection = Form.serverConnection.get(i);
            listUsers = listUsers.concat(":" + serverConnection.nickname);
        }
        sendMessage(listUsers);
    }

    private void deleteUser(String nickname) {
        listUsers = "";

        listUsers = listUsers.concat("\\userlist");
        for (int i = 0; i < Form.serverConnection.size(); i++) {
            Connection serverConnection = Form.serverConnection.get(i);
            if (!serverConnection.nickname.equals(nickname)) {
                listUsers = listUsers.concat(":" + serverConnection.nickname);
            }
        }
        sendMessage(listUsers);
    }

    protected void disconnect(String disconnectName) {
        for (int i = 0; i < Form.serverConnection.size(); i++) {
            Connection serverConnection = Form.serverConnection.get(i);
            if (serverConnection.nickname.equals(disconnectName)) {
                serverConnection.running = false;
                Form.serverConnection.remove(i);
                break;
            }

        }
    }
}
