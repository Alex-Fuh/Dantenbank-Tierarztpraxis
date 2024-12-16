package datenhaltungsschicht;

import java.io.FileWriter;
import java.io.IOException;  // Fehlenden Import hinzufügen
import java.io.PrintWriter;  // Fehlenden Import hinzufügen
import java.text.SimpleDateFormat;  // Fehlenden Import hinzufügen
import java.util.Date;  // Fehlenden Import hinzufügen

public class Logger {
    private static final String LOG_FILE_PATH = System.getProperty("user.dir") + "/";
    private static final String LOG_FILE_NAME = "log.txt";

    public static void logError(Exception e) {
        try (FileWriter fileWriter = new FileWriter(LOG_FILE_PATH + LOG_FILE_NAME, true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            Date timestamp = new Date();

            printWriter.println("Timestamp: " + dateFormat.format(timestamp));
            printWriter.println("Error Message: " + e.getMessage());
            printWriter.println("Stack Trace:");
            e.printStackTrace(printWriter);
            printWriter.println("\n");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void logCommand(String befehl) {
        try (FileWriter fileWriter = new FileWriter(LOG_FILE_PATH + LOG_FILE_NAME, true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            Date timestamp = new Date();

            printWriter.println("Timestamp: " + dateFormat.format(timestamp));
            printWriter.println("SQL-Command: " + befehl);
            printWriter.println("\n");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
