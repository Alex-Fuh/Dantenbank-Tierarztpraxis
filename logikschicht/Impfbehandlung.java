package logikschicht;

public class Impfbehandlung {
    private String impfbehandlungID;
    private String tierID;
    private String impfungID;
    private String tierarztID;
    private String datumDerVerabreichung;
    private String naechsteImpfungFaellig;
    private static int zaehler = 0;

    public Impfbehandlung() {
    }

    public Impfbehandlung(String impfbehandlungID, String tierID, String impfungID, String tierarztID,
            String datumDerVerabreichung, String naechsteImpfungFaellig) {
                zaehler++;
        impfbehandlungID = "" + zaehler;
        this.tierID = tierID;
        this.impfungID = impfungID;
        this.tierarztID = tierarztID;
        this.datumDerVerabreichung = datumDerVerabreichung;
        this.naechsteImpfungFaellig = naechsteImpfungFaellig;
    }

    public String getImpfbehandlungID() {
        return impfbehandlungID;
    }

    public String getTierID() {
        return tierID;
    }

    public String getImpfungID() {
        return impfungID;
    }

    public String getTierarztID() {
        return tierarztID;
    }

    public String getDatumDerVerabreichung() {
        return datumDerVerabreichung;
    }

    public String getNaechsteImpfungFaellig() {
        return naechsteImpfungFaellig;
    }

    public void setImpfbehandlungID(String impfbehandlungID) {
        this.impfbehandlungID = impfbehandlungID;
    }

    public void setTierID(String tierID) {
        this.tierID = tierID;
    }

    public void setImpfungID(String impfungID) {
        this.impfungID = impfungID;
    }

    public void setTierarztID(String tierarztID) {
        this.tierarztID = tierarztID;
    }

    public void setDatumDerVerabreichung(String datumDerVerabreichung) {
        this.datumDerVerabreichung = datumDerVerabreichung;
    }

    public void setNaechsteImpfungFaellig(String naechsteImpfungFaellig) {
        this.naechsteImpfungFaellig = naechsteImpfungFaellig;
    }

    @Override
    public String toString() {
        return "Impfbehandlungs-ID: " + impfbehandlungID +
                "\nTier-ID: " + tierID +
                "\nImpfung-ID: " + impfungID +
                "\nTierarzt-ID: " + tierarztID +
                "\nDatum der Verabreichung: " + datumDerVerabreichung +
                "\nNächste Impfung fällig: " + naechsteImpfungFaellig + "\n";
    }
}
