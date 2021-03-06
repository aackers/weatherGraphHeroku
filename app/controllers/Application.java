package controllers;

import com.google.common.collect.Lists;
import database.DatabaseConnectionFactory;
import domain.TemperatureRecord;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.joda.time.DateTime;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import scala.util.parsing.json.JSON;
import views.html.index;

import javax.servlet.ServletException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.List;
import java.util.Random;

import org.json.*;

public class Application extends Controller {

    public static Result index() throws ServletException, IOException {
        return ok(index.render("Last 2 hours of temperatures"));
    }

    public static Result getData() throws URISyntaxException, SQLException {
        List<TemperatureRecord> temperatureRecordList = Lists.newArrayList();

        Connection connection = DatabaseConnectionFactory.getConnection();

        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery("select * from weather_data where recorded_time >= now() - interval '2 hours'");

        while (rs.next()) {
           TemperatureRecord record = new TemperatureRecord(rs.getDouble("temperature"), rs.getString("recorded_time"));
           temperatureRecordList.add(record);
        }
        return ok(Json.toJson(temperatureRecordList)).as("JSON");
    }

    public Result testJson() throws IOException {
        List<TemperatureRecord> records = Lists.newArrayList();
        Random random = new Random();
        records.add(new TemperatureRecord(random.nextDouble(), DateTime.now().plusHours(1).toString()));
        records.add(new TemperatureRecord(random.nextDouble(), DateTime.now().plusHours(2).toString()));
        records.add(new TemperatureRecord(random.nextDouble(), DateTime.now().plusHours(3).toString()));
        records.add(new TemperatureRecord(random.nextDouble(), DateTime.now().plusHours(4).toString()));
        records.add(new TemperatureRecord(random.nextDouble(), DateTime.now().plusHours(5).toString()));
        return ok(Json.toJson(records)).as("JSON");
    }

}
