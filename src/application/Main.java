package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import logic.Customer;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	private Stage primaryStage;
	@Override
	public void start(Stage primaryStage) throws Exception{
		this.primaryStage=primaryStage;
		showPrimaryPage();
		//showCustomerLoginPage();
	}
	public void showPrimaryPage() throws IOException {
		Parent mainViewRoot =  FXMLLoader.load(getClass().getResource("welcomePage.fxml"));
		Scene scene = new Scene(mainViewRoot);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Bus Station");
		primaryStage.setResizable(false);
		primaryStage.show();
	}

public static void main(String[] args) {
// TODO Auto-generated method stub
      launch(args);
}

}

