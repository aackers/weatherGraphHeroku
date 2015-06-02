package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

import javax.servlet.ServletException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

public class Application extends Controller {

    public static Result index() throws ServletException, IOException {
        String dbResult = showDatabase();
        return ok(index.render(dbResult));

    }

    private static String showDatabase()
            throws ServletException, IOException {
        Connection connection = null;
        try {
            connection = getConnection();

            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
            stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
            ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

            String out = "Hello!\n";
            while (rs.next()) {
                out += "Read from DB: " + rs.getTimestamp("tick") + "\n";
            }

            return out;
        } catch (Exception e) {
            return "There was an error: " + e.getMessage();
        } finally {
            if (connection != null) try{connection.close();} catch(SQLException e){}
        }
    }

    private static Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        int port = dbUri.getPort();

        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ":" + port + dbUri.getPath();

        return DriverManager.getConnection(dbUrl, username, password);
    }

}
