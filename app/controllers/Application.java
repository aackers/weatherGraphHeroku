package controllers;

import com.google.common.collect.Lists;
import database.DatabaseConnectionFactory;
import domain.TemperatureRecord;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import scala.util.parsing.json.JSON;
import views.html.index;

import javax.servlet.ServletException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Random;

import org.json.*;

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

            stmt.executeUpdate("INSERT INTO weatherData VALUES (" + random.nextInt() + ", now())");
            ResultSet rs = stmt.executeQuery("SELECT * FROM weatherData");

            String out = "Hello!\n";
            while (rs.next()) {
                out += "Read from DB: " + rs.getInt("temperature") + " " + rs.getTimestamp("recorded_date") + "\n";
            }

            return out;
        } catch (Exception e) {
            return "There was an error: " + e.getMessage();
        } finally {
            if (connection != null) try {
                connection.close();
            } catch (SQLException e) {
            }
        }
    }

    public static Result getData() throws URISyntaxException, SQLException {
        List<TemperatureRecord> temperatureRecordList = Lists.newArrayList();

        Connection connection = DatabaseConnectionFactory.getConnection();

        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT * FROM weatherData");

        while (rs.next()) {
           TemperatureRecord record = new TemperatureRecord(rs.getInt("temperature"), rs.getTimestamp("recorded_date"));
           temperatureRecordList.add(record);
        }
        return ok(Json.toJson(temperatureRecordList)).as("JSON");
    }

}
