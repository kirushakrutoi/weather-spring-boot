package ru.kirill.WhetherSpringBoot.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.kirill.WhetherSpringBoot.models.DTOs.Location.LocationDTO;
import ru.kirill.WhetherSpringBoot.models.Location;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WhetherService {
    @Value("${WHETHER_API_TOKEN}")
    private String WEATHER_API_TOKEN;
    private static final String URL = "https://api.openweathermap.org/data/2.5/weather?";
    private final ObjectMapper objectMapper;

    @Autowired
    public WhetherService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Optional<LocationDTO> getWeatherByCityName(String city){

        try {
            HttpClient client = HttpClient.newHttpClient();

            URI uri = new URI("https://api.openweathermap.org/data/2.5/weather?q="
                    + city
                    + "&units=metric"
                    + "&appid=" + WEATHER_API_TOKEN);

            System.out.println(uri.toString());

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            LocationDTO locationsDto = objectMapper.readValue(json, LocationDTO.class);

            return Optional.of(locationsDto);

        } catch (URISyntaxException | InterruptedException | IOException e) {
            return Optional.empty();
        }

    }

    public List<LocationDTO> getWeatherByCoords(List<Location> locations){
        List<LocationDTO> locationDtoList = new ArrayList<>();
        try {
            for(Location location : locations) {
                String lon = location.getLongitude().toString();
                String lat = location.getLatitude().toString();

                HttpClient client = HttpClient.newHttpClient();

                URI uri = new URI("https://api.openweathermap.org/data/2.5/weather?" +
                        "lat=" + lat +
                        "&lon=" + lon
                        + "&units=metric"
                        + "&appid=" + WEATHER_API_TOKEN);

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(uri)
                        .GET()
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                String json = response.body();

                LocationDTO locationDto = objectMapper.readValue(json, LocationDTO.class);

                locationDtoList.add(locationDto);
            }
            return locationDtoList;
        } catch (URISyntaxException | InterruptedException | IOException e) {
            return new ArrayList<>();
        }
    }

    public void test(){
        System.out.println("Test: ");
    }
}
