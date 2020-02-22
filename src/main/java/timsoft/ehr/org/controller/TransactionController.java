/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timsoft.ehr.org.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import timsoft.ehr.org.model.Pump;
import timsoft.ehr.org.model.Stock;
import timsoft.ehr.org.model.Transactions;
import timsoft.ehr.org.repository.AppService;
import timsoft.ehr.org.utils.AppHelper;
import timsoft.ehr.org.utils.AppUtils;
import timsoft.ehr.org.utils.FacesUtils;
import timsoft.ehr.org.utils.MessageUtil;

/**
 *
 * @author HP
 */
@Component
@Scope("session")
public class TransactionController  implements Serializable{
    private List<Transactions> datalist;
    @Autowired
    AppService service;
    @Autowired
    LoginController login;
    List<Pump> pumplist;
    @PostConstruct
    public void init(){
        pumplist = new ArrayList<>();
        datalist = new ArrayList<>();
       reload();
    }
    public void computePrice(){
        Transactions tr =(Transactions)FacesUtils.getManagedBean("transactions");
        tr.setAmount(tr.getQuantity()*tr.getUnitprice());
    }
public void filterPump(){
    Transactions tr =(Transactions)FacesUtils.getManagedBean("transactions");
    pumplist = service.getPumpRepo().findByStockname(tr.getStockid().getId());
    Stock st = service.getStockRepo().findOne(tr.getStockid().getId());
    tr.setUnitprice(st.getUnitprice());
   
}
    public void filter() {
        AppHelper app = (AppHelper) FacesUtils.getManagedBean("appHelper");
        datalist = service.getTransactionRepo()
                .filterByDateRange(AppUtils.getDate(app.getDateFrom()), AppUtils.getDate(app.getDateTo()));
        if (datalist.isEmpty()) {
            login.log(MessageUtil.RECORD_NOT_FOUND, MessageUtil.ERROR, MessageUtil.ERROR_TAG);
        }
    }

    public void reload() {
        datalist = service.getTransactionRepo().findAll();
    }

    public void search() {
        AppHelper app = (AppHelper) FacesUtils.getManagedBean("appHelper");
        datalist = service.getTransactionRepo().search(app.getSearchterm());
        if (datalist.isEmpty()) {
            login.log(MessageUtil.RECORD_NOT_FOUND, MessageUtil.ERROR, MessageUtil.ERROR_TAG);
        }
    }

    public void add() {
        try {
            Transactions sp = (Transactions) FacesUtils.getManagedBean("transactions");
            sp.setDatecreated(new Date());
            service.getTransactionRepo().save(sp);
            login.reset("transactions");
            login.log(MessageUtil.RECORD_CREATED, MessageUtil.SUCCESS, MessageUtil.SUCCESS_TAG);
        } catch (Exception e) {
            e.printStackTrace();
            login.log(MessageUtil.INTERNAL_ERROR, MessageUtil.ERROR, MessageUtil.ERROR_TAG);
        }

    }

    public void update() {
        try {
            Transactions sp = (Transactions) FacesUtils.getManagedBean("transactions");
            sp.setDatecreated(new Date());
            service.getTransactionRepo().save(sp);
            login.reset("transactions");
            login.log(MessageUtil.RECORD_CREATED, MessageUtil.SUCCESS, MessageUtil.SUCCESS_TAG);
            reload();
        } catch (Exception e) {
            e.printStackTrace();
            login.log(MessageUtil.INTERNAL_ERROR, MessageUtil.ERROR, MessageUtil.ERROR_TAG);
        }

    }

    public void delete(Long id) {
        try {
            service.getTransactionRepo().delete(id);
            login.log(MessageUtil.RECORD_DELETED, MessageUtil.SUCCESS, MessageUtil.SUCCESS_TAG);
            reload();
        } catch (Exception e) {
            e.printStackTrace();
            login.log(MessageUtil.INTERNAL_ERROR, MessageUtil.ERROR, MessageUtil.ERROR_TAG);
        }
    }
    public List<Transactions> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<Transactions> datalist) {
        this.datalist = datalist;
    }

    public AppService getService() {
        return service;
    }

    public void setService(AppService service) {
        this.service = service;
    }

    public LoginController getLogin() {
        return login;
    }

    public void setLogin(LoginController login) {
        this.login = login;
    }

    public List<Pump> getPumplist() {
        return pumplist;
    }

    public void setPumplist(List<Pump> pumplist) {
        this.pumplist = pumplist;
    }
    
 
}
