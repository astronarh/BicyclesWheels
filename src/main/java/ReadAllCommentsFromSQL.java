import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ReadAllCommentsFromSQL {

    public static Map<String, String> read() {
        String query = "SELECT user, comments FROM bicycles";
        Map<String, String> comments = new HashMap<>();

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
        return comments;
    }
}