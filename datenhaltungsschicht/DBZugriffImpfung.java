package datenhaltungsschicht;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import logikschicht.Impfung;

public class DBZugriffImpfung extends DBZugriff {

    private static ResultSet datenmenge;

    public static boolean insert(Impfung impfung) throws Exception {
        connect();
        String insertCommand = "INSERT INTO Impfung (ImpfungID, MedikamentID, ErforderlicheDosis, Intervall, KostenProDosis) VALUES ("
                + "'" + impfung.getImpfungID() + "', '" + impfung.getMedikamentID() + "', "
                + impfung.getErforderlicheDosis() + ", " + impfung.getIntervall() + ", "
                + impfung.getKostenProDosis() + ")";

        try {
            befehl.executeUpdate(insertCommand);
        } catch (SQLException ex) {
            String errorMessage = "Fehler beim Hinzufügen der Impfung " + impfung.getImpfungID() + " aufgetreten.";
            Logger.logError(ex);
            throw new Exception(errorMessage);
        }
        Logger.logCommand(insertCommand);

        close();
        return true;
    }

    public static boolean update(Impfung impfung) throws Exception {
        connect();
        String updateCommand = "UPDATE Impfung SET MedikamentID = '" + impfung.getMedikamentID() +
                "', ErforderlicheDosis = " + impfung.getErforderlicheDosis() + ", Intervall = " + impfung.getIntervall()
                +
                ", KostenProDosis = " + impfung.getKostenProDosis() + " WHERE ImpfungID = '" + impfung.getImpfungID()
                + "'";

        try {
            befehl.executeUpdate(updateCommand);
        } catch (SQLException ex) {
            String errorMessage = "Es ist ein Fehler beim Aktualisieren der Impfung " + impfung.getImpfungID()
                    + " aufgetreten.";
            Logger.logError(ex);
            throw new Exception(errorMessage);
        }
        Logger.logCommand(updateCommand);

        close();
        return true;
    }

    public static boolean delete(Impfung impfung) throws Exception {
        connect();
        String deleteCommand = "DELETE FROM Impfung WHERE ImpfungID = '" + impfung.getImpfungID() + "'";

        try {
            befehl.executeUpdate(deleteCommand);
        } catch (SQLException ex) {
            String errorMessage = "Fehler beim Löschen der Impfung " + impfung.getImpfungID() + " aufgetreten.";
            Logger.logError(ex);
            throw new Exception(errorMessage);
        }
        Logger.logCommand(deleteCommand);

        close();
        return true;
    }

    public static List<Impfung> getAllImpfungen() throws Exception {
        List<Impfung> impfungenListe = new ArrayList<>();
        connect();
        datenmenge = befehl.executeQuery("SELECT * FROM Impfung");

        while (getNext()) {
            String impfungID = getImpfungID();
            String medikamentID = getMedikamentID();
            double erforderlicheDosis = getErforderlicheDosis();
            int intervall = getIntervall();
            double kostenProDosis = getKostenProDosis();

            Impfung impfung = new Impfung(impfungID, medikamentID, erforderlicheDosis, intervall, kostenProDosis);
            impfungenListe.add(impfung);
        }

        close();
        return impfungenListe;
    }

    public static Impfung getImpfungByID(String impfungID) throws Exception {
        connect();
        Impfung impfung = null;
        String query = "SELECT * FROM Impfung WHERE ImpfungID = '" + impfungID + "'";
        datenmenge = befehl.executeQuery(query);

        if (datenmenge.next()) {
            String medikamentID = getMedikamentID();
            double erforderlicheDosis = getErforderlicheDosis();
            int intervall = getIntervall();
            double kostenProDosis = getKostenProDosis();

            impfung = new Impfung(impfungID, medikamentID, erforderlicheDosis, intervall, kostenProDosis);
        }

        close();
        return impfung;
    }

    public static boolean getNext() throws SQLException {
        return datenmenge.next();
    }

    public static String getImpfungID() throws SQLException {
        return datenmenge.getString("ImpfungID");
    }

    public static String getMedikamentID() throws SQLException {
        return datenmenge.getString("MedikamentID");
    }

    public static double getErforderlicheDosis() throws SQLException {
        return datenmenge.getDouble("ErforderlicheDosis");
    }

    public static int getIntervall() throws SQLException {
        return datenmenge.getInt("Intervall");
    }

    public static double getKostenProDosis() throws SQLException {
        return datenmenge.getDouble("KostenProDosis");
    }
}
