package HotelManagement;

import java.time.LocalDate;

public interface HotelReservationIf {
	public boolean addHotel(Hotel hotel);
	public void findNumberOfWeekEndsDaysBetween(LocalDate startDate, LocalDate endDate);
	public Hotel findCheapestHotel(LocalDate startDate, LocalDate endDate, CustomerType customerType);
	public Hotel findBestRatedHotel(LocalDate startDate, LocalDate endDate);
	
}
