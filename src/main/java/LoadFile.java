import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;

public class LoadFile {
    private static String createTable = "CREATE TABLE IF NOT EXISTS bicycles (" +
            "id integer primary key auto_increment, " +
            "datetime datetime, " +
            "user VARCHAR(100), " +
            "file BLOB, " +
            "comments BLOB);";
    private static String loadData = "INSERT INTO bicycles (datetime, user, file, comments) VALUES (now(), ?, ?, ?)";

    public static void load(String filePath, String commentsPath, String user) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/forjava?user=root&password=");) {
            PreparedStatement preparedStatement = connection.prepareStatement(createTable);
            preparedStatement.execute();

            File comments = new File(commentsPath);
            FileInputStream commentsStream = new FileInputStream(comments);
            int lengthComments = (int) comments.length();
            File toLoad = new File(filePath);
            FileInputStream toLoadStream = new FileInputStream(toLoad);
            int lengthToLoad = (int) toLoad.length();

            preparedStatement = connection.prepareStatement(loadData);
            preparedStatement.setString(1, user);
            preparedStatement.setBinaryStream(2, toLoadStream, lengthToLoad);
            preparedStatement.setBinaryStream(3, commentsStream, lengthComments);
            preparedStatement.execute();

            connection.close();
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
