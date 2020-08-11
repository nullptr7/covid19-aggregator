package com.github.nullptr7.mapper;

import com.github.nullptr7.model.Covid19TrackerData;
import com.github.nullptr7.model.mathdroid.MathDroidMasterData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MathDroidToCovid19TrackerDataMapper {

    MathDroidToCovid19TrackerDataMapper FROM_MATH_DROID = Mappers.getMapper(MathDroidToCovid19TrackerDataMapper.class);

    @Mapping(source = "mathDroidCountryData.name", target = "countryName")
    @Mapping(source = "mathDroidCountryData.countryCodeIso2", target = "countryCode")
    @Mapping(source = "mathDroidCovidData.deaths", target = "totalDeaths")
    @Mapping(source = "mathDroidCovidData.recovered", target = "totalRecovered")
    @Mapping(source = "mathDroidCovidData.confirmed", target = "totalCases")
    Covid19TrackerData convert(MathDroidMasterData input);

}
