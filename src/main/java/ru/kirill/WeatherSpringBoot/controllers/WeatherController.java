package ru.kirill.WeatherSpringBoot.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kirill.WeatherSpringBoot.models.DTOs.Location.LocationDTO;
import ru.kirill.WeatherSpringBoot.models.Location;
import ru.kirill.WeatherSpringBoot.models.User;
import ru.kirill.WeatherSpringBoot.security.UserDetailsImpl;
import ru.kirill.WeatherSpringBoot.services.LocationService;
import ru.kirill.WeatherSpringBoot.services.WeatherService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherService weatherService;
    private final LocationService locationService;

    public WeatherController(WeatherService weatherService, LocationService locationService) {
        this.weatherService = weatherService;
        this.locationService = locationService;
    }

    @GetMapping("/mainmenu")
    public String getHelloPage(Model model){

        User user = getUser();

        List<Location> locations = locationService.findByUser(user);

        model.addAttribute("user", user);
        model.addAttribute("locations", weatherService.getWeatherByCoords(locations));

        return "mainmenu";
    }

    @GetMapping("/search")
    public String searchWhetherPage(@RequestParam("city") String cityName, Model model){
        Optional<LocationDTO> OLocation = weatherService.getWeatherByCityName(cityName);

        User user = getUser();

        if(OLocation.isEmpty()){
            model.addAttribute("whether", null);
            return "search";
        }

        LocationDTO locationDTO = OLocation.get();
        model.addAttribute("weather", locationDTO);
        model.addAttribute("user", user);
        return "search";

    }

    @PostMapping("/search")
    public String trackingWhether(@RequestParam("lat") BigDecimal lat,
                                  @RequestParam("lon") BigDecimal lon,
                                  @RequestParam("name") String cityName){

        Location location = new Location();

        User user = getUser();

        location.setLatitude(lat);
        location.setLongitude(lon);
        location.setName(cityName);
        location.setUser(user);

        if(locationService.findByUserAndName(user, location.getName()).isPresent()){
            return "redirect:/weather/mainmenu";
        }

        locationService.save(location);

        return "redirect:/weather/mainmenu";
    }

    @GetMapping("/{name}")
    public String locationPage(@PathVariable("name") String name,
                               Model model){

        User user = getUser();

        model.addAttribute("user", user);
        model.addAttribute("weather", weatherService.getWeatherByCityName(name).get());

        return "location";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("name") String name){

        User user = getUser();

        locationService.delete(user, name);

        return "redirect:/weather/mainmenu";
    }

    private User getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return userDetails.getUser();
    }
}
