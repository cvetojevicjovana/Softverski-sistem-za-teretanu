/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.korisnik;

import domen.Korisnik;
import domen.OpstiDomenskiObjekat;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author Roki
 */
public class UcitajKorisnikaSO extends OpstaSistemskaOperacija {

    private Korisnik korisnik;

    @Override
    protected void validacija(Object objekat) throws Exception {
        if (!(objekat instanceof Korisnik)) {
            throw new Exception("Pogresan parametar..");
        }
    }

    @Override
    protected void izvrsi(Object objekat) throws Exception {
        brokerBaze.vratiJednog((OpstiDomenskiObjekat) objekat);
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

}
