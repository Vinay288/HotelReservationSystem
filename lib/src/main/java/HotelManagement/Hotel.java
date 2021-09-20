/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package HotelManagement;

public class Hotel {
	String hotelName;
	int weekDayRate;
	int weekEndRate;
	int rating;
	int rateForRegularCustomer;

	public Hotel(String hotelName, int weekDayRate, int weekEndRate, int rating) {
		this.hotelName = hotelName;
		this.weekDayRate = weekDayRate;
		this.weekEndRate = weekEndRate;
		this.rating = rating;
	}

	public int getWeekDayRate() {
		return weekDayRate;
	}

	public void setWeekDayRate(int weekDayRate) {
		this.weekDayRate = weekDayRate;
	}

	public int getWeekEndRate() {
		return weekEndRate;
	}

	public void setWeekEndRate(int weekEndRate) {
		this.weekEndRate = weekEndRate;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getHotelName() {
		return hotelName;
	}

	public int getRateForRegularCustomer() {
		return rateForRegularCustomer;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public void setRateForRegularCustomer(int rateForRegularCustomer) {
		this.rateForRegularCustomer = rateForRegularCustomer;
	}

	@Override
	public String toString() {
		return "hotelName=" + hotelName + ", rateForRegularCustomer=" + rateForRegularCustomer + "]";
	}
}
