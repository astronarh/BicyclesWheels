import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LoadAllToSQL {
    private static String createTable = "CREATE TABLE IF NOT EXISTS bicycles (" +
            "id integer primary key auto_increment, " +
            "datetime datetime, " +
            "user VARCHAR(100), " +
            "file BLOB, " +
            "comments BLOB);";
    private static String loadData = "INSERT INTO bicycles (datetime, user, file, comments) VALUES (now(), ?, ?, ?)";

    public static void load(String arg, List<Object> selectedComments) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/forjava?user=root&password=");) {
            PreparedStatement preparedStatement = connection.prepareStatement(createTable);
            preparedStatement.execute();

            File file = new File(arg);
            FileInputStream fileInputStream = new FileInputStream(file);
            int lengthToLoad = (int) file.length();

            preparedStatement = connection.prepareStatement(loadData);
            preparedStatement.setString(1, (String) selectedComments.get(0));
            preparedStatement.setBinaryStream(2, fileInputStream, lengthToLoad);
            Map<String, String> commentsMap = (Map<String, String>) selectedComments.get(1);
            File commentTmpStream = File.createTempFile("file", ".tmp");
            OutputStream commentsStream = new FileOutputStream(commentTmpStream);
            Iterator iterator = commentsMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry mapEntry = (Map.Entry) iterator.next();
                commentsStream.write((mapEntry.getKey() + "\n").getBytes());
            }
            FileInputStream tmp = new FileInputStream(commentTmpStream);
            int lengtTmp = (int) commentTmpStream.length();
            preparedStatement.setBinaryStream(3, tmp, lengtTmp);
            preparedStatement.execute();

        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
