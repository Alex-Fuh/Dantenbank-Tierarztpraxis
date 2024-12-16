package datenhaltungsschicht;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import logikschicht.Medikament;

public class DBZugriffMedikament extends DBZugriff {

    private static ResultSet datenmenge;
    private static Statement befehl;
    private static Connection connection;

    public static void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:your_database_url", "your_username", "your_password");
        befehl = connection.createStatement();
    }

    public static boolean insert(Medikament medikament) throws Exception {
        connect();
        String insertCommand = "INSERT INTO Medikament (MedikamentID, Name, Wirkstoff, KostenProEinheit) VALUES ("
                + "'" + medikament.getMedikamentID() + "', '" + medikament.getName() + "', '"
                + medikament.getWirkstoff() + "', " + medikament.getKostenProEinheit() + ")";

        try {
            befehl.executeUpdate(insertCommand);
        } catch (SQLException ex) {
            String errorMessage = "Fehler beim Hinzufügen des Medikaments " + medikament.getMedikamentID()
                    + " aufgetreten.";
            Logger.logError(ex);
            throw new Exception(errorMessage);
        }
        Logger.logCommand(insertCommand);

        close();
        return true;
    }

    public static boolean update(Medikament medikament) throws Exception {
        connect();
        String updateCommand = "UPDATE Medikament SET Name = '" + medikament.getName() +
                "', Wirkstoff = '" + medikament.getWirkstoff() + "', KostenProEinheit = "
                + medikament.getKostenProEinheit() +
                " WHERE MedikamentID = '" + medikament.getMedikamentID() + "'";

        try {
            befehl.executeUpdate(updateCommand);
        } catch (SQLException ex) {
            String errorMessage = "Es ist ein Fehler beim Aktualisieren des Medikaments " + medikament.getMedikamentID()
                    + " aufgetreten.";
            Logger.logError(ex);
            throw new Exception(errorMessage);
        }
        Logger.logCommand(updateCommand);

        close();
        return true;
    }

    public static boolean delete(Medikament medikament) throws Exception {
        connect();
        String deleteCommand = "DELETE FROM Medikament WHERE MedikamentID = '" + medikament.getMedikamentID() + "'";

        try {
            befehl.executeUpdate(deleteCommand);
        } catch (SQLException ex) {
            String errorMessage = "Fehler beim Löschen des Medikaments " + medikament.getMedikamentID()
                    + " aufgetreten.";
            Logger.logError(ex);
            throw new Exception(errorMessage);
        }
        Logger.logCommand(deleteCommand);

        close();
        return true;
    }

    public static List<Medikament> getAllMedikamente() throws Exception {
        List<Medikament> medikamentenListe = new ArrayList<>();
        connect();
        datenmenge = befehl.executeQuery("SELECT * FROM Medikament");

        while (getNext()) {
            String medikamentID = getMedikamentID();
            String name = getName();
            String wirkstoff = getWirkstoff();
            double kostenProEinheit = getKostenProEinheit();

            Medikament medikament = new Medikament(medikamentID, name, wirkstoff, kostenProEinheit);
            medikamentenListe.add(medikament);
        }

        close();
        return medikamentenListe;
    }

    public static Medikament getMedikamentByID(String medikamentID) throws Exception {
        connect();
        Medikament medikament = null;
        String query = "SELECT * FROM Medikament WHERE MedikamentID = '" + medikamentID + "'";
        datenmenge = befehl.executeQuery(query);

        if (datenmenge.next()) {
            String name = getName();
            String wirkstoff = getWirkstoff();
            double kostenProEinheit = getKostenProEinheit();

            medikament = new Medikament(medikamentID, name, wirkstoff, kostenProEinheit);
        }

        close();
        return medikament;
    }

    public static boolean getNext() throws SQLException {
        return datenmenge.next();
    }

    public static String getMedikamentID() throws SQLException {
        return datenmenge.getString("MedikamentID");
    }

    public static String getName() throws SQLException {
        return datenmenge.getString("Name");
    }

    public static String getWirkstoff() throws SQLException {
        return datenmenge.getString("Wirkstoff");
    }

    public static double getKostenProEinheit() throws SQLException {
        return datenmenge.getDouble("KostenProEinheit");
    }

    public static void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
