/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Roki
 */
public interface OpstiDomenskiObjekat extends Serializable {

    String vratiVrednostiAtributa();

    String postaviVrednostAtrbuta();

    String vratiImeKlase();

    String vratiUslovZaNadjiSlog();

    String vratiUslovZaNadjiSlogove();

    String vratiImeKolone(int brojKolone);

    public OpstiDomenskiObjekat napuni(ResultSet rs) throws Exception;

    public ArrayList<OpstiDomenskiObjekat> napuniSve(ResultSet rs) throws Exception;

    public void ubaci(String nazivAtributa, Object vrednostAtributa);

    public String vratiUslovZaSortiranje() throws Exception;

    public String vratiTabeluSaUslovomSpajanja();

    String vratiID();

}
