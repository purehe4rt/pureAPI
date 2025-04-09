package eu.pureheart.api.time;

import java.time.LocalTime;

public interface TimeProvider {

    LocalTime getCurrentTime();
    LocalTime parseTime(String time);
}
