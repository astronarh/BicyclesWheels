import java.io.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ReadFile {
    private static String last = "SELECT * FROM bicycles ORDER BY id DESC LIMIT 1;";

    public static Map<String, File> read() {
        Map<String, File> fileMap = new HashMap<>();
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/forjava?user=root&password=");) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(last);
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("user");
                Blob file = resultSet.getBlob("file");
                Blob comments = resultSet.getBlob("comments");
                InputStream fileBinaryStream = file.getBinaryStream();
                InputStream commentsBinaryStream = comments.getBinaryStream();
                int fileSize = fileBinaryStream.available();
                int commentsSize = commentsBinaryStream.available();
                byte fileBytes[] = new byte[fileSize];
                byte commentsBytes[] = new byte[commentsSize];
                fileBinaryStream.read(fileBytes);
                commentsBinaryStream.read(commentsBytes);
                File filePath = File.createTempFile("file", ".tmp");
                File commentsPath = File.createTempFile("comments", ".tmp");
                try (OutputStream filePathStream = new FileOutputStream(filePath); OutputStream commentsPathStream = new FileOutputStream(commentsPath)) {
                    filePathStream.write(fileBytes);
                    commentsPathStream.write(commentsBytes);
                    fileMap.put("file", filePath);
                    fileMap.put("comments", commentsPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                /*try (OutputStream targetFile = new FileOutputStream("D:\\projects\\Bicycles&Wheels\\src\\main\\java\\files\\" + name)) {
                    targetFile.write(b);
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
            }
            return fileMap;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return fileMap;
    }
}
