package logikschicht;

import java.util.ArrayList;
import java.util.List;
import datenhaltungsschicht.DBZugriffMedikament;

public class Medikamentenverwaltung {
    private static List<Medikament> medikamentenListe = new ArrayList<>();

    public static boolean storeMedikament(Medikament medikament) throws Exception {
        boolean stored = DBZugriffMedikament.insert(medikament);
        if (stored) {
            medikamentenListe.add(medikament);
        }
        return stored;
    }

    public static boolean updateMedikament(Medikament medikament) throws Exception {
        boolean updated = DBZugriffMedikament.update(medikament);
        if (updated) {
            for (Medikament existingMedikament : medikamentenListe) {
                if (existingMedikament.getMedikamentID().equals(medikament.getMedikamentID())) {
                    existingMedikament.setName(medikament.getName());
                    existingMedikament.setWirkstoff(medikament.getWirkstoff());
                    existingMedikament.setKostenProEinheit(medikament.getKostenProEinheit());
                    break;
                }
            }
        }
        return updated;
    }

    public static boolean deleteMedikament(Medikament medikament) throws Exception {
        if (DBZugriffMedikament.delete(medikament) && medikamentenListe.contains(medikament)) {
            medikamentenListe.remove(medikament);
            return true;
        } else {
            return false;
        }
    }

    public static Medikament getMedikamentByID(String medikamentID) throws Exception {
        return DBZugriffMedikament.getMedikamentByID(medikamentID);
    }

    public static List<Medikament> getAllMedikamente() throws Exception {
        medikamentenListe = DBZugriffMedikament.getAllMedikamente();
        return medikamentenListe;
    }
}
