/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import domen.Korisnik;
import domen.OdabraniTrening;
import domen.OpstiDomenskiObjekat;
import domen.Radnik;
import domen.Trening;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import operacija.StatusOperacije;
import sun.security.krb5.internal.KDCOptions;
import transfer.OdgovorServera;
import transfer.ZahtevKlijenta;

/**
 *
 * @author Roki
 */
public class NitKlijenta extends Thread {

    private Socket s;
    private Radnik radnik;

    public NitKlijenta(Socket s) {
        this.s = s;
    }

    public Socket getS() {
        return s;
    }
    private boolean kraj = true;

    @Override
    public void run() {
        while (kraj) {
            ZahtevKlijenta zk = primi();
            OdgovorServera odg = new OdgovorServera();
            if (zk == null) {
                return;
            }
            switch (zk.getOperacija()) {
                case operacija.Operacija.LOGIN: {
                    try {

                        radnik = kontroler.Kontroler.getInstance().prijavaNaSistem((Radnik) zk.getParametar());
                        boolean jes = kontroler.Kontroler.getInstance().dajleVecNeko(radnik);
                        if (jes == true) {
                            odg.setOdgovor("Neko je vec ulogovan pod tim imenom.");
                        } else {
                            System.out.println(radnik.getImePrezime());
                            if (!radnik.getImePrezime().isEmpty()) {
                                odg.setOdgovor(radnik);
                                odg.setStatus(StatusOperacije.OK);
                                kontroler.Kontroler.getInstance().getAktivniKorisnici().add(this);
                                kontroler.Kontroler.getInstance().getForma().dodajPrijavljenogRadnik(radnik);
                            } else {
                                odg.setStatus(StatusOperacije.ERROR);
                                odg.setOdgovor("Neuspešna prijava na sistem.");
                            }
                        }
                    } catch (Exception ex) {
                        odg.setStatus(StatusOperacije.ERROR);
                        odg.setOdgovor("Neuspešna prijava na sistem.");
                        odg.setGreska(ex.getMessage());
                    }
                    posalji(odg);
                    break;
                }
                case operacija.Operacija.UNOS_KORISNIKA: {
                    try {
                        kontroler.Kontroler.getInstance().unosKorisnika((Korisnik) zk.getParametar());
                        odg.setStatus(StatusOperacije.OK);
                        odg.setOdgovor("Uspešno ste uneli korisnika.");
                    } catch (Exception ex) {
                        odg.setStatus(StatusOperacije.ERROR);
                        odg.setOdgovor("Neuspešan unos korisnika.");
                        odg.setGreska(ex.getMessage());
                    }
                    posalji(odg);
                    break;
                }
                case operacija.Operacija.REGISTRACIJA: {
                    try {
                        kontroler.Kontroler.getInstance().registracijaRadnik((Radnik) zk.getParametar());
                        odg.setStatus(StatusOperacije.OK);
                        odg.setOdgovor("Uspešna registracija.");
                    } catch (Exception ex) {
                        odg.setStatus(StatusOperacije.ERROR);
                        odg.setOdgovor("Neuspešna registracija.");
                        odg.setGreska(ex.getMessage());
                    }
                    posalji(odg);
                    break;
                }
                case operacija.Operacija.PRETRAGA_KORISNIKA: {
                    ArrayList<OpstiDomenskiObjekat> korisnici = new ArrayList<>();
                    try {
                        korisnici = kontroler.Kontroler.getInstance().pretraziKorisnike((Korisnik) zk.getParametar());
                        odg.setOdgovor(korisnici);
                        odg.setStatus(StatusOperacije.OK);
                        if (korisnici.isEmpty()) {
                            odg.setGreska("Ne postoji korisnik koji tražite.");
                        }
                    } catch (Exception ex) {
                        odg.setStatus(StatusOperacije.ERROR);
                        odg.setGreska(ex.getMessage());
                    }

                    posalji(odg);
                    break;
                }
                case operacija.Operacija.UNOS_TRENINGA: {
                    try {
                        kontroler.Kontroler.getInstance().unosTreninga((Trening) zk.getParametar());
                        odg.setStatus(StatusOperacije.OK);
                        odg.setOdgovor("Uspešno ste uneli trening.");
                    } catch (Exception ex) {
                        odg.setOdgovor("Neuspešan unos treninga.");
                        odg.setStatus(StatusOperacije.ERROR);
                        odg.setGreska(ex.getMessage());
                    }
                    posalji(odg);
                    break;
                }

                case operacija.Operacija.PRETRAGA_TRENINGA: {
                    ArrayList<OpstiDomenskiObjekat> treninzi = new ArrayList();
                    try {
                        treninzi = kontroler.Kontroler.getInstance().pretraziTreninge((Trening) zk.getParametar());
                        odg.setStatus(StatusOperacije.OK);
                        odg.setOdgovor(treninzi);
                        //if(treninzi.isEmpty()){
                        //    odg.setStatus(StatusOperacije.ERROR);
                        //  odg.setGreska("Ne postoji trening koji trazite.");
                        //}
                    } catch (Exception e) {
                        odg.setOdgovor("Nemoguće izvršiti pretragu treninga.");
                        odg.setStatus(StatusOperacije.ERROR);
                        odg.setGreska(e.getMessage());
                    }
                    posalji(odg);
                    break;
                }
                case operacija.Operacija.IZMENA_KORISNIKA: {
                    try {
                        Korisnik kor = kontroler.Kontroler.getInstance().izmenaKorisnik((Korisnik) zk.getParametar());
                        odg.setOdgovor(kor);
                        odg.setStatus(StatusOperacije.OK);
                    } catch (Exception ex) {
                        odg.setOdgovor("Nemoguća izmena korisnika.");
                        odg.setStatus(StatusOperacije.ERROR);
                        odg.setGreska(ex.getMessage());
                    }
                    posalji(odg);
                    break;

                }
                case operacija.Operacija.IZMENA_TRENINGA: {
                    try {
                        Trening t = kontroler.Kontroler.getInstance().izmenaTreninga((Trening) zk.getParametar());
                        odg.setOdgovor(t);
                        odg.setStatus(StatusOperacije.OK);
                    } catch (Exception ex) {
                        odg.setOdgovor("Nemoguća izmena treninga.");
                        odg.setStatus(StatusOperacije.ERROR);
                        odg.setGreska(ex.getMessage());
                    }
                    posalji(odg);
                    break;

                }
                case operacija.Operacija.VRATI_SVE_KORISNIKE: {
                    ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();
                    try {
                        lista = kontroler.Kontroler.getInstance().vratiSveKorisnike(new Korisnik());
                        odg.setOdgovor(lista);
                        odg.setStatus(StatusOperacije.OK);
                    } catch (Exception ex) {
                        odg.setStatus(StatusOperacije.ERROR);
                        odg.setOdgovor("Nije moguće vrartiti sve korisnike.");
                        odg.setGreska(ex.getMessage());
                    }

                    posalji(odg);
                    break;
                }
                case operacija.Operacija.VRATI_SVE_TRENINGE: {
                    ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();
                    try {
                        lista = kontroler.Kontroler.getInstance().vratiSveTreninge(new Trening());
                        odg.setOdgovor(lista);
                        odg.setStatus(StatusOperacije.OK);
                    } catch (Exception ex) {
                        odg.setOdgovor("Nemoguće vratiti sve treninge.");
                        odg.setStatus(StatusOperacije.ERROR);
                        odg.setGreska(ex.getMessage());
                    }
                    posalji(odg);
                    break;
                }
                case operacija.Operacija.UNOS_ODABRANOG_TRENINGA: {
                    try {
                        kontroler.Kontroler.getInstance().unesiOdabraniTrening((OdabraniTrening) zk.getParametar());
                        odg.setStatus(StatusOperacije.OK);
                        odg.setOdgovor("Uspešno ste uneli odabrani trening.");
                    } catch (Exception ex) {
                        odg.setStatus(StatusOperacije.ERROR);
                        odg.setOdgovor("Neuspešan unos odabranog treninga.");
                        odg.setGreska(ex.getMessage());
                    }

                    posalji(odg);
                    break;
                }
                case operacija.Operacija.PRETRAGA_ODABRANOG_TRENINGA: {
                    ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();
                    try {
                        lista = kontroler.Kontroler.getInstance().pretraziOdabraneTrening((OdabraniTrening) zk.getParametar());
                        odg.setOdgovor(lista);
                        odg.setStatus(StatusOperacije.OK);
                    } catch (Exception ex) {
                        odg.setGreska(ex.getMessage());
                        odg.setStatus(StatusOperacije.ERROR);
                        odg.setOdgovor("Nemoguće izvršiti pretragu odabranih treninga.");
                    }

                    posalji(odg);
                    break;
                }
                case operacija.Operacija.IZMENA_ODABRANOG_TRENING: {
                    try {
                        OdabraniTrening ot = kontroler.Kontroler.getInstance().izmeniOdabraniTrening((OdabraniTrening) zk.getParametar());
                        odg.setStatus(StatusOperacije.OK);
                        odg.setOdgovor("Uspešno ste izmenili odabrani trening.");
                    } catch (Exception ex) {
                        odg.setGreska(ex.getMessage());
                        odg.setStatus(StatusOperacije.ERROR);
                        odg.setOdgovor("Nemoguće izmeniti odabrani trening.");
                    }

                    posalji(odg);
                    break;
                }
                case operacija.Operacija.LOGOUT: {
                    Radnik rad = (Radnik) zk.getParametar();
                    try {
                        kontroler.Kontroler.getInstance().odjaviRadnik(rad);
                        odg.setStatus(StatusOperacije.KRAJ_RADA);

                    } catch (Exception ex) {
                        odg.setGreska(ex.getMessage());
                        odg.setStatus(StatusOperacije.ERROR);
                        odg.setOdgovor("Nemoguća odjava sa sistema.");
                    }
                    posalji(odg);
                    try {
                        s.close();
                        setKraj(false);
                        interrupt();
                    } catch (IOException ex) {
                        Logger.getLogger(NitKlijenta.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }
            }
        }
    }

    public void posalji(OdgovorServera odg) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(odg);
        } catch (IOException ex) {
            Logger.getLogger(NitKlijenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ZahtevKlijenta primi() {
        ZahtevKlijenta zk = null;

        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            zk = (ZahtevKlijenta) ois.readObject();
        } catch (IOException ex) {
            //Logger.getLogger(NitKlijenta.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            // Logger.getLogger(NitKlijenta.class.getName()).log(Level.SEVERE, null, ex);
        }

        return zk;
    }

    public boolean isKraj() {
        return kraj;
    }

    public void setKraj(boolean kraj) {
        this.kraj = kraj;
    }

    public Radnik getRadnik() {
        return radnik;
    }

    public void setRadnik(Radnik radnik) {
        this.radnik = radnik;
    }

}
