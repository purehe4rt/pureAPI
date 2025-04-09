package eu.pureheart.api.time.service;

import eu.pureheart.api.time.AbstractTimeService;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.TimeZone;

public class MoscowTimeService extends AbstractTimeService {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

    public MoscowTimeService() {
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Moscow");
        dateFormat.setTimeZone(timeZone);
    }

    @Override
    public LocalTime getCurrentTime() {
        String nowString = dateFormat.format(new Date());
        return LocalTime.parse(nowString, formatter);
    }
}
