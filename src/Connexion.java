import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    private static Connection connection = null;

    public static Connection connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/consultorio", "root", "");
            return connection;
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }
}
