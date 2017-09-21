import java.io.*;
import java.sql.*;

public class ReadFile {

    public static void read() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/forjava?user=root&password=");) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from bicycles where id=7");
            if (resultSet.next()) {
                String name = resultSet.getString("file_name");
                Blob file = resultSet.getBlob("file_contents");
                InputStream x = file.getBinaryStream();
                int size = x.available();
                byte b[] = new byte[size];
                x.read(b);
                try (OutputStream targetFile = new FileOutputStream("C:\\Users\\Nurzhan\\Desktop\\Новая папка\\" + name)) {
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
