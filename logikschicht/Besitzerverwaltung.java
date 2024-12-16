package logikschicht;

import java.util.ArrayList;
import java.util.List;
import datenhaltungsschicht.DBZugriffBesitzer;

public class Besitzerverwaltung {
    private static List<Besitzer> besitzerListe = new ArrayList<>();

    public static boolean storeBesitzer(Besitzer besitzer) throws Exception {
        boolean stored = DBZugriffBesitzer.insert(besitzer);
        if (stored) {
            besitzerListe.add(besitzer);
        }
        return stored;
    }

    public static boolean updateBesitzer(Besitzer besitzer) throws Exception {
        boolean updated = DBZugriffBesitzer.update(besitzer);
        if (updated) {
            for (Besitzer existingBesitzer : besitzerListe) {
                if (existingBesitzer.getBesitzerID().equals(besitzer.getBesitzerID())) {
                    existingBesitzer.setBesitzerNachname(besitzer.getBesitzerNachname());
                    existingBesitzer.setBesitzerVorname(besitzer.getBesitzerVorname());
                    existingBesitzer.setHausnummer(besitzer.getHausnummer());
                    existingBesitzer.setStrasse(besitzer.getStrasse());
                    existingBesitzer.setPlz(besitzer.getPlz());
                    existingBesitzer.setStadt(besitzer.getStadt());
                    existingBesitzer.setTelefonnummer(besitzer.getTelefonnummer());
                    break;
                }
            }
        }
        return updated;
    }

    public static boolean deleteBesitzer(Besitzer besitzer) throws Exception {
        if (DBZugriffBesitzer.delete(besitzer) && besitzerListe.contains(besitzer)) {
            besitzerListe.remove(besitzer);
            return true;
        } else {
            return false;
        }
    }

    public static Besitzer getBesitzerByID(String besitzerID) throws Exception {
        return DBZugriffBesitzer.getBesitzerByID(besitzerID);
    }

    public static List<Besitzer> getAllBesitzer() throws Exception {
        besitzerListe = DBZugriffBesitzer.getAllBesitzer();
        return besitzerListe;
    }
}
