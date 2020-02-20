/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timsoft.ehr.org.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import timsoft.ehr.org.model.Reservoirlog;

/**
 *
 * @author HP
 */
public class ReservoirlogController {
     private List<Reservoirlog> datalist;
    @PostConstruct
    public void init(){
        datalist = new ArrayList<>();
    }

    public List<Reservoirlog> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<Reservoirlog> datalist) {
        this.datalist = datalist;
    }
}
