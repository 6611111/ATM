import javax.swing.*;
import java.awt.*;

public class SignUp extends JPanel {
    private JTextField nameField;
    private JTextField cardNumberField;
    private JPasswordField pinField;
    private JTextField phoneField;
    private JTextField occupationField;
    private JTextField dobField;
    private Image backgroundImage;

    public SignUp(MainFrame parentFrame) {

        backgroundImage = new ImageIcon("D:/ATM/image5.png").getImage(); 
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel nameLabel = new JLabel("Name:");
        JLabel cardNumberLabel = new JLabel("Card Number:");
        JLabel pinLabel = new JLabel("PIN:");
        JLabel phoneLabel = new JLabel("Phone:");
        JLabel occupationLabel = new JLabel("Occupation:");
        JLabel dobLabel = new JLabel("Date of Birth:");
        nameField = new JTextField(15);
        cardNumberField = new JTextField(15);
        pinField = new JPasswordField(15);
        phoneField = new JTextField(15);
        occupationField = new JTextField(15);
        dobField = new JTextField(15);
        JButton signUpButton = new JButton("Sign Up");
        JButton backButton = new JButton("Back");

        Font font = new Font("Arial", Font.BOLD, 15);
        nameLabel.setFont(font);
        cardNumberLabel.setFont(font);
        pinLabel.setFont(font);
        phoneLabel.setFont(font);
        occupationLabel.setFont(font);
        dobLabel.setFont(font);
        nameField.setFont(font);
        cardNumberField.setFont(font);
        pinField.setFont(font);
        phoneField.setFont(font);
        occupationField.setFont(font);
        dobField.setFont(font);
        signUpButton.setFont(font);
        backButton.setFont(font);

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(cardNumberLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(cardNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(pinLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(pinField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        add(phoneLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        add(occupationLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        add(occupationField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        add(dobLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        add(dobField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.CENTER;
        add(signUpButton, gbc);

        gbc.gridy = 7;
        add(backButton, gbc);

        signUpButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                String cardNumber = cardNumberField.getText();
                String pin = new String(pinField.getPassword());
                String phone = phoneField.getText();
                String occupation = occupationField.getText();
                String dob = dobField.getText();
                nameField.setText("");
                cardNumberField.setText("");
                pinField.setText("");
                phoneField.setText("");
                occupationField.setText("");
                dobField.setText("");

                if (DatabaseUtil.registerUser(name, cardNumber, pin, phone, occupation, dob)) {
                    JOptionPane.showMessageDialog(SignUp.this, "Sign Up Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                    parentFrame.showPanel("Login");
                } else {
                    JOptionPane.showMessageDialog(SignUp.this, "Error in Sign Up", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        backButton.addActionListener(e -> parentFrame.showPanel("Login"));
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}









