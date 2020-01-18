package logic;
import java.io.*;
import java.util.ArrayList;
import java.sql.*;

public abstract class Person {

	private int id;
	private String username;
	private String password;

	public Person(int id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	abstract public boolean login(int id, String password);

	public Connection makeConnection() {
		Connection myconn = null;
		String url = "jdbc:mysql://localhost:3306/busstationdb";
		String user = "root";
		String pass = "";
		try {
			myconn = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return myconn;
	}

	public void closeConnection(Connection myconn) {
		try {
			myconn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
