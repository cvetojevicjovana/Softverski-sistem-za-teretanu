/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forma.OdabraniTrening.model;

import domen.OdabraniTrening;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Roki
 */
public class TableModelOdabraniTreninzi extends AbstractTableModel {

    private ArrayList<OdabraniTrening> odTreninzi;
    String[] kolone = {"ID", "Korisnik", "Trening", "DatumUplate", "DatumVazenja", "Status"};

    public TableModelOdabraniTreninzi() {
        odTreninzi = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return odTreninzi.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        OdabraniTrening ot = odTreninzi.get(row);
        switch (column) {
            case 0:
                return ot.getOdabraniTreningID();
            case 1:
                return ot.getKorisnik().getImePrezime();
            case 2:
                return ot.getTrening().getNaziv();
            case 3: {
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
                String datumNovi = sdf.format(ot.getDatumUplate());
                return datumNovi;
            }
            case 4: {
                SimpleDateFormat sdf2 = new SimpleDateFormat("kk:mm");
                String vremeNovo = sdf2.format(ot.getDatumVazenja());
                return vremeNovo;
            }
            case 5:
                return ot.getStatus();
            default:
                return "greska";
        }
    }

    public ArrayList<OdabraniTrening> getOdTreninzi() {
        return odTreninzi;
    }

    public void setOdTreninzi(ArrayList<OdabraniTrening> odTreninzi) {
        this.odTreninzi = odTreninzi;
        fireTableDataChanged();
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

}
