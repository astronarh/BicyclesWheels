import java.io.*;
import java.sql.*;

public class ReadFile {
    private static String last = "SELECT * FROM bicycles ORDER BY id DESC LIMIT 1;";

    public static void read() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/forjava?user=root&password=");) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(last);
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("user");
                Blob file = resultSet.getBlob("file");
                InputStream x = file.getBinaryStream();
                int size = x.available();
                byte b[] = new byte[size];
                x.read(b);
                try (OutputStream targetFile = new FileOutputStream("D:\\projects\\Bicycles&Wheels\\src\\main\\java\\files\\" + name)) {
                    targetFile.write(b);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
