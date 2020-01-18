package logic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Driver extends Person implements Select {
	ArrayList<Trip> myTrips;
	private float Salary;

	public Driver(int id, String username, String password, float Salary) {
		super(id, username, password);
		this.Salary = Salary;
		// TODO Auto-generated constructor stub
	}

	public float getSalary() {
		return Salary;
	}

	public void setSalary(float salary) {
		Salary = salary;
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
				String query = "SELECT * FROM DRIVERS WHERE DRIVER_ID=" + id + " AND BINARY DRIVER_PASSWORD= BINARY '"
						+ password + "'";
				ResultSet myres = mystat.executeQuery(query);
				if (!myres.next()) {
					closeConnection(myconn);
					return false;
				} else {
					this.setId(myres.getInt("DRIVER_ID"));
					this.setUsername(myres.getString("DRIVER_USERNAME"));
					this.setPassword(myres.getString("DRIVER_PASSWORD"));
					this.setSalary(myres.getFloat("DRIVER_SALARY"));
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
		Trip trip;
		ArrayList<Trip> mytrips = new ArrayList<Trip>();
		try {
			Statement mystat = myconn.createStatement();
			String query = "SELECT * FROM TRIPS WHERE DRIVER_ID=" + this.getId();
			ResultSet myres = mystat.executeQuery(query);
			if (!myres.next()) {
				System.out.println("No Trips assigned to the driver !!");
				closeConnection(myconn);
				return false;
			}
			myres.previous();
			while (myres.next()) {
				int driver_id = this.getId();
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
				mytrips.add(trip);
			}
			this.myTrips = mytrips;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		closeConnection(myconn);
		return true;
	}

	public ArrayList<Trip> getMyTrips() {
		return myTrips;
	}

	public void setMyTrips(ArrayList<Trip> myTrips) {
		this.myTrips = myTrips;
	}

}
