/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.radnik;

import domen.OpstiDomenskiObjekat;
import domen.Radnik;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author Roki
 */
public class RegistracijaSO extends OpstaSistemskaOperacija {

    private Radnik radnik;

    @Override
    protected void validacija(Object objekat) throws Exception {
        if (!(objekat instanceof Radnik)) {
            throw new Exception("Pogresan parametar..");
        }
    }

    @Override
    protected void izvrsi(Object objekat) throws Exception {
        brokerBaze.ubaci((OpstiDomenskiObjekat) objekat);
    }

    public Radnik getRadnik() {
        return radnik;
    }

    public void setRadnik(Radnik radnik) {
        this.radnik = radnik;
    }

}
