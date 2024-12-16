package datenhaltungsschicht;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;  

public class DBZugriff {

    private static Connection con;
    private static String url = "test"; 
    protected static Statement befehl;

    public static void connect() throws SQLException {
        try {
            con = DriverManager.getConnection("jdbc-DatenbankURL", "test", "oracle");  
            befehl = con.createStatement();  
            con.setAutoCommit(true);
        } catch (SQLException ex) {
            String errorMassage = "Es ist ein Fehler beim Herstellen der Verbindung zur Datenbank aufgetreten";
            throw new SQLException(errorMassage, ex);
        }
    }

    public static void close() throws SQLException {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            String errorMessage = "Fehler beim Schließen der Verbindung";
            Logger.logError(ex);
            throw new SQLException(errorMessage, ex);
        }
    }
}
