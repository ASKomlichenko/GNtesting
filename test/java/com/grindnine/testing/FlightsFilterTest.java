package com.grindnine.testing;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;



class FlightsFilterTest {
    static List<Flight> flightList;

    @BeforeAll
    public static void init(){
        flightList = FlightBuilder.createFlights();
    }

    @Test
    void testFlightsBeforeNow() {
        List<Flight> flights = FlightsFilter.flightsBeforeNow(flightList);
        assertThat(flights).isNotNull();
        assertThat(flights.size()).isEqualTo(1);
        assertThat(flights).containsExactlyElementsOf(new ArrayList<>(List.of(flightList.get(2))));
    }

    @Test
    void testFlightsArrivedTimeBeforeDeparture() {
        List<Flight> flights = FlightsFilter.flightsArrivedTimeBeforeDeparture(flightList);
        assertThat(flights).isNotNull();
        assertThat(flights.size()).isEqualTo(1);
        assertThat(flights).containsExactlyElementsOf(new ArrayList<>(List.of(flightList.get(3))));
    }

    @Test
    void testFlightsWithTwoHoursOnLand() {
        List<Flight> flights = FlightsFilter.flightsWithTwoHoursOnLand(flightList);
        assertThat(flights).isNotNull();
        assertThat(flights.size()).isEqualTo(2);
        assertThat(flights).containsExactlyElementsOf(new ArrayList<>(List.of(flightList.get(4),flightList.get(5))));
    }
}