
import database.DatabaseConnectionFactory;
import play.*;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Global extends GlobalSettings {

    @Override
    public void onStart(Application app) {
        try {
            Connection connection = DatabaseConnectionFactory.getConnection();
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS weatherData (" +
                                    "current_temperature INTEGER NOT NULLABLE, " +
                                    "recorded_time timestamp NOT NULLABLE )");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
