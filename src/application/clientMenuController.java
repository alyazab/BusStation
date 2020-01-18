package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.print.DocFlavor.URL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import logic.Customer;

public class clientMenuController {
	@FXML 
	protected void display(ActionEvent event)
	{
		
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		Parent p;
			try {
				p = FXMLLoader.load(getClass().getResource("loginPageClient.fxml"));
				Scene scene = new Scene(p);
				stage.setScene(scene);
				stage.setTitle("Bus Station login");
				stage.setResizable(false);
				stage.show();
			} catch (IOException e) {
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
				p = FXMLLoader.load(getClass().getResource("myTripsClient.fxml"));
				Scene scene = new Scene(p);
				stage.setScene(scene);
				stage.setTitle("Bus Station login");
				stage.setResizable(false);
				stage.show();
			} catch (IOException e) {
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
				p = FXMLLoader.load(getClass().getResource("removeTripClient.fxml"));
				Scene scene = new Scene(p);
				stage.setScene(scene);
				stage.setTitle("Bus Station login");
				stage.setResizable(false);
				stage.show();
			} catch (IOException e) {
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
				p = FXMLLoader.load(getClass().getResource("addTripClient.fxml"));
				Scene scene = new Scene(p);
				stage.setScene(scene);
				stage.setTitle("Bus Station login");
				stage.setResizable(false);
				stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
}