package application;

import java.io.IOException;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.control.cell.TextFieldTableCell;
import CsvAdressbook.*;

public class Control {

	ObservableList<ObservableContactDetails>		obserContactDetailsList;
	private ListView<ObservableContactDetails>	listView;
	private TableView<ObservableContactDetails>	tableView;
	private Button											btnprint;
	private Button											btnadd;
	private Button											btnsave;
	private Button											btnload;
	 													

	public Control(ListView<ObservableContactDetails> listViewIN,
			TableView<ObservableContactDetails> tableViewIN, Button btnprintIN,
			Button btnaddIN, Button btnsavIN, Button btnloadIN) {
		// Übergabeobjekte übernehmen
		this.listView = listViewIN;
		this.tableView = tableViewIN;
		this.btnprint = btnprintIN;
		this.btnadd = btnaddIN;
		this.btnsave = btnsavIN;
		this.btnload = btnloadIN;

//		obserContactDetailsList = FXCollections
//				.observableArrayList(ObservableContactDetails
//						.getObsContactDetails());

		obserContactDetailsList = FXCollections
				.observableArrayList();
		
		// Button listener

		btnprint.setOnAction(i -> printalles());
		btnadd.setOnAction(i -> addcontact());
		btnsave.setOnAction(i -> writeFile());
		btnload.setOnAction(i -> loadFile());
		

		// observableArrayList für TableView (Werden für FX Views benötigt um
		// Listen zu übergeben)
		obserContactDetailsList.addAll(new ObservableContactDetails("Peter",
				"Panner", "Nimmerland 3"));
		obserContactDetailsList.addAll(new ObservableContactDetails("Sepp",
				"Blatter", "Korruptionsweg 900"));
		obserContactDetailsList.addAll(new ObservableContactDetails("Frodo",
				"Beutling", "Auenland 4"));

		// observableArrayList für die ListView mit allen Keys
		// ObservableList<ObservableContactDetails> observableContactDetails =
		// FXCollections.observableArrayList();

		// Spalten für die TableView anlegen
		TableColumn<ObservableContactDetails, String> firstNameCol = new TableColumn<ObservableContactDetails, String>(
				"Vorname");
		TableColumn<ObservableContactDetails, String> lastNameCol = new TableColumn<ObservableContactDetails, String>(
				"Nachname");
		TableColumn<ObservableContactDetails, String> adressCol = new TableColumn<ObservableContactDetails, String>(
				"Adresse");

		// Lambda zum füllen der Spalten
		firstNameCol.setCellValueFactory(e -> e.getValue().getVornameProperty());
		lastNameCol.setCellValueFactory(e -> e.getValue().getNameProperty());
		adressCol.setCellValueFactory(e -> e.getValue().getAdresseProperty());

		// Textfelder setzen für editierbarkeit
		firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		adressCol.setCellFactory(TextFieldTableCell.forTableColumn());

		// Spalten der Tableview übergeben
		tableView.getColumns().addAll(firstNameCol, lastNameCol, adressCol);

		// Editable TableView / ListView
		tableView.setEditable(true);
		listView.setEditable(true);

		// Verhindert bei Übergröße das Anzeigen einer zusätzlichen Spalte, nur
		// letzte Spalte wird vergrößert
		tableView.setColumnResizePolicy(tableView.CONSTRAINED_RESIZE_POLICY);

		// der TableView und ListView die Liste mit unseren ObservableContactDetails übergeben
		tableView.itemsProperty().setValue(obserContactDetailsList);
		listView.itemsProperty().setValue(obserContactDetailsList);
		

		// ruft eigene listCellMethode auf
		//listView.setCellFactory(TextFieldListCell.forListView());

	}

	private Object loadFile() {
		List<ObservableContactDetails> obserlist = CSVContactsReader.readEntityList("test",";");
		obserContactDetailsList.clear();
		for (ObservableContactDetails contact : obserlist) obserContactDetailsList.addAll(contact);
		return null;
	}

	private Object writeFile() {
		printalles();
		List<ObservableContactDetails> obserlist = obserContactDetailsList;
		try {
			CSVContactsWriter.writeEntityList(obserlist,"test",";");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// Methode um neuen leeren Kontakt anzulegen
	private Object addcontact() {
		ObservableContactDetails newContact = new ObservableContactDetails(
				"Vorname", "Nachname", "Adresse");
		obserContactDetailsList.addAll(newContact);
		return null;
	}

	// Methode um alles aus der Observable Contact Details Liste auf der Konsole
	// auszugeben
	private void printalles() {
		for (ObservableContactDetails i : obserContactDetailsList) {
			System.out.println("Vorname: " + i.getVornameProperty().getValue()
					+ "\t Nachname: " + i.getNameProperty().getValue()
					+ "\t Adresse: " + i.getAdresseProperty().getValue());
		}
		}
	}



/* ################ besser mit .. (ilse folie zusammenfassung).. arbeiten
	// eigene ListCell für die ListView, da die ListVIew mit selbst definierten
	// Objekten nicht umgehen kann
	class MyListCell extends ListCell<ObservableContactDetails> {
		// Das Textfeld das an der Stelle des Eintrags der editiert werden soll
		// angezeigt wird
		private TextField			textField		= new TextField();
		private StringProperty	nameProperty	= new SimpleStringProperty();

		public MyListCell() {
			// Bindet die Sichtbarkeit des EditTextfeldes an die editing
			// Property der Zelle, also wenn die Zelle im bearbeiten Modus ist,
			// wird das Textfeld gezeigt sonst nicht
			this.textField.visibleProperty().bind(this.editingProperty());
			this.textField
					.setOnAction(a -> commitEdit(new ObservableContactDetails(this
							.getItem().getVorname(), textField.getText(), this
							.getItem().getAdresse())));
			// Eine Zelle besteht aus seinem Text und einer Graphic, die jede
			// Form von Node sein kann, in unserem Fall eben das EditTextfeld
			this.setGraphic(this.textField);
			// Ausrichtung des Textfeldes 
			this.setContentDisplay(ContentDisplay.RIGHT);
		}

		// Diese Methode wird immer dann aufgerufen wenn sich der Inhalt der
		// Zelle ändert auch beim erstmaligen beschreiben

		@Override
		protected void updateItem(ObservableContactDetails t, boolean bln) {
			super.updateItem(t, bln);// führt die Standartaufgaben der Methode
			// durch
			// Wenn die Zelle nicht null ist, wobei get Item das
			// ObservableContactDetails-Object aus unserer Liste darstellt
			if (this.getItem() != null) {
				// Bindet unseren NachNamen 
				this.nameProperty.bind(this.getItem().getNameProperty());
				// schreibt den letzten Eintrag in das Textfeld
				this.textField.setText(nameProperty.get());
				// legt den Anzeigetext fest
				setText(nameProperty.get());
			}
		}
		
		}
		*/

