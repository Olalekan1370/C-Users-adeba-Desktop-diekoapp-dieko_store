/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timsoft.ehr.org.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import timsoft.ehr.org.model.Reservoir;

/**
 *
 * @author HP
 */
public class ReservoirController {
     private List<Reservoir> datalist;
    @PostConstruct
    public void init(){
        datalist = new ArrayList<>();
    }

    public List<Reservoir> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<Reservoir> datalist) {
        this.datalist = datalist;
    }
}
