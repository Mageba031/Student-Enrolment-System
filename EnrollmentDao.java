package za.ac.cput.serverside;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDao {

    // CREATE
    public void addEnrollment(String studentId, String courseId, Date dateEnrolled) throws SQLException {
        String sql = "INSERT INTO ENROLLMENT (studentId, courseId, dateEnrolled) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, studentId);
            ps.setString(2, courseId);
            ps.setDate(3, dateEnrolled);
            ps.executeUpdate();
        }
    }

    // READ
    public List<String> getEnrollmentsByStudent(String studentId) throws SQLException {
        List<String> courses = new ArrayList<>();
        String sql = "SELECT courseId FROM ENROLLMENT WHERE studentId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    courses.add(rs.getString("courseId"));
                }
            }
        }
        return courses;
    }

    // UPDATE (example: change date)
    public void updateEnrollmentDate(String studentId, String courseId, Date newDate) throws SQLException {
        String sql = "UPDATE ENROLLMENT SET dateEnrolled = ? WHERE studentId = ? AND courseId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, newDate);
            ps.setString(2, studentId);
            ps.setString(3, courseId);
            ps.executeUpdate();
        }
    }

    // DELETE
    public void deleteEnrollment(String studentId, String courseId) throws SQLException {
        String sql = "DELETE FROM ENROLLMENT WHERE studentId = ? AND courseId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, studentId);
            ps.setString(2, courseId);
            ps.executeUpdate();
        }
    }
}

 


