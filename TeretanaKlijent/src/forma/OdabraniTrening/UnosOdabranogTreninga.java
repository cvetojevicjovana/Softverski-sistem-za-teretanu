/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forma.OdabraniTrening;

import domen.Korisnik;
import domen.Termin;
import domen.Trening;
import forma.Korisnik.PretragaKorisnika;
import forma.Korisnik.PrikazKorisnika;
import forma.Korisnik.UnosKorisnika;
import forma.OdabraniTrening.model.TableModelTermini;
import forma.Trening.PretragaTreninga;
import java.awt.Dialog;
import java.awt.Panel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Roki
 */
public class UnosOdabranogTreninga extends javax.swing.JPanel {

    TableModelTermini tmt;

    /**
     * Creates new form UnosOdabranogTreninga
     */
    public UnosOdabranogTreninga() {
        initComponents();
        inicijalizujVreme();
        tmt = new TableModelTermini();
        jTable1.setModel(tmt);
        inicijalizujKorisnike();
        inizijalizujTreninge();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cmbxStatus = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cmbxVreme = new javax.swing.JComboBox<>();
        btnDodaj = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnObrisi = new javax.swing.JButton();
        datumTermin = new datechooser.beans.DateChooserCombo();
        btnUnesi = new javax.swing.JButton();
        datumUplata = new datechooser.beans.DateChooserCombo();
        datumVazenja = new datechooser.beans.DateChooserCombo();
        cmbxKorisnici = new javax.swing.JComboBox();
        cmbxTreninzi = new javax.swing.JComboBox();

        jLabel1.setText("Korisnik:");

        jLabel2.setText("Trening:");

        jLabel3.setText("Datum uplate:");

        jLabel4.setText("Datum vazenja:");

        jLabel5.setText("Status:");

        cmbxStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Važeći", "Nevažeći" }));
        cmbxStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbxStatusActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Unos termina"));

        jLabel6.setText("Dan:");

        jLabel7.setText("Vreme:");

        btnDodaj.setText("Dodaj");
        btnDodaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDodajActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        btnObrisi.setText("Obriši");
        btnObrisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObrisiActionPerformed(evt);
            }
        });

        datumTermin.setWeekStyle(datechooser.view.WeekDaysStyle.SHORT);
        datumTermin.setLocale(new java.util.Locale("sr", "", ""));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnObrisi, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1)))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(9, 9, 9)
                .addComponent(datumTermin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbxVreme, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDodaj, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbxVreme, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDodaj))
                    .addComponent(jLabel7)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(datumTermin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)))
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnObrisi)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnUnesi.setText("Unesi novi");
        btnUnesi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUnesiActionPerformed(evt);
            }
        });

        datumUplata.setWeekStyle(datechooser.view.WeekDaysStyle.SHORT);
        datumUplata.setLocale(new java.util.Locale("sr", "", ""));

        datumVazenja.setWeekStyle(datechooser.view.WeekDaysStyle.SHORT);
        datumVazenja.setLocale(new java.util.Locale("sr", "", ""));

        cmbxKorisnici.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbxKorisniciActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnUnesi, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbxStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(datumVazenja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(datumUplata, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                                    .addComponent(cmbxKorisnici, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbxTreninzi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(cmbxKorisnici, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmbxTreninzi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(datumUplata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(datumVazenja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUnesi)
                .addContainerGap(17, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmbxStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbxStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbxStatusActionPerformed

    private void btnDodajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDodajActionPerformed

        Calendar datum = datumTermin.getSelectedDate();
        String vreme = (String) cmbxVreme.getSelectedItem();
        SimpleDateFormat sdfVreme = new SimpleDateFormat("hh:mm");
        Date vremeBaza;
        Date datumBaza = datum.getTime();
        try {
            vremeBaza = sdfVreme.parse(vreme);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Netačan format vremena.");
            return;
        }
        Termin t = new Termin();
        t.setDatum(datumBaza);
        t.setVreme(vremeBaza);
        tmt.dodajelement(t);
    }//GEN-LAST:event_btnDodajActionPerformed

    private void btnObrisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObrisiActionPerformed
        int selektovanRed = jTable1.getSelectedRow();
        if (selektovanRed == -1) {
            JOptionPane.showMessageDialog(this, "Morate selektovati red.");
            return;
        }
        tmt.obrisiElement(selektovanRed);
    }//GEN-LAST:event_btnObrisiActionPerformed

    private void cmbxKorisniciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbxKorisniciActionPerformed
        Korisnik kor = (Korisnik) cmbxKorisnici.getSelectedItem();
        sesija.Sesija.getInstance().setObjekat(kor);

    }//GEN-LAST:event_cmbxKorisniciActionPerformed

    private void btnUnesiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUnesiActionPerformed
        Korisnik kor = (Korisnik) cmbxKorisnici.getSelectedItem();
        Trening tren = (Trening) cmbxTreninzi.getSelectedItem();
        String status = (String) cmbxStatus.getSelectedItem();
        Date datumUplate = datumUplata.getSelectedDate().getTime();
        Date datumVaz = datumVazenja.getSelectedDate().getTime();
        ArrayList<Termin> listaTermina = tmt.getTermini();
        try {
            kontroler.Kontroler.getInstance().unesiOdabraniTrening(datumUplate, datumVaz, status, kor, tren, listaTermina);
            JOptionPane.showMessageDialog(this, "Uspešno ste uneli odabrani trening.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
            return;
        }
    }//GEN-LAST:event_btnUnesiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDodaj;
    private javax.swing.JButton btnObrisi;
    private javax.swing.JButton btnUnesi;
    private javax.swing.JComboBox cmbxKorisnici;
    private javax.swing.JComboBox<String> cmbxStatus;
    private javax.swing.JComboBox cmbxTreninzi;
    private javax.swing.JComboBox<String> cmbxVreme;
    private datechooser.beans.DateChooserCombo datumTermin;
    private datechooser.beans.DateChooserCombo datumUplata;
    private datechooser.beans.DateChooserCombo datumVazenja;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    private void inicijalizujVreme() {
        cmbxVreme.addItem("16:00");
        cmbxVreme.addItem("17:00");
        cmbxVreme.addItem("18:00");
        cmbxVreme.addItem("19:00");
        cmbxVreme.addItem("20:00");
        cmbxVreme.addItem("21:00");
        cmbxVreme.addItem("22:00");
    }

    private void inicijalizujKorisnike() {
        try {
            ArrayList<Korisnik> listaKorisnika = kontroler.Kontroler.getInstance().vratiSveKorisnike();
            for (Korisnik korisnik : listaKorisnika) {
                cmbxKorisnici.addItem(korisnik);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "GRESKA - korisnici");
            return;
        }
    }

    private void inizijalizujTreninge() {
        try {
            ArrayList<Trening> listaTreninga = kontroler.Kontroler.getInstance().vratiSveTreninge();
            for (Trening trening : listaTreninga) {
                cmbxTreninzi.addItem(trening);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "GRESKA - treninzi");
            return;
        }
    }

}