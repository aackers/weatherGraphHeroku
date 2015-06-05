package controllers;

import database.DatabaseConnectionFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class Application extends Controller {

    public static Result index() throws ServletException, IOException {

        return ok(index.render(showDatabase()));

    }

    private static String showDatabase()
            throws ServletException, IOException {
        Connection connection = null;
        try {
            connection = DatabaseConnectionFactory.getConnection();

            Statement stmt = connection.createStatement();

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS weatherData (temperature bigint NOT NULL, recorded_date timestamp)");

            Random random = new Random();

            stmt.executeUpdate("INSERT INTO weatherData VALUES (" +  random.nextInt() + ", now())");
            ResultSet rs = stmt.executeQuery("SELECT * FROM weatherData");

            String out = "Hello!\n";
            while (rs.next()) {
                out += "Read from DB: " + rs.getInt("temperature") + " " + rs.getTimestamp("recorded_date") + "\n";
            }

            return out;
        } catch (Exception e) {
            return "There was an error: " + e.getMessage();
        } finally {
            if (connection != null) try{connection.close();} catch(SQLException e){}
        }
    }

}
