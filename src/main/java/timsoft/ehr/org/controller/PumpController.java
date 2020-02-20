/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timsoft.ehr.org.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import timsoft.ehr.org.model.Pump;

/**
 *
 * @author HP
 */
public class PumpController {
      private List<Pump> datalist;
    @PostConstruct
    public void init(){
        datalist = new ArrayList<>();
    }

    public List<Pump> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<Pump> datalist) {
        this.datalist = datalist;
    }
}
