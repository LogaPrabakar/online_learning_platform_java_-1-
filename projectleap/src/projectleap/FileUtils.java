package projectleap;

//FileUtils.java
import java.io.*;

public class FileUtils {
 public static void saveUserProgress(User user) {
     try (FileWriter writer = new FileWriter("user_progress.txt", true)) {
         writer.write(user.getUserId() + " - " + user.getProgress() + "\n");
     } catch (IOException e) {
         System.out.println("Error saving progress.");
     }
 }
}
