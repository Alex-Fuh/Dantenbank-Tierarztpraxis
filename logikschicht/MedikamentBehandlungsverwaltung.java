package logikschicht;

import java.util.ArrayList;
import java.util.List;
import datenhaltungsschicht.DBZugriffMedikamentBehandlung;

public class MedikamentBehandlungsverwaltung {
    private static List<MedikamentBehandlung> medikamentBehandlungsListe = new ArrayList<>();

    public static boolean storeMedikamentBehandlung(MedikamentBehandlung medikamentBehandlung) throws Exception {
        boolean stored = DBZugriffMedikamentBehandlung.insert(medikamentBehandlung);
        if (stored) {
            medikamentBehandlungsListe.add(medikamentBehandlung);
        }
        return stored;
    }

    public static boolean updateMedikamentBehandlung(MedikamentBehandlung medikamentBehandlung) throws Exception {
        boolean updated = DBZugriffMedikamentBehandlung.update(medikamentBehandlung);
        if (updated) {
            for (MedikamentBehandlung existingMedikamentBehandlung : medikamentBehandlungsListe) {
                if (existingMedikamentBehandlung.getBehandlungsID().equals(medikamentBehandlung.getBehandlungsID())) {
                    existingMedikamentBehandlung.setMedikamentID(medikamentBehandlung.getMedikamentID());
                    existingMedikamentBehandlung.setDosierung(medikamentBehandlung.getDosierung());
                    existingMedikamentBehandlung.setDauer(medikamentBehandlung.getDauer());
                    break;
                }
            }
        }
        return updated;
    }

    public static boolean deleteMedikamentBehandlung(MedikamentBehandlung medikamentBehandlung) throws Exception {
        if (DBZugriffMedikamentBehandlung.delete(medikamentBehandlung)
                && medikamentBehandlungsListe.contains(medikamentBehandlung)) {
            medikamentBehandlungsListe.remove(medikamentBehandlung);
            return true;
        } else {
            return false;
        }
    }

    public static MedikamentBehandlung getMedikamentBehandlungByID(String behandlungsID) throws Exception {
        return DBZugriffMedikamentBehandlung.getMedikamentBehandlungByID(behandlungsID);
    }

    public static List<MedikamentBehandlung> getAllMedikamentBehandlungen() throws Exception {
        medikamentBehandlungsListe = DBZugriffMedikamentBehandlung.getAllMedikamentBehandlungen();
        return medikamentBehandlungsListe;
    }
}
