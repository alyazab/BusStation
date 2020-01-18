package logic;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Manager extends Person implements Update {

	ArrayList<Trip> allTrips;

	public Manager(int id, String username, String password) {
		super(id, username, password);
	}

	public void setAllTrips(ArrayList<Trip> allTrips) {
		this.allTrips = allTrips;
	}

	public ArrayList<Trip> getAllTrips() {
		return allTrips;
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
	public boolean login(int id, String password) {
		Connection myconn = makeConnection();
		if (myconn != null) {
			try {
				Statement mystat = myconn.createStatement();
				String query = "SELECT * FROM MANAGERS WHERE MANAGER_ID=" + id
						+ " AND BINARY MANAGER_PASSWORD= BINARY '" + password + "'";
				ResultSet myres = mystat.executeQuery(query);
				if (!myres.next()) {
					closeConnection(myconn);
					return false;
				} else {
					this.setId(myres.getInt("MANAGER_ID"));
					this.setUsername(myres.getString("MANAGER_USERNAME"));
					this.setPassword(myres.getString("MANAGER_PASSWORD"));
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

	public boolean addTrip(Trip trip) {
		Connection myconn = makeConnection();
		String query = "SELECT * FROM DRIVERS WHERE DRIVER_ID=" + trip.getDriverId();
		
		try {
			Statement mystat = myconn.createStatement();
			ResultSet myres = mystat.executeQuery(query);
			if(!myres.next())
			{
				closeConnection(myconn);
				return false;
			}
				
			 query = "INSERT INTO TRIPS VALUES (0," + trip.getDriverId() + "," + "'" + trip.getSource() + "','"
					+ trip.getDestination() + "'," + trip.getPrice() + "," + trip.isExternal() + ","
					+ trip.getNumOfSeats() + "," + trip.getNumOfStops() + "," + trip.isRound() + ",'" + trip.getTime()
					+ "'," + trip.getVehicleType() + ")";

			int m = mystat.executeUpdate(query);
			closeConnection(myconn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public void addDriver(String un , String pass , float salary) {
		Connection myconn = makeConnection();
		try {
		Statement mystat = myconn.createStatement();
		String query = "INSERT INTO DRIVERS VALUES(0,'" + un + "','" + pass + "'," + salary + ")";
		int m = mystat.executeUpdate(query);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean removeTrip(int trip_id) {
		Connection myconn = makeConnection();
		Statement mystat = null;
		String query = "SELECT * FROM TRIPS WHERE TRIP_ID =" + trip_id;
		int m;
		try {
			mystat = myconn.createStatement();
			ResultSet myres = mystat.executeQuery(query);
			Statement mystat2 = myconn.createStatement();
			myres.next();
			float price = myres.getFloat("price");
			query = "SELECT * FROM CUSTOMERSTRIPS WHERE TRIP_ID=" + trip_id;
			myres = mystat.executeQuery(query);
			if(myres.next()) {
				myres.previous();
				while (myres.next()) {
					int CustomerId = myres.getInt("CUSTOMER_ID");
					int bookedSeats = myres.getInt("NUMOFSEATS");
					query = "UPDATE CUSTOMERS SET CUSTOMER_BALANCE= CUSTOMER_BALANCE +" + bookedSeats * price + " WHERE CUSTOMER_ID ="
							+ CustomerId;
					m = mystat2.executeUpdate(query);
				}
				query = "DELETE FROM CUSTOMERSTRIPS WHERE TRIP_ID=" + trip_id;
				m = mystat2.executeUpdate(query);
			}
			query = "DELETE FROM TRIPS WHERE TRIP_ID=" + trip_id;
		    m = mystat2.executeUpdate(query);
			closeConnection(myconn);
			if (m != 0)
				return true;
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void getAllTripsFromDB() {
		Connection myconn = makeConnection();
		Statement mystat = null;
		ResultSet myres = null;
		Trip trip;
		ArrayList<Trip> alltrips = new ArrayList<Trip>();
		String query = "SELECT * FROM TRIPS";
		try {
			mystat = myconn.createStatement();
			myres = mystat.executeQuery(query);
			while (myres.next()) {
				int driver_id = myres.getInt("driver_id");
				int trip_id = myres.getInt("trip_id");
				String source = myres.getString("source");
				String destination = myres.getString("destination");
				float price = myres.getFloat("price");
				boolean isExternal = myres.getBoolean("isexternal");
				boolean isRound = myres.getBoolean("isround");
				int numOfSeats = myres.getInt("NUMOFSEATS");
				int numOfStops = myres.getInt("NUMOFStops");
				String trip_time = myres.getString("trip_time");
				int vehicleType = myres.getInt("vehicle_type");
				trip = new Trip(trip_id, source, destination, driver_id, price, isExternal, isRound, numOfSeats,
						numOfStops, trip_time, vehicleType);
				alltrips.add(trip);
			}
			this.allTrips = alltrips;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection(myconn);
	}

}
