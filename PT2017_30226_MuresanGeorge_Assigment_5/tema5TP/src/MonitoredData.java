/**
 * Created by George on 5/28/2017.
 */
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public  class MonitoredData {

    private LocalDateTime starttime;
    private LocalDateTime endtime;
    private String activitylab;

    public MonitoredData(LocalDateTime starttime, LocalDateTime endtime, String activitylab) {
        this.starttime = starttime;
        this.endtime = endtime;
        this.activitylab = activitylab;
    }

    public LocalDateTime getStarttime() {
        return starttime;
    }

    public void setStarttime(LocalDateTime starttime) {
        this.starttime = starttime;
    }

    public LocalDateTime getEndtime() {
        return endtime;
    }

    public void setEndtime(LocalDateTime endtime) {
        this.endtime = endtime;
    }

    public String getActivitylab() {
        return activitylab;
    }

    public void setActivitylab(String activitylab) {
        this.activitylab = activitylab;
    }

    public String toString()
    {
        return starttime.toString() + " " + endtime.toString() + " " + activitylab;
    }
    public long  functie()
    {
        Duration duration = Duration.between(getStarttime(), getEndtime());
        long period=0;
        period = duration.getSeconds();
        return period;
    }

}
