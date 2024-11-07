package com.example.crud;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentsRepository {
    private Connection connection;

    public StudentsRepository(Connection connection) {
        this.connection = connection;
    }

    public String addStudent(int studentId, String name, int age, String grade) {
        String sql = "INSERT INTO students (id, name, age, grade) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            stmt.setString(2, name);
            stmt.setInt(3, age);
            stmt.setString(4, grade);
            stmt.executeUpdate();
            return "Student " + name + " added successfully.";
        } catch (SQLException e) {
            return "Error adding student: " + e.getMessage();
        }
    }

    public Student getStudent(int studentId) {
        String sql = "SELECT * FROM students WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("grade")
                );
            } else {
                System.out.println("Student not found.");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving student: " + e.getMessage());
            return null;
        }
    }

    public String updateStudent(int studentId, String name, int age, String grade) {
        String sql = "UPDATE students SET name = ?, age = ?, grade = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.setString(3, grade);
            stmt.setInt(4, studentId);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0 ? "Student updated successfully." : "Student not found.";
        } catch (SQLException e) {
            return "Error updating student: " + e.getMessage();
        }
    }

    public String deleteStudent(int studentId) {
        String sql = "DELETE FROM students WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0 ? "Student deleted successfully." : "Student not found.";
        } catch (SQLException e) {
            return "Error deleting student: " + e.getMessage();
        }
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("grade")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving students: " + e.getMessage());
        }
        return students;
    }
}

