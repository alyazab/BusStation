package application;

import java.io.IOException;
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
import logic.Trip;

public class removeTripClientController implements Initializable {
	Customer c = CustomerTransporter.getCustomer();
	int i = 0;
	ArrayList<Trip> Custri = new ArrayList<Trip>();
	@FXML
	private ListView<String> ll;

	@FXML
	protected void lala() {

		boolean r = c.getMyTripsFromDB();
		if (r) {

			Custri = c.getMyTrips();
			int size = Custri.size();
			ObservableList<String> o = FXCollections.observableArrayList();
			while (size > 0) {

				String from = Custri.get(i).getSource();
				String to = Custri.get(i).getDestination();
				float price = Custri.get(i).getPrice();
				String Type;
				String Ticket;
				if (Custri.get(i).isExternal() == false)
					Type = "Internal";
				else
					Type = "External";
				if (Custri.get(i).isRound() == false)
					Ticket = "One Way";
				else
					Ticket = "Round";
				String Vehicle = c.getVehicleType(Custri.get(i).getVehicleType());
				String data = from + ' ' + ' ' + ' ' + to + ' ' + ' ' + ' ' + String.valueOf(price) + ' ' + ' ' + ' '
						+ Vehicle + ' ' + ' ' + ' ' + Type + ' ' + ' ' + ' ' + Ticket;
				o.add(data);
				i++;
				size--;
			}
			ll.setItems(o);
			
		} else {
			Alert wrong = new Alert(AlertType.ERROR);
			wrong.setTitle("ERROR");
			wrong.setHeaderText(null);
			wrong.setContentText("NO TRIPS");
			wrong.showAndWait();
		}
		

	}

	@FXML
	private void lala2() {
		int a = ll.getSelectionModel().getSelectedIndex();
		int id = Custri.get(a).getTripId();
		boolean res =c.removeTrip(id);
		if(res) {
				Alert wrong = new Alert(AlertType.INFORMATION);
				wrong.setTitle("ERROR");
				wrong.setHeaderText(null);
				wrong.setContentText("Removed Successfully");
				wrong.showAndWait();
			
		}
		else {
				Alert wrong = new Alert(AlertType.ERROR);
				wrong.setTitle("ERROR");
				wrong.setHeaderText(null);
				wrong.setContentText("Unsuccessful removal");
				wrong.showAndWait();
			
		}
		
		
	}

	@FXML
	protected void display(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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

	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {
		lala();
	}

}
