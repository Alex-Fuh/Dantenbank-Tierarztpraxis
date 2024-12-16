package logikschicht;

public class Medikament {
    private String medikamentID;
    private String name;
    private String wirkstoff;
    private double kostenProEinheit;
    private static int zaehler = 0;

    public Medikament() {
    }

    public Medikament(String medikamentID, String name, String wirkstoff, double kostenProEinheit) {
        zaehler++;
        medikamentID = "" + zaehler;
        this.name = name;
        this.wirkstoff = wirkstoff;
        this.kostenProEinheit = kostenProEinheit;
    }

    public String getMedikamentID() {
        return medikamentID;
    }

    public String getName() {
        return name;
    }

    public String getWirkstoff() {
        return wirkstoff;
    }

    public double getKostenProEinheit() {
        return kostenProEinheit;
    }

    public void setMedikamentID(String medikamentID) {
        this.medikamentID = medikamentID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWirkstoff(String wirkstoff) {
        this.wirkstoff = wirkstoff;
    }

    public void setKostenProEinheit(double kostenProEinheit) {
        this.kostenProEinheit = kostenProEinheit;
    }

    @Override
    public String toString() {
        return "Medikament-ID: " + medikamentID +
                "\nName: " + name +
                "\nWirkstoff: " + wirkstoff +
                "\nKosten pro Einheit: " + kostenProEinheit + "\n";
    }
}
