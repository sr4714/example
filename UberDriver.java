package hw1;

/**
 * This class simulates the activity of a driver for a shared-ride service similar to Uber
 * @author siddharth rana
 *
 */
public class UberDriver {
	
	
	/**
	* Maximum number of passengers allowed in the vehicle at one time. */
	public static final int MAX_PASSENGERS = 4;
	/**
	* Cost to operate the vehicle per mile. */
	public static final double OPERATING_COST = 0.5;
	
	/**
	 * Initialized with the values given by the user
	 */
	private double perMileRate,perMinuteRate; 
	/**
	 * keeps track of the number of passengers in the cab at any time
	 */
	private int num_Passengers=0;
	/**
	 * stores the number of credits calculated every time drive function is called
	 * stores the total number of credits earned since the construction of Uber Driver
	 */
	private double totalDrive=0,totalCreds=0;
	/**
	 * stores the total number of miles and minutes since the construction of Uber Driver
	 */
	private int allMiles=0,allMinutes=0; 
	
	/**
	 * This constructs a Uber Driver with a specified per mile and per minute rate
	 * @param givenPerMileRate  Per mile rate given by user
	 * @param givenPerMinuteRate Per minute rate given by user
	 */
	public UberDriver(double givenPerMileRate, double givenPerMinuteRate)
	{
		 perMileRate=givenPerMileRate;
		 perMinuteRate=givenPerMinuteRate;
	}
	
	/**
	 * 
	 * @return total number of miles the cab drives
	 */
	public int getTotalMiles()
	{
		return allMiles;
	}
	
	/**
	 * 
	 * @return total number of minutes the cab drives
	 */
	public int getTotalMinutes()
	{
		return allMinutes;
	}
	
	/**
	 * @param miles number of miles the cab drives at a go, given by user
	 * @param minutes number of minutes the cab drives at a go, given by user
	 */
	public void drive(int miles, int minutes)
	{
		totalDrive=((miles*perMileRate)+(minutes*perMinuteRate))*getPassengerCount();
		totalCreds=totalCreds+totalDrive;
		allMiles=allMiles+miles;
		allMinutes=allMinutes+minutes;
	}
	
	/**
	 * 
	 * @param minutes number of minutes the cab waits without moving, given by user
	 */
	public void waitAround(int minutes)
	{
		drive(0,minutes);
	}
	
	/**
	 * 
	 * @param miles number of miles the cab drives at a go, given by user
	 * @param averageSpeed the speed at which the car moves, given by user
	 */
	public void driveAtSpeed(int miles, double averageSpeed)
	{
		double m=(miles/averageSpeed)*60; //calculates the actual number of minutes required
		drive(miles,(int)Math.round(m));
	}
	
	/**
	 * Increases the passenger count by 1, not exceeding MAX_PASSENGERS. 
	 */
	public void pickUp()
	{
		if(num_Passengers<=3)
			++num_Passengers;
	}
	
	/**
	 * Increases the passenger count by 1, not exceeding MAX_PASSENGERS.
	 */
	public void dropOff()
	{
		if(num_Passengers!=0)
		--num_Passengers;
	}
	
	/**
	 * 
	 * @return the total number of passengers at any given time
	 */
	public int getPassengerCount()
	{
		return num_Passengers;
	}
	
	/**
	 * 
	 * @return the total credits earned by the driver
	 */
	public double getTotalCredits()
	{
		return totalCreds;
	}
	
	/**
	 * 
	 * @return profit the driver gets after subtracting the operating costs
	 */
	public double getProfit()
	{
		return getTotalCredits()-(getTotalMiles()*OPERATING_COST);
	}
	
	/**
	 * 
	 * @return UberDriver's average profit per hour worked
	 */
	public double getAverageProfitPerHour()
	{
		return (60*getProfit())/getTotalMinutes();
	}
	
}
