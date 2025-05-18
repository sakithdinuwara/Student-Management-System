package dao;

import model.Enrollment;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO {
    public void addEnrollment(Enrollment enrollment) throws SQLException {
        String sql = "INSERT INTO enrollments (student_id, course_id, enrollment_date) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, enrollment.getStudentId());
            stmt.setInt(2, enrollment.getCourseId());
            stmt.setDate(3, new java.sql.Date(enrollment.getEnrollmentDate().getTime()));
            stmt.executeUpdate();
        }
    }

    public Enrollment getEnrollmentById(int enrollmentId) throws SQLException {
        String sql = "SELECT * FROM enrollments WHERE enrollment_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, enrollmentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Enrollment(
                        rs.getInt("enrollment_id"),
                        rs.getInt("student_id"),
                        rs.getInt("course_id"),
                        rs.getDate("enrollment_date")
                );
            }
        }
        return null;
    }

    public List<Enrollment> getAllEnrollments() throws SQLException {
        List<Enrollment> enrollments = new ArrayList<>();
        String sql = "SELECT * FROM enrollments";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                enrollments.add(new Enrollment(
                        rs.getInt("enrollment_id"),
                        rs.getInt("student_id"),
                        rs.getInt("course_id"),
                        rs.getDate("enrollment_date")
                ));
            }
        }
        return enrollments;
    }

    public void deleteEnrollment(int enrollmentId) throws SQLException {
        String sql = "DELETE FROM enrollments WHERE enrollment_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, enrollmentId);
            stmt.executeUpdate();
        }
    }
}
