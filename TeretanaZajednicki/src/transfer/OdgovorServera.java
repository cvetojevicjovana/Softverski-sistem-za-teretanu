/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import java.io.Serializable;
import operacija.StatusOperacije;

/**
 *
 * @author Roki
 */
public class OdgovorServera implements Serializable {

    private Object odgovor;
    private Object greska;
    private StatusOperacije status;

    public OdgovorServera() {
    }

    public OdgovorServera(Object odgovor, Object greska, StatusOperacije status) {
        this.odgovor = odgovor;
        this.greska = greska;
        this.status = status;
    }

    public Object getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(Object odgovor) {
        this.odgovor = odgovor;
    }

    public StatusOperacije getStatus() {
        return status;
    }

    public void setStatus(StatusOperacije status) {
        this.status = status;
    }

    public Object getGreska() {
        return greska;
    }

    public void setGreska(Object greska) {
        this.greska = greska;
    }

}
