package HotelManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HotelReservation {

	public boolean addHotel(String hotelName, int rate) {
		Scanner scanner = new Scanner(System.in);
		List<Hotel> hotels = new ArrayList<Hotel>();
		Hotel hotel = new Hotel("Name", 2000);
		hotels.add(hotel);
		return true;
	}
}
