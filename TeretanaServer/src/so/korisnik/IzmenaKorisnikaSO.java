
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
public class IzmenaKorisnikaSO extends OpstaSistemskaOperacija {

    private Korisnik korsinik;

    @Override
    protected void validacija(Object objekat) throws Exception {
        if (!(objekat instanceof Korisnik)) {
            throw new Exception("Pogresan parametar..");
        }
    }

    @Override
    protected void izvrsi(Object objekat) throws Exception {
        brokerBaze.izmeni((OpstiDomenskiObjekat) objekat);
    }

    public Korisnik getKorsinik() {
        return korsinik;
    }

    public void setKorsinik(Korisnik korsinik) {
        this.korsinik = korsinik;
    }

}
