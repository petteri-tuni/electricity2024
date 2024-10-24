package fi.sade24.electricy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ElectricityPriceController {
    private final ElectricityPriceService electricityPriceService;

    @Autowired
    public ElectricityPriceController(ElectricityPriceService electricityPriceService) {
        this.electricityPriceService = electricityPriceService;
    }

    @GetMapping("/info")
    public String info() {
        return "This is the Copilot application!";
    }

    @GetMapping("/raw-price")
    public String getRawPrice() {

        String local_url = "https://api.porssisahko.net/v1/latest-prices.json";

        return electricityPriceService.fetchRawData(local_url);
    }


    @GetMapping("/electricity-price")
    public ElPrice getElectricityPrice() {
    // public ElectricityPrice getElectricityPrice(@RequestParam String url) {

        String local_url = "https://api.porssisahko.net/v1/latest-prices.json";

        return electricityPriceService.fetchElectricityPrice(local_url);
    }

    @Autowired
    private ElTimes elTimes;
    @GetMapping("/time-info")
    public String getTimeInfo() {
        return elTimes.timeInfo();
    }

    @Autowired
    private ElPriceStorage storage;

    @GetMapping("/current-price-closest")
    public ElectricityPrice getCurrentPrice() {
        return storage.getClosestPrice();
    }

    @GetMapping("/current-price")
    public ElectricityPrice getCurrentPrice2() {
        return storage.getCurrentHourPrice();
    }

}