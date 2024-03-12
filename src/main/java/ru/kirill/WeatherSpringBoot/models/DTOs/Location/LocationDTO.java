package ru.kirill.WeatherSpringBoot.models.DTOs.Location;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationDTO {
    private int id;

    private String name;

    @JsonSetter("main")
    private MainInfoDTO mainInfo;

    private List<WhetherDTO> weather;

    private  WindDTO wind;

    private CoordDTO coord;
}
