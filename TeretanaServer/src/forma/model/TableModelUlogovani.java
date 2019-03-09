/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forma.model;

import domen.Radnik;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Roki
 */
public class TableModelUlogovani extends AbstractTableModel {

    String[] kolone = {"Ime i prezime", "Korisnicko ime", "Telefon"};
    private ArrayList<Radnik> radnici;

    public TableModelUlogovani() {
        radnici = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return radnici.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Radnik r = radnici.get(row);
        switch (column) {
            case 0:
                return r.getImePrezime();
            case 1:
                return r.getKorisnickoIme();
            case 2:
                return r.getTelefon();
            default:
                return "GRESKA";
        }
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    public void dodajElement(Radnik radnik) {
        radnici.add(radnik);
        fireTableDataChanged();
    }

    public void obrisiElement(int row) {
        radnici.remove(row);
        fireTableDataChanged();
    }

    public void odjaviRadnik(Radnik r) {
        radnici.remove(r);
        fireTableDataChanged();
    }

    public ArrayList<Radnik> getRadnici() {
        return radnici;
    }

    public void setRadnici(ArrayList<Radnik> radnici) {
        this.radnici = radnici;
    }

    public void obrisiElemente() {
        this.radnici = new ArrayList<>();
    }

}
