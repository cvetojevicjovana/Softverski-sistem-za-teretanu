/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.odabraniTrening;

import domen.OdabraniTrening;
import domen.OpstiDomenskiObjekat;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author Roki
 */
public class UcitajOdabraniTreningSO extends OpstaSistemskaOperacija {

    private OdabraniTrening odTrening;

    @Override
    protected void validacija(Object objekat) throws Exception {
        if (!(objekat instanceof OdabraniTrening)) {
            throw new Exception("Pogresan parametar..");
        }
    }

    @Override
    protected void izvrsi(Object objekat) throws Exception {
        brokerBaze.vratiJednog((OpstiDomenskiObjekat) objekat);
    }

    public OdabraniTrening getOdTrening() {
        return odTrening;
    }

    public void setOdTrening(OdabraniTrening odTrening) {
        this.odTrening = odTrening;
    }

}
