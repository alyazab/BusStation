package application;


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
import logic.Manager;
import logic.ManagerTransporter;


public class employeeLogin2Controller {
	
	@FXML TextField idTFmanager;
	@FXML PasswordField kkk;
	@FXML 
	protected void display(ActionEvent event)
	{
		
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		Parent p;
			try {
				p = FXMLLoader.load(getClass().getResource("employee1.fxml"));
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
		Manager m = new Manager(0, null, null);
		boolean res = m.login(Integer.parseInt(idTFmanager.getText()), kkk.getText());
		if (res) {
				ManagerTransporter.setManager(m);
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		Parent p;
			try {
				p = FXMLLoader.load(getClass().getResource("managerPage.fxml"));
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
		else {
	    	  Alert wrong = new Alert(AlertType.ERROR);
				wrong.setTitle("Manager Account");
				wrong.setHeaderText(null);
				wrong.setContentText("Wrong Id or Password");
				wrong.showAndWait();
	      }

	}
}