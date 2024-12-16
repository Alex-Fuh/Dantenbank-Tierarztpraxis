package logikschicht;

import java.util.ArrayList;
import java.util.List;
import datenhaltungsschicht.DBZugriffImpfbehandlung;

public class Impfbehandlungsverwaltung {
    private static List<Impfbehandlung> impfbehandlungsListe = new ArrayList<>();

    public static boolean storeImpfbehandlung(Impfbehandlung impfbehandlung) throws Exception {
        boolean stored = DBZugriffImpfbehandlung.insert(impfbehandlung);
        if (stored) {
            impfbehandlungsListe.add(impfbehandlung);
        }
        return stored;
    }

    public static boolean updateImpfbehandlung(Impfbehandlung impfbehandlung) throws Exception {
        boolean updated = DBZugriffImpfbehandlung.update(impfbehandlung);
        if (updated) {
            for (Impfbehandlung existingImpfbehandlung : impfbehandlungsListe) {
                if (existingImpfbehandlung.getImpfbehandlungID().equals(impfbehandlung.getImpfbehandlungID())) {
                    existingImpfbehandlung.setTierID(impfbehandlung.getTierID());
                    existingImpfbehandlung.setImpfungID(impfbehandlung.getImpfungID());
                    existingImpfbehandlung.setTierarztID(impfbehandlung.getTierarztID());
                    existingImpfbehandlung.setDatumDerVerabreichung(impfbehandlung.getDatumDerVerabreichung());
                    existingImpfbehandlung.setNaechsteImpfungFaellig(impfbehandlung.getNaechsteImpfungFaellig());
                    break;
                }
            }
        }
        return updated;
    }

    public static boolean deleteImpfbehandlung(Impfbehandlung impfbehandlung) throws Exception {
        if (DBZugriffImpfbehandlung.delete(impfbehandlung) && impfbehandlungsListe.contains(impfbehandlung)) {
            impfbehandlungsListe.remove(impfbehandlung);
            return true;
        } else {
            return false;
        }
    }

    public static Impfbehandlung getImpfbehandlungByID(String impfbehandlungID) throws Exception {
        return DBZugriffImpfbehandlung.getImpfbehandlungByID(impfbehandlungID);
    }

    public static List<Impfbehandlung> getAllImpfbehandlungen() throws Exception {
        impfbehandlungsListe = DBZugriffImpfbehandlung.getAllImpfbehandlungen();
        return impfbehandlungsListe;
    }
}
