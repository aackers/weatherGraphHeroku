package domain;


import org.joda.time.DateTime;

public class TemperatureRecord {

    private double temperature;
    private String recordedTime;

    public TemperatureRecord(double temperature, String recordedTime) {
        this.temperature = temperature;
        this.recordedTime = recordedTime;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getRecordedTime() {
        return recordedTime;
    }
}
