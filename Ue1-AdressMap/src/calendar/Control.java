package calendar;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
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
import CsvTerminkalender.CSVTerminReader;
import CsvTerminkalender.CSVTerminWriter;

public class Control {

	ObservableList<Appointment>		obserAppointmentList;
	private ListView<Appointment>		listView;
	private TableView<Appointment>	tableView;
	private Button							btnprint;
	private Button							btnadd;
	private Button							btnsave;
	private Button							btnload;

	public Control(ListView<Appointment> listViewIN,
			TableView<Appointment> tableViewIN, Button btnprintIN,
			Button btnaddIN, Button btnsavIN, Button btnloadIN) {
		// �bergabeobjekte �bernehmen
		this.listView = listViewIN;
		this.tableView = tableViewIN;
		this.btnprint = btnprintIN;
		this.btnadd = btnaddIN;
		this.btnsave = btnsavIN;
		this.btnload = btnloadIN;

		//		obserContactDetailsList = FXCollections
		//				.observableArrayList(ObservableContactDetails
		//						.getObsContactDetails());

		obserAppointmentList = FXCollections.observableArrayList();

		// Button listener

		btnprint.setOnAction(i -> printalles());
		btnadd.setOnAction(i -> addcontact());
		btnsave.setOnAction(i -> writeFile());
		btnload.setOnAction(i -> loadFile());

		// observableArrayList f�r TableView (Werden f�r FX Views ben�tigt um
		// Listen zu �bergeben)
		obserAppointmentList.addAll(new Appointment("Kat 1", LocalDate.of(2015,
				05, 29), LocalTime.of(10, 20), LocalTime.of(12, 20), "Termin1",
				"gleicher Tag"));
		obserAppointmentList.addAll(new Appointment("Kat 2", LocalDate.of(2015,
				05, 29), LocalTime.of(10, 20), LocalTime.of(14, 20), "Termin2",
				"gleicher Tag"));
		obserAppointmentList.addAll(new Appointment("Kat 2", LocalDate.of(2015,
				07, 01), LocalTime.of(9, 00), LocalTime.of(14, 00), "Termin3",
				"andere Tag"));

		//		// Spalten f�r die TableView anlegen
		//		TableColumn<Appointment, String> katCol = new TableColumn<Appointment, String>("Vorname");
		//		TableColumn<ObservableContactDetails, String> lastNameCol = new TableColumn<ObservableContactDetails, String>(
		//				"Nachname");
		//		TableColumn<ObservableContactDetails, String> adressCol = new TableColumn<ObservableContactDetails, String>(
		//				"Adresse");
		//
		//		// Lambda zum f�llen der Spalten
		//		firstNameCol.setCellValueFactory(e -> e.getValue().getVornameProperty());
		//		lastNameCol.setCellValueFactory(e -> e.getValue().getNameProperty());
		//		adressCol.setCellValueFactory(e -> e.getValue().getAdresseProperty());
		//
		//		// Textfelder setzen f�r editierbarkeit
		//		firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		//		lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		//		adressCol.setCellFactory(TextFieldTableCell.forTableColumn());
		//
		//		// Spalten der Tableview �bergeben
		//		tableView.getColumns().addAll(firstNameCol, lastNameCol, adressCol);

		// Editable TableView / ListView
		tableView.setEditable(true);
		listView.setEditable(true);

		// Verhindert bei �bergr��e das Anzeigen einer zus�tzlichen Spalte, nur
		// letzte Spalte wird vergr��ert
		tableView.setColumnResizePolicy(tableView.CONSTRAINED_RESIZE_POLICY);

		// der TableView und ListView die Liste mit unseren ObservableContactDetails �bergeben
		tableView.itemsProperty().setValue(obserAppointmentList);
		listView.itemsProperty().setValue(obserAppointmentList);

		// ruft eigene listCellMethode auf
		//listView.setCellFactory(TextFieldListCell.forListView());

	}

	private Object loadFile() {
		List<Appointment> obserlist = CSVTerminReader.readAppointment("Termine",
				";");
		obserAppointmentList.clear();
		
		for (Appointment contact : obserlist)
			obserAppointmentList.addAll(contact);
		return null;
	}

	private Object writeFile() {
		printalles();
		List<Appointment> obserlist = obserAppointmentList;
		try {
			CSVTerminWriter.writeAppointment(obserlist, "test", ";");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// Methode um neuen leeren Kontakt anzulegen
	private Object addcontact() {
		Appointment newapp = new Appointment();
		obserAppointmentList.addAll(newapp);
		return null;
	}

	// Methode um alles aus der Observable Contact Details Liste auf der Konsole
	// auszugeben
	private void printalles() {
		for (Appointment i : obserAppointmentList) {
			System.out.println("Vorname: " + i.getVornameProperty().getValue()
					+ "\t Nachname: " + i.getNameProperty().getValue()
					+ "\t Adresse: " + i.getAdresseProperty().getValue());
		}
	}
}

/* ################ besser mit .. (ilse folie zusammenfassung).. arbeiten //
 * eigene ListCell f�r die ListView, da die ListVIew mit selbst definierten //
 * Objekten nicht umgehen kann class MyListCell extends
 * ListCell<ObservableContactDetails> { // Das Textfeld das an der Stelle des
 * Eintrags der editiert werden soll // angezeigt wird private TextField
 * textField = new TextField(); private StringProperty nameProperty = new
 * SimpleStringProperty();
 * 
 * public MyListCell() { // Bindet die Sichtbarkeit des EditTextfeldes an die
 * editing // Property der Zelle, also wenn die Zelle im bearbeiten Modus ist,
 * // wird das Textfeld gezeigt sonst nicht
 * this.textField.visibleProperty().bind(this.editingProperty()); this.textField
 * .setOnAction(a -> commitEdit(new ObservableContactDetails(this
 * .getItem().getVorname(), textField.getText(), this
 * .getItem().getAdresse()))); // Eine Zelle besteht aus seinem Text und einer
 * Graphic, die jede // Form von Node sein kann, in unserem Fall eben das
 * EditTextfeld this.setGraphic(this.textField); // Ausrichtung des Textfeldes
 * this.setContentDisplay(ContentDisplay.RIGHT); }
 * 
 * // Diese Methode wird immer dann aufgerufen wenn sich der Inhalt der // Zelle
 * �ndert auch beim erstmaligen beschreiben
 * 
 * @Override protected void updateItem(ObservableContactDetails t, boolean bln)
 * { super.updateItem(t, bln);// f�hrt die Standartaufgaben der Methode // durch
 * // Wenn die Zelle nicht null ist, wobei get Item das //
 * ObservableContactDetails-Object aus unserer Liste darstellt if
 * (this.getItem() != null) { // Bindet unseren NachNamen
 * this.nameProperty.bind(this.getItem().getNameProperty()); // schreibt den
 * letzten Eintrag in das Textfeld this.textField.setText(nameProperty.get());
 * // legt den Anzeigetext fest setText(nameProperty.get()); } }
 * 
 * } */

