/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package podesavanja;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roki
 */
public class PodesavanjaBaze {

    private static PodesavanjaBaze instance;
    private Properties props;

    private PodesavanjaBaze() {
        try {
            props = new Properties();
            props.load(new FileInputStream("podesavanja_baze.properties"));
        } catch (Exception ex) {
            Logger.getLogger(PodesavanjaBaze.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static PodesavanjaBaze getInstance() {
        if (instance == null) {
            instance = new PodesavanjaBaze();
        }
        return instance;
    }

    public void ucitaj() throws Exception {
        props.store(new FileOutputStream("podesavanja_baze.properties"), "");
    }

    public String getProperty(String key) {
        return props.getProperty(key, "n/a");
    }

    public void setProperty(String key, String vrednost) {
        props.setProperty(key, vrednost);
    }

}
