package datenhaltungsschicht;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import logikschicht.Besitzer;

public class DBZugriffBesitzer extends DBZugriff {

    private static ResultSet datenmenge;
    private static Statement befehl;
    private static Connection connection;

    public static void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:your_database_url", "your_username", "your_password");
        befehl = connection.createStatement();
    }

    public static void close() throws SQLException {
        if (befehl != null) {
            befehl.close();
        }
        if (datenmenge != null) {
            datenmenge.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    public static boolean insert(Besitzer besitzer) throws Exception {
        connect();
        String insertCommand = "INSERT INTO Besitzer (Nachname, Vorname, Straße, Hausnummer, PLZ, Stadt, Telefonnummer) VALUES ("
                + "'" + besitzer.getBesitzerNachname() + "', '" + besitzer.getBesitzerVorname() + "', '"
                + besitzer.getStrasse() + "', '" + besitzer.getHausnummer() + "', '" + besitzer.getPlz() + "', '"
                + besitzer.getStadt() + "', '" + besitzer.getTelefonnummer() + "')";

        try {
            befehl.executeUpdate(insertCommand);
        } catch (SQLException ex) {
            String errorMessage = "Fehler beim Hinzufügen des Besitzers " + besitzer.getBesitzerID() + " aufgetreten.";
            Logger.logError(ex);
            throw new Exception(errorMessage);
        }
        Logger.logCommand(insertCommand);

        close();
        return true;
    }

    public static boolean update(Besitzer besitzer) throws Exception {
        connect();
        String updateCommand = "UPDATE Besitzer SET Nachname = '" + besitzer.getBesitzerNachname() +
                "', Vorname = '" + besitzer.getBesitzerVorname() + "', Straße = '" + besitzer.getStrasse() +
                "', Hausnummer = '" + besitzer.getHausnummer() + "', PLZ = '" + besitzer.getPlz() + 
                "', Stadt = '" + besitzer.getStadt() + "', Telefonnummer = '" + besitzer.getTelefonnummer() + 
                "' WHERE BesitzerID = " + besitzer.getBesitzerID();

        try {
            befehl.executeUpdate(updateCommand);
        } catch (SQLException ex) {
            String errorMessage = "Es ist ein Fehler beim Aktualisieren des Besitzers " + besitzer.getBesitzerID()
                    + " aufgetreten.";
            Logger.logError(ex);
            throw new Exception(errorMessage);
        }
        Logger.logCommand(updateCommand);

        close();
        return true;
    }

    public static boolean delete(Besitzer besitzer) throws Exception {
        connect();
        String deleteCommand = "DELETE FROM Besitzer WHERE BesitzerID = " + besitzer.getBesitzerID();

        try {
            befehl.executeUpdate(deleteCommand);
        } catch (SQLException ex) {
            String errorMessage = "Fehler beim Löschen des Besitzers " + besitzer.getBesitzerID() + " aufgetreten.";
            Logger.logError(ex);
            throw new Exception(errorMessage);
        }
        Logger.logCommand(deleteCommand);

        close();
        return true;
    }

    public static List<Besitzer> getAllBesitzer() throws Exception {
        List<Besitzer> besitzerListe = new ArrayList<>();
        connect();
        datenmenge = befehl.executeQuery("SELECT * FROM Besitzer");

        while (getNext()) {
            String besitzerID = getBesitzerID();
            String nachname = getNachname();
            String vorname = getVorname();
            String strasse = getStrasse();
            String hausnummer = getHausnummer();
            String plz = getPlz();
            String stadt = getStadt();
            String telefonnummer = getTelefonnummer();

            Besitzer besitzer = new Besitzer(besitzerID, nachname, vorname, strasse, hausnummer, plz, stadt, telefonnummer);
            besitzerListe.add(besitzer);
        }

        close();
        return besitzerListe;
    }

    public static Besitzer getBesitzerByID(String besitzerID) throws Exception {
        connect();
        Besitzer besitzer = null;
        String query = "SELECT * FROM Besitzer WHERE BesitzerID = " + besitzerID;
        datenmenge = befehl.executeQuery(query);

        if (datenmenge.next()) {
            String nachname = getNachname();
            String vorname = getVorname();
            String strasse = getStrasse();
            String hausnummer = getHausnummer();
            String plz = getPlz();
            String stadt = getStadt();
            String telefonnummer = getTelefonnummer();

            besitzer = new Besitzer(besitzerID, nachname, vorname, strasse, hausnummer, plz, stadt, telefonnummer);
        }

        close();
        return besitzer;
    }

    public static boolean getNext() throws SQLException {
        return datenmenge.next();
    }

    public static String getBesitzerID() throws SQLException {
        return datenmenge.getString("BesitzerID");
    }

    public static String getNachname() throws SQLException {
        return datenmenge.getString("Nachname");
    }

    public static String getVorname() throws SQLException {
        return datenmenge.getString("Vorname");
    }

    public static String getStrasse() throws SQLException {
        return datenmenge.getString("Straße");
    }

    public static String getHausnummer() throws SQLException {
        return datenmenge.getString("Hausnummer");
    }

    public static String getPlz() throws SQLException {
        return datenmenge.getString("PLZ");
    }

    public static String getStadt() throws SQLException {
        return datenmenge.getString("Stadt");
    }

    public static String getTelefonnummer() throws SQLException {
        return datenmenge.getString("Telefonnummer");
    }
}
