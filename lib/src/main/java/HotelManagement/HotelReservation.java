package HotelManagement;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HotelReservation {
	List<Hotel> hotelsList = new ArrayList<Hotel>();
	int numberOfWeekEnds, numberOfWeekDays;

	public boolean addHotel(Hotel hotel) {
		hotelsList.add(hotel);
		return true;
	}

	public Hotel findCheapestHotel(LocalDate startDate, LocalDate endDate) {
		Predicate<LocalDate> isWeekend = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY
				|| date.getDayOfWeek() == DayOfWeek.SUNDAY;

		long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);

		List<LocalDate> weekEnds = Stream.iterate(startDate, date -> date.plusDays(1)).limit(daysBetween + 1)
				.filter((isWeekend)).collect(Collectors.toList());

		numberOfWeekEnds = weekEnds.size();
		numberOfWeekDays = (int) (daysBetween + 1) - numberOfWeekEnds;
		int cheapestPrice = hotelsList.stream().mapToInt(hotel -> (int) (hotel.getWeekDayRate() * numberOfWeekDays)
				+ (int) (hotel.getWeekEndRate() * numberOfWeekEnds)).min().orElse(Integer.MAX_VALUE);

		Hotel cheapestHotel = hotelsList.stream()
				.filter(hotel -> hotel.getWeekDayRate() * numberOfWeekDays
						+ (hotel.getWeekEndRate() * numberOfWeekEnds) == cheapestPrice)
				.max(Comparator.comparing(Hotel::getRating)).orElse(null);
		cheapestHotel.setRateForRegularCustomer(cheapestPrice);
		return cheapestHotel;
	}

	public List<Hotel> getHotelList() {
		return hotelsList;
	}
}
