package datenhaltungsschicht;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import logikschicht.Tierarzt;

public class DBZugriffTierarzt extends DBZugriff {

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

    public static boolean insert(Tierarzt tierarzt) throws Exception {
        connect();
        String insertCommand = "INSERT INTO Tierarzt (TierarztID, ArztNachname, ArztVorname, Fachgebiet, Telefonnummer, VorgesetzterID) VALUES ("
                + "'" + tierarzt.getTierarztID() + "', '" + tierarzt.getArztNachname() + "', '"
                + tierarzt.getArztVorname() + "', '" + tierarzt.getFachgebiet() + "', '"
                + tierarzt.getTelefonnummer() + "', '" + tierarzt.getVorgesetzterID() + "')";

        try {
            befehl.executeUpdate(insertCommand);
        } catch (SQLException ex) {
            String errorMessage = "Fehler beim Hinzufügen des Tierarztes " + tierarzt.getTierarztID() + " aufgetreten.";
            Logger.logError(ex);
            throw new Exception(errorMessage);
        }
        Logger.logCommand(insertCommand);

        close();
        return true;
    }

    public static boolean update(Tierarzt tierarzt) throws Exception {
        connect();
        String updateCommand = "UPDATE Tierarzt SET ArztNachname = '" + tierarzt.getArztNachname() +
                "', ArztVorname = '" + tierarzt.getArztVorname() + "', Fachgebiet = '" + tierarzt.getFachgebiet() +
                "', Telefonnummer = '" + tierarzt.getTelefonnummer() + "', VorgesetzterID = '"
                + tierarzt.getVorgesetzterID() +
                "' WHERE TierarztID = '" + tierarzt.getTierarztID() + "'";

        try {
            befehl.executeUpdate(updateCommand);
        } catch (SQLException ex) {
            String errorMessage = "Es ist ein Fehler beim Aktualisieren des Tierarztes " + tierarzt.getTierarztID()
                    + " aufgetreten.";
            Logger.logError(ex);
            throw new Exception(errorMessage);
        }
        Logger.logCommand(updateCommand);

        close();
        return true;
    }

    public static boolean delete(Tierarzt tierarzt) throws Exception {
        connect();
        String deleteCommand = "DELETE FROM Tierarzt WHERE TierarztID = '" + tierarzt.getTierarztID() + "'";

        try {
            befehl.executeUpdate(deleteCommand);
        } catch (SQLException ex) {
            String errorMessage = "Fehler beim Löschen des Tierarztes " + tierarzt.getTierarztID() + " aufgetreten.";
            Logger.logError(ex);
            throw new Exception(errorMessage);
        }
        Logger.logCommand(deleteCommand);

        close();
        return true;
    }

    public static List<Tierarzt> getAllTierärzte() throws Exception {
        List<Tierarzt> tierarztListe = new ArrayList<>();
        connect();
        datenmenge = befehl.executeQuery("SELECT * FROM Tierarzt");

        while (getNext()) {
            String tierarztID = getTierarztID();
            String arztNachname = getArztNachname();
            String arztVorname = getArztVorname();
            String fachgebiet = getFachgebiet();
            String telefonnummer = getTelefonnummer();
            String vorgesetzterID = getVorgesetzterID();

            Tierarzt tierarzt = new Tierarzt(tierarztID, arztNachname, arztVorname, fachgebiet, telefonnummer,
                    vorgesetzterID);
            tierarztListe.add(tierarzt);
        }

        close();
        return tierarztListe;
    }

    public static Tierarzt getTierarztByID(String tierarztID) throws Exception {
        connect();
        Tierarzt tierarzt = null;
        String query = "SELECT * FROM Tierarzt WHERE TierarztID = '" + tierarztID + "'";
        datenmenge = befehl.executeQuery(query);

        if (datenmenge.next()) {
            String arztNachname = getArztNachname();
            String arztVorname = getArztVorname();
            String fachgebiet = getFachgebiet();
            String telefonnummer = getTelefonnummer();
            String vorgesetzterID = getVorgesetzterID();

            tierarzt = new Tierarzt(tierarztID, arztNachname, arztVorname, fachgebiet, telefonnummer, vorgesetzterID);
        }

        close();
        return tierarzt;
    }

    public static boolean getNext() throws SQLException {
        return datenmenge.next();
    }

    public static String getTierarztID() throws SQLException {
        return datenmenge.getString("TierarztID");
    }

    public static String getArztNachname() throws SQLException {
        return datenmenge.getString("ArztNachname");
    }

    public static String getArztVorname() throws SQLException {
        return datenmenge.getString("ArztVorname");
    }

    public static String getFachgebiet() throws SQLException {
        return datenmenge.getString("Fachgebiet");
    }

    public static String getTelefonnummer() throws SQLException {
        return datenmenge.getString("Telefonnummer");
    }

    public static String getVorgesetzterID() throws SQLException {
        return datenmenge.getString("VorgesetzterID");
    }
}
