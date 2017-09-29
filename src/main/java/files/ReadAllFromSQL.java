package files;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ReadAllFromSQL {
    private static String query = "SELECT user, comments FROM bicycles";
    private static Map<String, String> comments = new HashMap<>();

    public static void read() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/forjava?user=root&password=");) {
            Statement statement = connection.createStatement();
            statement.executeQuery(query);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                String user = resultSet.getString("user");
                String[] comment = resultSet.getString("comments").split("\n");
                for (String x : comment) comments.put(x.replace("\r", ""), user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(comments);
    }
}
