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
import logic.Manager;
import logic.ManagerTransporter;
import logic.Trip;

public class addTripMang {boolean externalCheck ;
boolean roundCheck ;
int stopCheck;
int busCheck;
String sour;
String Des;
String time;
String price;
String seats;
String driveid ;
@FXML TextField sourceTF1;
@FXML TextField destinationTF1;
@FXML TextField priceTF1;
@FXML TextField diTF1;
@FXML TextField seatsTF1;
@FXML TextField timeTF1;
@FXML RadioButton internalRB1;
@FXML RadioButton externalRB1;
@FXML RadioButton onewayRB1;
@FXML RadioButton roundRB1;
@FXML RadioButton nonstopsRB1;
@FXML RadioButton onestopRB1;
@FXML RadioButton manystopsRB1;
@FXML RadioButton busRB1;
@FXML RadioButton miniRB1;
@FXML RadioButton limRB1;
@FXML
public void radioSelect(ActionEvent event) {
	
	if (internalRB1.isSelected()) {
		externalCheck=false;
	}
	 if(externalRB1.isSelected()) {
		externalCheck=true;
	}
}
@FXML
public void radioSelect1(ActionEvent event) {
	if(onewayRB1.isSelected()) {
		roundCheck=false;
	}
	 if(roundRB1.isSelected()) {
		roundCheck=true;
	}
}
@FXML TextField stops;

@FXML
public void radioSelect3(ActionEvent event) {
	if(busRB1.isSelected()) {
		busCheck=0;
	}
	if(miniRB1.isSelected()) {
		busCheck=1;
	}
    if(limRB1.isSelected()) {
		busCheck=2;
	}
}
@FXML 
protected void display(ActionEvent event)
{
	
	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
	Parent p;
		try {
			p = FXMLLoader.load(getClass().getResource("managerPage.fxml"));
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
	Manager m = ManagerTransporter.getManager();
	driveid = diTF1.getText();
	price =priceTF1.getText();
	seats= seatsTF1.getText();
	time = timeTF1.getText();
	sour=sourceTF1.getText();
	Des=destinationTF1.getText();
	int stopss = Integer.parseInt(stops.getText());
	Trip newTrip = new Trip(0,sour,Des,Integer.parseInt(driveid),Float.parseFloat(price),externalCheck,roundCheck,Integer.parseInt(seats),stopss,time,busCheck);
	boolean res = m.addTrip(newTrip);
	if(res) {
		 Alert wrong = new Alert(AlertType.INFORMATION);
			wrong.setTitle("Manager Account");
			wrong.setHeaderText(null);
			wrong.setContentText("Added Successfully");
			wrong.showAndWait();
	}
	else {
		 Alert wrong = new Alert(AlertType.ERROR);
			wrong.setTitle("Manager Account");
			wrong.setHeaderText(null);
			wrong.setContentText("Driver doesnot exist");
			wrong.showAndWait();
	}
			Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
			Parent p;
				try {
					p = FXMLLoader.load(getClass().getResource("managerPage.fxml"));
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



