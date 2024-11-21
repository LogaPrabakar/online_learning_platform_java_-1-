package projectleap;


public class Course {
 private int courseId;
 private String courseName;
 private String description;
 private String level; // 'Basic' or 'Advanced'
 private int duration;

 public Course(int courseId, String courseName, String description, String level, int duration) {
     this.courseId = courseId;
     this.courseName = courseName;
     this.description = description;
     this.level = level;
     this.duration = duration;
 }


 public int getCourseId() { return courseId; }
 public void setCourseId(int courseId) { this.courseId = courseId; }
 
 public String getCourseName() { return courseName; }
 public void setCourseName(String courseName) { this.courseName = courseName; }

 public String getDescription() { return description; }
 public void setDescription(String description) { this.description = description; }

 public String getLevel() { return level; }
 public void setLevel(String level) { this.level = level; }

 public int getDuration() { return duration; }
 public void setDuration(int duration) { this.duration = duration; }
}
