/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Roki
 */
public class OdabraniTrening implements OpstiDomenskiObjekat, Serializable {

    private int odabraniTreningID;
    private Date datumUplate;
    private Date datumVazenja;
    private String status;
    private Trening trening;
    private Korisnik korisnik;
    private ArrayList<Termin> termini;

    public OdabraniTrening() {
        termini = new ArrayList<>();
    }

    public OdabraniTrening(int odabraniTreningID, Date datumUplate, Date datumVazenja, String status, Trening trening, Korisnik klijent, ArrayList<Termin> termini) {
        this.odabraniTreningID = odabraniTreningID;
        this.datumUplate = datumUplate;
        this.datumVazenja = datumVazenja;
        this.status = status;
        this.trening = trening;
        this.korisnik = klijent;
        this.termini = termini;
    }

    public int getOdabraniTreningID() {
        return odabraniTreningID;
    }

    public void setOdabraniTreningID(int odabraniTreningID) {
        this.odabraniTreningID = odabraniTreningID;
    }

    public Date getDatumUplate() {
        return datumUplate;
    }

    public void setDatumUplate(Date datumUplate) {
        this.datumUplate = datumUplate;
    }

    public Date getDatumVazenja() {
        return datumVazenja;
    }

    public void setDatumVazenja(Date datumVazenja) {
        this.datumVazenja = datumVazenja;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Trening getTrening() {
        return trening;
    }

    public void setTrening(Trening trening) {
        this.trening = trening;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public ArrayList<Termin> getTermin() {
        return termini;
    }

    public void setTermin(ArrayList<Termin> termin) {
        this.termini = termin;
    }

    @Override
    public String vratiVrednostiAtributa() {
        return odabraniTreningID + ", " + (datumUplate == null ? null : "'" + new java.sql.Date(datumUplate.getTime()) + "'") + ", " + (datumVazenja == null ? null : "'" + new java.sql.Date(datumVazenja.getTime()) + "'") + ", " + (status == null ? null : "'" + status + "'") + ", " + (korisnik == null ? null : "'" + korisnik.getKorisnikID() + "'") + ", " + (trening == null ? null : "'" + trening.getTreningID() + "'");
    }

    @Override
    public String postaviVrednostAtrbuta() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiImeKlase() {
        return "OdabraniTrening";
    }

    @Override
    public String vratiUslovZaNadjiSlog() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiUslovZaNadjiSlogove() {
        String rez = "";
        if (korisnik != null && korisnik.getImePrezime() != null) {
            rez += "k.imePrezime='" + korisnik.getImePrezime() + "' AND ";
        }
        if (trening != null) {
            rez += "t.nazivTreninga='" + trening.getNaziv() + "' AND ";
        }
        if (status != null) {
            rez += "ot.status='" + status + "' AND ";
        }
        rez = rez.substring(0, rez.length() - 4);
        return " where " + rez;
    }

    @Override
    public String vratiImeKolone(int brojKolone) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OpstiDomenskiObjekat napuni(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<OpstiDomenskiObjekat> napuniSve(ResultSet rs) throws Exception {
        ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();
        try {
            while (rs.next()) {
                int idOT = rs.getInt("ot.odabraniID");
                Date datumU = rs.getDate("ot.datumUplate");
                Date datumV = rs.getDate("ot.datumVazenja");
                String status = rs.getString("ot.status");

                int kID = rs.getInt("k.korisnikID");
                String ime = rs.getString("imePrezime");
                String tel = rs.getString("telefon");
                String mail = rs.getString("eMail");
                Korisnik kor = new Korisnik(kID, ime, mail, tel, null);

                int trID = rs.getInt("t.treningID");
                String naz = rs.getString("t.nazivTreninga");
                String op = rs.getString("t.opis");
                int brTr = rs.getInt("t.brojTermina");
                double cena = rs.getDouble("t.cena");
                Trening tr = new Trening(trID, naz, op, brTr, cena);

                OdabraniTrening ot = new OdabraniTrening(idOT, datumU, datumV, status, tr, kor, null);
                lista.add(ot);
            }
            return lista;
        } catch (Exception ex) {
            throw new Exception("Greska prilikom uzimanja vednosti iz baze.");
        }
    }

    @Override
    public void ubaci(String nazivAtributa, Object vrednostAtributa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiUslovZaSortiranje() throws Exception {
        return " ORDER BY k.imePrezime ASC ";
    }

    @Override
    public String vratiTabeluSaUslovomSpajanja() {
        return " ot INNER JOIN Korisnik k ON ot.korisnikID=k.korisnikID INNER JOIN Trening t ON ot.treningID = t.treningID";
    }

    @Override
    public String vratiID() {
        return "odabraniID";
    }

}
