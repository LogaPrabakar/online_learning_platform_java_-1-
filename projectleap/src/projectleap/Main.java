package projectleap;

import java.sql.*;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            System.out.println("Welcome to the Online Learning Platform");

            
            User user = loginUser();
            if (user == null) {
                System.out.println("Invalid login. Exiting...");
                return;
            }

          
            boolean exit = false;
            while (!exit) {
                System.out.println("\nMenu:");
                System.out.println("1. Enroll in a Course");
                System.out.println("2. View Courses");
                System.out.println("3. Take Quiz");
                System.out.println("4. View Progress");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                        enrollInCourse(user);
                        break;
                    case 2:
                        viewCourses();
                        break;
                    case 3:
                        takeQuiz(user);
                        break;
                    case 4:
                        viewProgress(user);
                        break;
                    case 5:
                        exit = true;
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option! Please try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // User login or registration
    private static User loginUser() throws SQLException {
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        User user = Database.getUserByEmailAndPassword(email, password);
        if (user == null) {
            System.out.println("Invalid login credentials.");
        }
        return user;
    }

    // Enroll in a course
    private static void enrollInCourse(User user) throws SQLException {
        System.out.println("Available Courses:");
        ResultSet rs = Database.getCourses();
        while (rs.next()) {
            System.out.println(rs.getInt("course_id") + ". " + rs.getString("course_name"));
        }
        System.out.print("Enter course ID to enroll: ");
        int courseId = scanner.nextInt();
        scanner.nextLine();  

        if (Database.enrollInCourse(user.getUserId(), courseId)) {
            System.out.println("Successfully enrolled in the course!");
        } else {
            System.out.println("Failed to enroll in the course.");
        }
    }

    
    private static void viewCourses() throws SQLException {
        ResultSet rs = Database.getCourses();
        while (rs.next()) {
            System.out.println(rs.getInt("course_id") + ". " + rs.getString("course_name"));
        }
    }

   
    private static void takeQuiz(User user) throws SQLException {
        System.out.print("Enter the course ID to take a quiz: ");
        int courseId = scanner.nextInt();
        scanner.nextLine(); 

        ResultSet quizzes = Database.getQuizzes(courseId);
        if (quizzes.next()) {
            int quizId = quizzes.getInt("quiz_id");
            System.out.println("Starting quiz: " + quizzes.getString("quiz_title"));
            int score = startQuiz(quizId); 
            Database.saveQuizResult(user.getUserId(), quizId, score);  
            System.out.println("Quiz completed! Your score: " + score);
        } else {
            System.out.println("No quiz found for this course.");
        }
    }


    private static int startQuiz(int quizId) throws SQLException {
        ResultSet questions = Database.getQuestions(quizId);
        int score = 0;

        while (questions.next()) {
            System.out.println(questions.getString("question_text"));
            System.out.println("A. " + questions.getString("option_a"));
            System.out.println("B. " + questions.getString("option_b"));
            System.out.println("C. " + questions.getString("option_c"));
            System.out.println("D. " + questions.getString("option_d"));
            System.out.print("Your answer: ");
            String answer = scanner.nextLine().toUpperCase();

            if (answer.equals(questions.getString("correct_option"))) {
                score++;
            }
        }

        return score;
    }

   
    private static void viewProgress(User user) throws SQLException {
        String progress = Database.getUserProgress(user.getUserId());
        if (progress != null) {
            System.out.println("Your progress: " + progress);
        } else {
            System.out.println("No progress data available.");
        }
    }
}
