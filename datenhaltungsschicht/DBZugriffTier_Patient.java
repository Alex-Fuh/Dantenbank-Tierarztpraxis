package datenhaltungsschicht;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import logikschicht.Tier_Patient;

public class DBZugriffTier_Patient extends DBZugriff {

    private static ResultSet datenmenge;
    private static Statement befehl;

    public static void connect() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:your_database_url", "your_username", "your_password");
        befehl = connection.createStatement();
    }

    public static void close() throws SQLException {
        if (befehl != null) {
            befehl.close();
        }
        if (datenmenge != null) {
            datenmenge.close();
        }
    }

    public static boolean insert(Tier_Patient tier_Patient) throws Exception {
        connect();
        String insertCommand = "INSERT INTO Tier_Patient (TierID, TierName, Art, Rasse, Geburtsdatum, BesitzerID) VALUES ("
                + "'" + tier_Patient.getTierID() + "', '" + tier_Patient.getTierName() + "', '"
                + tier_Patient.getArt() + "', '" + tier_Patient.getRasse() + "', '"
                + tier_Patient.getGeburtsdatum() + "', '" + tier_Patient.getBesitzerID() + "')";

        try {
            befehl.executeUpdate(insertCommand);
        } catch (SQLException ex) {
            String errorMessage = "Fehler beim Hinzufügen des Tier-Patienten " + tier_Patient.getTierID() + " aufgetreten.";
            Logger.logError(ex);
            throw new Exception(errorMessage);
        }
        Logger.logCommand(insertCommand);

        close();
        return true;
    }

    public static boolean update(Tier_Patient tier_Patient) throws Exception {
        connect();
        String updateCommand = "UPDATE Tier_Patient SET TierName = '" + tier_Patient.getTierName() +
                "', Art = '" + tier_Patient.getArt() + "', Rasse = '" + tier_Patient.getRasse() +
                "', Geburtsdatum = '" + tier_Patient.getGeburtsdatum() + "', BesitzerID = '" +
                tier_Patient.getBesitzerID() + "' WHERE TierID = '" + tier_Patient.getTierID() + "'";

        try {
            befehl.executeUpdate(updateCommand);
        } catch (SQLException ex) {
            String errorMessage = "Es ist ein Fehler beim Aktualisieren des Tier-Patienten " + tier_Patient.getTierID()
                    + " aufgetreten.";
            Logger.logError(ex);
            throw new Exception(errorMessage);
        }
        Logger.logCommand(updateCommand);

        close();
        return true;
    }

    public static boolean delete(Tier_Patient tier_Patient) throws Exception {
        connect();
        String deleteCommand = "DELETE FROM Tier_Patient WHERE TierID = '" + tier_Patient.getTierID() + "'";

        try {
            befehl.executeUpdate(deleteCommand);
        } catch (SQLException ex) {
            String errorMessage = "Fehler beim Löschen des Tier-Patienten " + tier_Patient.getTierID() + " aufgetreten.";
            Logger.logError(ex);
            throw new Exception(errorMessage);
        }
        Logger.logCommand(deleteCommand);

        close();
        return true;
    }

    public static List<Tier_Patient> getAllTierPatienten() throws Exception {
        List<Tier_Patient> tierPatientenListe = new ArrayList<>();
        connect();
        datenmenge = befehl.executeQuery("SELECT * FROM Tier_Patient");

        while (getNext()) {
            String tierID = getTierID();
            String tierName = getTierName();
            String art = getArt();
            String rasse = getRasse();
            String geburtsdatum = getGeburtsdatum();
            String besitzerID = getBesitzerID();

            Tier_Patient tierPatient = new Tier_Patient(tierID, tierName, art, rasse, geburtsdatum, besitzerID);
            tierPatientenListe.add(tierPatient);
        }

        close();
        return tierPatientenListe;
    }

    public static Tier_Patient getTierPatientByID(String tierID) throws Exception {
        connect();
        Tier_Patient tierPatient = null;
        String query = "SELECT * FROM Tier_Patient WHERE TierID = '" + tierID + "'";
        datenmenge = befehl.executeQuery(query);

        if (datenmenge.next()) {
            String tierName = getTierName();
            String art = getArt();
            String rasse = getRasse();
            String geburtsdatum = getGeburtsdatum();
            String besitzerID = getBesitzerID();

            tierPatient = new Tier_Patient(tierID, tierName, art, rasse, geburtsdatum, besitzerID);
        }

        close();
        return tierPatient;
    }

    public static boolean getNext() throws SQLException {
        return datenmenge.next();
    }

    public static String getTierID() throws SQLException {
        return datenmenge.getString("TierID");
    }

    public static String getTierName() throws SQLException {
        return datenmenge.getString("TierName");
    }

    public static String getArt() throws SQLException {
        return datenmenge.getString("Art");
    }

    public static String getRasse() throws SQLException {
        return datenmenge.getString("Rasse");
    }

    public static String getGeburtsdatum() throws SQLException {
        return datenmenge.getString("Geburtsdatum");
    }

    public static String getBesitzerID() throws SQLException {
        return datenmenge.getString("BesitzerID");
    }
}
