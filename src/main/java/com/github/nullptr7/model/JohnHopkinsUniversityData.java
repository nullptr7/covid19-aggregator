package com.github.nullptr7.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JohnHopkinsUniversityData {

    @JsonProperty(value = "country")
    private String countryName;

    @JsonProperty(value = "province")
    private String province;

    private Long confirmed;

    private Long deaths;

    private Long recovered;

    private Double latitude;

    private Double longitude;

    @JsonProperty(value = "stats")
    private void loadStatsInfo(Map<String, Long> stats) {
        setConfirmed(stats.get("confirmed"));
        setDeaths(stats.get("deaths"));
        setRecovered(stats.get("recovered"));
    }

    @JsonProperty(value = "coordinates")
    private void loadCoordinatesInfo(Map<String, Double> coordinates) {
        setLatitude(coordinates.get("latitude"));
        setLongitude(coordinates.get("longitude"));
    }
}
