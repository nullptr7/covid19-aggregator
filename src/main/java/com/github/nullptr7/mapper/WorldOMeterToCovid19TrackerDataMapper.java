package com.github.nullptr7.mapper;

import com.github.nullptr7.model.Covid19TrackerData;
import com.github.nullptr7.model.worldometer.WorldOMeterData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WorldOMeterToCovid19TrackerDataMapper {

    WorldOMeterToCovid19TrackerDataMapper FROM_WORLD_O_METER = Mappers.getMapper(WorldOMeterToCovid19TrackerDataMapper.class);

    @Mapping(source = "cases", target = "totalCases")
    @Mapping(source = "recovered", target = "totalRecovered")
    @Mapping(source = "deaths", target = "totalDeaths")
    Covid19TrackerData convert(WorldOMeterData input);

}
