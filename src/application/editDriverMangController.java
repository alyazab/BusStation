package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import logic.Manager;
import logic.ManagerTransporter;

public class editDriverMangController {
	@FXML TextField usTF;
	@FXML TextField passTF;
	@FXML TextField salTF;
	@FXML 
	protected void display(ActionEvent event)
	{
		
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
	@FXML 
	protected void display1(ActionEvent event)
	{
		
		String un = usTF.getText();
		String pass = passTF.getText();
		float Salary = Float.parseFloat(salTF.getText());
		Manager m = ManagerTransporter.getManager();
		m.addDriver(un, pass, Salary);
		Alert wrong = new Alert(AlertType.INFORMATION);
		wrong.setTitle("Manager Account");
		wrong.setHeaderText(null);
		wrong.setContentText("Added Successfully");
		wrong.showAndWait();
		
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
}
