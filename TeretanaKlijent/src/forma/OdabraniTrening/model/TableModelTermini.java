/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forma.OdabraniTrening.model;

import domen.Termin;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Roki
 */
public class TableModelTermini extends AbstractTableModel {

    private ArrayList<Termin> termini;
    String[] kolone = {"Redni broj", "Datum", "Vreme"};

    public TableModelTermini() {
        termini = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return termini.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Termin t = termini.get(row);
        switch (column) {
            case 0:
                return t.getRedniBroj();
            case 1: {
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
                String datumNovi = sdf.format(t.getDatum());
                return datumNovi;
            }
            case 2: {
                SimpleDateFormat sdf2 = new SimpleDateFormat("kk:mm");
                String vremeNovo = sdf2.format(t.getVreme());
                return vremeNovo;
            }
            default:
                return "greska";
        }

    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    public void dodajelement(Termin t) {
        t.setRedniBroj(termini.size() + 1);
        termini.add(t);
        fireTableDataChanged();
    }

    public void obrisiElement(int row) {
        termini.remove(row);
        setRedniBroj();
        fireTableDataChanged();
    }

    private void setRedniBroj() {
        int rbr = 0;
        for (Termin termin : termini) {
            termin.setRedniBroj(++rbr);
        }
    }

    public ArrayList<Termin> getTermini() {
        return termini;
    }

    public void setTermini(ArrayList<Termin> termini) {
        int rbr = 0;
        for (Termin termin : termini) {
            termin.setRedniBroj(++rbr);
        }
        this.termini = termini;
        fireTableDataChanged();
    }

}
