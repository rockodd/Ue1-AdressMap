package application;

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
import javafx.scene.control.cell.TextFieldTableCell;

public class Control {

	ObservableList<ObservableContactDetails>		obserContactDetailsList;
	private ListView<ObservableContactDetails>	listView;
	private TableView<ObservableContactDetails>	tableView;
	private Button											btnprint;
	private Button											btnadd;

	public Control(ListView<ObservableContactDetails> listViewIN,
			TableView<ObservableContactDetails> tableViewIN, Button btnprintIN,
			Button btnaddIN) {
		// �bergabeobjekte �bernehmen
		this.listView = listViewIN;
		this.tableView = tableViewIN;
		this.btnprint = btnprintIN;
		this.btnadd = btnaddIN;

		obserContactDetailsList = FXCollections
				.observableArrayList(ObservableContactDetails
						.getObsContactDetails());

		// Button listener
		btnprint.setOnAction(i -> printalles());
		btnadd.setOnAction(i -> addcontact());

		// observableArrayList f�r TableView (Werden f�r FX Views ben�tigt um
		// Listen zu �bergeben)
		obserContactDetailsList.addAll(new ObservableContactDetails("Peter",
				"Panner", "Nimmerland 3"));
		obserContactDetailsList.addAll(new ObservableContactDetails("Sepp",
				"Blatter", "Korruptionsweg 900"));
		obserContactDetailsList.addAll(new ObservableContactDetails("Frodo",
				"Beutling", "Auenland 4"));

		// observableArrayList f�r die ListView mit allen Keys
		// ObservableList<ObservableContactDetails> observableContactDetails =
		// FXCollections.observableArrayList();

		// Spalten f�r die TableView anlegen
		TableColumn<ObservableContactDetails, String> firstNameCol = new TableColumn<ObservableContactDetails, String>(
				"Vorname");
		TableColumn<ObservableContactDetails, String> lastNameCol = new TableColumn<ObservableContactDetails, String>(
				"Nachname");
		TableColumn<ObservableContactDetails, String> adressCol = new TableColumn<ObservableContactDetails, String>(
				"Adresse");

		// Lambda zum f�llen der Spalten
		firstNameCol.setCellValueFactory(e -> e.getValue().getVornameProperty());
		lastNameCol.setCellValueFactory(e -> e.getValue().getNameProperty());
		adressCol.setCellValueFactory(e -> e.getValue().getAdresseProperty());

		// Textfelder setzen f�r editierbarkeit
		firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		adressCol.setCellFactory(TextFieldTableCell.forTableColumn());

		// Spalten der Tableview �bergeben
		tableView.getColumns().addAll(firstNameCol, lastNameCol, adressCol);

		// Editable TableView
		tableView.setEditable(true);

		// Verhindert bei �bergr��e das Anzeigen einer zus�tzlichen Spalte, nur
		// letzte Spalte wird vergr��ert
		tableView.setColumnResizePolicy(tableView.CONSTRAINED_RESIZE_POLICY);

		// der TableView und ListView die Liste mit unseren ObservableContactDetails �bergeben
		tableView.setItems(obserContactDetailsList);
		listView.setItems(obserContactDetailsList);
		listView.setEditable(true);

		// ruft eigene listCellMethode auf
		listView.setCellFactory(p -> new MyListCell());

	}

	// Methode um neuen leeren Kontakt anzulegen
	private Object addcontact() {
		ObservableContactDetails newContact = new ObservableContactDetails(
				"Vorname", "Nachname", "Adresse");
		obserContactDetailsList.add(newContact);
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

	// eigene ListCell f�r die ListView, da die ListVIew mit selbst definierten
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
		// Zelle �ndert auch beim erstmaligen beschreiben

		@Override
		protected void updateItem(ObservableContactDetails t, boolean bln) {
			super.updateItem(t, bln);// f�hrt die Standartaufgaben der Methode
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

}
