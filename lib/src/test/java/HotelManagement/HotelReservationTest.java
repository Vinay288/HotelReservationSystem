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
	static HotelReservation hotelReservation = new HotelReservation();
	static Hotel hotel1;
	static Hotel hotel2;
	static Hotel hotel3;

	@Before
	public void initialize() {
		hotel1 = new Hotel("Lakewood", 110, 90, 3, 80, 80);
		hotel2 = new Hotel("Bridgewood", 150, 50, 4, 110, 50);
		hotel3 = new Hotel("Ridgewood", 220, 150, 5, 100, 40);

		hotelReservation.addHotel(hotel1);
		hotelReservation.addHotel(hotel2);
		hotelReservation.addHotel(hotel3);
	}

	@Test
	public void givenDetails_WhenCorrect_ShoulReturnTrue() {
		Hotel hotel = new Hotel("Lakewood", 110, 90, 3, 100, 50);
		boolean isValidHotel = hotelReservation.addHotel(hotel);
		assertTrue(isValidHotel);
	}

	@Test
	public void givenThreeHotel_WhenCalledCheapestHotelMethodWithRegularCustomer_ShoulReturnBridgeWood() {
		LocalDate date1 = LocalDate.of(2020, Month.SEPTEMBER, 11);
		LocalDate date2 = LocalDate.of(2020, Month.SEPTEMBER, 12);

		Hotel cheapestHotel = hotelReservation.findCheapestHotel(date1, date2, CustomerType.REGULAR_CUSTOMER);
		Assert.assertEquals("Bridgewood", cheapestHotel.getHotelName());
	}

	@Test
	public void givenThreeHotel_WhenCalledCheapestHotelMethodWithRewardCustomer_ShoulReturnRidgeWood() {
		LocalDate date1 = LocalDate.of(2020, Month.SEPTEMBER, 11);
		LocalDate date2 = LocalDate.of(2020, Month.SEPTEMBER, 12);

		Hotel cheapestHotel = hotelReservation.findCheapestHotel(date1, date2, CustomerType.REWARD_CUSTOMER);
		Assert.assertEquals("Ridgewood", cheapestHotel.getHotelName());
	}

	@Test
	public void givenThreeHotel_WhenCalledFindRatedHotel_ShouldreturnRidgeWood() {
		LocalDate date1 = LocalDate.of(2020, Month.SEPTEMBER, 11);
		LocalDate date2 = LocalDate.of(2020, Month.SEPTEMBER, 12);

		Hotel bestHotel = hotelReservation.findBestRatedHotel(date1, date2);
		Assert.assertEquals("Ridgewood", bestHotel.getHotelName());
	}

	@Test
	public void givenDate_WhenCorrect_ShouldReturnTrue() {
		String date = "2020-09-11";
		try {
			boolean result = HotelReservation.validateDate(date);
			Assert.assertEquals(true, result);
		} catch (HotelReservationException e) {

		}
	}

	@Test
	public void givenDate_WhenCorrect_ShouldCatchNullException() {
		String date = null;
		try {
			boolean result = HotelReservation.validateDate(date);
		} catch (HotelReservationException e) {
			Assert.assertEquals(HotelReservationException.ExceptionType.DATE_NULL, e.type);
		}
	}

	@Test
	public void givenDate_WhenInvalid_ShouldCatchInvalidException() {
		String date = "12-1234-22";
		try {
			boolean result = HotelReservation.validateDate(date);
		} catch (HotelReservationException e) {
			Assert.assertEquals(HotelReservationException.ExceptionType.DATE_INVALID, e.type);
		}
	}

	@Test
	public void givenCustomerType_WhenCorrect_ShouldReturnTrue() {
		String customerType = "REWARDS_CUSTOMER";
		try {
			boolean result = HotelReservation.validateCustomerType(customerType);
			Assert.assertEquals(true, result);
		} catch (HotelReservationException e) {

		}
	}

	@Test
	public void givenCustomerType_WhenNull_ShouldCatchNullException() {
		String customerType = null;
		try {
			boolean result = HotelReservation.validateCustomerType(customerType);

		} catch (HotelReservationException e) {
			Assert.assertEquals(HotelReservationException.ExceptionType.CUSTOMER_TYPE_NULL, e.type);
		}
	}

	@Test
	public void givenCustomerType_WhenInvalid_ShouldCatchInvalidException() {
		String customerType = "hduhuas";
		try {
			boolean result = HotelReservation.validateCustomerType(customerType);

		} catch (HotelReservationException e) {
			Assert.assertEquals(HotelReservationException.ExceptionType.CUTOMER_TYPE_INVALID, e.type);
		}
	}
}
