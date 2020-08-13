package com.github.nullptr7;

import com.github.nullptr7.controller.CovidTrackerService;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MicronautTest
public class Covid19AggregatorTest {


    @Inject
    CovidTrackerService covidTrackerService;


    @Test
    @DisplayName("Queries covid-19 data from all countries and takes 2")
    void getAllCases() {

        final var allCases = covidTrackerService.getAllCases();

        assertNotNull(allCases);

        allCases.take(2).blockingForEach(data -> {
            assertNotNull(data);
            assertFalse("".equalsIgnoreCase(data.getCountryName()));
        });

    }
}
