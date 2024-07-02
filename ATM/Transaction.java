
import java.sql.*;
public class Transaction {
    private double amount;
    private String type;
    private Timestamp timestamp;

    public Transaction(double amount, String type, Timestamp timestamp) {
        this.amount = amount;
        this.type = type;
        this.timestamp = timestamp;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
}