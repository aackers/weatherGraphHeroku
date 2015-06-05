package domain;


import java.sql.Timestamp;

public class TemperatureRecord {

    private int temperature;
    private Timestamp recordedTime;

    public TemperatureRecord(int temperature, Timestamp recordedTime) {
        this.temperature = temperature;
        this.recordedTime = recordedTime;
    }

    public int getTemperature() {
        return temperature;
    }

    public Timestamp getRecordedTime() {
        return recordedTime;
    }
}
