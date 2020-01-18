package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import logic.Customer;
import logic.CustomerTransporter;
import logic.Trip;

public class addTripClientController {
	boolean externalCheck ;
	boolean roundCheck ;
	int stopCheck;
	int busCheck;
	String sour;
	String Des;
	@FXML TextField sourceTF;
	@FXML TextField destinationTF;
	@FXML RadioButton internalRB;
	@FXML RadioButton externalRB;
	@FXML RadioButton onewayRB;
	@FXML RadioButton roundRB;
	@FXML RadioButton nonstopsRB;
	@FXML RadioButton onestopRB;
	@FXML RadioButton manystopsRB;
	@FXML RadioButton busRB;
	@FXML RadioButton miniRB;
	@FXML RadioButton limRB;
	@FXML
	public void radioSelect(ActionEvent event) {
		
		if (internalRB.isSelected()) {
			externalCheck=false;
		}
		 if(externalRB.isSelected()) {
			externalCheck=true;
		}
	}
	@FXML
	public void radioSelect1(ActionEvent event) {
		if(onewayRB.isSelected()) {
			roundCheck=false;
		}
		 if(roundRB.isSelected()) {
			roundCheck=true;
		}
	}
	@FXML
	public void radioSelect2(ActionEvent event) {
		 if(nonstopsRB.isSelected()) {
			stopCheck=0;
		}
		if(onestopRB.isSelected()) {
			stopCheck=1;
		}
	
		 if(manystopsRB.isSelected()) {
			stopCheck=2;
		}
	}
	@FXML
	public void radioSelect3(ActionEvent event) {
		if(busRB.isSelected()) {
			busCheck=0;
		}
		if(miniRB.isSelected()) {
			busCheck=1;
		}
	    if(limRB.isSelected()) {
			busCheck=2;
		}
	}
	@FXML 
	protected void display(ActionEvent event)
	{
		
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		Parent p;
			try {
				p = FXMLLoader.load(getClass().getResource("clientMenu.fxml"));
				Scene scene = new Scene(p);
				stage.setScene(scene);
				stage.setTitle("Bus Station profile");
				stage.setResizable(false);
				stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
}
	@FXML 
	protected void display1(ActionEvent event){
		Customer c = CustomerTransporter.getCustomer();
		sour=sourceTF.getText();
		Des=destinationTF.getText();
		Trip requestedTrip = new Trip(0,sour,Des,0,0,externalCheck,roundCheck,0,stopCheck,null,busCheck);
		boolean res = c.getAllTripsFromDB(requestedTrip);
		if(res) {
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		Parent p;
			try {
				p = FXMLLoader.load(getClass().getResource("addListClient.fxml"));
				Scene scene = new Scene(p);
				stage.setScene(scene);
				stage.setTitle("Bus Station profile");
				stage.setResizable(false);
				stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else 
		{
			 Alert wrong = new Alert(AlertType.ERROR);
				wrong.setTitle("Personal Account");
				wrong.setHeaderText(null);
				wrong.setContentText("NO TRIPS AVAILABLE");
				wrong.showAndWait();
				Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
				Parent p;
					try {
						p = FXMLLoader.load(getClass().getResource("clientMenu.fxml"));
						Scene scene = new Scene(p);
						stage.setScene(scene);
						stage.setTitle("Bus Station profile");
						stage.setResizable(false);
						stage.show();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
	      }

		}
}

