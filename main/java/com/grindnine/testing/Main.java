package com.grindnine.testing;

public class Main {
    public static void main(String[] args) {
        System.out.println(FlightsFilter.flightsBeforeNow(FlightBuilder.createFlights()));
        System.out.println(FlightsFilter.flightsArrivedTimeBeforeDeparture(FlightBuilder.createFlights()));
        System.out.println(FlightsFilter.flightsWithTwoHoursOnLand(FlightBuilder.createFlights()));
    }
}
