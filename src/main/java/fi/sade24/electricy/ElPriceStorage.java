
    // make this a class that contains a map of ElectrityPrice objects
    // the key of the map is the start timestamp of the electricity price
    // the value of the map is the electricity price object
    // the class should have a method that returns the electricity price for a given timestamp
    // the class should have a method that returns the electricity price for the current timestamp
    //  the class should have a method that adds a new electricity price to the map
    // the class should have a method that returns all the electricity prices in the map
    // the class should have a method that returns the electricity price for the current timestamp
    // the class should have a method that returns the electricity price for closest timestamp to the current timestamp


package fi.sade24.electricy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Component
public class ElPriceStorage {
    private HashMap<Instant, ElectricityPrice> priceMap = new HashMap<>();

    public void addPrice(Instant timestamp, ElectricityPrice price) {
        priceMap.put(timestamp, price);
    }

    public ElectricityPrice getPriceForTimestamp(Instant timestamp) {
        return priceMap.get(timestamp);
    }

    public ElectricityPrice getCurrentPrice() {
        Instant now = Instant.now();
        return priceMap.get(now);
    }

    public Map<Instant, ElectricityPrice> getAllPrices() {
        return new HashMap<>(priceMap);
    }

    public ElectricityPrice getClosestPrice() {
        Instant timestamp = Instant.now();

        return priceMap.entrySet().stream()
                .min((entry1, entry2) -> Long.compare(
                        Math.abs(entry1.getKey().toEpochMilli() - timestamp.toEpochMilli()),
                        Math.abs(entry2.getKey().toEpochMilli() - timestamp.toEpochMilli())
                ))
                .map(Map.Entry::getValue)
                .orElse(null);
    }

    // method that takes an array of ElectricityPrice objects and adds them to the map
    public void addPrices(ArrayList<ElectricityPrice> prices) {
        for (ElectricityPrice price : prices) {
            priceMap.put(price.getStartDate(), price);
        }
    }

    @Autowired
    private ElTimes elTimes;

    public ElectricityPrice getHourPrice(Instant theHour) {

        System.out.println("Current hour:  " + theHour);

        // return the price for the current hour
        // if the price for the current hour is not available, return null

        ElectricityPrice curPrice;

        if(priceMap.containsKey(theHour)) {
            curPrice = priceMap.get(theHour);
        } else {
            curPrice = getClosestPrice();
        }

        if (curPrice != null) {
            System.out.println("Current price:  " + curPrice.getPrice());
        }

        return curPrice;
    }

    public ElectricityPrice getCurrentHourPrice () {
        Instant curHour = elTimes.currentHour();
        return getHourPrice(curHour);
    }


}