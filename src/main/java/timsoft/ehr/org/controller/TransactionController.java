/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timsoft.ehr.org.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import timsoft.ehr.org.model.Transactions;

/**
 *
 * @author HP
 */
@Component
@Scope("session")
public class TransactionController  implements Serializable{
    private List<Transactions> datalist;
    @PostConstruct
    public void init(){
        datalist = new ArrayList<>();
    }

    public List<Transactions> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<Transactions> datalist) {
        this.datalist = datalist;
    }
    
 
}
