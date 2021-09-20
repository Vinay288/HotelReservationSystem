package HotelManagement;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HotelReservation {
	List<Hotel> hotelsList = new ArrayList<Hotel>();

	public boolean addHotel(Hotel hotel) {
		hotelsList.add(hotel);
		return true;
	}

	public Hotel findCheapestHotel(LocalDateTime startDate, LocalDateTime endDate) {
		int noOfDays = startDate.compareTo(endDate);
		double min = Double.MAX_VALUE;
		Hotel cheapest = hotelsList.stream()
				.min((hotel1, hotel2) -> (int) hotel1.getWeekDayRate() - (int) hotel2.weekDayRate).orElse(null);
		return cheapest;

	}

	public List<Hotel> getHotelList() {
		return hotelsList;
	}
}
