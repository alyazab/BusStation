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
import logic.Customer;
import logic.CustomerTransporter;

public class loginPageClientController {
	@FXML TextField idTF;
	@FXML PasswordField loginPFcl;
	@FXML 
	protected void display(ActionEvent event)
	{
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		Parent p;
			try {
				p = FXMLLoader.load(getClass().getResource("welcomePage.fxml"));
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
	      Customer c = new Customer(0,null ,null,0);
	      int id = Integer.parseInt(idTF.getText());
	      boolean res = c.login(id, loginPFcl.getText());
	      if(res) {
		System.out.println(c.getUsername());
		CustomerTransporter.setCustomer(c);
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		Parent p;
			try {
				p = FXMLLoader.load(getClass().getResource("ClientMenu.fxml"));
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
	      else {
	    	  Alert wrong = new Alert(AlertType.ERROR);
				wrong.setTitle("Personal Account");
				wrong.setHeaderText(null);
				wrong.setContentText("Wrong Password");
				wrong.showAndWait();
	      }


	}
}
