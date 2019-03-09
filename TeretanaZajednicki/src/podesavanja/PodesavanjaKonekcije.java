/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package podesavanja;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roki
 */
public class PodesavanjaKonekcije {

    private static PodesavanjaKonekcije instance;
    private Properties props;

    private PodesavanjaKonekcije() {
        try {
            props = new Properties();
            props.load(new FileInputStream("C:\\Users\\Roki\\Documents\\NetBeansProjects\\TeretanaZajednicki\\podesavanje_konekcije.properties"));
        } catch (Exception ex) {
            Logger.getLogger(PodesavanjaKonekcije.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static PodesavanjaKonekcije getInstance() {
        if (instance == null) {
            instance = new PodesavanjaKonekcije();
        }
        return instance;
    }

    public void ucitaj() throws Exception {
        props.store(new FileOutputStream("C:\\Users\\Roki\\Documents\\NetBeansProjects\\TeretanaZajednicki\\podesavanje_konekcije.properties"), "");
    }

    public String getProperty(String key) {
        return props.getProperty(key, "n/a");
    }

    public void setProperty(String key, String vrednost) {
        props.setProperty(key, vrednost);
    }
}
