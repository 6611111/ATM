import javax.swing.*;
import java.awt.*;

public class Login extends JPanel {
    private JTextField cardNumberField;
    private JPasswordField pinField;
    private MainFrame parentFrame;
    private Image backgroundImage;

    public Login(MainFrame parentFrame) {
        backgroundImage = new ImageIcon("D:/ATM/image5.png").getImage(); 


        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel cardNumberLabel = new JLabel("Card Number:");
        JLabel pinLabel = new JLabel("PIN:");
        cardNumberField = new JTextField(15);
        pinField = new JPasswordField(15);
        JButton loginButton = new JButton("Login");
        JButton signUpButton = new JButton("Sign Up");
        JButton backButton = new JButton("Back");

        Font font = new Font("Arial", Font.BOLD, 15);
        cardNumberLabel.setFont(font);
        pinLabel.setFont(font);
        cardNumberField.setFont(font);
        pinField.setFont(font);
        loginButton.setFont(font);
        signUpButton.setFont(font);
        backButton.setFont(font);

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(cardNumberLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(cardNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(pinLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(pinField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(loginButton, gbc);

        gbc.gridx = 3;
        //gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(signUpButton, gbc);

        //gbc.gridy = 4;
        //add(backButton, gbc);
        //gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        add(backButton, gbc);


        loginButton.addActionListener(e -> {
            try {
                String cardNumber = cardNumberField.getText();
                String pin = new String(pinField.getPassword());
                cardNumberField.setText("");
                pinField.setText("");
                if (DatabaseUtil.authenticateUser(cardNumber, pin)) {
                    double balance=DatabaseUtil.getUserBalance(cardNumber,pin);
                    parentFrame.setUserDetails(cardNumber, pin, balance);
                    parentFrame.handleLogin(cardNumber, pin, balance);
                    JOptionPane.showMessageDialog(Login.this, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    parentFrame.showPanel("MainMenu");

                } else {
                    JOptionPane.showMessageDialog(Login.this, "Invalid card number or PIN", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        signUpButton.addActionListener(e -> parentFrame.showPanel("SignUp"));
        backButton.addActionListener(e -> parentFrame.showPanel("Welcome"));
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}





