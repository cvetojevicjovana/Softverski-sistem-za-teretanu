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
public class IzmenaOdabranogTreningaSO extends OpstaSistemskaOperacija {

    private OdabraniTrening odabraniTrening;

    @Override
    protected void validacija(Object objekat) throws Exception {
        if (!(objekat instanceof OdabraniTrening)) {
            throw new Exception("Pogresan parametar..");
        }
    }

    @Override
    protected void izvrsi(Object objekat) throws Exception {
        brokerBaze.izmeni((OpstiDomenskiObjekat) objekat);
        OdabraniTrening ot = (OdabraniTrening) objekat;
        ArrayList<Termin> stavke = ot.getTermin();
        for (Termin termin : stavke) {
            brokerBaze.izmeni(termin);
        }

    }

    public OdabraniTrening getOdabraniTrening() {
        return odabraniTrening;
    }

    public void setOdabraniTrening(OdabraniTrening odabraniTrening) {
        this.odabraniTrening = odabraniTrening;
    }

}
