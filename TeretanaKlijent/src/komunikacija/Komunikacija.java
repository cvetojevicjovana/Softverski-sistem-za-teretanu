/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import transfer.OdgovorServera;
import transfer.ZahtevKlijenta;

/**
 *
 * @author Roki
 */
public class Komunikacija {

    private static Komunikacija instance;
    Socket s;

    private Komunikacija() {
        try {
            int port = Integer.parseInt(podesavanja.PodesavanjaKonekcije.getInstance().getProperty("port"));
            String adresa = podesavanja.PodesavanjaKonekcije.getInstance().getProperty("adresa");
            s = new Socket(adresa, port);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.");
            System.exit(0);
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Komunikacija getInstance() {
        if (instance == null) {
            instance = new Komunikacija();
        }
        return instance;
    }

    public void posalji(ZahtevKlijenta zk) {

        try {
            ObjectOutputStream outSocket = new ObjectOutputStream(s.getOutputStream());
            outSocket.writeObject(zk);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.");
            System.exit(0);
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public OdgovorServera primi() {
        OdgovorServera odg = null;
        try {
            ObjectInputStream inSocket = new ObjectInputStream(s.getInputStream());
            odg = (OdgovorServera) inSocket.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
        return odg;
    }

}
