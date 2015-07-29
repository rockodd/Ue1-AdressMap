package tests;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import application.ObservableContactDetails;
import calendar.Appointment;

/**
 * Hilfsklasse zum Laden der Testdaten.
 */
public final class TestData {

  static final Appointment TERMIN1;
  static final Appointment TERMIN2;
  static final Appointment TERMIN3;


  static {
    try {
      TERMIN1 = new Appointment();
      TERMIN2 = new Appointment("Uni", LocalDate.parse("2015-05-29"), LocalTime.parse("10:00"), LocalTime.parse("18:00"), "Test", "Das ist ein Test");
      TERMIN3 = new Appointment("Uni", LocalDate.parse("2015-06-02"), LocalTime.parse("09:00"), LocalTime.parse("23:00"), "Test3", "Das ist ein Test3");

    } catch (Exception e) {
      throw new IllegalArgumentException(e.getMessage(), e);
    }
  }

  // Privater Standard-Konstruktor um neue Instanzen dieser Klasse zu vermeiden.
  private TestData() {
  }

  /**
   * Liefert die  Termine 2 und 3 .
   *
   * @return Die Liste der Termine.
   */
  public static List<Appointment> getValidAppointments() {
    List<Appointment> appointments = new ArrayList<>();
    appointments.add(TERMIN2);
    appointments.add(TERMIN3);
    return appointments;
  }

  public static List<Appointment> getTermin2(int count) {
    List<Appointment> appointments = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      appointments.add(TERMIN2);
    }
    return appointments;
  }


}
