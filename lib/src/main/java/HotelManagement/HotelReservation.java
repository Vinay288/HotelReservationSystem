package HotelManagement;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HotelReservation {
	List<Hotel> hotels = new ArrayList<Hotel>();

	public boolean addHotel(String hotelName, int rate) {
		Scanner scanner = new Scanner(System.in);
		List<Hotel> hotels = new ArrayList<Hotel>();
		Hotel hotel = new Hotel("Name", 2000);
		hotels.add(hotel);
		return true;
	}

	public Hotel getCheapestHotel(LocalDate startDate, LocalDate lastDate) {
		long daysBetween = ChronoUnit.DAYS.between(startDate, lastDate);

		Hotel cheapestHotel = hotels.stream()
				.min((n1, n2) -> n1.getRateForRegularCustomer() - n2.getRateForRegularCustomer()).orElse(null);
		long cheapestRate = (daysBetween + 1) * cheapestHotel.getRateForRegularCustomer();
		System.out
				.println("Cheapest hotel name is :" + cheapestHotel.getHotelName() + "Total rate is :" + cheapestRate);
		return cheapestHotel;

	}
}
