import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ParseFile {
    public static void parse(File file) {
        Map<String, String> newCommentList = new TreeMap<>();
        Map<String, String> presentCommentList = new TreeMap<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String[] newComment = scanner.nextLine().trim().split("//");
                if (newComment.length > 1) newCommentList.put(newComment[newComment.length - 2], newComment[newComment.length - 1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(newCommentList);

        Map<String, File> fileMap = ReadFileFromSQL.read();
        try (Scanner scanner = new Scanner(fileMap.get("comments"))) {
            while (scanner.hasNext()) {
                String lineString = scanner.nextLine().trim();
                String[] trimmedString = lineString.split(" ");
                presentCommentList.put(trimmedString[0], lineString.substring(trimmedString[0].length() + 1));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(presentCommentList);

        presentCommentList.putAll(newCommentList);

        System.out.println(presentCommentList);

        LoadFileToSQL.load(file.getPath(), "D:\\projects\\Bicycles&Wheels\\src\\main\\java\\files\\comments.txt", "user");

        fileMap.get("file").deleteOnExit();
        fileMap.get("comments").deleteOnExit();
    }
}
