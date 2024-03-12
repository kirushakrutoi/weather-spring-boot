package ru.kirill.WeatherSpringBoot.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.kirill.WeatherSpringBoot.models.DTOs.Location.LocationDTO;
import ru.kirill.WeatherSpringBoot.models.Location;

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
public class WeatherService {
    @Value("${WHETHER_API_TOKEN}")
    private String WEATHER_API_TOKEN;
    private static final String URL = "https://api.openweathermap.org/data/2.5/weather?";
    private final ObjectMapper objectMapper;

    @Autowired
    public WeatherService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Optional<LocationDTO> getWeatherByCityName(String city){

        try {
            HttpClient client = HttpClient.newHttpClient();

            URI uri = new URI("https://api.openweathermap.org/data/2.5/weather?q="
                    + city
                    + "&units=metric"
                    + "&appid=" + WEATHER_API_TOKEN);

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

        for(Location location : locations) {
            LocationDTO locationDto = getWeatherByCoord(location).get();

            locationDtoList.add(locationDto);
        }

        return locationDtoList;
    }

    public Optional<LocationDTO> getWeatherByCoord(Location location) {
        try {
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

            return Optional.of(objectMapper.readValue(json, LocationDTO.class));
        } catch (URISyntaxException | InterruptedException | IOException e) {
            return Optional.empty();
        }
    }

    public void test(){
        System.out.println("Test: ");
    }
}
