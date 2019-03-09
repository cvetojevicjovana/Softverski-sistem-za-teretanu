/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skladiste.baza.konekcija;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import podesavanja.PodesavanjaBaze;

/**
 *
 * @author Roki
 */
public class BazaKonekcija {

    private final Connection konekcija;
    private static BazaKonekcija instance;

    private BazaKonekcija() throws SQLException {
        String url = PodesavanjaBaze.getInstance().getProperty("url");
        String user = PodesavanjaBaze.getInstance().getProperty("user");
        String password = PodesavanjaBaze.getInstance().getProperty("password");
        System.out.println(url + " " + user + " " + password);
        konekcija = DriverManager.getConnection(url, user, password);
        konekcija.setAutoCommit(false);
    }

    public static BazaKonekcija getInstance() throws SQLException {
        if (instance == null) {
            instance = new BazaKonekcija();
        }
        return instance;
    }

    public Connection getKonekcija() {
        return konekcija;
    }

    public void pocniTransakciju() throws SQLException {
        konekcija.setAutoCommit(false);
    }

    public void commit() throws SQLException {
        konekcija.commit();
    }

    public void rollback() throws SQLException {
        konekcija.rollback();
    }

}
