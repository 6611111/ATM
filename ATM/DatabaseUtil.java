import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtil {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ATM";
    private static final String user = "root";
    private static final String pass = "9328";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, user, pass);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static boolean authenticateUser(String cardNumber, String pin) {
        String sql = "SELECT * FROM user WHERE card_number = ? AND pin = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cardNumber);
            pstmt.setString(2, pin);
            ResultSet rs = pstmt.executeQuery();

            return rs.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean registerUser(String name, String cardNumber, String pin, String phoneNumber, String occupation, String dateOfBirth) {
        String sql = "INSERT INTO user(name, card_number, pin, phone_number, occupation, date_of_birth) VALUES(?, ?, ?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, cardNumber);
            pstmt.setString(3, pin);
            pstmt.setString(4, phoneNumber);
            pstmt.setString(5, occupation);
            pstmt.setString(6, dateOfBirth);
            pstmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static double getUserBalance(String cardNumber, String pin) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        double balance = 0.0;

        try {
            conn = DriverManager.getConnection(DB_URL, user, pass);
            String sql = "SELECT balance FROM user WHERE card_number = ? AND pin = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, cardNumber);
            ps.setString(2, pin);
            rs = ps.executeQuery();

            if (rs.next()) {
                balance = rs.getDouble("balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return balance;
    }

    public static void updateUserBalance(String cardNumber, String pin, double newBalance) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DriverManager.getConnection(DB_URL, user, pass);
            String sql = "UPDATE user SET balance = ? WHERE card_number = ? AND pin = ?";
            ps = conn.prepareStatement(sql);
            ps.setDouble(1, newBalance);
            ps.setString(2, cardNumber);
            ps.setString(3, pin);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void changeUserPin(String cardNumber, String newPin) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DriverManager.getConnection(DB_URL, user, pass);
            String query = "UPDATE user SET pin = ? WHERE card_number = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, newPin);
            ps.setString(2, cardNumber);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void recordTransaction(String cardNumber, double amount, String type) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DriverManager.getConnection(DB_URL, user, pass);
            String sql = "INSERT INTO transactions(card_number, amount, type, timestamp) VALUES(?, ?, ?, NOW())";
            ps = conn.prepareStatement(sql);
            ps.setString(1, cardNumber);
            ps.setDouble(2, amount);
            ps.setString(3, type);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Transaction> getLastTransactions(String cardNumber, int limit) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT amount, type, timestamp FROM transactions WHERE card_number = ? ORDER BY timestamp DESC LIMIT ?";
    
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
    
            pstmt.setString(1, cardNumber);
            pstmt.setInt(2, limit);
            ResultSet rs = pstmt.executeQuery();
    
            while (rs.next()) {
                double amount = rs.getDouble("amount");
                String type = rs.getString("type");
                Timestamp timestamp = rs.getTimestamp("timestamp");
                transactions.add(new Transaction(amount, type, timestamp));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return transactions;
    }
}

