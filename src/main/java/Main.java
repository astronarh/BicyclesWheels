import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String... args){
        String filePath = args[0];
        Map<String, String> newComments = ReadAllCommentsFromFile.read(filePath);
        System.out.println(newComments);
        Map<String, String> presentComments = ReadAllCommentsFromSQL.read();
        System.out.println(presentComments);
        List<Object> selectedComments = CompareComments.compare(presentComments, newComments);
        System.out.println(selectedComments);
        LoadAllToSQL.load(args[0], selectedComments);
        GUI gui = new GUI();
        gui.setSize(320, 240);
        gui.setVisible(true);
    }
}
