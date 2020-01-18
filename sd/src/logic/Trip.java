package logic;
public class Trip {
	private int trip_id;
	private String source;
	private String destination;
	private int driverId;
	private float price;
	private boolean isExternal;
	private boolean isRound;
	private int numOfSeats;
	private int numOfStops;
	private String time;
	private int vehicleType;

	public Trip(int trip_id, String source, String destination, int driverId, float price, boolean isExternal,
			boolean isRound, int numOfSeats, int numOfStops, String time, int vehicleType) {
		super();
		this.trip_id = trip_id;
		this.source = source;
		this.destination = destination;
		this.driverId = driverId;
		this.price = price;
		this.isExternal = isExternal;
		this.isRound = isRound;
		this.numOfSeats = numOfSeats;
		this.numOfStops = numOfStops;
		this.time = time;
		this.vehicleType = vehicleType;
	}

	public boolean isRound() {
		return isRound;
	}

	public void setRound(boolean isRound) {
		this.isRound = isRound;
	}

	public int getNumOfSeats() {
		return numOfSeats;
	}

	public void setNumOfSeats(int numOfSeats) {
		this.numOfSeats = numOfSeats;
	}

	public int getNumOfStops() {
		return numOfStops;
	}

	public void setNumOfStops(int numOfStops) {
		this.numOfStops = numOfStops;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getTripId() {
		return trip_id;
	}

	public void setTripId(int id) {
		this.trip_id = trip_id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public boolean isExternal() {
		return isExternal;
	}

	public int getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(int vehicleType) {
		this.vehicleType = vehicleType;
	}

	public void setExternal(boolean isExternal) {
		this.isExternal = isExternal;
	}

}
