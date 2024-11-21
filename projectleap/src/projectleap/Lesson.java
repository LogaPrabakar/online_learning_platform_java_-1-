package projectleap;


public class Lesson {
 private int lessonId;
 private int courseId;
 private String lessonTitle;
 private String content;
 private int order;

 public Lesson(int lessonId, int courseId, String lessonTitle, String content, int order) {
     this.lessonId = lessonId;
     this.courseId = courseId;
     this.lessonTitle = lessonTitle;
     this.content = content;
     this.order = order;
 }

 
 public int getLessonId() { return lessonId; }
 public void setLessonId(int lessonId) { this.lessonId = lessonId; }

 public int getCourseId() { return courseId; }
 public void setCourseId(int courseId) { this.courseId = courseId; }

 public String getLessonTitle() { return lessonTitle; }
 public void setLessonTitle(String lessonTitle) { this.lessonTitle = lessonTitle; }

 public String getContent() { return content; }
 public void setContent(String content) { this.content = content; }

 public int getOrder() { return order; }
 public void setOrder(int order) { this.order = order; }
}
