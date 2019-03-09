/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Roki
 */
public class Korisnik implements OpstiDomenskiObjekat, Serializable {

    private int korisnikID;
    private String imePrezime;
    private String eMail;
    private String telefon;
    private Radnik radnik;

    public Korisnik() {
    }

    @Override
    public String toString() {
        return imePrezime;
    }

    public Korisnik(int korisnikID, String imePrezime, String eMail, String telefon, Radnik radnik) {
        this.korisnikID = korisnikID;
        this.imePrezime = imePrezime;
        this.eMail = eMail;
        this.telefon = telefon;
        this.radnik = radnik;
    }

    public Korisnik(String imePrezime, String eMail, String telefon, Radnik radnik) {
        this.imePrezime = imePrezime;
        this.eMail = eMail;
        this.telefon = telefon;
        this.radnik = radnik;
    }

    public int getKorisnikID() {
        return korisnikID;
    }

    public void setKorisnikID(int korisnikID) {
        this.korisnikID = korisnikID;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Radnik getRadnik() {
        return radnik;
    }

    public void setRadnik(Radnik radnik) {
        this.radnik = radnik;
    }

    @Override
    public String vratiVrednostiAtributa() {
        return korisnikID + ", " + (imePrezime == null ? null : "'" + imePrezime + "'") + ", " + (eMail == null ? null : "'" + eMail + "'") + ", " + (telefon == null ? null : "'" + telefon + "'") + ", " + (radnik == null ? null : "'" + radnik.getRadnikID() + "'");
    }

    @Override
    public String postaviVrednostAtrbuta() {
        return "korisnikID=" + korisnikID + ", imePrezime=" + (imePrezime == null ? null : "'" + imePrezime + "'") + ", email=" + (eMail == null ? null : "'" + eMail + "'") + ", telefon=" + (telefon == null ? null : "'" + telefon + "'") + ", radnikID=" + (radnik == null ? null : "'" + radnik.getRadnikID() + "'");
    }

    @Override
    public String vratiImeKlase() {
        return "Korisnik";
    }

    @Override
    public String vratiUslovZaNadjiSlog() {
        return "korisnikID=" + korisnikID;
    }

    @Override
    public String vratiUslovZaNadjiSlogove() {
        String rez = "";
        if (imePrezime != null) {
            rez += "k.imePrezime='" + imePrezime + "' AND ";
        }
        if (korisnikID != 0) {
            rez += "k.korisnikID=" + korisnikID + " AND ";
        }
        if (radnik != null && radnik.getImePrezime() != null) {
            rez += "r.imePrezime LIKE '%" + radnik.getImePrezime() + "%' AND ";
        }
        rez = rez.substring(0, rez.length() - 4);
        return "where " + rez;
    }

    @Override
    public String vratiImeKolone(int brojKolone) {
        String[] kolone = {"korisnikID", "imePrezime", "email", "telefon", "radnikID"};
        return kolone[brojKolone];
    }

    @Override
    public ArrayList<OpstiDomenskiObjekat> napuniSve(ResultSet rs) throws Exception {
        ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();
        try {
            while (rs.next()) {
                int id = rs.getInt("k.korisnikID");
                String ime = rs.getString("k.imePrezime");
                String eMail = rs.getString("k.email");
                String telefon = rs.getString("k.telefon");

                int radnikID = rs.getInt("k.radnikID");
                String ImeRadnika = rs.getString("r.imePrezime");
                String korIme = rs.getString("r.username");
                String sifra = rs.getString("r.password");
                String telefonRadnika = rs.getString("r.telefon");

                Radnik r = new Radnik(radnikID, ImeRadnika, korIme, sifra, telefonRadnika);
                Korisnik k = new Korisnik(id, ime, eMail, telefon, r);
                lista.add(k);

            }
            return lista;
        } catch (Exception ex) {
            throw new Exception("Greska prilikom uzimanja vednosti iz baze.");
        }
    }

    @Override
    public void ubaci(String nazivAtributa, Object vrednostAtributa) {
        switch (nazivAtributa) {
            case "korisnikID":
                setKorisnikID((int) vrednostAtributa);
                break;
            case "imePrezime":
                setImePrezime((String) vrednostAtributa);
                break;
            case "eMail":
                seteMail((String) vrednostAtributa);
                break;
            case "telefon":
                setTelefon((String) vrednostAtributa);
                break;
            case "radnikID":
                setRadnik((Radnik) vrednostAtributa);
                break;
        }
    }

    @Override
    public String vratiUslovZaSortiranje() {
        return "ORDER BY k.imePrezime ASC";
    }

    @Override
    public String vratiTabeluSaUslovomSpajanja() {
        return " k INNER JOIN radnik r ON k.radnikID = r.radnikID";
    }

    @Override
    public OpstiDomenskiObjekat napuni(ResultSet rs) throws Exception {
        Korisnik k = new Korisnik();
        try {
            while (rs.next()) {
                int id = rs.getInt("k.korisnikID");
                String ime = rs.getString("k.imePrezime");
                String eMail = rs.getString("k.email");
                String telefon = rs.getString("k.telefon");

                int radnikID = rs.getInt("k.radnikID");
                String ImeRadnika = rs.getString("r.imePrezime");
                String korIme = rs.getString("r.username");
                String sifra = rs.getString("r.password");
                String telefonRadnika = rs.getString("r.telefon");

                Radnik r = new Radnik(radnikID, ImeRadnika, korIme, sifra, telefonRadnika);
                k = new Korisnik(id, ime, eMail, telefon, r);
            }
            return k;
        } catch (Exception ex) {
            throw new Exception("Neusplo citanje iz baze");
        }
    }

    @Override
    public String vratiID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
