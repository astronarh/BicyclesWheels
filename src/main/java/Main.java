import java.io.File;
import java.util.Map;

public class Main {
    public static void main(String... args){
        //LoadFile.load("D:\\projects\\Bicycles&Wheels\\src\\main\\java\\files\\ToLoad.txt", "D:\\projects\\Bicycles&Wheels\\src\\main\\java\\files\\comments.txt", "user");
        //Map<String, File> fileMap = ReadFile.read();
        //System.out.println(fileMap);
        //fileMap.get("file").deleteOnExit();
        //fileMap.get("comments").deleteOnExit();
        if (args.length == 1) ParseFile.parse(new File(args[0]));
    }
}
