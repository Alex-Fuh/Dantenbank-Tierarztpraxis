package logikschicht;

public class Behandlung {
    private String behandlungsID;
    private String tierID;
    private String tierarztID;
    private String datum;
    private String diagnose;
    private double behandlungskosten;
    private String behandlungsart;
    private String zahlungsstatus;
    private String rechnungsdatum;
    private static int zaehler = 0;

    public Behandlung() {
    }

    public Behandlung(String behandlungsID, String tierID, String tierarztID, String datum, String diagnose,
            double behandlungskosten, String behandlungsart, String zahlungsstatus, String rechnungsdatum) {
        zaehler++;
        behandlungsID = "" + zaehler;
        this.tierID = tierID;
        this.tierarztID = tierarztID;
        this.datum = datum;
        this.diagnose = diagnose;
        this.behandlungskosten = behandlungskosten;
        this.behandlungsart = behandlungsart;
        this.zahlungsstatus = zahlungsstatus;
        this.rechnungsdatum = rechnungsdatum;
    }

    public String getBehandlungsID() {
        return behandlungsID;
    }

    public String getTierID() {
        return tierID;
    }

    public String getTierarztID() {
        return tierarztID;
    }

    public String getDatum() {
        return datum;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public double getBehandlungskosten() {
        return behandlungskosten;
    }

    public String getBehandlungsart() {
        return behandlungsart;
    }

    public String getZahlungsstatus() {
        return zahlungsstatus;
    }

    public String getRechnungsdatum() {
        return rechnungsdatum;
    }

    public void setBehandlungsID(String behandlungsID) {
        this.behandlungsID = behandlungsID;
    }

    public void setTierID(String tierID) {
        this.tierID = tierID;
    }

    public void setTierarztID(String tierarztID) {
        this.tierarztID = tierarztID;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    public void setBehandlungskosten(double behandlungskosten) {
        this.behandlungskosten = behandlungskosten;
    }

    public void setBehandlungsart(String behandlungsart) {
        this.behandlungsart = behandlungsart;
    }

    public void setZahlungsstatus(String zahlungsstatus) {
        this.zahlungsstatus = zahlungsstatus;
    }

    public void setRechnungsdatum(String rechnungsdatum) {
        this.rechnungsdatum = rechnungsdatum;
    }

    @Override
    public String toString() {
        return "Behandlungs-ID: " + behandlungsID +
                "\nTier-ID: " + tierID +
                "\nTierarzt-ID: " + tierarztID +
                "\nDatum: " + datum +
                "\nDiagnose: " + diagnose +
                "\nBehandlungskosten: " + behandlungskosten +
                "\nBehandlungsart: " + behandlungsart +
                "\nZahlungsstatus: " + zahlungsstatus +
                "\nRechnungsdatum: " + rechnungsdatum + "\n";
    }
}
