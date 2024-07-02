import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private Welcome welcome;
    private Login login;
    private SignUp signUp;
    private MainMenu mainMenu;

    public String cardNumber;
    private String pin;
    private double balance;

    public MainFrame() {
        setTitle("ATM");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Initialize all panels
        welcome = new Welcome(this);
        login = new Login(this);
        signUp = new SignUp(this);
        mainMenu = new MainMenu(this);

        // Add panels to the main panel with a unique identifier
        mainPanel.add(welcome, "Welcome");
        mainPanel.add(login, "Login");
        mainPanel.add(signUp, "SignUp");
        mainPanel.add(mainMenu, "MainMenu");
        showPanel("Welcome");

        // Add the main panel to the frame
        add(mainPanel);

        setVisible(true);
    }
    public void setUserDetails(String cardnumber,String pi,double balanc){
        cardNumber=cardnumber;
        pin=pi;
        balance=balanc;
    }
    public String getCardNumber(){
        return cardNumber;
    }
    public String getPin(){
        return pin;
    }
    public double getBalance(){
        return balance;
    }
    public void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }


    public void handleLogin(String cardNumber, String pin, double balance) {
        setUserDetails(cardNumber, pin, balance);
        mainMenu.updateWelcomeMessage(cardNumber); // Update welcome message
        showPanel("MainMenu"); // Show the MainMenu panel
    }

    public void handleLogout() {
        setUserDetails(null, null, 0); // Clear user details
        showPanel("Login"); // Show the Login panel
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame());
    }
}
