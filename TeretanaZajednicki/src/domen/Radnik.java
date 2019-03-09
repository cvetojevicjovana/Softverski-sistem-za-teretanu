/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Roki
 */
public class Radnik implements OpstiDomenskiObjekat, Serializable {

    private int radnikID;
    private String imePrezime;
    private String korisnickoIme;
    private String sifra;
    private String telefon;

    public Radnik() {
    }

    public Radnik(int radnikID, String imePrezime, String korisnickoIme, String sifra, String telefon) {
        this.radnikID = radnikID;
        this.imePrezime = imePrezime;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
        this.telefon = telefon;
    }

    public Radnik(String imePrezime, String korisnickoIme, String sifra, String telefon) {
        this.imePrezime = imePrezime;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
        this.telefon = telefon;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public int getRadnikID() {
        return radnikID;
    }

    public void setRadnikID(int radnikID) {
        this.radnikID = radnikID;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    @Override
    public String toString() {
        return imePrezime;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Radnik other = (Radnik) obj;
        if (!Objects.equals(this.korisnickoIme, other.korisnickoIme)) {
            return false;
        }
        return true;
    }

    @Override
    public String vratiVrednostiAtributa() {
        return radnikID + ", " + (imePrezime == null ? null : "'" + imePrezime + "'") + ", " + (korisnickoIme == null ? null : "'" + korisnickoIme + "'") + ", " + (sifra == null ? null : "'" + sifra + "'") + ", " + (telefon == null ? null : "'" + telefon + "'");
    }

    @Override
    public String postaviVrednostAtrbuta() {
        return "radnikID=" + radnikID + ", imePrezime=" + (imePrezime == null ? null : "'" + imePrezime + "'") + ", username=" + (korisnickoIme == null ? null : "'" + korisnickoIme + "'") + ", password=" + (sifra == null ? null : "'" + sifra + "'") + ", telefon=" + (telefon == null ? null : "'" + telefon + "'");
    }

    @Override
    public String vratiImeKlase() {
        return "Radnik";
    }

    @Override
    public String vratiUslovZaNadjiSlog() {
        // return "radnikID="+radnikID;
        return "username=" + "'" + korisnickoIme + "' AND " + "password=" + "'" + sifra + "'";
    }

    @Override
    public String vratiUslovZaNadjiSlogove() {
        return "username=" + korisnickoIme + "password=" + sifra;
    }

    @Override
    public String vratiImeKolone(int brojKolone) {
        String[] kolone = {"radnikID, imePrezime, username, password, telefon"};
        return kolone[brojKolone];
    }

    @Override
    public OpstiDomenskiObjekat napuni(ResultSet rs) throws Exception {
        Radnik radnik = new Radnik();
        try {
            while (rs.next()) {
                int id = rs.getInt("radnikID");
                String ime = rs.getString("imePrezime");
                String korIme = rs.getString("username");
                String pass = rs.getString("password");
                String tele = rs.getString("telefon");
                radnik = new Radnik(id, ime, korIme, pass, tele);
            }
            return radnik;
        } catch (Exception ex) {
            throw new Exception("Greska prilikom uzimanja vrednosti iz baze");
        }
    }

    @Override
    public void ubaci(String nazivAtributa, Object vrednostAtributa) {
        switch (nazivAtributa) {
            case "radnikID":
                setRadnikID((int) vrednostAtributa);
                break;
            case "imePrezime":
                setImePrezime((String) vrednostAtributa);
                break;
            case "username":
                setKorisnickoIme((String) vrednostAtributa);
                break;
            case "password":
                setImePrezime((String) vrednostAtributa);
                break;
            case "telefon":
                setTelefon((String) vrednostAtributa);
                break;
        }
    }

    @Override
    public String vratiUslovZaSortiranje() throws Exception {
        return "ORDER BY imePrezime ASC";
    }

    @Override
    public String vratiTabeluSaUslovomSpajanja() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<OpstiDomenskiObjekat> napuniSve(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
