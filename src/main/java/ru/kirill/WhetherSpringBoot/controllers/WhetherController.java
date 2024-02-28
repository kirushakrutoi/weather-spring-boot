package ru.kirill.WhetherSpringBoot.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kirill.WhetherSpringBoot.models.DTOs.Location.LocationDTO;
import ru.kirill.WhetherSpringBoot.models.Location;
import ru.kirill.WhetherSpringBoot.models.User;
import ru.kirill.WhetherSpringBoot.security.UserDetailsImpl;
import ru.kirill.WhetherSpringBoot.services.LocationService;
import ru.kirill.WhetherSpringBoot.services.WhetherService;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/whether")
public class WhetherController {
    private final WhetherService whetherService;
    private final LocationService locationService;

    public WhetherController(WhetherService whetherService, LocationService locationService) {
        this.whetherService = whetherService;
        this.locationService = locationService;
    }

    @GetMapping("/mainmenu")
    public String getHelloPage(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        User user = userDetails.getUser();

        List<Location> locations = locationService.findByUser(user);

        model.addAttribute("locations", whetherService.getWeatherByCoords(locations));

        return "mainmenu";
    }

    @GetMapping("/search")
    public String searchWhetherPage(@RequestParam("city") String cityName, Model model){
        Optional<LocationDTO> OLocation = whetherService.getWeatherByCityName(cityName);

        if(OLocation.isEmpty()){
            model.addAttribute("whether", null);
            return "search";
        }

        LocationDTO locationDTO = OLocation.get();
        model.addAttribute("whether", locationDTO);
        return "search";

    }

    @PostMapping("/search")
    public String trackingWhether(@RequestParam("lat") BigDecimal lat,
                                  @RequestParam("lon") BigDecimal lon,
                                  @RequestParam("name") String cityName){

        Location location = new Location();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        User user = userDetails.getUser();

        location.setLatitude(lat);
        location.setLongitude(lon);
        location.setName(cityName);
        location.setUser(user);

        locationService.save(location);

        return "redirect:/whether/mainmenu";
    }
}
