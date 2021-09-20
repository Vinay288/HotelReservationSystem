package HotelManagement;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class HotelReservation {
	List<Hotel> hotelsList = new ArrayList<Hotel>();

	public boolean addHotel(Hotel hotel) {
		hotelsList.add(hotel);
		System.out.println(hotelsList);
		return true;
	}

	public Hotel findCheapestHotel(LocalDate startDate, LocalDate endDate) {
		int noOfDays = endDate.compareTo(startDate);
		int weekdayCounter = 0;
		int weekendCounter = 0;

		for (LocalDate dateCounter = startDate; startDate.isEqual(endDate);) {
			if (dateCounter.getDayOfWeek() == DayOfWeek.SATURDAY || dateCounter.getDayOfWeek() == DayOfWeek.SUNDAY)
				weekendCounter++;
			else
				weekdayCounter++;
			startDate=dateCounter.plusDays(1);
		}

		final int weekdayCount = weekdayCounter;
		final int weekendCount = weekendCounter;
		int cheapestPrice = hotelsList.stream().mapToInt(
				hotel -> (int) (hotel.getWeekDayRate() * weekdayCount) + (int) (hotel.getWeekEndRate() * weekendCount))
				.min().orElse(Integer.MAX_VALUE);
		System.out.println(cheapestPrice);

		Hotel cheapestHotel = hotelsList.stream().filter(hotel -> hotel.getWeekDayRate() * weekdayCount
				+ (hotel.getWeekEndRate() * weekendCount) == cheapestPrice).findFirst().orElse(null);
		return cheapestHotel;
	}

	public List<Hotel> getHotelList() {
		return hotelsList;
	}
}
