package logikschicht;

import java.util.ArrayList;
import java.util.List;
import datenhaltungsschicht.DBZugriffTier_Patient;

public class TierPatientVerwaltung {
    private static List<Tier_Patient> tierPatientenListe = new ArrayList<>();

    public static boolean storeTierPatient(Tier_Patient tierPatient) throws Exception {
        boolean stored = DBZugriffTier_Patient.insert(tierPatient);
        if (stored) {
            tierPatientenListe.add(tierPatient);
        }
        return stored;
    }

    public static boolean updateTierPatient(Tier_Patient tierPatient) throws Exception {
        boolean updated = DBZugriffTier_Patient.update(tierPatient);
        if (updated) {
            for (Tier_Patient existingTierPatient : tierPatientenListe) {
                if (existingTierPatient.getTierID().equals(tierPatient.getTierID())) {
                    existingTierPatient.setTierName(tierPatient.getTierName());
                    existingTierPatient.setArt(tierPatient.getArt());
                    existingTierPatient.setRasse(tierPatient.getRasse());
                    existingTierPatient.setGeburtsdatum(tierPatient.getGeburtsdatum());
                    existingTierPatient.setBesitzerID(tierPatient.getBesitzerID());
                    break;
                }
            }
        }
        return updated;
    }

    public static boolean deleteTierPatient(Tier_Patient tierPatient) throws Exception {
        if (DBZugriffTier_Patient.delete(tierPatient) && tierPatientenListe.contains(tierPatient)) {
            tierPatientenListe.remove(tierPatient);
            return true;
        } else {
            return false;
        }
    }

    public static Tier_Patient getTierPatientByID(String tierID) throws Exception {
        return DBZugriffTier_Patient.getTierPatientByID(tierID);
    }

    public static List<Tier_Patient> getAllTierPatienten() throws Exception {
        tierPatientenListe = DBZugriffTier_Patient.getAllTierPatienten();
        return tierPatientenListe;
    }
}
