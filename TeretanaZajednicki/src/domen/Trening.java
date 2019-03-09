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
public class Trening implements OpstiDomenskiObjekat, Serializable {

    private int treningID;
    private String naziv;
    private String opis;
    private int brojTermina;
    private double cena;

    public Trening() {
    }

    public Trening(int treningID, String naziv, String opis, int brojTermina, double cena) {
        this.treningID = treningID;
        this.naziv = naziv;
        this.opis = opis;
        this.brojTermina = brojTermina;
        this.cena = cena;
    }

    @Override
    public String toString() {
        return naziv;
    }

    public Trening(String naziv, String opis, int brojTermina, double cena) {
        this.naziv = naziv;
        this.opis = opis;
        this.brojTermina = brojTermina;
        this.cena = cena;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public int getTreningID() {
        return treningID;
    }

    public void setTreningID(int treningID) {
        this.treningID = treningID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getBrojTermina() {
        return brojTermina;
    }

    public void setBrojTermina(int brojTermina) {
        this.brojTermina = brojTermina;
    }

    @Override
    public String vratiVrednostiAtributa() {
        return treningID + ", " + (naziv == null ? null : "'" + naziv + "'") + ", " + (opis == null ? null : "'" + opis + "'") + ", " + (brojTermina < 0 ? null : brojTermina) + ", " + (cena < 0 ? null : cena);
    }

    @Override
    public String postaviVrednostAtrbuta() {
        return "treningID=" + treningID + ", nazivTreninga=" + (naziv == null ? null : "'" + naziv + "'") + ", opis=" + (opis == null ? null : "'" + opis + "'") + ", brojTermina=" + (brojTermina < 0 ? null : brojTermina) + ", cena=" + (cena < 0 ? null : cena);

    }

    @Override
    public String vratiImeKlase() {
        return "Trening";
    }

    @Override
    public String vratiUslovZaNadjiSlog() {
        return "treningID=" + treningID;
    }

    @Override
    public String vratiUslovZaNadjiSlogove() {
        String rez = "";
        if (naziv != null) {
            rez += "nazivTreninga='" + naziv + "' AND ";
        }
        if (treningID != 0) {
            rez += "treningID=" + treningID + " AND ";
        }
        if (cena > 0) {
            rez += "cena=" + cena + " AND ";
        }
        if (opis != null) {
            rez += "opis LIKE '%" + opis + "%' AND ";
        }
        rez = rez.substring(0, rez.length() - 4);
        return "where " + rez;
    }

    @Override
    public String vratiImeKolone(int brojKolone) {
        String[] kolone = {"treningID", "naziv", "opis", "brojTermina", "cena"};
        return kolone[brojKolone];
    }

    @Override
    public OpstiDomenskiObjekat napuni(ResultSet rs) throws Exception {
        Trening trening = new Trening();
        try {
            while (rs.next()) {
                int id = rs.getInt("treningID");
                String naziv = rs.getString("naziv");
                String opis = rs.getString("opis");
                int brTermina = rs.getInt("brojTermina");
                double cena = rs.getDouble("cena");
                trening = new Trening(id, naziv, opis, brTermina, cena);
            }
            return trening;
        } catch (Exception ex) {
            throw new Exception("Greska prilikom citanja iz baze.");
        }
    }

    @Override
    public ArrayList<OpstiDomenskiObjekat> napuniSve(ResultSet rs) throws Exception {
        ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();
        try {
            while (rs.next()) {
                int id = rs.getInt("treningID");
                String naz = rs.getString("nazivTreninga");
                String op = rs.getString("opis");
                int brojTerm = rs.getInt("brojTermina");
                double cenaa = rs.getDouble("cena");

                Trening tr = new Trening(id, naz, op, brojTerm, cenaa);
                lista.add(tr);
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
        return " ORDER BY nazivTreninga ASC";
    }

    @Override
    public String vratiTabeluSaUslovomSpajanja() {
        return "";
    }

    @Override
    public String vratiID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
