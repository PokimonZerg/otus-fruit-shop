package ru.otus.qa.fruitshop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@RestController
@RequestMapping(path = "/shop")
public class ShopController {

    @Value("${warehouse.url}")
    private String warehouseUrl;

    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping(path = "/apples")
    public FruitView apples() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<CounterView> response = restTemplate.exchange(warehouseUrl + "apples", HttpMethod.GET, entity, CounterView.class);
        return new FruitView("Яблоко", response.getBody().getCount());
    }
}
