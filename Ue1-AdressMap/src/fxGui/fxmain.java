package fxGui;

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
		
		// ListView und TableView erzeugen
		final ListView listView = new ListView();
		final TableView tableView = new TableView();
		
		// observableArrayList f�r TableView (Werden f�r FX Views ben�tigt um Listen zu �bergeben)
	    ObservableList <ContactDetails> ContactDetailsList = FXCollections.observableArrayList(meinAdressbuch.allDetails());
		
		// observableArrayList f�r die ListView mit allen Keys
	    ObservableList keyList = FXCollections.observableArrayList(meinAdressbuch.allKeys());
	    
	    // Spalten f�r die TableView anlegen
        TableColumn firstNameCol = new TableColumn("Vorname");
        TableColumn lastNameCol = new TableColumn("Nachname");
        TableColumn adressCol = new TableColumn("Adresse");
        firstNameCol.setCellValueFactory( new PropertyValueFactory<ContactDetails, String>("vorname"));
        lastNameCol.setCellValueFactory( new PropertyValueFactory<ContactDetails, String>("name"));
        adressCol.setCellValueFactory( new PropertyValueFactory<ContactDetails, String>("adresse"));
        // Spalten der Tableview �bergeben
        tableView.getColumns().addAll(firstNameCol,lastNameCol,adressCol);

        // Verhindert bei �bergr��e das Anzeigen einer zus�tzlichen Spalte, nur letzte Spalte wird vergr��ert
        tableView.setColumnResizePolicy(tableView.CONSTRAINED_RESIZE_POLICY);
		
        // Zellwerte der ListView und der TableView mit unseren Daten f�llen 
		listView.setItems(keyList);
		tableView.setItems(ContactDetailsList);		
		
		// ListView und TableView auf der BorderPane platzieren
		root.setLeft(listView);
		root.setCenter(tableView);
		// Scene erstellen und auf PrimaryStage anzeigen
		Scene scene = new Scene (root);
		primaryStage.setScene(scene);
		primaryStage.show();
		}
	
	public static void main(String[] args) {
		launch(args);
	}
}
