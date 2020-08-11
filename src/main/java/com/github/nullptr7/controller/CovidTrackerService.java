package com.github.nullptr7.controller;

import com.github.nullptr7.model.JohnHopkinsUniversityData;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.RxHttpClient;
import io.reactivex.Flowable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.github.nullptr7.model.Covid19TrackerData;
import com.github.nullptr7.model.mathdroid.MathDroidCountryData;
import com.github.nullptr7.model.mathdroid.MathDroidCountryDataWrapper;
import com.github.nullptr7.model.mathdroid.MathDroidCovidData;
import com.github.nullptr7.model.mathdroid.MathDroidMasterData;
import com.github.nullptr7.model.worldometer.WorldOMeterData;
import com.github.nullptr7.util.Constants;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;

import static hu.akarnokd.rxjava3.bridge.RxJavaBridge.toV3Flowable;
import static io.micronaut.http.HttpRequest.GET;
import static java.util.Collections.singletonMap;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.ObjectUtils.allNotNull;
import static com.github.nullptr7.mapper.JHUDToCovid19TrackerDataMapper.FROM_JHUD;
import static com.github.nullptr7.mapper.MathDroidToCovid19TrackerDataMapper.FROM_MATH_DROID;
import static com.github.nullptr7.mapper.WorldOMeterToCovid19TrackerDataMapper.FROM_WORLD_O_METER;


@Controller("/api/v1/covid-19")
public class CovidTrackerService {

    @Inject
    private RxHttpClient client;

    @Get("/all")
    @Tag(name = "Covid Data")
    @ApiResponse(responseCode = "200", description = "Ok")
    @ApiResponse(responseCode = "500", description = "Server error")
    @Operation(summary = "Gives list of Covid details", description = "Gives list of Covid details")
    public io.reactivex.rxjava3.core.Flowable<Covid19TrackerData> getAllCases() {

        final var fromWorldOMeterFlowable = client.retrieve(GET(Constants.DISEASE_SH_ALL), WorldOMeterData[].class)
                                                  .flatMapIterable(Arrays::asList)
                                                  .map(FROM_WORLD_O_METER::convert);

        final var fromMathDroidFlowable = client.retrieve(GET(Constants.MATHDROID_COUNTRIES_V1), MathDroidCountryDataWrapper[].class)
                                                .flatMapIterable(Arrays::asList)
                                                .flatMapIterable(MathDroidCountryDataWrapper::getCountryDataList)
                                                .filter(iso -> iso.getCountryCodeIso2() != null)
                                                .flatMap(this::getCovidDataByCountryCode)
                                                .map(FROM_MATH_DROID::convert);

        //TODO: Instead of this blockingGet need to do something better
        final var jhUDList = client.retrieve(GET(Constants.JHUCSSE_ALL), JohnHopkinsUniversityData[].class)
                                   .flatMapIterable(Arrays::asList)
                                   .toList()
                                   .blockingGet();


        final var mapByCountryNames = jhUDList.stream()
                                              .collect(groupingBy(JohnHopkinsUniversityData::getCountryName));

        //System.out.println("mapByCountryNames.values() = " + mapByCountryNames.values());

        final var finalJHUDList = mapByCountryNames.values()
                                                   .stream()
                                                   .map(jHUD -> jHUD.stream()
                                                                    .reduce(consolidateDataByCountry)
                                                                    .orElseGet(() -> JohnHopkinsUniversityData.builder()
                                                                                                              .confirmed(0L)
                                                                                                              .deaths(0L)
                                                                                                              .recovered(0L)
                                                                                                              .latitude(0.0)
                                                                                                              .longitude(0.0)
                                                                                                              .countryName("")
                                                                                                              .province("")
                                                                                                              .build()))
                                                   .collect(toList());


        final var jHUDFlowable = Flowable.fromIterable(finalJHUDList.stream()
                                                                    .map(FROM_JHUD::convert)
                                                                    .collect(toList()));

        final var finalCovid19TrackerData = fromWorldOMeterFlowable.mergeWith(fromMathDroidFlowable)
                                                                   .mergeWith(jHUDFlowable);

        final var map = finalCovid19TrackerData.groupBy(Covid19TrackerData::getCountryName)
                                               .flatMapSingle(f -> f.collect(() -> singletonMap(f.getKey(), new ArrayList<Covid19TrackerData>()),
                                                                             (m, i) -> m.get(f.getKey()).add(i)
                                               ));


        final Flowable<Covid19TrackerData> data = map.map(each -> each.get(each.keySet()
                                                                               .stream()
                                                                               .findFirst()
                                                                               .orElseGet(defaultStringValue))
                                                                      .stream()
                                                                      .reduce(getLatestData))
                                                     .flatMap(a -> Flowable.just(a.orElseGet(defaultCovidData)));

        return toV3Flowable(data);
    }

    private final Supplier<Covid19TrackerData> defaultCovidData = () -> Covid19TrackerData.builder().build();

    private final Supplier<String> defaultStringValue = () -> "";

    private final BinaryOperator<Covid19TrackerData> getLatestData = (data1, data2) -> data1.getTotalCases() > data2.getTotalCases() ? data1 : data2;

    private final BinaryOperator<JohnHopkinsUniversityData> consolidateDataByCountry = (data1, data2) -> {
        data1.setProvince(null);
        if (data1.getCountryName().equalsIgnoreCase(data2.getCountryName())) {
            data1.setRecovered(data1.getRecovered() + data2.getRecovered());
            data1.setConfirmed(data1.getConfirmed() + data2.getConfirmed());
            data1.setDeaths(data1.getDeaths() + data2.getDeaths());
            if (allNotNull(data1.getLatitude(), data1.getLongitude(), data2.getLatitude(), data2.getLongitude())) {
                data1.setLatitude((data1.getLatitude() + data2.getLatitude()) / 2);
                data1.setLongitude((data1.getLongitude() + data2.getLongitude()) / 2);
            }
        }
        return data1;
    };

    private Flowable<MathDroidMasterData> getCovidDataByCountryCode(MathDroidCountryData countryData) {
        return client.retrieve(GET(Constants.MATHDROID_COUNTRIES_V2 + countryData.getCountryCodeIso2()), MathDroidCovidData.class)
                     .onErrorReturnItem(MathDroidCovidData.builder()
                                                          .confirmed(0L)
                                                          .recovered(0L)
                                                          .deaths(0L)
                                                          .build())
                     .map(covidData -> MathDroidMasterData.builder()
                                                          .mathDroidCountryData(countryData)
                                                          .mathDroidCovidData(covidData)
                                                          .build());
    }

}
