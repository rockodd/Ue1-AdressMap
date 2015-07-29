package calendar;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class fxmain extends Application {
	// observableArrayList f�r TableView (Werden f�r FX Views ben�tigt um Listen zu �bergeben)

	@Override
	public void start(Stage primaryStage) throws IOException {
		primaryStage.setTitle("edit Lists GUI");
		BorderPane root = new BorderPane();
		HBox hbox = new HBox();

		//Button erzeugen
		Button btnprint = new Button("DRUCK");
		Button btnadd = new Button("NEU");
		Button btnsav = new Button("SPEICHERN");
		Button btnload = new Button("LADEN");

		// ListView und TableView erzeugen
		final TableView<Appointment> tableView = new TableView<Appointment>();
		// Control starten und Objekte �bergeben
		new Control(tableView, btnprint, btnadd, btnsav, btnload);

		// TableView auf der BorderPane platzieren
		root.setCenter(tableView);
		hbox.getChildren().addAll(btnprint, btnadd, btnsav, btnload);
		root.setBottom(hbox);
		// Scene erstellen und auf PrimaryStage anzeigen
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
