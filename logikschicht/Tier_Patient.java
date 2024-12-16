package logikschicht;

public class Tier_Patient {
    private String tierID;
    private String tierName;
    private String art;
    private String rasse;
    private String geburtsdatum;
    private String besitzerID;
    private static int zaehler = 0;

    public Tier_Patient() {
    }

    public Tier_Patient(String tierID, String tierName, String art, String rasse, String geburtsdatum,
            String besitzerID) {
        zaehler++;
        tierID = "" + zaehler;
        this.tierName = tierName;
        this.art = art;
        this.rasse = rasse;
        this.geburtsdatum = geburtsdatum;
        this.besitzerID = besitzerID;
    }

    public String getTierID() {
        return tierID;
    }

    public String getTierName() {
        return tierName;
    }

    public String getArt() {
        return art;
    }

    public String getRasse() {
        return rasse;
    }

    public String getGeburtsdatum() {
        return geburtsdatum;
    }

    public String getBesitzerID() {
        return besitzerID;
    }

    public void setTierID(String tierID) {
        this.tierID = tierID;
    }

    public void setTierName(String tierName) {
        this.tierName = tierName;
    }

    public void setArt(String art) {
        this.art = art;
    }

    public void setRasse(String rasse) {
        this.rasse = rasse;
    }

    public void setGeburtsdatum(String geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public void setBesitzerID(String besitzerID) {
        this.besitzerID = besitzerID;
    }

    @Override
    public String toString() {
        return "Tier_ID: " + tierID +
                "\nTier_Name: " + tierName +
                "\nArt: " + art +
                "\nRasse: " + rasse +
                "\nGeburtsdatum: " + geburtsdatum +
                "\nBesitzer_ID: " + besitzerID + "\n";
    }
}
