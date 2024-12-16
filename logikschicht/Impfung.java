package logikschicht;

public class Impfung {
    private String impfungID;
    private String medikamentID;
    private double erforderlicheDosis;
    private int intervall;
    private double kostenProDosis;
    private static int zaehler = 0;

    public Impfung() {
    }

    public Impfung(String impfungID, String medikamentID, double erforderlicheDosis, int intervall,
            double kostenProDosis) {
                zaehler++;
        impfungID = "" + zaehler;
        this.medikamentID = medikamentID;
        this.erforderlicheDosis = erforderlicheDosis;
        this.intervall = intervall;
        this.kostenProDosis = kostenProDosis;
    }

    public String getImpfungID() {
        return impfungID;
    }

    public String getMedikamentID() {
        return medikamentID;
    }

    public double getErforderlicheDosis() {
        return erforderlicheDosis;
    }

    public int getIntervall() {
        return intervall;
    }

    public double getKostenProDosis() {
        return kostenProDosis;
    }

    public void setImpfungID(String impfungID) {
        this.impfungID = impfungID;
    }

    public void setMedikamentID(String medikamentID) {
        this.medikamentID = medikamentID;
    }

    public void setErforderlicheDosis(double erforderlicheDosis) {
        this.erforderlicheDosis = erforderlicheDosis;
    }

    public void setIntervall(int intervall) {
        this.intervall = intervall;
    }

    public void setKostenProDosis(double kostenProDosis) {
        this.kostenProDosis = kostenProDosis;
    }

    @Override
    public String toString() {
        return "Impfung-ID: " + impfungID +
                "\nMedikamenten-ID: " + medikamentID +
                "\nErforderliche Dosis: " + erforderlicheDosis +
                "\nIntervall (in Monaten): " + intervall +
                "\nKosten pro Dosis: " + kostenProDosis + "\n";
    }
}
