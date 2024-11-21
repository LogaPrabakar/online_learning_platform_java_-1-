package projectleap;


import java.util.ArrayList;

public class OnlineLearningPlatform implements CourseManagement {
    private ArrayList<Course> courses = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();

    @Override
    public void enrollCourse(User user, Course course) {
        System.out.println(user.getName() + " has enrolled in " + course.getCourseName());
        user.setProgress("{\"courseId\": " + course.getCourseId() + ", \"status\": \"In Progress\"}");
    }

    @Override
    public void startQuiz(User user, Quiz quiz) {
        System.out.println(user.getName() + " is taking the quiz: " + quiz.getQuizTitle());
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addUser(User user) {
        users.add(user);
    }

   
    public void updateProgress(User user, String progress) {
        user.setProgress(progress);
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
