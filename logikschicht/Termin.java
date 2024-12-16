package logikschicht;

public class Termin {
    private String datumUndUhrzeit;
    private String tierID;
    private String besitzerID;
    private String tierarztID;
    private String grund;

    public Termin() {
    }

    public Termin(String datumUndUhrzeit, String tierID, String besitzerID, String tierarztID, String grund) {
        this.datumUndUhrzeit = datumUndUhrzeit;
        this.tierID = tierID;
        this.besitzerID = besitzerID;
        this.tierarztID = tierarztID;
        this.grund = grund;
    }

    public String getDatumUndUhrzeit() {
        return datumUndUhrzeit;
    }

    public String getTierID() {
        return tierID;
    }

    public String getBesitzerID() {
        return besitzerID;
    }

    public String getTierarztID() {
        return tierarztID;
    }

    public String getGrund() {
        return grund;
    }

    public void setDatumUndUhrzeit(String datumUndUhrzeit) {
        this.datumUndUhrzeit = datumUndUhrzeit;
    }

    public void setTierID(String tierID) {
        this.tierID = tierID;
    }

    public void setBesitzerID(String besitzerID) {
        this.besitzerID = besitzerID;
    }

    public void setTierarztID(String tierarztID) {
        this.tierarztID = tierarztID;
    }

    public void setGrund(String grund) {
        this.grund = grund;
    }

    @Override
    public String toString() {
        return "Datum und Uhrzeit: " + datumUndUhrzeit + 
               "\nTier-ID: " + tierID + 
               "\nBesitzer-ID: " + besitzerID + 
               "\nTierarzt-ID: " + tierarztID + 
               "\nGrund: " + grund + "\n";
    }
}
