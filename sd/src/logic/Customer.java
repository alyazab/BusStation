package logic;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Customer extends Person implements Select, Update {
	ArrayList<Trip> myTrips;
	ArrayList<Trip> allTrips;
	private float balance;

	public Customer(int id, String username, String password, float balance) {
		super(id, username, password);
		this.balance = balance;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public ArrayList<Trip> getMyTrips() {
		return myTrips;
	}

	public void setMyTrips(ArrayList<Trip> myTrips) {
		this.myTrips = myTrips;
	}

	public ArrayList<Trip> getAllTrips() {
		return allTrips;
	}

	public void setAllTrips(ArrayList<Trip> allTrips) {
		this.allTrips = allTrips;
	}

	@Override
	public boolean login(int id, String password) {
		Connection myconn = makeConnection();
		if (myconn != null) {
			try {
				Statement mystat = myconn.createStatement();
				String query = "SELECT * FROM CUSTOMERS WHERE CUSTOMER_ID=" + id
						+ " AND BINARY CUSTOMER_PASSWORD= BINARY '" + password + "'";
				ResultSet myres = mystat.executeQuery(query);
				if (!myres.next()) {
					closeConnection(myconn);
					return false;
				} else {
					this.setId(myres.getInt("CUSTOMER_ID"));
					this.setUsername(myres.getString("CUSTOMER_USERNAME"));
					this.setPassword(myres.getString("CUSTOMER_PASSWORD"));
					this.setBalance(myres.getFloat("CUSTOMER_BALANCE"));
					closeConnection(myconn);
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean getMyTripsFromDB() {
		Connection myconn = makeConnection();
		Statement mystat = null;
		Statement mystat2 = null;
		ResultSet myres = null;
		ResultSet myres2 = null;
		ArrayList<Trip> mytrips = new ArrayList<Trip>();
		Trip trip;
		String query = "SELECT * FROM CUSTOMERSTRIPS WHERE CUSTOMER_ID =" + this.getId();
		try {
			mystat = myconn.createStatement();
			mystat2 = myconn.createStatement();
			myres = mystat.executeQuery(query);
			if (!myres.next()) {
				System.out.println("No Trips Available");
				closeConnection(myconn);
				return false;
			}
			myres.previous();// to return that one next step from the if statement !@!@!@
			while (myres.next()) {
				int trip_id = myres.getInt("trip_id");
				query = "SELECT * FROM TRIPS WHERE TRIP_ID =" + trip_id;
				myres2 = mystat2.executeQuery(query);
				while (myres2.next()) {
					int driver_id = myres2.getInt("driver_id");
					String source = myres2.getString("source");
					String destination = myres2.getString("destination");
					float price = myres2.getFloat("price");
					boolean isExternal = myres2.getBoolean("isexternal");
					boolean isRound = myres2.getBoolean("isround");
					int numOfSeats = myres2.getInt("NUMOFSEATS");
					int numOfStops = myres2.getInt("NUMOFStops");
					String trip_time = myres2.getString("trip_time");
					int vehicleType = myres2.getInt("vehicle_type");
					trip = new Trip(trip_id, source, destination, driver_id, price, isExternal, isRound, numOfSeats,
							numOfStops, trip_time, vehicleType);
					mytrips.add(trip);
				}
			}
			this.myTrips = mytrips;
		}

		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		closeConnection(myconn);
		return true;

	}

	public boolean getAllTripsFromDB(Trip requestedTrip) {
		Connection myconn = makeConnection();
		Statement mystat = null;
		ResultSet myres = null;
		Trip trip;
		ArrayList<Trip> alltrips = new ArrayList<Trip>();
		int numOfStopsNeeded;
		if (requestedTrip.getNumOfStops() == 0)
			numOfStopsNeeded = 0;
		else if (requestedTrip.getNumOfStops() == 1)
			numOfStopsNeeded = 1;
		else
			numOfStopsNeeded = 2;
		String query = "SELECT * FROM TRIPS WHERE SOURCE= '" + requestedTrip.getSource() + "' AND DESTINATION= '"
				+ requestedTrip.getDestination() + "' AND ISEXTERNAL=" + requestedTrip.isExternal() + " AND ISROUND="
				+ requestedTrip.isRound() + " AND VEHICLE_TYPE =" + requestedTrip.getVehicleType();
		if (numOfStopsNeeded < 2)
			query += " AND NUMOFSTOPS = " + numOfStopsNeeded;
		else
			query += " AND NUMOFSTOPS > 1";
		System.out.println(query);

		try {
			mystat = myconn.createStatement();
			myres = mystat.executeQuery(query);
			if (!myres.next())
				return false;
			myres.previous();
			while (myres.next()) {
				int driver_id = myres.getInt("driver_id");
				int trip_id = myres.getInt("trip_id");
				String source = myres.getString("source");
				String destination = myres.getString("destination");
				float price = myres.getFloat("price");
				boolean isExternal = myres.getBoolean("isexternal");
				boolean isRound = myres.getBoolean("isround");
				int numOfSeats = myres.getInt("NUMOFSEATS");
				int numOfStops = myres.getInt("NUMOFSTOPS");
				String trip_time = myres.getString("trip_time");
				int vehicleType = myres.getInt("vehicle_type");
				trip = new Trip(trip_id, source, destination, driver_id, price, isExternal, isRound, numOfSeats,
						numOfStops, trip_time, vehicleType);
				alltrips.add(trip);
			}
			this.allTrips = alltrips;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		closeConnection(myconn);
		return true;
	}

	public int addTrip(int trip_id, int numOfSeats) {
		Connection myconn = makeConnection();
		Statement mystat = null;
		try {
			mystat = myconn.createStatement();
			String query = "SELECT * FROM CUSTOMERS WHERE CUSTOMER_ID=" + this.getId();
			ResultSet myres = mystat.executeQuery(query);
			myres.next();
			float balance = myres.getFloat("CUSTOMER_BALANCE");
			query = "SELECT * FROM TRIPS WHERE TRIP_ID=" + trip_id;
			myres = mystat.executeQuery(query);
			myres.next();
			float price = myres.getFloat("price");
			int SeatsAvailable = myres.getInt("NUMOFSEATS");
			if (balance < price * numOfSeats)
				return 0;
			if (numOfSeats > SeatsAvailable)
				return 1;
			query = "UPDATE TRIPS SET NUMOFSEATS= NUMOFSEATS -" + numOfSeats + " WHERE TRIP_ID =" + trip_id;
			int m = mystat.executeUpdate(query);
			if (m != 0) {
				query = "INSERT INTO CUSTOMERSTRIPS VALUES (" + trip_id + "," + this.getId() + "," + numOfSeats + ")";
				m = mystat.executeUpdate(query);
				query = "UPDATE CUSTOMERS SET CUSTOMER_BALANCE= CUSTOMER_BALANCE -" + price * numOfSeats
						+ " WHERE CUSTOMER_ID =" + this.getId();
				m = mystat.executeUpdate(query);
				closeConnection(myconn);
				if (m != 0) {
					return 2;
				} else
					return 3;
			} else
				return 3;
		} catch (SQLException e) {
			return 3;
		}

	}
		public String getVehicleType(int VehicleType) {
			if(VehicleType==0)
				return "BUS";
			else if(VehicleType==1)
				return "MINIBUS";
			else
				return "LIMOUSINE";
		}
	@Override
	public boolean removeTrip(int trip_id) {
		Connection myconn = makeConnection();
		Statement mystat = null;
		String query = "SELECT * FROM CUSTOMERSTRIPS WHERE TRIP_ID=" + trip_id;
		try {
			mystat = myconn.createStatement();
			ResultSet myres = mystat.executeQuery(query);
			myres.next();
			int bookedSeats = myres.getInt("numofseats");
			query = "SELECT * FROM TRIPS WHERE TRIP_ID=" + trip_id;
			myres = mystat.executeQuery(query);
			myres.next();
			float price = myres.getFloat("price");
			query = "UPDATE TRIPS SET NUMOFSEATS= NUMOFSEATS +" + bookedSeats + " WHERE " + "TRIP_ID =" + trip_id;
			int m = mystat.executeUpdate(query);
			query = "UPDATE CUSTOMERS SET CUSTOMER_BALANCE= CUSTOMER_BALANCE +" + price * bookedSeats + " WHERE "
					+ "CUSTOMER_ID =" + this.getId();
			m = mystat.executeUpdate(query);
			query = "DELETE FROM CUSTOMERSTRIPS WHERE TRIP_ID=" + trip_id + " AND CUSTOMER_ID=" + this.getId();
			m = mystat.executeUpdate(query);
			closeConnection(myconn);
			if (m != 0)
				return true;
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
