/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.trening;

import domen.OpstiDomenskiObjekat;
import domen.Trening;
import java.util.ArrayList;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author Roki
 */
public class VratiTreningeSO extends OpstaSistemskaOperacija {

    private ArrayList<OpstiDomenskiObjekat> treninzi;

    @Override
    protected void validacija(Object objekat) throws Exception {
        if (!(objekat instanceof Trening)) {
            throw new Exception("Pogresan parametar..");
        }
    }

    @Override
    protected void izvrsi(Object objekat) throws Exception {
        treninzi = (ArrayList<OpstiDomenskiObjekat>) brokerBaze.vratiSveBezUslova((OpstiDomenskiObjekat) objekat);
    }

    public ArrayList<OpstiDomenskiObjekat> getTeninzi() {
        return treninzi;
    }

    public void setTeninzi(ArrayList<OpstiDomenskiObjekat> teninzi) {
        this.treninzi = teninzi;
    }

}
