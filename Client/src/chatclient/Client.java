package chatclient;

/**
 *
 * @author User
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Login loginForm = new Login();
        AlignmentWindows.center(loginForm);
        loginForm.setVisible(true);
    }
}
