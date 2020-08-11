package com.github.nullptr7.model.mathdroid;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MathDroidCountryData {

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "iso2")
    private String countryCodeIso2;

    @JsonProperty(value = "iso3")
    private String countryCodeIso3;

}
