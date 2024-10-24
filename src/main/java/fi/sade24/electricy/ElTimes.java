package fi.sade24.electricy;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

@Component
public class ElTimes {

    public String timeInfo() {
        // Get the current timestamp in the Helsinki time zone
        ZonedDateTime helsinkiTime = ZonedDateTime.now(ZoneId.of("Europe/Helsinki"));
        String formattedHelsinki = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH")
                .format(helsinkiTime);

        ZonedDateTime utcTime = ZonedDateTime.now(ZoneId.of("UTC"));
        utcTime = utcTime.truncatedTo(ChronoUnit.HOURS);
        String formattedUtc = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH")
                .format(utcTime);
        String retHelsinki = helsinkiTime.toString();
        String retUtc = utcTime.toString();

        Instant instant = Instant.now();
        instant = instant.truncatedTo(ChronoUnit.HOURS);
        String retInstant = instant.toString();

        String retTime = " - Helsinki: " + retHelsinki + "\n - UTC time: " + retUtc + "\n - Instant: " + retInstant + "\n";
        System.out.println(retTime);
        return retTime;
    }

    public Instant currentHour() {
        ZonedDateTime currentTimestamp = Instant.now().atZone(ZoneId.of("Europe/Helsinki"));
        currentTimestamp = currentTimestamp.truncatedTo(ChronoUnit.HOURS);
        return currentTimestamp.toInstant();
    }

    public Instant currentTimeInstant() {
        ZonedDateTime currentTimestamp = Instant.now().atZone(ZoneId.of("Europe/Helsinki"));
        System.out.println(currentTimestamp);
        System.out.println(currentTimestamp.toInstant());

        return currentTimestamp.toInstant();
    }

}
