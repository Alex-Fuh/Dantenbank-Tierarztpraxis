package datenhaltungsschicht;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import logikschicht.Termin;

public class DBZugriffTermin extends DBZugriff {

    private static ResultSet datenmenge;

    public static boolean insert(Termin termin) throws Exception {
        connect();
        String insertCommand = "INSERT INTO Termin (DatumUndUhrzeit, TierID, BesitzerID, TierarztID, Grund) VALUES ("
                + "'" + termin.getDatumUndUhrzeit() + "', '" + termin.getTierID() + "', '"
                + termin.getBesitzerID() + "', '" + termin.getTierarztID() + "', '" + termin.getGrund() + "')";

        try {
            befehl.executeUpdate(insertCommand);
        } catch (SQLException ex) {
            String errorMessage = "Fehler beim Hinzufügen des Termins " + termin.getDatumUndUhrzeit() + " aufgetreten.";
            Logger.logError(ex);
            throw new Exception(errorMessage);
        }
        Logger.logCommand(insertCommand);

        close();
        return true;
    }

    public static boolean update(Termin termin) throws Exception {
        connect();
        String updateCommand = "UPDATE Termin SET TierID = '" + termin.getTierID() +
                "', BesitzerID = '" + termin.getBesitzerID() + "', TierarztID = '" + termin.getTierarztID() +
                "', Grund = '" + termin.getGrund() + "' WHERE DatumUndUhrzeit = '" + termin.getDatumUndUhrzeit() + "'";

        try {
            befehl.executeUpdate(updateCommand);
        } catch (SQLException ex) {
            String errorMessage = "Es ist ein Fehler beim Aktualisieren des Termins " + termin.getDatumUndUhrzeit()
                    + " aufgetreten.";
            Logger.logError(ex);
            throw new Exception(errorMessage);
        }
        Logger.logCommand(updateCommand);

        close();
        return true;
    }

    public static boolean delete(Termin termin) throws Exception {
        connect();
        String deleteCommand = "DELETE FROM Termin WHERE DatumUndUhrzeit = '" + termin.getDatumUndUhrzeit()
                + "' AND TierID = '"
                + termin.getTierID() + "' AND BesitzerID = '" + termin.getBesitzerID() + "' AND TierarztID = '"
                + termin.getTierarztID() + "'";

        try {
            befehl.executeUpdate(deleteCommand);
        } catch (SQLException ex) {
            String errorMessage = "Fehler beim Löschen des Termins " + termin.getDatumUndUhrzeit() + " aufgetreten.";
            Logger.logError(ex);
            throw new Exception(errorMessage);
        }
        Logger.logCommand(deleteCommand);

        close();
        return true;
    }

    public static List<Termin> getAllTermine() throws Exception {
        List<Termin> terminListe = new ArrayList<>();
        connect();
        datenmenge = befehl.executeQuery("SELECT * FROM Termin");

        while (getNext()) {
            String datumUndUhrzeit = getDatumUndUhrzeit();
            String tierID = getTierID();
            String besitzerID = getBesitzerID();
            String tierarztID = getTierarztID();
            String grund = getGrund();

            Termin termin = new Termin(datumUndUhrzeit, tierID, besitzerID, tierarztID, grund);
            terminListe.add(termin);
        }

        close();
        return terminListe;
    }

    public static Termin getTerminByID(String datumUndUhrzeit, String tierID, String besitzerID, String tierarztID)
            throws Exception {
        connect();
        Termin termin = null;
        String query = "SELECT * FROM Termin WHERE DatumUndUhrzeit = '" + datumUndUhrzeit + "' AND TierID = '" + tierID
                +
                "' AND BesitzerID = '" + besitzerID + "' AND TierarztID = '" + tierarztID + "'";
        datenmenge = befehl.executeQuery(query);

        if (datenmenge.next()) {
            String grund = getGrund();
            termin = new Termin(datumUndUhrzeit, tierID, besitzerID, tierarztID, grund);
        }

        close();
        return termin;
    }

    public static boolean getNext() throws SQLException {
        return datenmenge.next();
    }

    public static String getDatumUndUhrzeit() throws SQLException {
        return datenmenge.getString("DatumUndUhrzeit");
    }

    public static String getTierID() throws SQLException {
        return datenmenge.getString("TierID");
    }

    public static String getBesitzerID() throws SQLException {
        return datenmenge.getString("BesitzerID");
    }

    public static String getTierarztID() throws SQLException {
        return datenmenge.getString("TierarztID");
    }

    public static String getGrund() throws SQLException {
        return datenmenge.getString("Grund");
    }
}
