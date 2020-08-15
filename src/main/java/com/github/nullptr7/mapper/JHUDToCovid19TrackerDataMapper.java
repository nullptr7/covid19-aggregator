package com.github.nullptr7.mapper;

import com.github.nullptr7.model.Covid19TrackerData;
import com.github.nullptr7.model.johnhopkins.JohnHopkinsUniversityData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JHUDToCovid19TrackerDataMapper {

    JHUDToCovid19TrackerDataMapper FROM_JHUD = Mappers.getMapper(JHUDToCovid19TrackerDataMapper.class);


    @Mapping(source = "countryName", target = "countryName")
    @Mapping(source = "deaths", target = "totalDeaths")
    @Mapping(source = "recovered", target = "totalRecovered")
    @Mapping(source = "confirmed", target = "totalCases")
    Covid19TrackerData convert(JohnHopkinsUniversityData input);
}
