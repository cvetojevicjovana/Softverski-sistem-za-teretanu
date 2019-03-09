/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import skladiste.BrokerBazeInterfejs;
import skladiste.baza.BrokerBaze;
import skladiste.baza.konekcija.BazaKonekcija;

/**
 *
 * @author Roki
 */
public abstract class OpstaSistemskaOperacija {

    protected BrokerBazeInterfejs brokerBaze;

    public OpstaSistemskaOperacija() {
        brokerBaze = new BrokerBaze();
    }

    public final void opsteIzvrsenje(Object objekat) throws Exception {
        try {
            validacija(objekat);
            try {
                pocniTransakciju();
                izvrsi(objekat);
                commit();
            } catch (Exception ex) {
                rollback();
                throw ex;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    protected abstract void validacija(Object objekat) throws Exception;

    private void pocniTransakciju() throws Exception {
        BazaKonekcija.getInstance().pocniTransakciju();
    }

    protected abstract void izvrsi(Object objekat) throws Exception;

    private void commit() throws Exception {
        BazaKonekcija.getInstance().commit();
    }

    private void rollback() throws Exception {
        BazaKonekcija.getInstance().rollback();
    }

}
