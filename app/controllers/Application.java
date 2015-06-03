package controllers;

import com.github.dvdme.ForecastIOLib.ForecastIO;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
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

        return ok(index.render(getWeatherData()));

    }

    private static String getWeatherData() throws IOException {
        ForecastIO fio = new ForecastIO("bd83368832b4c183be52330304af31cc");
        fio.setUnits(ForecastIO.UNITS_SI);

        DefaultHttpClient httpClient = new DefaultHttpClient();
        System.out.println(fio.getUrl("40.728549", "-73.990034"));
        HttpGet httpget = new HttpGet(fio.getUrl("40.728549", "-73.990034"));
        return EntityUtils.toString(httpClient.execute(httpget).getEntity());
    }
//    private static String showDatabase()
//            throws ServletException, IOException {
//        Connection connection = null;
//        try {
//            connection = getConnection();
//
//            Statement stmt = connection.createStatement();
//            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
//            stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
//            ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");
//
//            String out = "Hello!\n";
//            while (rs.next()) {
//                out += "Read from DB: " + rs.getTimestamp("tick") + "\n";
//            }
//
//            return out;
//        } catch (Exception e) {
//            return "There was an error: " + e.getMessage();
//        } finally {
//            if (connection != null) try{connection.close();} catch(SQLException e){}
//        }
//    }

}
