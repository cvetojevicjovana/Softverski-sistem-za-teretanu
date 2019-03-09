/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forma.Trening.model;

import domen.Korisnik;
import domen.Trening;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Roki
 */
public class TableModelTrening extends AbstractTableModel {

    String[] kolone = {"ID", "Naziv", "Opis", "Broj termina", "Cena"};
    private ArrayList<Trening> treninzi;

    public TableModelTrening() {
        treninzi = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return treninzi.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Trening t = treninzi.get(row);
        switch (column) {
            case 0:
                return t.getTreningID();
            case 1:
                return t.getNaziv();
            case 2:
                return t.getOpis();
            case 3:
                return t.getBrojTermina();
            case 4:
                return t.getCena();
            default:
                return "GRESKA";
        }
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    public void dodajElement(Trening t) {
        treninzi.add(t);
        fireTableDataChanged();
    }

    public void obrisiElement(int row) {
        treninzi.remove(row);
        fireTableDataChanged();
    }

    public ArrayList<Trening> getTreninzi() {
        return treninzi;
    }

    public void setTreninzi(ArrayList<Trening> treninzi) {
        this.treninzi = treninzi;
        fireTableDataChanged();
    }

    public void isprazniTabelu() {
        treninzi = new ArrayList<>();
        fireTableDataChanged();
    }

}
