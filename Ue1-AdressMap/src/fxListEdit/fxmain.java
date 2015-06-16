package fxListEdit;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class fxmain extends Application {
	//		// observableArrayList für TableView (Werden für FX Views benötigt um Listen zu übergeben)
	ObservableList<ObservableContactDetails> obserContactDetailsList = FXCollections.observableArrayList();
	
	
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("edit Lists GUI");
		BorderPane root = new BorderPane();
		
		//Button erzeugen
		Button btn = new Button("DRUCK");
		
		//Button listener
		btn.setOnAction(i -> printalles());
		
	
		// ListView und TableView erzeugen
		final ListView<ObservableContactDetails> listView = new ListView<ObservableContactDetails>();
		final TableView<ObservableContactDetails> tableView = new TableView<ObservableContactDetails>();
		
		// observableArrayList für TableView (Werden für FX Views benötigt um Listen zu übergeben)
		obserContactDetailsList.addAll(new ObservableContactDetails("Peter", "Pan", "Nimmerland 3"));
		obserContactDetailsList.addAll(new ObservableContactDetails("Sepp", "Blatter", "Korruptionsweg 900"));
		obserContactDetailsList.addAll(new ObservableContactDetails("Frodo", "Beutling", "Auenland 4"));

		// observableArrayList für die ListView mit allen Keys
	    ObservableList<ObservableContactDetails> observableContactDetails = FXCollections.observableArrayList();
	    
	    // Spalten für die TableView anlegen
        TableColumn<ObservableContactDetails, String> firstNameCol = new TableColumn<ObservableContactDetails,String>("Vorname");
        TableColumn<ObservableContactDetails, String> lastNameCol = new TableColumn<ObservableContactDetails,String>("Nachname");
        TableColumn<ObservableContactDetails, String> adressCol = new TableColumn<ObservableContactDetails,String>("Adresse");
        
        // Lambda zum füllen der Spalten
        firstNameCol.setCellValueFactory(e -> e.getValue().getVornameProperty());
        lastNameCol.setCellValueFactory( e -> e.getValue().getNameProperty());
        adressCol.setCellValueFactory( e -> e.getValue().getAdresseProperty());
        
        // Textfeld setzen für editierbarkeit
        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        adressCol.setCellFactory(TextFieldTableCell.forTableColumn());
        
        // Spalten der Tableview übergeben
        tableView.getColumns().addAll(firstNameCol,lastNameCol,adressCol);
        
        //Editable TableView
        tableView.setEditable(true);
        //tableView.setOn(a -> handleTableEdit(a));
        // Verhindert bei Übergröße das Anzeigen einer zusätzlichen Spalte, nur letzte Spalte wird vergrößert
        tableView.setColumnResizePolicy(tableView.CONSTRAINED_RESIZE_POLICY);
		
        // Zellwerte der ListView und der TableView mit unseren Daten füllen 
		//listView.setItems((ObservableList<ObservableContactDetails>) obser);
        //listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        // listView.setItems(obserContactDetailsList);
        //listView.setCellFactory(TextFieldListCell.forListView());
		tableView.setItems(obserContactDetailsList);		
		
		//listView.itemsProperty().bind(obserContactDetailsList);
		listView.setItems(obserContactDetailsList);
		listView.setEditable(true);
		//listView.setCellFactory();
		
		// ListView und TableView auf der BorderPane platzieren
		root.setLeft(listView);
		root.setCenter(tableView);
		root.setBottom(btn);
		// Scene erstellen und auf PrimaryStage anzeigen
		Scene scene = new Scene (root);
		primaryStage.setScene(scene);
		primaryStage.show();
		}
	
	private void printalles(){
		for (ObservableContactDetails i:obserContactDetailsList){
			System.out.println("Vorname: " + i.getVornameProperty().getValue() + "\t Nachname: " + i.getNameProperty().getValue() + "\t Adresse: " + i.getAdresseProperty().getValue());
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
