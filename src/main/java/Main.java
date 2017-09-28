import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String... args){
        //LoadFileToSQL.load("D:\\projects\\Bicycles&Wheels\\src\\main\\java\\files\\ToLoad.txt", "D:\\projects\\Bicycles&Wheels\\src\\main\\java\\files\\comments.txt", "astronarh");
        //Map<String, File> fileMap = ReadFileFromSQL.read();
        //System.out.println(fileMap);
        //fileMap.get("file").deleteOnExit();
        //fileMap.get("comments").deleteOnExit();
        //if (args.length == 1) ParseFile.parse(new File(args[0]));
        //File file = ReadAllCommentsFromSQL.read();
        //System.out.println(file);
        String filePath = args[0];
        Map<String, String> newComments = ReadAllCommentsFromFile.read(filePath);
        System.out.println(newComments);
        Map<String, String> presentComments = ReadAllCommentsFromSQL.read();
        System.out.println(presentComments);
        List<Object> selectedComments = CompareComments.compare(presentComments, newComments);
        System.out.println(selectedComments);
        LoadAllToSQL.load(args[0], selectedComments);
    }
}
