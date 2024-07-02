import javax.swing.*;
import java.awt.*;

public class Welcome extends JPanel {
    
    private Image backgroundImage;

    public Welcome(MainFrame parentFrame) {
        // Load the background image
        backgroundImage = new ImageIcon("D:/ATM/image5.png").getImage(); 
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel welcomeLabel = new JLabel("Welcome to ATM");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(welcomeLabel, BorderLayout.NORTH);

        // Load and set the main image
        ImageIcon icon = new ImageIcon("D:/ATM/image4.png");
        JLabel imageLabel = new JLabel(icon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(imageLabel, BorderLayout.CENTER);

        JButton continueButton = new JButton("Continue");
        continueButton.setFont(new Font("Arial", Font.PLAIN, 18));
        continueButton.setBackground(Color.decode("#FFD700")); // Gold color
        continueButton.setForeground(Color.decode("#000080"));
        continueButton.addActionListener(e -> parentFrame.showPanel("Login"));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false); // Make the button panel transparent
        buttonPanel.add(continueButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}


