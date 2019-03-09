/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import domen.Korisnik;
import domen.OdabraniTrening;
import domen.Radnik;
import domen.Termin;
import domen.Trening;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;
import operacija.StatusOperacije;
import transfer.OdgovorServera;
import transfer.ZahtevKlijenta;

/**
 *
 * @author Roki
 */
public class Kontroler {

    private static Kontroler instance;

    private Kontroler() {
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public Object prijavaNaSistem(String username, String password) throws Exception {
        Radnik r = new Radnik();
        r.setKorisnickoIme(username);
        r.setSifra(password);
        ZahtevKlijenta zk = new ZahtevKlijenta(operacija.Operacija.LOGIN, r);
        komunikacija.Komunikacija.getInstance().posalji(zk);
        OdgovorServera odg = komunikacija.Komunikacija.getInstance().primi();
        System.out.println(odg.getGreska() + " " + odg.getStatus() + " " + odg.getOdgovor());
        if (odg.getStatus() == StatusOperacije.OK) {
            sesija.Sesija.getInstance().setRadnik((Radnik) odg.getOdgovor());
            return (Radnik) odg.getOdgovor();
        } else if (odg.getStatus() == StatusOperacije.ERROR) {
            System.out.println((String) odg.getGreska());
            throw new Exception((String) odg.getOdgovor());
        }
        throw new Exception();
    }

    public Object unosKorisnika(String imePrezime, String email, String telefon, Radnik radnik) throws Exception {
        radnik = sesija.Sesija.getInstance().getRadnik();
        Korisnik korisnik = new Korisnik(imePrezime, email, telefon, radnik);
        ZahtevKlijenta zk = new ZahtevKlijenta(operacija.Operacija.UNOS_KORISNIKA, korisnik);
        komunikacija.Komunikacija.getInstance().posalji(zk);
        OdgovorServera odg = komunikacija.Komunikacija.getInstance().primi();
        if (odg.getStatus() == StatusOperacije.OK) {
            return odg.getOdgovor();
        } else if (odg.getStatus() == StatusOperacije.ERROR) {
            System.out.println((String) odg.getOdgovor());
            throw new Exception((String) odg.getGreska());
        }
        throw new Exception();

    }

    public Object registrujRadnika(String imePrezime, String korisnickoIme, String sifra, String telefon) throws Exception {
        Radnik rad = new Radnik(imePrezime, korisnickoIme, sifra, telefon);
        ZahtevKlijenta zk = new ZahtevKlijenta(operacija.Operacija.REGISTRACIJA, rad);
        komunikacija.Komunikacija.getInstance().posalji(zk);
        OdgovorServera odg = komunikacija.Komunikacija.getInstance().primi();
        if (odg.getStatus() == StatusOperacije.OK) {
            return odg.getOdgovor();
        } else if (odg.getStatus() == StatusOperacije.ERROR) {
            System.out.println((String) odg.getGreska());
            throw new Exception((String) odg.getOdgovor());
        }
        throw new Exception();
    }

    public Korisnik izmeniKorisnika(Korisnik k) throws Exception {
        ZahtevKlijenta zk = new ZahtevKlijenta(operacija.Operacija.IZMENA_KORISNIKA, k);
        komunikacija.Komunikacija.getInstance().posalji(zk);
        OdgovorServera odg = komunikacija.Komunikacija.getInstance().primi();
        if (odg.getStatus() == StatusOperacije.OK) {
            return (Korisnik) odg.getOdgovor();
        } else if (odg.getStatus() == StatusOperacije.ERROR) {
            System.out.println((String) odg.getGreska());
            throw new Exception((String) odg.getOdgovor());
        }

        throw new Exception();
    }

    public ArrayList<Korisnik> pretraziKorisnike(String id, String imePrezime, String radnikUneo) throws Exception {
        Korisnik k = new Korisnik();
        if (!id.trim().isEmpty()) {
            try {
                int idINT = Integer.parseInt(id);
                k.setKorisnikID(idINT);
            } catch (Exception ex) {
                throw new Exception("ID mora biti broj!");
            }
        }
        if (!imePrezime.trim().isEmpty()) {
            k.setImePrezime(imePrezime);
        }
        if (!radnikUneo.trim().isEmpty()) {
            k.setRadnik(new Radnik(radnikUneo, null, null, null));
        }
        ZahtevKlijenta zk = new ZahtevKlijenta(operacija.Operacija.PRETRAGA_KORISNIKA, k);
        komunikacija.Komunikacija.getInstance().posalji(zk);
        OdgovorServera odg = komunikacija.Komunikacija.getInstance().primi();
        if (odg.getStatus() == StatusOperacije.OK) {
            return (ArrayList<Korisnik>) odg.getOdgovor();
        } else if (odg.getStatus() == StatusOperacije.ERROR) {
            System.out.println((String) odg.getGreska());
            throw new Exception((String) odg.getOdgovor());
        }
        throw new Exception();
    }

    public Object unesiTrening(String naziv, String opis, int broj, double cenaD) throws Exception {
        Trening trening = new Trening(naziv, opis, broj, cenaD);
        ZahtevKlijenta zk = new ZahtevKlijenta(operacija.Operacija.UNOS_TRENINGA, trening);
        komunikacija.Komunikacija.getInstance().posalji(zk);
        OdgovorServera odg = komunikacija.Komunikacija.getInstance().primi();
        if (odg.getStatus() == StatusOperacije.OK) {
            return odg.getOdgovor();
        } else if (odg.getStatus() == StatusOperacije.ERROR) {
            System.out.println((String) odg.getGreska());
            throw new Exception((String) odg.getOdgovor());
        }
        throw new Exception();

    }

    public ArrayList<Trening> pretraziTreninge(String id, String naziv, String opis, String cena) throws Exception {
        Trening trening = new Trening();
        if (!id.trim().isEmpty()) {
            try {
                int idINT = Integer.parseInt(id);
                trening.setTreningID(idINT);
            } catch (Exception ex) {
                throw new Exception("ID mora biti broj!");
            }
        }
        if (!cena.trim().isEmpty()) {
            try {
                double cenaN = Double.parseDouble(cena);
                trening.setCena(cenaN);
            } catch (Exception e) {
                throw new Exception("Cena mora biti broj");
            }
        }
        if (!naziv.trim().isEmpty()) {
            trening.setNaziv(naziv);
        }
        if (!opis.trim().isEmpty()) {
            trening.setOpis(opis);
        }
        ZahtevKlijenta zk = new ZahtevKlijenta(operacija.Operacija.PRETRAGA_TRENINGA, trening);
        komunikacija.Komunikacija.getInstance().posalji(zk);
        OdgovorServera odg = komunikacija.Komunikacija.getInstance().primi();
        if (odg.getStatus() == StatusOperacije.OK) {
            return (ArrayList<Trening>) odg.getOdgovor();
        } else if (odg.getStatus() == StatusOperacije.ERROR) {
            System.out.println((String) odg.getGreska());
            throw new Exception((String) odg.getOdgovor());
        }
        throw new Exception();
    }

    public Trening izmeniTrening(Trening trening) throws Exception {
        ZahtevKlijenta zk = new ZahtevKlijenta(operacija.Operacija.IZMENA_TRENINGA, trening);
        komunikacija.Komunikacija.getInstance().posalji(zk);
        OdgovorServera odg = komunikacija.Komunikacija.getInstance().primi();
        if (odg.getStatus() == StatusOperacije.OK) {
            return (Trening) odg.getOdgovor();
        } else if (odg.getStatus() == StatusOperacije.ERROR) {
            System.out.println((String) odg.getGreska());
            throw new Exception((String) odg.getOdgovor());
        }
        throw new Exception();
    }

    public ArrayList<Trening> vratiSveTreninge() throws Exception {
        ZahtevKlijenta zk = new ZahtevKlijenta(operacija.Operacija.VRATI_SVE_TRENINGE, null);
        komunikacija.Komunikacija.getInstance().posalji(zk);
        OdgovorServera odg = komunikacija.Komunikacija.getInstance().primi();
        if (odg.getStatus() == StatusOperacije.OK) {
            return (ArrayList<Trening>) odg.getOdgovor();
        } else if (odg.getStatus() == StatusOperacije.ERROR) {
            System.out.println((String) odg.getGreska());
            throw new Exception((String) odg.getOdgovor());
        }
        throw new Exception();
    }

    public ArrayList<Korisnik> vratiSveKorisnike() throws Exception {
        ZahtevKlijenta zk = new ZahtevKlijenta(operacija.Operacija.VRATI_SVE_KORISNIKE, null);
        komunikacija.Komunikacija.getInstance().posalji(zk);
        OdgovorServera odg = komunikacija.Komunikacija.getInstance().primi();
        if (odg.getStatus() == StatusOperacije.OK) {
            return (ArrayList<Korisnik>) odg.getOdgovor();
        } else if (odg.getStatus() == StatusOperacije.ERROR) {
            System.out.println((String) odg.getGreska());
            throw new Exception((String) odg.getOdgovor());
        }
        throw new Exception();
    }

    public Object unesiOdabraniTrening(Date datumUplate, Date datumVaz, String status, Korisnik kor, Trening tren, ArrayList<Termin> listaTermina) throws Exception {
        OdabraniTrening ot = new OdabraniTrening(0, datumUplate, datumVaz, status, tren, kor, listaTermina);
        ZahtevKlijenta zk = new ZahtevKlijenta(operacija.Operacija.UNOS_ODABRANOG_TRENINGA, ot);
        komunikacija.Komunikacija.getInstance().posalji(zk);
        OdgovorServera odg = komunikacija.Komunikacija.getInstance().primi();
        if (odg.getStatus() == StatusOperacije.OK) {
            return odg.getOdgovor();
        } else if (odg.getStatus() == StatusOperacije.ERROR) {
            System.out.println((String) odg.getGreska());
            throw new Exception((String) odg.getOdgovor());
        }
        throw new Exception();
    }

    public ArrayList<OdabraniTrening> pretraziOdabraneTreninge(Korisnik kor, Trening tr, String status) throws Exception {
        OdabraniTrening ot = new OdabraniTrening();
        if (kor != null) {
            ot.setKorisnik(kor);
        }
        if (tr != null) {
            ot.setTrening(tr);
        }
        if (!status.trim().isEmpty()) {
            ot.setStatus(status);
        }
        ZahtevKlijenta zk = new ZahtevKlijenta(operacija.Operacija.PRETRAGA_ODABRANOG_TRENINGA, ot);
        komunikacija.Komunikacija.getInstance().posalji(zk);
        OdgovorServera odg = komunikacija.Komunikacija.getInstance().primi();
        if (odg.getStatus() == StatusOperacije.OK) {
            return (ArrayList<OdabraniTrening>) odg.getOdgovor();
        } else if (odg.getStatus() == StatusOperacije.ERROR) {
            System.out.println((String) odg.getGreska());
            throw new Exception((String) odg.getOdgovor());
        }
        throw new Exception();
    }

    public Object izmeniOdabraniTrening(OdabraniTrening ot) throws Exception {
        ZahtevKlijenta zk = new ZahtevKlijenta(operacija.Operacija.IZMENA_ODABRANOG_TRENING, ot);
        komunikacija.Komunikacija.getInstance().posalji(zk);
        OdgovorServera odg = komunikacija.Komunikacija.getInstance().primi();
        if (odg.getStatus() == StatusOperacije.OK) {
            return odg.getOdgovor();
        } else if (odg.getStatus() == StatusOperacije.ERROR) {
            System.out.println((String) odg.getGreska());
            throw new Exception((String) odg.getOdgovor());
        }
        throw new Exception();
    }

    public void odajvaSaSistema() throws Exception {
        ZahtevKlijenta zk = new ZahtevKlijenta(operacija.Operacija.LOGOUT, sesija.Sesija.getInstance().getRadnik());
        komunikacija.Komunikacija.getInstance().posalji(zk);
        OdgovorServera odg = komunikacija.Komunikacija.getInstance().primi();
        if (odg.getStatus() == StatusOperacije.KRAJ_RADA) {
            return;
        } else if (odg.getStatus() == StatusOperacije.ERROR) {
            System.out.println((String) odg.getGreska());
            throw new Exception((String) odg.getOdgovor());
        }
        throw new Exception();

    }

}
