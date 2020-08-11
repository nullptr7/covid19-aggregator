package com.github.nullptr7.model.worldometer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

import static java.lang.Double.parseDouble;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorldOMeterData {

    @JsonProperty(value = "cases")
    private Long cases;

    @JsonProperty(value = "deaths")
    private Long deaths;

    @JsonProperty(value = "recovered")
    private Long recovered;

    @JsonProperty(value = "country")
    private String countryName;

    private String countryCode;

    private Double longitude;

    private Double latitude;

    @JsonProperty(value = "countryInfo")
    private void loadCountryInfo(Map<String, String> countryInfo) {
        setCountryCode(countryInfo.get("iso2"));
        setLongitude(parseDouble(countryInfo.get("long")));
        setLatitude(parseDouble(countryInfo.get("lat")));
    }
}
