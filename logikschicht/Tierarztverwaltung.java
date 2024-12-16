package logikschicht;

import java.util.ArrayList;
import java.util.List;
import datenhaltungsschicht.DBZugriffTierarzt;

public class Tierarztverwaltung {
    private static List<Tierarzt> tierarztListe = new ArrayList<>();

    public static boolean storeTierarzt(Tierarzt tierarzt) throws Exception {
        boolean stored = DBZugriffTierarzt.insert(tierarzt);
        if (stored) {
            tierarztListe.add(tierarzt);
        }
        return stored;
    }

    public static boolean updateTierarzt(Tierarzt tierarzt) throws Exception {
        boolean updated = DBZugriffTierarzt.update(tierarzt);
        if (updated) {
            for (Tierarzt existingTierarzt : tierarztListe) {
                if (existingTierarzt.getTierarztID().equals(tierarzt.getTierarztID())) {
                    existingTierarzt.setArztNachname(tierarzt.getArztNachname());
                    existingTierarzt.setArztVorname(tierarzt.getArztVorname());
                    existingTierarzt.setFachgebiet(tierarzt.getFachgebiet());
                    existingTierarzt.setTelefonnummer(tierarzt.getTelefonnummer());
                    existingTierarzt.setVorgesetzterID(tierarzt.getVorgesetzterID());
                    break;
                }
            }
        }
        return updated;
    }

    public static boolean deleteTierarzt(Tierarzt tierarzt) throws Exception {
        if (DBZugriffTierarzt.delete(tierarzt) && tierarztListe.contains(tierarzt)) {
            tierarztListe.remove(tierarzt);
            return true;
        } else {
            return false;
        }
    }

    public static Tierarzt getTierarztByID(String tierarztID) throws Exception {
        return DBZugriffTierarzt.getTierarztByID(tierarztID);
    }

    public static List<Tierarzt> getAllTierärzte() throws Exception {
        tierarztListe = DBZugriffTierarzt.getAllTierärzte();
        return tierarztListe;
    }
}
