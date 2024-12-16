package logikschicht;

public class Tierarzt {
    private String tierarztID;
    private String arztNachname;
    private String arztVorname;
    private String fachgebiet;
    private String telefonnummer;
    private String vorgesetzterID;
    private static int zaehler = 0;


    public Tierarzt() {
    }

    public Tierarzt(String tierarztID, String arztNachname, String arztVorname, String fachgebiet, String telefonnummer,
            String vorgesetzterID) {
        zaehler++;
        tierarztID = "" + zaehler;
        this.arztNachname = arztNachname;
        this.arztVorname = arztVorname;
        this.fachgebiet = fachgebiet;
        this.telefonnummer = telefonnummer;
        this.vorgesetzterID = vorgesetzterID;
    }

    public String getTierarztID() {
        return tierarztID;
    }

    public String getArztNachname() {
        return arztNachname;
    }

    public String getArztVorname() {
        return arztVorname;
    }

    public String getFachgebiet() {
        return fachgebiet;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public String getVorgesetzterID() {
        return vorgesetzterID;
    }

    public void setTierarztID(String tierarztID) {
        this.tierarztID = tierarztID;
    }

    public void setArztNachname(String arztNachname) {
        this.arztNachname = arztNachname;
    }

    public void setArztVorname(String arztVorname) {
        this.arztVorname = arztVorname;
    }

    public void setFachgebiet(String fachgebiet) {
        this.fachgebiet = fachgebiet;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public void setVorgesetzterID(String vorgesetzterID) {
        this.vorgesetzterID = vorgesetzterID;
    }

    @Override
    public String toString() {
        return "Tierarzt-ID: " + tierarztID +
                "\nArzt-Nachname: " + arztNachname +
                "\nArzt-Vorname: " + arztVorname +
                "\nFachgebiet: " + fachgebiet +
                "\nTelefonnummer: " + telefonnummer +
                "\nVorgesetzter-ID: " + vorgesetzterID + "\n";
    }
}
