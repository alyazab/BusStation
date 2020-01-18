package application;




import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class employee1Controller {
	
	@FXML 
	protected void display1(ActionEvent event)
	{
		
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		Parent p;
			try {
				p = FXMLLoader.load(getClass().getResource("employeeLogin1.fxml"));
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
			protected void display(ActionEvent event)
			{
				
				Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
				Parent p;
					try {
						p = FXMLLoader.load(getClass().getResource("welcomePage.fxml"));
						Scene scene = new Scene(p);
						stage.setScene(scene);
						stage.setTitle("Bus Station");
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
						p = FXMLLoader.load(getClass().getResource("employeeLogin2.fxml"));
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