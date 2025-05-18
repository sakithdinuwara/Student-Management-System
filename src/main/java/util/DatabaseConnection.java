package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class to manage database connections for the Student Management System.
 * Provides a singleton connection to the MySQL database.
 */
public class DatabaseConnection {
    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3307/student_management_system_be?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root"; // Replace with your MySQL username
    private static final String PASSWORD = "root"; // Replace with your MySQL password

    // Singleton connection instance
    private static Connection connection = null;

    /**
     * Gets a connection to the database. If no connection exists or the existing one is closed,
     * a new connection is created.
     * @return Connection object to the database
     * @throws SQLException if a database access error occurs or the driver is not found
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                // Explicitly load the MySQL JDBC driver (optional for JDBC 4.0+ but included for safety)
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Database connection established successfully to: " + URL);
            } catch (ClassNotFoundException e) {
                throw new SQLException("MySQL JDBC Driver not found. Ensure the driver is in the classpath: " + e.getMessage(), e);
            } catch (SQLException e) {
                throw new SQLException("Failed to connect to the database. Check URL, username, and password: " + e.getMessage(), e);
            }
        }
        return connection;
    }

    /**
     * Closes the database connection if it exists and is not already closed.
     * @throws SQLException if a database access error occurs while closing the connection
     */
    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            System.out.println("Database connection closed.");
        }
    }
}
