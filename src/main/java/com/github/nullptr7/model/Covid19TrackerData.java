package com.github.nullptr7.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Covid19TrackerData {

    private String countryName;
    private String countryCode;
    private Long totalCases;
    private Long totalRecovered;
    private Long totalDeaths;
    private Double longitude;
    private Double latitude;
}
