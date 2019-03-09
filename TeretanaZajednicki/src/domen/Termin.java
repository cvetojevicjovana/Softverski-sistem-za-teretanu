/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Roki
 */
public class Termin implements OpstiDomenskiObjekat, Serializable {

    private OdabraniTrening odTrening;
    private int redniBroj;
    private Date datum;
    private Date vreme;

    public Termin() {
    }

    public Termin(OdabraniTrening odTrening, int redniBroj, Date datum, Date vreme) {
        this.odTrening = odTrening;
        this.redniBroj = redniBroj;
        this.datum = datum;
        this.vreme = vreme;
    }

    public int getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(int redniBroj) {
        this.redniBroj = redniBroj;
    }

    public OdabraniTrening getOdTrening() {
        return odTrening;
    }

    public void setOdTrening(OdabraniTrening odTrening) {
        this.odTrening = odTrening;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Date getVreme() {
        return vreme;
    }

    public void setVreme(Date vreme) {
        this.vreme = vreme;
    }

    @Override
    public String vratiVrednostiAtributa() {
        return redniBroj + ", " + odTrening.getOdabraniTreningID() + ", " + (datum == null ? null : "'" + new java.sql.Date(datum.getTime()) + "'") + ", " + (vreme == null ? null : "'" + new java.sql.Time(vreme.getTime()) + "'");
    }

    @Override
    public String postaviVrednostAtrbuta() {
        return "redniBroj=" + redniBroj + ", odabraniTreningID=" + (odTrening == null ? null : "'" + odTrening.getOdabraniTreningID() + "'") + ", datum=" + (datum == null ? null : "'" + datum + "'") + ", vreme=" + (vreme == null ? null : "'" + vreme + "'");
    }

    @Override
    public String vratiImeKlase() {
        return "Termin";
    }

    @Override
    public String vratiUslovZaNadjiSlog() {
        return "redniBroj=" + redniBroj + " AND odabraniTreningID=" + odTrening.getOdabraniTreningID();
    }

    @Override
    public String vratiUslovZaNadjiSlogove() {
        return " WHERE odabraniTreningID=" + odTrening.getOdabraniTreningID();
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

                int radniBr = rs.getInt("redniBroj");
                Date datum = rs.getDate("datum");
                Time vreme = rs.getTime("vreme");

                OdabraniTrening ot = new OdabraniTrening(idOT, datumU, datumV, status, tr, kor, null);
                Termin t = new Termin(ot, redniBroj, datum, vreme);

                lista.add(t);
            }
            return lista;
        } catch (Exception ex) {
            throw new Exception("Greska prilikom uzimanja vednosti iz baze.");
        }
    }

    @Override
    public void ubaci(String nazivAtributa, Object vrednostAtributa
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiUslovZaSortiranje() throws Exception {
        return "ORDER BY t.nazivTreninga ASC";
    }

    @Override
    public String vratiTabeluSaUslovomSpajanja() {
        return "tt INNER JOIN odabraniTrening ot ON ot.odabraniID= tt.odabraniTreningID INNER JOIN Korisnik k ON ot.korisnikID=k.korisnikID INNER JOIN Trening t ON ot.treningID = t.treningID";
    }

    @Override
    public String vratiID() {
        return "redniBroj";
    }

}
