package com.grindnine.testing;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.time.Duration;
import java.time.Period;

public class FlightsFilter {
    public static List<Flight> flightsBeforeNow(@NotNull List<Flight> flightList) {
        return flightList.stream().filter(flight -> flight.getSegments().stream()
                        .anyMatch(segment -> segment.getDepartureDate().isBefore(LocalDateTime.now())))
                .collect(Collectors.toList());
    }

    public static List<Flight> flightsArrivedTimeBeforeDeparture(@NotNull List<Flight> flightList) {
        return flightList.stream()
                .filter(flight -> flight.getSegments().stream()
                        .anyMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate())))
                .collect(Collectors.toList());
    }

    public static List<Flight> flightsWithTwoHoursOnLand(@NotNull List<Flight> flightList) {
        List<Flight> result = new ArrayList<>();
        for (Flight flight : flightList) {
            int timeMinutes = 0;
            for (int i = 0; i < flight.getSegments().size() - 1; i++) {
                LocalDateTime arrivalDate = flight.getSegments().get(i + 1).getDepartureDate();
                LocalDateTime departureDate = flight.getSegments().get(i).getArrivalDate();

                if (arrivalDate.plusHours(2).isBefore(departureDate)) {
                    result.add(flight);
                    break;
                }
                Period period = Period.between(departureDate.toLocalDate(), arrivalDate.toLocalDate());
                Duration duration = Duration.between(departureDate.toLocalTime(), arrivalDate.toLocalTime());

                timeMinutes += (period.getDays() * 24 * 60) + (duration.toHoursPart() * 60) + duration.toMinutesPart();
                if(timeMinutes > 120){
                    result.add(flight);
                    break;
                }
            }
        }
        return result;
    }
}
