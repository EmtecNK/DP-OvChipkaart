package P.P3;

import java.time.LocalDate;

public class Reiziger {
    private int reiziger_id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private LocalDate geboortedatum;
    private Adres adres;

    // Constructor
    public Reiziger(int reiziger_id, String voorletters, String tussenvoegsel,
                    String achternaam, LocalDate geboortedatum) {
        this.reiziger_id = reiziger_id;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
    }

    // Getters and setters
    public int getReiziger_id() { return reiziger_id; }
    public void setReiziger_id(int reiziger_id) { this.reiziger_id = reiziger_id; }
    public String getVoorletters() { return voorletters; }
    public void setVoorletters(String voorletters) { this.voorletters = voorletters; }
    public String getTussenvoegsel() { return tussenvoegsel; }
    public void setTussenvoegsel(String tussenvoegsel) { this.tussenvoegsel = tussenvoegsel; }
    public String getAchternaam() { return achternaam; }
    public void setAchternaam(String achternaam) { this.achternaam = achternaam; }
    public LocalDate getGeboortedatum() { return geboortedatum; }
    public void setGeboortedatum(LocalDate geboortedatum) { this.geboortedatum = geboortedatum; }
    public Adres getAdres() { return adres; }
    public void setAdres(Adres adres) { this.adres = adres; }

    @Override
    public String toString() {
        return "Reiziger {#" + reiziger_id + " " + voorletters + ". " + (tussenvoegsel != null ? tussenvoegsel + " " : "") + achternaam + ", geb. " + geboortedatum + "}";
    }

}
