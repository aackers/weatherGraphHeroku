import controllers.DataWriter;
import play.Application;
import play.GlobalSettings;
import play.libs.Akka;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.FiniteDuration;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class Global extends GlobalSettings {


    @Override
    public void onStart(Application app) {
        FiniteDuration delay = FiniteDuration.create(0, TimeUnit.SECONDS);
        FiniteDuration frequency = FiniteDuration.create(10, TimeUnit.MINUTES);
        Runnable writeData = new Runnable() {
            @Override
            public void run() {
                try {
                    DataWriter.writeData();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        };
        Akka.system().scheduler().schedule(delay,
                frequency,
                writeData,
                Akka.system().dispatcher());
    }

}


