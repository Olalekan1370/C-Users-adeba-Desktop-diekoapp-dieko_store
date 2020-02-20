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
import javax.faces.bean.ManagedBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import timsoft.ehr.org.model.Supplier;
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
@ManagedBean
public class SupplierController implements Serializable{

    private List<Supplier> datalist;
    @Autowired
    AppService service;
    @Autowired
    LoginController login;

    @PostConstruct
    public void init() {
        datalist = new ArrayList<>();
        reload();
    }
public void filter(){
    AppHelper app =(AppHelper)FacesUtils.getManagedBean("appHelper");
   datalist = service.getSupplierRepo()
           .filterByDateRange(AppUtils.getDate(app.getDateFrom()), AppUtils.getDate(app.getDateTo()));
   if(datalist.isEmpty()){
       login.log(MessageUtil.RECORD_NOT_FOUND, MessageUtil.ERROR, MessageUtil.ERROR_TAG);
   }
}
    public void reload() {
        datalist = service.getSupplierRepo().findAll();
    }
public void search(){
    AppHelper app =(AppHelper)FacesUtils.getManagedBean("appHelper");
   datalist = service.getSupplierRepo().search(app.getSearchterm());
   if(datalist.isEmpty()){
       login.log(MessageUtil.RECORD_NOT_FOUND, MessageUtil.ERROR, MessageUtil.ERROR_TAG);
   }
}
    public void add() {
        try {
            Supplier sp = (Supplier) FacesUtils.getManagedBean("supplier");
            sp.setDatecreated(new Date());
            sp.setLastmodified(new Date());
            service.getSupplierRepo().save(sp);
            login.reset("supplier");
            login.log(MessageUtil.RECORD_CREATED, MessageUtil.SUCCESS, MessageUtil.SUCCESS_TAG);
        } catch (Exception e) {
            e.printStackTrace();
            login.log(MessageUtil.INTERNAL_ERROR, MessageUtil.ERROR, MessageUtil.ERROR_TAG);
        }

    }
    public void update() {
        try {
            Supplier sp = (Supplier) FacesUtils.getManagedBean("supplier");
            sp.setDatecreated(new Date());
            sp.setLastmodified(new Date());
            service.getSupplierRepo().save(sp);
            login.reset("supplier");
            login.log(MessageUtil.RECORD_CREATED, MessageUtil.SUCCESS, MessageUtil.SUCCESS_TAG);
            reload();
        } catch (Exception e) {
            e.printStackTrace();
            login.log(MessageUtil.INTERNAL_ERROR, MessageUtil.ERROR, MessageUtil.ERROR_TAG);
        }

    }
    
    public void delete(Long id){
        try{
            service.getSupplierRepo().delete(id);
            login.log(MessageUtil.RECORD_DELETED, MessageUtil.SUCCESS, MessageUtil.SUCCESS_TAG);
            reload();
        }catch(Exception e){
             e.printStackTrace();
            login.log(MessageUtil.INTERNAL_ERROR, MessageUtil.ERROR, MessageUtil.ERROR_TAG);
        }
    }
    

    public List<Supplier> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<Supplier> datalist) {
        this.datalist = datalist;
    }

}
