import java.sql.*;

public class Main {
    static final String DB_NAME = "newdb";
    static final String URL = "jdbc:mysql://localhost:3306/"+DB_NAME;
    static final String USER = "developer";
    static final String PASSWORD = "password";

    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = conn.createStatement();
             PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO students (last_name, first_name) VALUES (?, ?)")) {

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS students ("
                    + " student_id INT(10) NOT NULL AUTO_INCREMENT, "
                    + "last_name VARCHAR(30) NOT NULL, "
                    + "first_name VARCHAR(30) NOT NULL, "
                    + "PRIMARY KEY (student_id))");

            insertStudents(preparedStatement, "Giovanni", "Giovannini");
            insertStudents(preparedStatement, "Marco", "Marchesi");
            insertStudents(preparedStatement, "Elio", "Palloncino");
            insertStudents(preparedStatement, "Grazia", "Altrettanta");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertStudents(PreparedStatement preparedStatement, String firstName, String lastName) throws SQLException {
        preparedStatement.setString(1, lastName);
        preparedStatement.setString(2, firstName);
        preparedStatement.executeUpdate();
    }

}

