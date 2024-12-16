package logikschicht;

import java.util.ArrayList;
import java.util.List;
import datenhaltungsschicht.DBZugriffBehandlung;

public class BehandlungVerwaltung {
    private static List<Behandlung> behandlungListe = new ArrayList<>();

    public static boolean storeBehandlung(Behandlung behandlung) throws Exception {
        boolean stored = DBZugriffBehandlung.insert(behandlung);
        if (stored) {
            behandlungListe.add(behandlung);
        }
        return stored;
    }

    public static boolean updateBehandlung(Behandlung behandlung) throws Exception {
        boolean updated = DBZugriffBehandlung.update(behandlung);
        if (updated) {
            for (Behandlung existingBehandlung : behandlungListe) {
                if (existingBehandlung.getBehandlungsID().equals(behandlung.getBehandlungsID())) {
                    existingBehandlung.setTierID(behandlung.getTierID());
                    existingBehandlung.setTierarztID(behandlung.getTierarztID());
                    existingBehandlung.setDatum(behandlung.getDatum());
                    existingBehandlung.setDiagnose(behandlung.getDiagnose());
                    existingBehandlung.setBehandlungskosten(behandlung.getBehandlungskosten());
                    existingBehandlung.setBehandlungsart(behandlung.getBehandlungsart());
                    existingBehandlung.setZahlungsstatus(behandlung.getZahlungsstatus());
                    existingBehandlung.setRechnungsdatum(behandlung.getRechnungsdatum());
                    break;
                }
            }
        }
        return updated;
    }

    public static boolean deleteBehandlung(Behandlung behandlung) throws Exception {
        if (DBZugriffBehandlung.delete(behandlung) && behandlungListe.contains(behandlung)) {
            behandlungListe.remove(behandlung);
            return true;
        } else {
            return false;
        }
    }

    public static Behandlung getBehandlungByID(String behandlungsID) throws Exception {
        return DBZugriffBehandlung.getBehandlungByID(behandlungsID);
    }
}