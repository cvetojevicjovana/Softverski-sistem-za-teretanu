/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.odabraniTrening;

import domen.OdabraniTrening;
import domen.OpstiDomenskiObjekat;
import domen.Termin;
import java.util.ArrayList;
import java.util.List;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author Roki
 */
public class PronadjiOdabraniTreningSO extends OpstaSistemskaOperacija {

    private ArrayList<OpstiDomenskiObjekat> odTreninzi;

    @Override
    protected void validacija(Object objekat) throws Exception {
        if (!(objekat instanceof OdabraniTrening)) {
            throw new Exception("Pogresan parametar..");
        }
    }

    @Override
    protected void izvrsi(Object objekat) throws Exception {
        odTreninzi = (ArrayList<OpstiDomenskiObjekat>) brokerBaze.vratiSve((OpstiDomenskiObjekat) objekat);
        for (OpstiDomenskiObjekat opstiDomenskiObjekat : odTreninzi) {
            OdabraniTrening ot = (OdabraniTrening) opstiDomenskiObjekat;
            int id = ot.getOdabraniTreningID();
            Termin t = new Termin();
            t.setOdTrening(ot);
            List<OpstiDomenskiObjekat> lista = brokerBaze.vratiSve(t);
            ArrayList<Termin> termini = new ArrayList<>();
            for (OpstiDomenskiObjekat opstiDomenskiObjekat1 : lista) {
                t = (Termin) opstiDomenskiObjekat1;
                termini.add(t);
            }
            ot.setTermin(termini);
        }
    }

    public ArrayList<OpstiDomenskiObjekat> getOdTreninzi() {
        return odTreninzi;
    }

    public void setOdTreninzi(ArrayList<OpstiDomenskiObjekat> odTreninzi) {
        this.odTreninzi = odTreninzi;
    }

}
