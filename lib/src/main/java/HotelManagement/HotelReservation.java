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
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import HotelManagement.HotelReservationException.ExceptionType;

public class HotelReservation {
	List<Hotel> hotelsList = new ArrayList<Hotel>();
	int numberOfWeekEnds, numberOfWeekDays;

	public boolean addHotel(Hotel hotel) {
		hotelsList.add(hotel);
		return true;
	}

	public void findNumberOfWeekEndsDaysBetween(LocalDate startDate, LocalDate endDate) {
		Predicate<LocalDate> isWeekend = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY
				|| date.getDayOfWeek() == DayOfWeek.SUNDAY;

		long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);

		List<LocalDate> weekEnds = Stream.iterate(startDate, date -> date.plusDays(1)).limit(daysBetween + 1)
				.filter((isWeekend)).collect(Collectors.toList());

		numberOfWeekEnds = weekEnds.size();
		numberOfWeekDays = (int) (daysBetween + 1) - numberOfWeekEnds;
	}

	public Hotel findCheapestHotel(LocalDate startDate, LocalDate endDate, CustomerType customerType) {
		Hotel cheapestHotel;
		findNumberOfWeekEndsDaysBetween(startDate, endDate);
		if (customerType.equals(CustomerType.REGULAR_CUSTOMER)) {
			int cheapestPrice = hotelsList.stream().mapToInt(hotel -> (int) (hotel.getWeekDayRate() * numberOfWeekDays)
					+ (int) (hotel.getWeekEndRate() * numberOfWeekEnds)).min().orElse(Integer.MAX_VALUE);

			cheapestHotel = hotelsList.stream()
					.filter(hotel -> hotel.getWeekDayRate() * numberOfWeekDays
							+ (hotel.getWeekEndRate() * numberOfWeekEnds) == cheapestPrice)
					.max(Comparator.comparing(Hotel::getRating)).orElse(null);
			cheapestHotel.setRateForRegularCustomer(cheapestPrice);

		} else {

			int cheapestPrice = hotelsList.stream()
					.mapToInt(hotel -> (int) (hotel.getRewardWeekdayRates() * numberOfWeekDays)
							+ (int) (hotel.getRewardWeekEndrates() * numberOfWeekEnds))
					.min().orElse(Integer.MAX_VALUE);

			cheapestHotel = hotelsList.stream()
					.filter(hotel -> hotel.getRewardWeekdayRates() * numberOfWeekDays
							+ (hotel.getRewardWeekEndrates() * numberOfWeekEnds) == cheapestPrice)
					.max(Comparator.comparing(Hotel::getRating)).orElse(null);
			cheapestHotel.setRateForRegularCustomer(cheapestPrice);
		}
		return cheapestHotel;
	}

	public Hotel findBestRatedHotel(LocalDate startDate, LocalDate endDate) {
		findNumberOfWeekEndsDaysBetween(startDate, endDate);
		return hotelsList.stream().max(Comparator.comparing(Hotel::getRating)).orElse(null);
	}

	public List<Hotel> getHotelList() {
		return hotelsList;
	}

	public static boolean validateDate(String date) {
		try {
			String dateRegex = "^([0-9]{4})[\\-]((0[1-9])|1[012])[\\-]([012][0-9]|[3][01])$";
			if (Pattern.matches(dateRegex, date))
				return true;
			else
				throw new HotelReservationException("Enter date in proper format", ExceptionType.DATE_INVALID);
		} catch (NullPointerException e) {
			throw new HotelReservationException("Enter date", ExceptionType.DATE_NULL);
		}

	}

	public static boolean validateCustomerType(String customerType) {
		try {
			String customerTypeRegex = "^(REGULAR_CUSTOMER)|(REWARDS_CUSTOMER)$";
			if (Pattern.matches(customerTypeRegex, customerType))
				return true;
			else
				throw new HotelReservationException("Enter proper customer type", ExceptionType.CUTOMER_TYPE_INVALID);
		} catch (NullPointerException e) {
			throw new HotelReservationException("Enter Customer type", ExceptionType.CUSTOMER_TYPE_NULL);
		}
	}
}
