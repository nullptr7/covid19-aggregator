package com.github.nullptr7.model.mathdroid;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class MathDroidCountryDataWrapper {

    @JsonProperty(value = "countries")
    private List<MathDroidCountryData> countryDataList;
}
