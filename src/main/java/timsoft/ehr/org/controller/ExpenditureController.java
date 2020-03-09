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
import org.primefaces.event.SelectEvent;
import org.python.icu.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import timsoft.ehr.org.model.Excategory;
import timsoft.ehr.org.model.Expenditure;
import timsoft.ehr.org.model.VwBalance;
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
public class ExpenditureController implements Serializable {

    private List<Expenditure> datalist;
    @Autowired
    AppService service;
    @Autowired
    LoginController login;
    private Date currentDate;
    private Double staffdeficit;
    private Expenditure current;
    private Excategory category;
Date trandate;
    @PostConstruct
    public void init() {
        
        category = new Excategory();
        current = new Expenditure();
        staffdeficit = 0.0;
        currentDate = new Date();
        datalist = new ArrayList<>();
        reload();
    }
public void updateDate(SelectEvent event){
    trandate = (Date)event.getObject();
    System.out.println(trandate);
}
    public void updateCategory() {
        category = service.getExcategoryRepo().findOne(current.getExcategoryid().getId());
    }

    public void filterLoss() {
        if(category.getName().startsWith("SALARY")){
            System.out.println("Current Date......................_"+ current.getTransdate());
           Calendar cl =Calendar.getInstance();
           cl.setTime(trandate);
        List<VwBalance> list = service.getVwBalanceRepo().listByStaff(current.getStaffid().getId(),
                cl.get(Calendar.YEAR), cl.get(Calendar.MONTH)+1);
        Double gain =0.0;
        staffdeficit=0.0;
        for(VwBalance rs: list){
            gain+=rs.getGain();
            staffdeficit+=rs.getLoss();
        }
        Double result = staffdeficit-gain;
        if(result>0){
            staffdeficit=result;
        }else{
            result*=-1;
            staffdeficit=result;
        }
        current.setDeducation(staffdeficit);
        }else{
            System.out.println("Entere second here");
            current.setDeducation(0.0);
        }
    }

    public void updateAmount() {
        current.setAmount_issued(current.getAmount() - staffdeficit);
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public void filter() {
        AppHelper app = (AppHelper) FacesUtils.getManagedBean("appHelper");
        datalist = service.getExpenditureRepo()
                .filterByDateRange(AppUtils.getDate(app.getDateFrom()), AppUtils.getDate(app.getDateTo()));
        if (datalist.isEmpty()) {
            login.log(MessageUtil.RECORD_NOT_FOUND, MessageUtil.ERROR, MessageUtil.ERROR_TAG);
        }
    }

    public void reload() {
        datalist = service.getExpenditureRepo().findAll();
    }

    public void search() {
        AppHelper app = (AppHelper) FacesUtils.getManagedBean("appHelper");
        datalist = service.getExpenditureRepo().search(app.getSearchterm());
        if (datalist.isEmpty()) {
            login.log(MessageUtil.RECORD_NOT_FOUND, MessageUtil.ERROR, MessageUtil.ERROR_TAG);
        }
    }

    public void add() {
        try {
            current.setDatecreated(new Date());
            service.getExpenditureRepo().save(current);
            reload();
            login.log(MessageUtil.RECORD_CREATED, MessageUtil.SUCCESS, MessageUtil.SUCCESS_TAG);
            current = new Expenditure();
        } catch (Exception e) {
            e.printStackTrace();
            login.log(MessageUtil.INTERNAL_ERROR, MessageUtil.ERROR, MessageUtil.ERROR_TAG);
        }

    }

    public void update() {
        try {
            Expenditure sp = (Expenditure) FacesUtils.getManagedBean("expenditure");
            sp.setDatecreated(new Date());
            service.getExpenditureRepo().save(sp);
            login.reset("expenditure");
            reload();
            login.log(MessageUtil.RECORD_CREATED, MessageUtil.SUCCESS, MessageUtil.SUCCESS_TAG);
            reload();
        } catch (Exception e) {
            e.printStackTrace();
            login.log(MessageUtil.INTERNAL_ERROR, MessageUtil.ERROR, MessageUtil.ERROR_TAG);
        }

    }

    public void delete(Long id) {
        try {
            service.getExpenditureRepo().delete(id);
            login.log(MessageUtil.RECORD_DELETED, MessageUtil.SUCCESS, MessageUtil.SUCCESS_TAG);
            reload();
        } catch (Exception e) {
            e.printStackTrace();
            login.log(MessageUtil.INTERNAL_ERROR, MessageUtil.ERROR, MessageUtil.ERROR_TAG);
        }
    }

    public List<Expenditure> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<Expenditure> datalist) {
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

    public Double getStaffdeficit() {
        return staffdeficit;
    }

    public void setStaffdeficit(Double staffdeficit) {
        this.staffdeficit = staffdeficit;
    }

    public Expenditure getCurrent() {
        return current;
    }

    public void setCurrent(Expenditure current) {
        this.current = current;
    }

    public Excategory getCategory() {
        return category;
    }

    public void setCategory(Excategory category) {
        this.category = category;
    }

}
