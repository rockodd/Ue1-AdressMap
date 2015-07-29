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
import javafx.scene.control.DateCell;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.control.cell.TextFieldTableCell;
import CsvAdressbook.*;
import CsvTerminkalender.BinAppointmentReader;
import CsvTerminkalender.BinAppointmentWriter;
import CsvTerminkalender.BufferedAppointmentReader;
import CsvTerminkalender.BufferedAppointmentWriter;
import CsvTerminkalender.CSVTerminReader;
import CsvTerminkalender.CSVTerminWriter;

public class Control {

	ObservableList<Appointment>		obserAppointmentList;
	private TableView<Appointment>	tableView;
	private Button							btnprint;
	private Button							btnadd;
	private Button							btnsave;
	private Button							btnload;

	public Control(
			TableView<Appointment> tableViewIN, Button btnprintIN,
			Button btnaddIN, Button btnsavIN, Button btnloadIN) {
		// Übergabeobjekte übernehmen
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

		// observableArrayList für TableView (Werden für FX Views benötigt um
		// Listen zu übergeben)
		obserAppointmentList.addAll(new Appointment("Kat 1", LocalDate.of(2015,
				05, 29), LocalTime.of(10, 20), LocalTime.of(12, 20), "Termin1",
				"gleicher Tag"));
		obserAppointmentList.addAll(new Appointment("Kat 2", LocalDate.of(2015,
				05, 29), LocalTime.of(10, 20), LocalTime.of(14, 20), "Termin2",
				"gleicher Tag"));
		obserAppointmentList.addAll(new Appointment("Kat 2", LocalDate.of(2015,
				07, 01), LocalTime.of(9, 00), LocalTime.of(14, 00), "Termin3",
				"andere Tag"));

				// Spalten für die TableView anlegen
				TableColumn<Appointment, String> katCol = new TableColumn<Appointment, String>("Kategorie");
				TableColumn<Appointment, String> bezCol = new TableColumn<Appointment, String>("Bezeichnung");
				TableColumn<Appointment, String> beschCol = new TableColumn<Appointment, String>("Beschreibung");
				TableColumn<Appointment, LocalDate> datCol = new TableColumn<Appointment, LocalDate>("Datum");
				TableColumn<Appointment, LocalTime> startCol = new TableColumn<Appointment, LocalTime>("Beginn");
				TableColumn<Appointment, LocalTime> endeCol = new TableColumn<Appointment, LocalTime>("Ende");
		
				// Lambda zum füllen der Spalten
				katCol.setCellValueFactory(e -> e.getValue().getTerminkategorie());
				bezCol.setCellValueFactory(e -> e.getValue().getTerminBezeichnung());
				beschCol.setCellValueFactory(e -> e.getValue().getTerminBeschreibung());
				datCol.setCellValueFactory(e -> e.getValue().getDatum());
				startCol.setCellValueFactory(e -> e.getValue().getStartUhrzeit());
				endeCol.setCellValueFactory(e -> e.getValue().getEndUhrzeit());
				
				//datCol.setText(DateUtil.format();
		        //birthdayField.setText(DateUtil.format(person.getBirthday()));
		        //birthdayField.setPromptText("dd.mm.yyyy");

		
				// Textfelder setzen für editierbarkeit
				katCol.setCellFactory(TextFieldTableCell.forTableColumn());
				bezCol.setCellFactory(TextFieldTableCell.forTableColumn());
				beschCol.setCellFactory(TextFieldTableCell.forTableColumn());
//				datCol.setCellFactory(TextFieldTableCell.forTableColumn());
//				datCol.setCellFactory(c -> new TableCell<>());
//				startCol.setCellFactory(TextFieldTableCell.forTableColumn());
//				endeCol.setCellFactory(TextFieldTableCell.forTableColumn());
		
				// Spalten der Tableview übergeben
				tableView.getColumns().addAll(katCol, bezCol, beschCol, datCol, startCol, endeCol);

		// Editable TableView / ListView
		tableView.setEditable(true);

		// Verhindert bei Übergröße das Anzeigen einer zusätzlichen Spalte, nur
		// letzte Spalte wird vergrößert
		tableView.setColumnResizePolicy(tableView.CONSTRAINED_RESIZE_POLICY);

		// der TableView und ListView die Liste mit unseren ObservableContactDetails übergeben
		tableView.itemsProperty().setValue(obserAppointmentList);

		// ruft eigene listCellMethode auf
		//listView.setCellFactory(TextFieldListCell.forListView());

	}

	private Object loadFile() {
		List<Appointment> obserlist = null;
		try {
			obserlist = BinAppointmentReader.readAppointment("Termine",";");
		} catch (IOException | BeforeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		obserAppointmentList.clear();
		
		for (Appointment contact : obserlist)
			obserAppointmentList.addAll(contact);
		return null;
	}

	private Object writeFile() {
		printalles();
		List<Appointment> obserlist = obserAppointmentList;
		try {
			BinAppointmentWriter.writeAppointment(obserlist, "Termine", ";");
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
			System.out.println("Beschreibung: " + i.getTerminBeschreibung()
					+ "\t Bezeichnung: " + i.getTerminBezeichnung()
					+ "\t Kategorie: " + i.getTerminkategorie()
					+ "\t Datum: " + i.getDatum()
					+ "\t Startzeit: " + i.getStartUhrzeit()
					+ "\t Endzeit: " + i.getEndUhrzeit());
		}
	}
}

/* ################ besser mit .. (ilse folie zusammenfassung).. arbeiten //
 * eigene ListCell für die ListView, da die ListVIew mit selbst definierten //
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
 * ändert auch beim erstmaligen beschreiben
 * 
 * @Override protected void updateItem(ObservableContactDetails t, boolean bln)
 * { super.updateItem(t, bln);// führt die Standartaufgaben der Methode // durch
 * // Wenn die Zelle nicht null ist, wobei get Item das //
 * ObservableContactDetails-Object aus unserer Liste darstellt if
 * (this.getItem() != null) { // Bindet unseren NachNamen
 * this.nameProperty.bind(this.getItem().getNameProperty()); // schreibt den
 * letzten Eintrag in das Textfeld this.textField.setText(nameProperty.get());
 * // legt den Anzeigetext fest setText(nameProperty.get()); } }
 * 
 * } */

