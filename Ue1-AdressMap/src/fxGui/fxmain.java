
package fxGui;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import AdressBook.*;

public class fxmain extends Application {
	private static AdressBookDefault meinAdressbuch = new AdressBookDefault();
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Adressbuch GUI");
		BorderPane root = new BorderPane();
		
		final ListView listView = new ListView();
		final TableView tableView = new TableView();
		
		// observableArrayList erstellen, werden für ListView benötigt und werden mit Daten gefüllt
	    ObservableList keys = FXCollections.observableArrayList(meinAdressbuch.allKeys());
	    
		
		
		
		
		listView.setItems(keys);
		tableView.setItems(keys);
		
		
		
		
		root.setLeft(listView);
		root.setCenter(tableView);
		
		Scene scene = new Scene (root);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
