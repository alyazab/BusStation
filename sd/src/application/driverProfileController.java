package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import logic.Customer;
import logic.CustomerTransporter;
import logic.Driver;
import logic.DriverTransporter;
import logic.Trip;

public class driverProfileController implements Initializable {
	ArrayList<Trip> Custri = new ArrayList<Trip>();
	@FXML
	private ListView<String> ll;
	@FXML
	Driver d = DriverTransporter.getDriver();
	boolean r = d.getMyTripsFromDB();
		protected void lala ()
		{
			
			if(r) {

				Custri =d.getMyTrips();
				int size = Custri.size();
				ObservableList<String> o=FXCollections.observableArrayList();
				int i=0;
				while (size>0)
				{
					String from = Custri.get(i).getSource();
					String to = Custri.get(i).getDestination();
					float price = Custri.get(i).getPrice();
					String Type;
					String Ticket;
					if(Custri.get(i).isExternal()==false)
						Type = "Internal";
					else
						Type = "External";
					if(Custri.get(i).isRound()==false)
						Ticket = "One Way";
					else
						Ticket = "Round";
					String Vehicle = d.getVehicleType(Custri.get(i).getVehicleType());
					String data = from+' '+' '+' '+to+' '+' '+' '+String.valueOf(price)+' '+' '+' '+Vehicle+' '+' '+' '+Type+' '+' '+' '+Ticket;
					o.add(data);
				    i++;
				    size--;
			}
				ll.setItems(o);
			}
			else {
				Alert wrong = new Alert(AlertType.ERROR);
				wrong.setTitle("ERROR");
				wrong.setHeaderText(null);
				wrong.setContentText("NO TRIPS");
				wrong.showAndWait();
			} 
	
			}

      
		
	@FXML 
	protected void display(ActionEvent event)
	{
		 Alert wrong = new Alert(AlertType.INFORMATION);
			wrong.setTitle("Personal Account");
			wrong.setHeaderText(null);
			wrong.setContentText("Thank You For Your Visit Have a Nice Day!!");
			wrong.showAndWait();
		
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
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		lala();
	}
}
