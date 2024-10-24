package fi.sade24.electricy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ElectricityPriceService {

    private final RestTemplate restTemplate;

    public ElectricityPriceService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    private ElPriceStorage priceStorage;

    public ElPrice fetchElectricityPrice(String url) {
        System.out.println("Fetching electricity price from: " + url);

        ElPrice elPrice = restTemplate.getForObject(url, ElPrice.class);
        priceStorage.addPrices(elPrice.getPrices());

        return restTemplate.getForObject(url, ElPrice.class);
    }

    public String fetchRawData(String url) {
        return restTemplate.getForObject(url, String.class);
    }

}
