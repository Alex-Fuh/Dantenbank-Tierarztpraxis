package logikschicht;

import java.util.ArrayList;
import java.util.List;
import datenhaltungsschicht.DBZugriffImpfung;

public class Impfungsverwaltung {
    private static List<Impfung> impfungenListe = new ArrayList<>();

    public static boolean storeImpfung(Impfung impfung) throws Exception {
        boolean stored = DBZugriffImpfung.insert(impfung);
        if (stored) {
            impfungenListe.add(impfung);
        }
        return stored;
    }

    public static boolean updateImpfung(Impfung impfung) throws Exception {
        boolean updated = DBZugriffImpfung.update(impfung);
        if (updated) {
            for (Impfung existingImpfung : impfungenListe) {
                if (existingImpfung.getImpfungID().equals(impfung.getImpfungID())) {
                    existingImpfung.setMedikamentID(impfung.getMedikamentID());
                    existingImpfung.setErforderlicheDosis(impfung.getErforderlicheDosis());
                    existingImpfung.setIntervall(impfung.getIntervall());
                    existingImpfung.setKostenProDosis(impfung.getKostenProDosis());
                    break;
                }
            }
        }
        return updated;
    }

    public static boolean deleteImpfung(Impfung impfung) throws Exception {
        if (DBZugriffImpfung.delete(impfung) && impfungenListe.contains(impfung)) {
            impfungenListe.remove(impfung);
            return true;
        } else {
            return false;
        }
    }

    public static Impfung getImpfungByID(String impfungID) throws Exception {
        return DBZugriffImpfung.getImpfungByID(impfungID);
    }

    public static List<Impfung> getAllImpfungen() throws Exception {
        impfungenListe = DBZugriffImpfung.getAllImpfungen();
        return impfungenListe;
    }
}
