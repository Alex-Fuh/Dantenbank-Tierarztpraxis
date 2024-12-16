package datenhaltungsschicht;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import logikschicht.MedikamentBehandlung;

public class DBZugriffMedikamentBehandlung extends DBZugriff {

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

    public static boolean insert(MedikamentBehandlung medikamentBehandlung) throws Exception {
        connect();
        String insertCommand = "INSERT INTO MedikamentBehandlung (BehandlungsID, MedikamentID, Dosierung, Dauer) VALUES ("
                + "'" + medikamentBehandlung.getBehandlungsID() + "', '" + medikamentBehandlung.getMedikamentID()
                + "', '"
                + medikamentBehandlung.getDosierung() + "', '" + medikamentBehandlung.getDauer() + "')";

        try {
            befehl.executeUpdate(insertCommand);
        } catch (SQLException ex) {
            String errorMessage = "Fehler beim Hinzufügen der Medikamentbehandlung "
                    + medikamentBehandlung.getBehandlungsID() + " aufgetreten.";
            Logger.logError(ex);
            throw new Exception(errorMessage);
        }
        Logger.logCommand(insertCommand);

        close();
        return true;
    }

    public static boolean update(MedikamentBehandlung medikamentBehandlung) throws Exception {
        connect();
        String updateCommand = "UPDATE MedikamentBehandlung SET MedikamentID = '"
                + medikamentBehandlung.getMedikamentID() +
                "', Dosierung = '" + medikamentBehandlung.getDosierung() + "', Dauer = '"
                + medikamentBehandlung.getDauer() +
                "' WHERE BehandlungsID = '" + medikamentBehandlung.getBehandlungsID() + "'";

        try {
            befehl.executeUpdate(updateCommand);
        } catch (SQLException ex) {
            String errorMessage = "Fehler beim Aktualisieren der Medikamentbehandlung "
                    + medikamentBehandlung.getBehandlungsID() + " aufgetreten.";
            Logger.logError(ex);
            throw new Exception(errorMessage);
        }
        Logger.logCommand(updateCommand);

        close();
        return true;
    }

    public static boolean delete(MedikamentBehandlung medikamentBehandlung) throws Exception {
        connect();
        String deleteCommand = "DELETE FROM MedikamentBehandlung WHERE BehandlungsID = '"
                + medikamentBehandlung.getBehandlungsID() + "'";

        try {
            befehl.executeUpdate(deleteCommand);
        } catch (SQLException ex) {
            String errorMessage = "Fehler beim Löschen der Medikamentbehandlung "
                    + medikamentBehandlung.getBehandlungsID() + " aufgetreten.";
            Logger.logError(ex);
            throw new Exception(errorMessage);
        }
        Logger.logCommand(deleteCommand);

        close();
        return true;
    }

    public static List<MedikamentBehandlung> getAllMedikamentBehandlungen() throws Exception {
        List<MedikamentBehandlung> medikamentBehandlungsListe = new ArrayList<>();
        connect();
        datenmenge = befehl.executeQuery("SELECT * FROM MedikamentBehandlung");

        while (getNext()) {
            String behandlungsID = getBehandlungsID();
            String medikamentID = getMedikamentID();
            String dosierung = getDosierung();
            String dauer = getDauer();

            MedikamentBehandlung medikamentBehandlung = new MedikamentBehandlung(behandlungsID, medikamentID, dosierung,
                    dauer);
            medikamentBehandlungsListe.add(medikamentBehandlung);
        }

        close();
        return medikamentBehandlungsListe;
    }

    public static MedikamentBehandlung getMedikamentBehandlungByID(String behandlungsID) throws Exception {
        connect();
        MedikamentBehandlung medikamentBehandlung = null;
        String query = "SELECT * FROM MedikamentBehandlung WHERE BehandlungsID = '" + behandlungsID + "'";
        datenmenge = befehl.executeQuery(query);

        if (datenmenge.next()) {
            String medikamentID = getMedikamentID();
            String dosierung = getDosierung();
            String dauer = getDauer();

            medikamentBehandlung = new MedikamentBehandlung(behandlungsID, medikamentID, dosierung, dauer);
        }

        close();
        return medikamentBehandlung;
    }

    public static boolean getNext() throws SQLException {
        return datenmenge.next();
    }

    public static String getBehandlungsID() throws SQLException {
        return datenmenge.getString("BehandlungsID");
    }

    public static String getMedikamentID() throws SQLException {
        return datenmenge.getString("MedikamentID");
    }

    public static String getDosierung() throws SQLException {
        return datenmenge.getString("Dosierung");
    }

    public static String getDauer() throws SQLException {
        return datenmenge.getString("Dauer");
    }
}
