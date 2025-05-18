package dao;

import model.Grade;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GradeDAO {
    public void addGrade(Grade grade) throws SQLException {
        String sql = "INSERT INTO grades (student_id, course_id, grade) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, grade.getStudentId());
            stmt.setInt(2, grade.getCourseId());
            stmt.setString(3, grade.getGrade());
            stmt.executeUpdate();
        }
    }

    public Grade getGradeById(int gradeId) throws SQLException {
        String sql = "SELECT * FROM grades WHERE grade_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, gradeId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Grade(
                        rs.getInt("grade_id"),
                        rs.getInt("student_id"),
                        rs.getInt("course_id"),
                        rs.getString("grade")
                );
            }
        }
        return null;
    }

    public List<Grade> getAllGrades() throws SQLException {
        List<Grade> grades = new ArrayList<>();
        String sql = "SELECT * FROM grades";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                grades.add(new Grade(
                        rs.getInt("grade_id"),
                        rs.getInt("student_id"),
                        rs.getInt("course_id"),
                        rs.getString("grade")
                ));
            }
        }
        return grades;
    }

    public void updateGrade(Grade grade) throws SQLException {
        String sql = "UPDATE grades SET grade = ? WHERE grade_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, grade.getGrade());
            stmt.setInt(2, grade.getGradeId());
            stmt.executeUpdate();
        }
    }

    public void deleteGrade(int gradeId) throws SQLException {
        String sql = "DELETE FROM grades WHERE grade_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, gradeId);
            stmt.executeUpdate();
        }
    }
}
