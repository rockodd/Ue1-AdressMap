
package fxGui;


import java.awt.event.KeyListener;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
		
		// observableArrayList erstellen, werden für TableView
	    ObservableList <ContactDetails> ContactDetailsList = FXCollections.observableArrayList(meinAdressbuch.allDetails());
		
		// observableArrayList erstellen, werden für ListView benötigt und werden mit Daten gefüllt
	    ObservableList keyList = FXCollections.observableArrayList(meinAdressbuch.allKeys());
	    
	    // Spalten für die TableView anlegen
        TableColumn firstNameCol = new TableColumn("Vorname");
        TableColumn lastNameCol = new TableColumn("Nachname");
        TableColumn adressCol = new TableColumn("Adresse");
        firstNameCol.setCellValueFactory( new PropertyValueFactory<ContactDetails, String>("vorname"));
        lastNameCol.setCellValueFactory( new PropertyValueFactory<ContactDetails, String>("name"));
        adressCol.setCellValueFactory( new PropertyValueFactory<ContactDetails, String>("adresse"));
        
        tableView.getColumns().addAll(firstNameCol,lastNameCol,adressCol);
        // Verhindert bei Übergröße das anzeigen einer zusätzlichen Spalte, nur letzte Spalte wird vergrößert
        tableView.setColumnResizePolicy(tableView.CONSTRAINED_RESIZE_POLICY);
        

		
		
		
		listView.setItems(keyList);
		tableView.setItems(ContactDetailsList);
		
		
		
		
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
