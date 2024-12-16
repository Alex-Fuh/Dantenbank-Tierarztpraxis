package datenhaltungsschicht;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import logikschicht.Behandlung;

public class DBZugriffBehandlung extends DBZugriff {

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

    public static boolean insert(Behandlung behandlung) throws Exception {
        connect();
        String insertCommand = "INSERT INTO Behandlung (behandlungsID, tierID, tierarztID, datum, diagnose, behandlungskosten, behandlungsart, zahlungsstatus, rechnungsdatum) VALUES ("
                + "'" + behandlung.getBehandlungsID() + "', '" + behandlung.getTierID() + "', '"
                + behandlung.getTierarztID() + "', '" + behandlung.getDatum() + "', '"
                + behandlung.getDiagnose() + "', '" + behandlung.getBehandlungskosten() + "' , '"
                + behandlung.getBehandlungsart() + "' , '" + behandlung.getZahlungsstatus() + "' , '"
                + behandlung.getRechnungsdatum() + "')";
        try {
            befehl.executeUpdate(insertCommand);
        } catch (SQLException ex) {
            String errorMessage = "Fehler beim Hinzufügen der Behandlung.";
            Logger.logError(ex);
            throw new Exception(errorMessage);
        }
        Logger.logCommand(insertCommand);
        close();
        return true;
    }

    public static boolean update(Behandlung behandlung) throws Exception {
        connect();
        String updateCommand = "UPDATE Behandlung SET diagnose = '" + behandlung.getDiagnose() +
                "', behandlungskosten = '" + behandlung.getBehandlungskosten() + "', behandlungsart = '"
                + behandlung.getBehandlungsart() +
                "', zahlungsstatus = '" + behandlung.getZahlungsstatus() + "', rechnungsdatum = '"
                + behandlung.getRechnungsdatum() +
                "' WHERE behandlungsID = '" + behandlung.getBehandlungsID() + "'";
        try {
            befehl.executeUpdate(updateCommand);
        } catch (SQLException ex) {
            String errorMessage = "Es ist ein Fehler beim Aktualisieren der Behandlung.";
            Logger.logError(ex);
            throw new Exception(errorMessage);
        }
        Logger.logCommand(updateCommand);
        close();
        return true;
    }

    public static boolean delete(Behandlung behandlung) throws Exception {
        connect();
        String deleteCommand = "DELETE FROM Behandlung WHERE behandlungsID = '" + behandlung.getBehandlungsID() + "'";
        try {
            befehl.executeUpdate(deleteCommand);
        } catch (SQLException ex) {
            String errorMessage = "Fehler beim Löschen der Behandlung.";
            Logger.logError(ex);
            throw new Exception(errorMessage);
        }
        Logger.logCommand(deleteCommand);
        close();
        return true;
    }

    public static List<Behandlung> getAllBehandlung() throws Exception {
        List<Behandlung> behandlungListe = new ArrayList<>();
        connect();
        datenmenge = befehl.executeQuery("SELECT * FROM Behandlung");
        while (datenmenge.next()) {
            String behandlungsID = datenmenge.getString("behandlungsID");
            String tierID = datenmenge.getString("tierID");
            String tierarztID = datenmenge.getString("tierarztID");
            String datum = datenmenge.getString("datum");
            String diagnose = datenmenge.getString("diagnose");
            double behandlungskosten = datenmenge.getDouble("behandlungskosten");
            String behandlungsart = datenmenge.getString("behandlungsart");
            String zahlungsstatus = datenmenge.getString("zahlungsstatus");
            String rechnungsdatum = datenmenge.getString("rechnungsdatum");

            Behandlung behandlung = new Behandlung(behandlungsID, tierID, tierarztID, datum, diagnose,
                    behandlungskosten, behandlungsart, zahlungsstatus, rechnungsdatum);
            behandlungListe.add(behandlung);
        }
        close();
        return behandlungListe;
    }

    public static Behandlung getBehandlungByID(String behandlungsID) throws Exception {
        connect();
        Behandlung behandlung = null;
        String query = "SELECT * FROM Behandlung WHERE behandlungsID = '" + behandlungsID + "'";
        datenmenge = befehl.executeQuery(query);
        if (datenmenge.next()) {
            String tierID = datenmenge.getString("tierID");
            String tierarztID = datenmenge.getString("tierarztID");
            String datum = datenmenge.getString("datum");
            String diagnose = datenmenge.getString("diagnose");
            double behandlungskosten = datenmenge.getDouble("behandlungskosten");
            String behandlungsart = datenmenge.getString("behandlungsart");
            String zahlungsstatus = datenmenge.getString("zahlungsstatus");
            String rechnungsdatum = datenmenge.getString("rechnungsdatum");

            behandlung = new Behandlung(behandlungsID, tierID, tierarztID, datum, diagnose, behandlungskosten,
                    behandlungsart, zahlungsstatus, rechnungsdatum);
        }
        close();
        return behandlung;
    }

    public static boolean getNext() throws SQLException {
        return datenmenge.next();
    }

    public static String getBehandlungsID() throws SQLException {
        return datenmenge.getString("behandlungsID");
    }

    public static String getTierID() throws SQLException {
        return datenmenge.getString("tierID");
    }

    public static String getTierarztID() throws SQLException {
        return datenmenge.getString("tierarztID");
    }

    public static String getDatum() throws SQLException {
        return datenmenge.getString("datum");
    }

    public static String getDiagnose() throws SQLException {
        return datenmenge.getString("diagnose");
    }

    public static double getBehandlungskosten() throws SQLException {
        return datenmenge.getDouble("behandlungskosten");
    }

    public static String getBehandlungsart() throws SQLException {
        return datenmenge.getString("behandlungsart");
    }

    public static String getZahlungsstatus() throws SQLException {
        return datenmenge.getString("zahlungsstatus");
    }

    public static String getRechnungsdatum() throws SQLException {
        return datenmenge.getString("rechnungsdatum");
    }
}
