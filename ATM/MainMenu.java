import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainMenu extends JPanel {
    private Image backgroundImage;
    private MainFrame parentFrame;
    private JLabel welcome;

    public MainMenu(MainFrame parentFrame) {
        this.parentFrame=parentFrame;
        backgroundImage = new ImageIcon("D:/ATM/image5.png").getImage();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        welcome = new JLabel();
        welcome.setFont(new Font("Arial", Font.BOLD, 20));
        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");
        JButton balanceButton = new JButton("Balance");
        JButton changePinButton = new JButton("Change PIN");
        JButton miniStatementButton = new JButton("transaction-statement");
        JButton logoutButton = new JButton("Logout");
        JButton backButton = new JButton("Back");

        Font font = new Font("Arial", Font.BOLD, 15);
        withdrawButton.setFont(font);
        depositButton.setFont(font);
        balanceButton.setFont(font);
        changePinButton.setFont(font);
        miniStatementButton.setFont(font);
        logoutButton.setFont(font);
        backButton.setFont(font);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        add(welcome, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        add(withdrawButton, gbc);
        gbc.gridx = 1;
        add(depositButton, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        add(changePinButton, gbc);
        gbc.gridx = 1;
        add(balanceButton, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        add(miniStatementButton, gbc);

        gbc.gridy = 3;
        gbc.gridx = 1;
        //gbc.gridwidth = 2;
        //gbc.anchor = GridBagConstraints.CENTER;
        add(logoutButton, gbc);

        

        withdrawButton.addActionListener(e -> {
            String amountStr = JOptionPane.showInputDialog(parentFrame, "Enter withdrawal amount:");
            if (amountStr != null && !amountStr.isEmpty()) {
                try {
                    double amount = Double.parseDouble(amountStr);
                    double currentBalance = parentFrame.getBalance();
                    if (amount > currentBalance) {
                        JOptionPane.showMessageDialog(parentFrame, "Insufficient balance.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        double newBalance = currentBalance - amount;
                        DatabaseUtil.updateUserBalance(parentFrame.getCardNumber(), parentFrame.getPin(), newBalance);
                        DatabaseUtil.recordTransaction(parentFrame.getCardNumber(), amount, "Withdraw");
                        parentFrame.setUserDetails(parentFrame.getCardNumber(), parentFrame.getPin(), newBalance);
                        JOptionPane.showMessageDialog(parentFrame, "Withdrawal of " + amount + "/- successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(parentFrame, "Invalid amount entered. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        depositButton.addActionListener(e -> {
            String amountStr = JOptionPane.showInputDialog(parentFrame, "Enter deposit amount:");
            if (amountStr != null && !amountStr.isEmpty()) {
                try {
                    double amount = Double.parseDouble(amountStr);
                    double currentBalance = parentFrame.getBalance();
                    double newBalance = currentBalance + amount;
                    DatabaseUtil.updateUserBalance(parentFrame.getCardNumber(), parentFrame.getPin(), newBalance);
                    DatabaseUtil.recordTransaction(parentFrame.getCardNumber(), amount, "Deposit");
                    parentFrame.setUserDetails(parentFrame.getCardNumber(), parentFrame.getPin(), newBalance);
                    JOptionPane.showMessageDialog(parentFrame, "Deposit of " + amount + "/- successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(parentFrame, "Invalid amount entered. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        balanceButton.addActionListener(e -> {
            double balance = parentFrame.getBalance();
            JOptionPane.showMessageDialog(parentFrame, "Your current balance is: " + balance + "/-", "Balance", JOptionPane.INFORMATION_MESSAGE);
        });

        changePinButton.addActionListener(e -> {
            String currentPin = JOptionPane.showInputDialog(this, "Enter current PIN:");
            if (currentPin != null && currentPin.equals(parentFrame.getPin())) {
                String newPin = JOptionPane.showInputDialog(this, "Enter new PIN:");
                if (newPin != null && !newPin.isEmpty()) {
                    DatabaseUtil.changeUserPin(parentFrame.getCardNumber(), newPin);
                    JOptionPane.showMessageDialog(this, "PIN changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "New PIN cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Current PIN is incorrect.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        miniStatementButton.addActionListener(e -> {
            List<Transaction> transactions = DatabaseUtil.getLastTransactions(parentFrame.getCardNumber(), 5);
            StringBuilder receipt = new StringBuilder();
            receipt.append("Transaction-Statement:\n\n");

            for (Transaction transaction : transactions) {
                receipt.append("Type  : ").append(transaction.getType())
                       .append("\nAmount  : ").append(transaction.getAmount())
                       .append("\nDate  : ").append(transaction.getTimestamp())
                       .append("\n\n");
            }

            JTextArea receiptArea = new JTextArea(receipt.toString());
            receiptArea.setEditable(false);
            receiptArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

            JOptionPane.showMessageDialog(parentFrame, new JScrollPane(receiptArea), "Mini-Statement", JOptionPane.INFORMATION_MESSAGE);
        });

        logoutButton.addActionListener(e -> parentFrame.showPanel("Login"));
        //backButton.addActionListener(e -> parentFrame.showPanel("Login"));
    }
    public void updateWelcomeMessage(String cardNumber) {
        welcome.setText("Welcome " + (cardNumber != null ? cardNumber : "") + "...");
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}