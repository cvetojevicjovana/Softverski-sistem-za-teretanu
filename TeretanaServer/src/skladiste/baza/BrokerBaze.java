/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skladiste.baza;

import domen.OpstiDomenskiObjekat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import skladiste.BrokerBazeInterfejs;
import skladiste.baza.konekcija.BazaKonekcija;

/**
 *
 * @author Roki
 */
public class BrokerBaze implements BrokerBazeInterfejs {

    @Override
    public OpstiDomenskiObjekat vratiJednog(OpstiDomenskiObjekat odo) throws Exception {
        Connection konekcija = BazaKonekcija.getInstance().getKonekcija();
        String upit = "SELECT * FROM " + odo.vratiImeKlase() + " WHERE " + odo.vratiUslovZaNadjiSlog();
        Statement stat = konekcija.createStatement();
        ResultSet rs = stat.executeQuery(upit);
        OpstiDomenskiObjekat rezultat = odo.napuni(rs);
        rs.close();
        stat.close();
        return rezultat;
    }

    @Override
    public void ubaci(OpstiDomenskiObjekat odo) throws Exception {
        try {
            Connection konekcija = BazaKonekcija.getInstance().getKonekcija();
            String upit = "INSERT INTO " + odo.vratiImeKlase() + " VALUES (" + odo.vratiVrednostiAtributa() + ")";
            System.out.println(upit);
            PreparedStatement prepst = konekcija.prepareStatement(upit);
            prepst.executeUpdate(upit);
            prepst.close();
        } catch (Exception ex) {
            //throw new Exception("Neuspešno čuvanje objekta!", ex);
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiSve(OpstiDomenskiObjekat odo) throws Exception {
        ArrayList<OpstiDomenskiObjekat> lista;
        Connection konekcija = BazaKonekcija.getInstance().getKonekcija();
        String upit = "SELECT * FROM " + odo.vratiImeKlase() + " " + odo.vratiTabeluSaUslovomSpajanja() + " " + odo.vratiUslovZaNadjiSlogove() + " " + odo.vratiUslovZaSortiranje();
        System.out.println(upit);
        Statement stat = konekcija.createStatement();
        ResultSet rs = stat.executeQuery(upit);
        lista = odo.napuniSve(rs);
        rs.close();
        stat.close();
        return lista;
    }

    @Override
    public void izmeni(OpstiDomenskiObjekat odo) throws Exception {
        Connection konekcija = BazaKonekcija.getInstance().getKonekcija();
        String upit = "UPDATE " + odo.vratiImeKlase() + " SET " + odo.postaviVrednostAtrbuta() + " WHERE " + odo.vratiUslovZaNadjiSlog();
        PreparedStatement prestat = konekcija.prepareStatement(upit);
        System.out.println(upit);
        prestat.executeUpdate(upit);
        prestat.close();
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiSveBezUslova(OpstiDomenskiObjekat odo) throws Exception {
        ArrayList<OpstiDomenskiObjekat> lista;
        Connection konekcija = BazaKonekcija.getInstance().getKonekcija();
        String upit = "SELECT * FROM " + odo.vratiImeKlase() + " " + odo.vratiTabeluSaUslovomSpajanja() + " " + odo.vratiUslovZaSortiranje();
        System.out.println(upit);
        Statement stat = konekcija.createStatement();
        ResultSet rs = stat.executeQuery(upit);
        lista = odo.napuniSve(rs);
        rs.close();
        stat.close();
        return lista;
    }

    @Override
    public int vratiMaxID(OpstiDomenskiObjekat odo) throws Exception {
        int maxID = 0;
        Connection konekcija = BazaKonekcija.getInstance().getKonekcija();
        String upit = "SELECT MAX(" + odo.vratiID() + ") FROM " + odo.vratiImeKlase();
        System.out.println(upit);
        Statement stat = konekcija.createStatement();
        ResultSet rs = stat.executeQuery(upit);
        rs.next();
        maxID = rs.getInt(1);
        rs.close();
        stat.close();
        return maxID;
    }

}
