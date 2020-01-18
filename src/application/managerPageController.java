package application;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class managerPageController {
	
	@FXML 
	protected void display(ActionEvent event)
	{
		
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		Parent p;
			try {
				p = FXMLLoader.load(getClass().getResource("employeelogin2.fxml"));
				Scene scene = new Scene(p);
				stage.setScene(scene);
				stage.setTitle("Bus Station Login");
				stage.setResizable(false);
				stage.show();
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
	@FXML 
	protected void display1(ActionEvent event)
	{
		
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		Parent p;
			try {
				p = FXMLLoader.load(getClass().getResource("addTripMang.fxml"));
				Scene scene = new Scene(p);
				stage.setScene(scene);
				stage.setTitle("Bus Station Login");
				stage.setResizable(false);
				stage.show();
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
	@FXML 
	protected void display2(ActionEvent event)
	{
		
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		Parent p;
			try {
				p = FXMLLoader.load(getClass().getResource("removeTripMang.fxml"));
				Scene scene = new Scene(p);
				stage.setScene(scene);
				stage.setTitle("Bus Station Login");
				stage.setResizable(false);
				stage.show();
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
	@FXML 
	protected void display3(ActionEvent event)
	{
		
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		Parent p;
			try {
				p = FXMLLoader.load(getClass().getResource("editDriverMang.fxml"));
				Scene scene = new Scene(p);
				stage.setScene(scene);
				stage.setTitle("Bus Station Login");
				stage.setResizable(false);
				stage.show();
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
}