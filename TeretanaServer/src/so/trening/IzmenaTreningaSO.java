/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.trening;

import domen.OpstiDomenskiObjekat;
import domen.Trening;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author Roki
 */
public class IzmenaTreningaSO extends OpstaSistemskaOperacija {

    private Trening trening;

    @Override
    protected void validacija(Object objekat) throws Exception {
        if (!(objekat instanceof Trening)) {
            throw new Exception("Pogresan parametar..");
        }
    }

    @Override
    protected void izvrsi(Object objekat) throws Exception {
        brokerBaze.izmeni((OpstiDomenskiObjekat) objekat);
    }

    public Trening getTrening() {
        return trening;
    }

    public void setTrening(Trening trening) {
        this.trening = trening;
    }

}
