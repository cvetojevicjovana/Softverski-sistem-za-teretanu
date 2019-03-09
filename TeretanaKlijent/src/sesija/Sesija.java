/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesija;

import domen.OpstiDomenskiObjekat;
import domen.Radnik;
import java.util.Map;

/**
 *
 * @author Roki
 */
public class Sesija {

    private static Sesija instance;
    private Radnik radnik;
    private OpstiDomenskiObjekat objekat;

    private Sesija() {
    }

    public static Sesija getInstance() {
        if (instance == null) {
            instance = new Sesija();
        }
        return instance;
    }

    public Radnik getRadnik() {
        return radnik;
    }

    public void setRadnik(Radnik radnik) {
        this.radnik = radnik;
    }

    public OpstiDomenskiObjekat getObjekat() {
        return objekat;
    }

    public void setObjekat(OpstiDomenskiObjekat objekat) {
        this.objekat = objekat;
    }

}
