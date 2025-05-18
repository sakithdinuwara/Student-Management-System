package dao;

import model.User;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password")
                );
            }
        }
        return null;
    }

    public boolean validateUser(String username, String password) throws SQLException {
        User user = getUserByUsername(username);
        if (user != null) {
            // WARNING: Plain text password comparison for demo only!
            // Use hashed passwords in production!
            return user.getPassword().equals(password);
        }
        return false;
    }
}
