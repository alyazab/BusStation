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
import logic.Manager;
import logic.ManagerTransporter;
import logic.Trip;

public class removeTripMangController implements Initializable {
	Manager m = ManagerTransporter.getManager();
	
	int i = 0;
	ArrayList<Trip> Custri = new ArrayList<Trip>();
	@FXML
	private ListView<String> ll;

	@FXML
	protected void lala() {

		m.getAllTripsFromDB();
			Custri = m.getAllTrips();
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
				String Vehicle = m.getVehicleType(Custri.get(i).getVehicleType());
				String data = from + ' ' + ' ' + ' ' + to + ' ' + ' ' + ' ' + String.valueOf(price) + ' ' + ' ' + ' '
						+ Vehicle + ' ' + ' ' + ' ' + Type + ' ' + ' ' + ' ' + Ticket;
				o.add(data);
				i++;
				size--;
			}
			ll.setItems(o);
			
		
		

	}

	@FXML
	private void lala2() {
		int a = ll.getSelectionModel().getSelectedIndex();
		int id = Custri.get(a).getTripId();
		boolean res =m.removeTrip(id);
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
	protected void display1(ActionEvent event) {
		lala2();
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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

	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {
		lala();
	}

}

