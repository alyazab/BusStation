package logic;

public class ManagerTransporter {

	private static Manager manager;

	public static void setManager(Manager m) {
		ManagerTransporter.manager = m;
	}

	public static Manager getManager() {
		return manager;
	}

}