package projectleap;

import java.sql.*;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3307/online_learning";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

   
    public static User getUserByEmailAndPassword(String email, String password) throws SQLException {
        String query = "SELECT * FROM Users WHERE email = ? AND password = ?";
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("user_id"), rs.getString("name"), rs.getString("email"));
            }
            return null;
        }
    }

    public static boolean enrollInCourse(int userId, int courseId) throws SQLException {
        String query = "INSERT INTO Enrollments (user_id, course_id, enroll_date, completion_status) VALUES (?, ?, CURDATE(), 'In Progress')";
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, courseId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public static ResultSet getCourses() throws SQLException {
        String query = "SELECT * FROM Courses";
        Connection connection = getConnection();
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(query);
    }

    
    public static ResultSet getQuizzes(int courseId) throws SQLException {
        String query = "SELECT * FROM Quizzes WHERE course_id = ?";
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, courseId);
        return stmt.executeQuery();
    }

    
    public static ResultSet getQuestions(int quizId) throws SQLException {
        String query = "SELECT * FROM Questions WHERE quiz_id = ?";
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, quizId);
        return stmt.executeQuery();
    }

    public static void saveQuizResult(int userId, int quizId, int score) throws SQLException {
        String query = "INSERT INTO QuizResults (user_id, quiz_id, score, attempt_date) VALUES (?, ?, ?, CURDATE())";
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, quizId);
            stmt.setInt(3, score);
            stmt.executeUpdate();
        }
    }

   
    public static String getUserProgress(int userId) throws SQLException {
        String query = "SELECT progress FROM Users WHERE user_id = ?";
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("progress");
            } else {
                return "No progress available.";
            }
        }
    }
}
