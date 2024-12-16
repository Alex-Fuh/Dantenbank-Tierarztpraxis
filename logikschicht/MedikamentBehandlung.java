package logikschicht;

public class MedikamentBehandlung {
    private String behandlungsID;
    private String medikamentID;
    private String dosierung;
    private String dauer;

    public MedikamentBehandlung() {
    }

    public MedikamentBehandlung(String behandlungsID, String medikamentID, String dosierung, String dauer) {
        this.behandlungsID = behandlungsID;
        this.medikamentID = medikamentID;
        this.dosierung = dosierung;
        this.dauer = dauer;
    }

    public String getBehandlungsID() {
        return behandlungsID;
    }

    public String getMedikamentID() {
        return medikamentID;
    }

    public String getDosierung() {
        return dosierung;
    }

    public String getDauer() {
        return dauer;
    }

    public void setBehandlungsID(String behandlungsID) {
        this.behandlungsID = behandlungsID;
    }

    public void setMedikamentID(String medikamentID) {
        this.medikamentID = medikamentID;
    }

    public void setDosierung(String dosierung) {
        this.dosierung = dosierung;
    }

    public void setDauer(String dauer) {
        this.dauer = dauer;
    }

    @Override
    public String toString() {
        return "Behandlungs-ID: " + behandlungsID + 
               "\nMedikament-ID: " + medikamentID + 
               "\nDosierung: " + dosierung + 
               "\nDauer: " + dauer + "\n";
    }
}
