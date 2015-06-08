package controllers;

import database.DatabaseConnectionFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DataWriter {

    public static void writeData() throws IOException, URISyntaxException, SQLException {
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet("https://api.forecast.io/forecast/bd83368832b4c183be52330304af31cc/40.712784,-74.005941");
        JSONObject object = new JSONObject(EntityUtils.toString(client.execute(httpGet).getEntity()));
        Connection connection = DatabaseConnectionFactory.getConnection();

        Statement stmt = connection.createStatement();

        stmt.executeUpdate("INSERT INTO weather_data " +
                "VALUES (" + object.getJSONObject("currently").getDouble("temperature") + ", now())");
    }

}
