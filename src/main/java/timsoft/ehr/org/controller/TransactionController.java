/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timsoft.ehr.org.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import timsoft.ehr.org.model.Transaction;

/**
 *
 * @author HP
 */
@Component
@Scope("session")
public class TransactionController {
    private List<Transaction> datalist;
    @PostConstruct
    public void init(){
        datalist = new ArrayList<>();
    }

    public List<Transaction> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<Transaction> datalist) {
        this.datalist = datalist;
    }
    
 
}
