package logikschicht;

import java.util.ArrayList;
import java.util.List;
import datenhaltungsschicht.DBZugriffTermin;

public class Terminverwaltung {
    private static List<Termin> terminListe = new ArrayList<>();

    public static boolean storeTermin(Termin termin) throws Exception {
        boolean stored = DBZugriffTermin.insert(termin);
        if (stored) {
            terminListe.add(termin);
        }
        return stored;
    }

    public static boolean updateTermin(Termin termin) throws Exception {
        boolean updated = DBZugriffTermin.update(termin);
        if (updated) {
            for (Termin existingTermin : terminListe) {
                if (existingTermin.getDatumUndUhrzeit().equals(termin.getDatumUndUhrzeit()) &&
                        existingTermin.getTierID().equals(termin.getTierID()) &&
                        existingTermin.getBesitzerID().equals(termin.getBesitzerID()) &&
                        existingTermin.getTierarztID().equals(termin.getTierarztID())) {

                    existingTermin.setGrund(termin.getGrund());
                    break;
                }
            }
        }
        return updated;
    }

    public static boolean deleteTermin(Termin termin) throws Exception {
        if (DBZugriffTermin.delete(termin) && terminListe.contains(termin)) {
            terminListe.remove(termin);
            return true;
        } else {
            return false;
        }
    }

    public static Termin getTerminByID(String datumUndUhrzeit, String tierID, String besitzerID, String tierarztID)
            throws Exception {
        return DBZugriffTermin.getTerminByID(datumUndUhrzeit, tierID, besitzerID, tierarztID);
    }

    public static List<Termin> getAllTermine() throws Exception {
        terminListe = DBZugriffTermin.getAllTermine();
        return terminListe;
    }
}
