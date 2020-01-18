package application;
import java.sql.SQLException;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import logic.Driver;
import logic.DriverTransporter;


public class employeeLogin1Controller {
	@FXML TextField idTFdriver;
	@FXML PasswordField ppp;
	
	
	
	@FXML
	protected void display(ActionEvent event)
	{
		
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		Parent p;
			try {
				p = FXMLLoader.load(getClass().getResource("employee1.fxml"));
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
			protected void display1 (ActionEvent event)
			{
				
					Driver d = new Driver(0, null, null, 0);
					boolean res = d.login(Integer.parseInt(idTFdriver.getText()), ppp.getText());
					if(res) {
							DriverTransporter.setDriver(d);
				
				Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
				Parent p;
					try {
						p = FXMLLoader.load(getClass().getResource("driverProfile.fxml"));
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
					else {
				    	  Alert wrong = new Alert(AlertType.ERROR);
							wrong.setTitle("Personal Account");
							wrong.setHeaderText(null);
							wrong.setContentText("Wrong Id or Password");
							wrong.showAndWait();
				      }


	}
}
