/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import operacija.StatusOperacije;
import transfer.OdgovorServera;

/**
 *
 * @author Roki
 */
public class PokretanjeServera extends Thread {

    ArrayList<NitKlijenta> aktivneNiti;
    ServerSocket ss;
    public static boolean krajRadaServera;

    public PokretanjeServera() {
        aktivneNiti = new ArrayList<>();
        krajRadaServera = true;
    }

    @Override
    public void run() {
        int port = Integer.parseInt(podesavanja.PodesavanjaKonekcije.getInstance().getProperty("port"));
        try {
            ss = new ServerSocket(port);
            kontroler.Kontroler.getInstance().getForma().postaviStatus("Server program je pokrenut na portu: " + port + ".", true);
            while (krajRadaServera) {
                Socket s = ss.accept();
                NitKlijenta nit = new NitKlijenta(s);
                nit.start();
                aktivneNiti.add(nit);
            }
        } catch (IOException ex) {
            //  Logger.getLogger(PokretanjeServera.class.getName()).log(Level.SEVERE, null, ex);
            kontroler.Kontroler.getInstance().getForma().postaviStatus("Server program nije pokrenut.", false);
        }

    }

    public void zaustaviServer(String poruka) throws IOException {
        for (NitKlijenta nitKlijenta : aktivneNiti) {

            nitKlijenta.getS().close();
            nitKlijenta.interrupt();
            nitKlijenta.setKraj(false);

        }

        aktivneNiti = new ArrayList<>();
        try {
            ss.close();
        } catch (IOException ex) {
            Logger.getLogger(PokretanjeServera.class.getName()).log(Level.SEVERE, null, ex);
        }
        krajRadaServera = false;

    }

}
