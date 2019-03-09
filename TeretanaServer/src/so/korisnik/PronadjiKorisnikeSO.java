/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.korisnik;

import domen.Korisnik;
import domen.OpstiDomenskiObjekat;
import java.util.ArrayList;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author Roki
 */
public class PronadjiKorisnikeSO extends OpstaSistemskaOperacija {

    private ArrayList<OpstiDomenskiObjekat> korisnici;

    @Override
    protected void validacija(Object objekat) throws Exception {
        if (!(objekat instanceof Korisnik)) {
            throw new Exception("Pogresan parametar..");
        }
    }

    @Override
    protected void izvrsi(Object objekat) throws Exception {
        korisnici = (ArrayList<OpstiDomenskiObjekat>) brokerBaze.vratiSve((OpstiDomenskiObjekat) objekat);
    }

    public ArrayList<OpstiDomenskiObjekat> getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(ArrayList<OpstiDomenskiObjekat> korisnici) {
        this.korisnici = korisnici;
    }

}
