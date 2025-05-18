package dao;

import model.Attendance;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAO {
    public void addAttendance(Attendance attendance) throws SQLException {
        String sql = "INSERT INTO attendance (student_id, course_id, attendance_date, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, attendance.getStudentId());
            stmt.setInt(2, attendance.getCourseId());
            stmt.setDate(3, new java.sql.Date(attendance.getAttendanceDate().getTime()));
            stmt.setString(4, attendance.getStatus());
            stmt.executeUpdate();
        }
    }

    public Attendance getAttendanceById(int attendanceId) throws SQLException {
        String sql = "SELECT * FROM attendance WHERE attendance_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, attendanceId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Attendance(
                        rs.getInt("attendance_id"),
                        rs.getInt("student_id"),
                        rs.getInt("course_id"),
                        rs.getDate("attendance_date"),
                        rs.getString("status")
                );
            }
        }
        return null;
    }

    public List<Attendance> getAllAttendance() throws SQLException {
        List<Attendance> attendanceList = new ArrayList<>();
        String sql = "SELECT * FROM attendance";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                attendanceList.add(new Attendance(
                        rs.getInt("attendance_id"),
                        rs.getInt("student_id"),
                        rs.getInt("course_id"),
                        rs.getDate("attendance_date"),
                        rs.getString("status")
                ));
            }
        }
        return attendanceList;
    }

    public void updateAttendance(Attendance attendance) throws SQLException {
        String sql = "UPDATE attendance SET status = ? WHERE attendance_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, attendance.getStatus());
            stmt.setInt(2, attendance.getAttendanceId());
            stmt.executeUpdate();
        }
    }

    public void deleteAttendance(int attendanceId) throws SQLException {
        String sql = "DELETE FROM attendance WHERE attendance_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, attendanceId);
            stmt.executeUpdate();
        }
    }
}
