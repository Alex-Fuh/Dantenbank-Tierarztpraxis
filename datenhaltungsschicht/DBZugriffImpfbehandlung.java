package datenhaltungsschicht;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import logikschicht.Impfbehandlung;

public class DBZugriffImpfbehandlung extends DBZugriff {

    private static ResultSet datenmenge;

    public static boolean insert(Impfbehandlung impfbehandlung) throws Exception {
        connect();
        String insertCommand = "INSERT INTO Impfbehandlung (ImpfbehandlungID, TierID, ImpfungID, TierarztID, DatumDerVerabreichung, NaechsteImpfungFaellig) VALUES ("
                + "'" + impfbehandlung.getImpfbehandlungID() + "', '" + impfbehandlung.getTierID() + "', '"
                + impfbehandlung.getImpfungID() + "', '" + impfbehandlung.getTierarztID() + "', '"
                + impfbehandlung.getDatumDerVerabreichung() + "', '" + impfbehandlung.getNaechsteImpfungFaellig()
                + "')";

        try {
            befehl.executeUpdate(insertCommand);
        } catch (SQLException ex) {
            String errorMessage = "Fehler beim Hinzufügen der Impfbehandlung " + impfbehandlung.getImpfbehandlungID()
                    + " aufgetreten.";
            Logger.logError(ex);
            throw new Exception(errorMessage);
        }
        Logger.logCommand(insertCommand);

        close();
        return true;
    }

    public static boolean update(Impfbehandlung impfbehandlung) throws Exception {
        connect();
        String updateCommand = "UPDATE Impfbehandlung SET TierID = '" + impfbehandlung.getTierID() +
                "', ImpfungID = '" + impfbehandlung.getImpfungID() + "', TierarztID = '"
                + impfbehandlung.getTierarztID() +
                "', DatumDerVerabreichung = '" + impfbehandlung.getDatumDerVerabreichung()
                + "', NaechsteImpfungFaellig = '" +
                impfbehandlung.getNaechsteImpfungFaellig() + "' WHERE ImpfbehandlungID = '"
                + impfbehandlung.getImpfbehandlungID() + "'";

        try {
            befehl.executeUpdate(updateCommand);
        } catch (SQLException ex) {
            String errorMessage = "Es ist ein Fehler beim Aktualisieren der Impfbehandlung "
                    + impfbehandlung.getImpfbehandlungID()
                    + " aufgetreten.";
            Logger.logError(ex);
            throw new Exception(errorMessage);
        }
        Logger.logCommand(updateCommand);

        close();
        return true;
    }

    public static boolean delete(Impfbehandlung impfbehandlung) throws Exception {
        connect();
        String deleteCommand = "DELETE FROM Impfbehandlung WHERE ImpfbehandlungID = '"
                + impfbehandlung.getImpfbehandlungID() + "'";

        try {
            befehl.executeUpdate(deleteCommand);
        } catch (SQLException ex) {
            String errorMessage = "Fehler beim Löschen der Impfbehandlung " + impfbehandlung.getImpfbehandlungID()
                    + " aufgetreten.";
            Logger.logError(ex);
            throw new Exception(errorMessage);
        }
        Logger.logCommand(deleteCommand);

        close();
        return true;
    }

    public static List<Impfbehandlung> getAllImpfbehandlungen() throws Exception {
        List<Impfbehandlung> impfbehandlungsListe = new ArrayList<>();
        connect();
        datenmenge = befehl.executeQuery("SELECT * FROM Impfbehandlung");

        while (getNext()) {
            String impfbehandlungID = getImpfbehandlungID();
            String tierID = getTierID();
            String impfungID = getImpfungID();
            String tierarztID = getTierarztID();
            String datumDerVerabreichung = getDatumDerVerabreichung();
            String naechsteImpfungFaellig = getNaechsteImpfungFaellig();

            Impfbehandlung impfbehandlung = new Impfbehandlung(impfbehandlungID, tierID, impfungID, tierarztID,
                    datumDerVerabreichung, naechsteImpfungFaellig);
            impfbehandlungsListe.add(impfbehandlung);
        }

        close();
        return impfbehandlungsListe;
    }

    public static Impfbehandlung getImpfbehandlungByID(String impfbehandlungID) throws Exception {
        connect();
        Impfbehandlung impfbehandlung = null;
        String query = "SELECT * FROM Impfbehandlung WHERE ImpfbehandlungID = '" + impfbehandlungID + "'";
        datenmenge = befehl.executeQuery(query);

        if (datenmenge.next()) {
            String tierID = getTierID();
            String impfungID = getImpfungID();
            String tierarztID = getTierarztID();
            String datumDerVerabreichung = getDatumDerVerabreichung();
            String naechsteImpfungFaellig = getNaechsteImpfungFaellig();

            impfbehandlung = new Impfbehandlung(impfbehandlungID, tierID, impfungID, tierarztID, datumDerVerabreichung,
                    naechsteImpfungFaellig);
        }

        close();
        return impfbehandlung;
    }

    public static boolean getNext() throws SQLException {
        return datenmenge.next();
    }

    public static String getImpfbehandlungID() throws SQLException {
        return datenmenge.getString("ImpfbehandlungID");
    }

    public static String getTierID() throws SQLException {
        return datenmenge.getString("TierID");
    }

    public static String getImpfungID() throws SQLException {
        return datenmenge.getString("ImpfungID");
    }

    public static String getTierarztID() throws SQLException {
        return datenmenge.getString("TierarztID");
    }

    public static String getDatumDerVerabreichung() throws SQLException {
        return datenmenge.getString("DatumDerVerabreichung");
    }

    public static String getNaechsteImpfungFaellig() throws SQLException {
        return datenmenge.getString("NaechsteImpfungFaellig");
    }
}
