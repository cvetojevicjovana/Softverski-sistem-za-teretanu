/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import domen.Korisnik;
import domen.OdabraniTrening;
import domen.OpstiDomenskiObjekat;
import domen.Radnik;
import domen.Trening;
import forma.FormaServer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import niti.NitKlijenta;
import niti.PokretanjeServera;
import operacija.StatusOperacije;
import so.korisnik.IzmenaKorisnikaSO;
import so.korisnik.UnosKorisnikaSO;
import so.trening.UnosTreningaSO;
import so.korisnik.PronadjiKorisnikeSO;
import so.korisnik.VratiKorisnikeSO;
import so.odabraniTrening.IzmenaOdabranogTreningaSO;
import so.odabraniTrening.PronadjiOdabraniTreningSO;
import so.odabraniTrening.UnosOdabranogTreningaSO;
import so.radnik.PrijavljivanjeSO;
import so.radnik.RegistracijaSO;
import so.trening.IzmenaTreningaSO;
import so.trening.PronadjiTreningeSO;
import so.trening.VratiTreningeSO;
import transfer.OdgovorServera;

/**
 *
 * @author Roki
 */
public class Kontroler {

    private static Kontroler instance;
    ArrayList<NitKlijenta> aktivniKorisnici;
    private FormaServer forma;

    private Kontroler() {
        aktivniKorisnici = new ArrayList<>();
    }

    public ArrayList<NitKlijenta> getAktivniKorisnici() {
        return aktivniKorisnici;
    }

    public void setAktivniKorisnici(ArrayList<NitKlijenta> aktivniKorisnici) {
        this.aktivniKorisnici = aktivniKorisnici;
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public FormaServer getForma() {
        return forma;
    }

    public void setForma(FormaServer forma) {
        this.forma = forma;
    }

    public boolean dajleVecNeko(Radnik r) {
        for (NitKlijenta nit : aktivniKorisnici) {
            if (nit.getRadnik().equals(r)) {

                return true;
            }
        }
        return false;
    }

    public Radnik prijavaNaSistem(Radnik r) throws Exception {
        PrijavljivanjeSO pso = new PrijavljivanjeSO();
        pso.opsteIzvrsenje(r);
        return pso.getRadnik();
    }

    public void odjaviRadnik(Radnik r) throws Exception {
        for (int i = 0; i < aktivniKorisnici.size(); i++) {

            if (aktivniKorisnici.get(i).getRadnik().equals(r)) {
                System.out.println("odjava: " + aktivniKorisnici.get(i).getRadnik());
                forma.getTmu().odjaviRadnik(r);
                aktivniKorisnici.get(i).setKraj(false);

                aktivniKorisnici.remove(i);
                break;
            }
        }
    }

    public void unosKorisnika(Korisnik korisnik) throws Exception {
        UnosKorisnikaSO ukso = new UnosKorisnikaSO();
        ukso.opsteIzvrsenje(korisnik);
    }

    public void registracijaRadnik(Radnik radnik) throws Exception {
        RegistracijaSO rso = new RegistracijaSO();
        rso.opsteIzvrsenje(radnik);
    }

    public ArrayList<OpstiDomenskiObjekat> pretraziKorisnike(Korisnik korisnik) throws Exception {
        PronadjiKorisnikeSO vso = new PronadjiKorisnikeSO();
        vso.opsteIzvrsenje(korisnik);
        return vso.getKorisnici();
    }

    public void unosTreninga(Trening trening) throws Exception {
        UnosTreningaSO utso = new UnosTreningaSO();
        utso.opsteIzvrsenje(trening);
    }

    public ArrayList<OpstiDomenskiObjekat> pretraziTreninge(Trening trening) throws Exception {
        PronadjiTreningeSO tso = new PronadjiTreningeSO();
        tso.opsteIzvrsenje(trening);
        return tso.getTrenizni();
    }

    public Korisnik izmenaKorisnik(Korisnik korisnik) throws Exception {
        IzmenaKorisnikaSO izso = new IzmenaKorisnikaSO();
        izso.opsteIzvrsenje(korisnik);
        return izso.getKorsinik();
    }

    public Trening izmenaTreninga(Trening trening) throws Exception {
        IzmenaTreningaSO izso = new IzmenaTreningaSO();
        izso.opsteIzvrsenje(trening);
        return izso.getTrening();
    }

    public ArrayList<OpstiDomenskiObjekat> vratiSveKorisnike(Korisnik korisnik) throws Exception {
        VratiKorisnikeSO vso = new VratiKorisnikeSO();
        vso.opsteIzvrsenje(korisnik);
        return vso.getKorisnici();
    }

    public ArrayList<OpstiDomenskiObjekat> vratiSveTreninge(Trening trening) throws Exception {
        VratiTreningeSO vso = new VratiTreningeSO();
        vso.opsteIzvrsenje(trening);
        return vso.getTeninzi();
    }

    public void unesiOdabraniTrening(OdabraniTrening odabraniTrening) throws Exception {
        UnosOdabranogTreningaSO uso = new UnosOdabranogTreningaSO();
        uso.opsteIzvrsenje(odabraniTrening);
    }

    public ArrayList<OpstiDomenskiObjekat> pretraziOdabraneTrening(OdabraniTrening odabraniTrening) throws Exception {
        PronadjiOdabraniTreningSO pos = new PronadjiOdabraniTreningSO();
        pos.opsteIzvrsenje(odabraniTrening);
        return pos.getOdTreninzi();
    }

    public OdabraniTrening izmeniOdabraniTrening(OdabraniTrening odabraniTrening) throws Exception {
        IzmenaOdabranogTreningaSO ios = new IzmenaOdabranogTreningaSO();
        ios.opsteIzvrsenje(odabraniTrening);
        return ios.getOdabraniTrening();
    }

}
