package com.github.nullptr7.model.mathdroid;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MathDroidMasterData {

    private MathDroidCovidData mathDroidCovidData;

    private MathDroidCountryData mathDroidCountryData;
}
