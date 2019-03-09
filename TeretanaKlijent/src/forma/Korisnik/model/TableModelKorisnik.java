/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forma.Korisnik.model;

import domen.Korisnik;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Roki
 */
public class TableModelKorisnik extends AbstractTableModel {

    String[] kolone = {"ID", "Ime i prezime", "E-Mail", "Telefon", "Radnik"};
    private ArrayList<Korisnik> korisnici;

    public TableModelKorisnik() {
        korisnici = new ArrayList<>();
    }

    public void setKorisnici(ArrayList<Korisnik> korisnici) {
        this.korisnici = korisnici;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return korisnici.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Korisnik k = korisnici.get(row);
        switch (column) {
            case 0:
                return k.getKorisnikID();
            case 1:
                return k.getImePrezime();
            case 2:
                return k.geteMail();
            case 3:
                return k.getTelefon();
            case 4:
                return k.getRadnik();
            default:
                return "GRESKA";
        }
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    public void dodajElement(Korisnik k) {
        korisnici.add(k);
        fireTableDataChanged();
    }

    public void obrisiElement(int row) {
        korisnici.remove(row);
        fireTableDataChanged();
    }

    public void isprazniTabelu() {
        korisnici = new ArrayList<>();
        fireTableDataChanged();
    }

    public ArrayList<Korisnik> getKorisnici() {
        return korisnici;
    }

}
