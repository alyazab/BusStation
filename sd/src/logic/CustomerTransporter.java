package logic;

public class CustomerTransporter {

	private static Customer customer;

	public static void setCustomer(Customer c) {
		CustomerTransporter.customer = c;
	}
	public static Customer getCustomer() {
		return customer;
	}

}
