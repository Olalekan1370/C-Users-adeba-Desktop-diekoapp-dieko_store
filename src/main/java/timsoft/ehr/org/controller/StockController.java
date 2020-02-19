/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timsoft.ehr.org.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import timsoft.ehr.org.model.Stock;

/**
 *
 * @author HP
 */
@Component
@Scope("session")
public class StockController {
     private List<Stock> datalist;
    @PostConstruct
    public void init(){
        datalist = new ArrayList<>();
    }

    public List<Stock> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<Stock> datalist) {
        this.datalist = datalist;
    }
        

    
}
