package com.pal.detroitmapper;

import com.pal.detroitmapper.appartmentsapi.ApartmentFixtures;
import com.pal.detroitmapper.appartmentsapi.ApartmentInfo;
import com.pal.detroitmapper.appartmentsapi.ApartmentsClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class HomeController {

    private final ApartmentsClient apartmentsClient;
    private final ApartmentFixtures apartmentFixtures;

    public HomeController(ApartmentsClient apartmentsClient, ApartmentFixtures apartmentFixtures) {
        this.apartmentsClient = apartmentsClient;
        this.apartmentFixtures = apartmentFixtures;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/setup")
    public String setup(Map<String, Object> model) {
        for (ApartmentInfo info: apartmentFixtures.load()) {
            apartmentsClient.addApartment(info);
        }
        model.put("restaurants", apartmentsClient.getAppartments());

        return "setup";
    }
}
