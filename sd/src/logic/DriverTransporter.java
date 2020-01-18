package logic;

public class DriverTransporter {

	private static Driver driver;

	public static void setDriver(Driver d) {
		DriverTransporter.driver = d;
	}

	public static Driver getDriver() {
		return driver;
	}

}