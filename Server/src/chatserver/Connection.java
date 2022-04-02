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
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private String name;
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

    public void sendMessageToAllClients(String message) {
        for (int i = 0; i < Form.serverConnection.size(); i++) {
            try {
                Connection serverConnection = Form.serverConnection.get(i);
                serverConnection.dataOutputStream.writeUTF(message);
            } catch (IOException ex) {
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void run() {
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            while (running) {
               String message = dataInputStream.readUTF();
                if (!message.isEmpty()) {
                    if (!isCommand(message)) {
                        Form.addToLogPanel(name, message);
                        sendMessageToAllClients("    [" + name + "]:" + message);
                    } else {
                        sendMessageToAllClients(message);
                    }
                }
            }
            dataInputStream.close();
            dataOutputStream.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean isCommand(String message) {
        if (message.startsWith("\\connect:")) {

            String connectName = message.substring(message.indexOf(":") + 1);
            if (this.name == null) {
                this.name = connectName;
            }

            addInUserList();
            Form.addToLogPanel("Server", "User [" + connectName + "] connected.");

            return true;

        } else if (message.startsWith("\\disconnect:")) {
            String disconnectName = message.substring(message.indexOf(":") + 1);
            disconnect(disconnectName);
            deleteFromUserList(name);
            Form.addToLogPanel("Server", "User [" + name + "] disconnected.");
            return true;
        }
        return false;
    }

    private void addInUserList() {
        listUsers = "";

        listUsers = listUsers.concat("\\userlist");
        for (int i = 0; i < Form.serverConnection.size(); i++) {
            Connection serverConnection = Form.serverConnection.get(i);

            listUsers = listUsers.concat(":" + serverConnection.name);
        }

        sendMessageToAllClients(listUsers);
    }

    private void deleteFromUserList(String name) {
        listUsers = "";

        listUsers = listUsers.concat("\\userlist");
        for (int i = 0; i < Form.serverConnection.size(); i++) {
            Connection serverConnection = Form.serverConnection.get(i);

            if (!serverConnection.name.equals(name)) {
                listUsers = listUsers.concat(":" + serverConnection.name);
            }
        }

        sendMessageToAllClients(listUsers);
    }

    protected void disconnect(String disconnectName) {
        for (int i = 0; i < Form.serverConnection.size(); i++) {
            Connection serverConnection = Form.serverConnection.get(i);
            if (serverConnection.name.equals(disconnectName)) {
                serverConnection.running = false;
                Form.serverConnection.remove(i);
                break;
            }

        }
    }
}
