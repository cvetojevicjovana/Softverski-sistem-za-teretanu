
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
import so.OpstaSistemskaOperacija;

/**
 *
 * @author Roki
 */
public class UnosOdabranogTreningaSO extends OpstaSistemskaOperacija {

    @Override
    protected void validacija(Object objekat) throws Exception {
        if (!(objekat instanceof OdabraniTrening)) {
            throw new Exception("Pogresan parametar..");
        }
    }

    @Override
    protected void izvrsi(Object objekat) throws Exception {
        OdabraniTrening ot = (OdabraniTrening) objekat;
        int id = brokerBaze.vratiMaxID((OpstiDomenskiObjekat) objekat) + 1;
        ot.setOdabraniTreningID(id);
        brokerBaze.ubaci((OpstiDomenskiObjekat) objekat);
        ArrayList<Termin> stavke = ot.getTermin();
        for (Termin termin : stavke) {
            termin.setOdTrening(ot);
            brokerBaze.ubaci(termin);
        }
    }

}
