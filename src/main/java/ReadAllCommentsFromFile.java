import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ReadAllCommentsFromFile {
    public static Map<String, String> read(String arg) {
        Map<String, String> comments = new HashMap<>();
        try (Scanner scanner = new Scanner(new File(arg))) {
            while (scanner.hasNext()) {
                String[] newComment = scanner.nextLine().trim().split("//");
                if (newComment.length > 1) comments.put(newComment[newComment.length - 1].replace("\n", ""), newComment[newComment.length - 2]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return comments;
    }
}
