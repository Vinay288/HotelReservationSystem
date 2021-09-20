/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package HotelManagement;

import org.junit.Test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;

public class HotelReservationTest {

	@Test
	public void givenDetails_WhenCorrect_ShoulReturnTrue() {
		HotelReservation hotelReservation = new HotelReservation();
		Hotel hotel = new Hotel("Lakewood", 110, 90, 3);
		boolean isValidHotel = hotelReservation.addHotel(hotel);
		assertTrue(isValidHotel);
	}

	@Test
	public void givenOneHotel_WhenCorrect_ShoulReturnProperHotelName() {
		HotelReservation listOfHotels = new HotelReservation();
		Hotel hotel1 = new Hotel("Lakewood", 100, 200, 1);
		Hotel hotel2 = new Hotel("Bridgewood", 150, 150, 2);
		Hotel hotel3 = new Hotel("Ridgewood", 200, 300, 3);
		listOfHotels.addHotel(hotel1);
		listOfHotels.addHotel(hotel2);
		listOfHotels.addHotel(hotel3);
		LocalDate date1 = LocalDate.of(2020, Month.SEPTEMBER, 11);
		LocalDate date2 = LocalDate.of(2020, Month.SEPTEMBER, 12);

		Hotel cheapestHotel = listOfHotels.findCheapestHotel(date1, date2);
System.out.println(cheapestHotel);
		Assert.assertEquals("Lakewood", cheapestHotel.getHotelName());
	}
}
