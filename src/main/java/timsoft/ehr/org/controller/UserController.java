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
import timsoft.ehr.org.model.User;
import timsoft.ehr.org.model.User;
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
public class UserController implements Serializable {

    private List<User> datalist;
    @Autowired
    AppService service;
    @Autowired
    LoginController login;

    @PostConstruct
    public void init() {
        datalist = new ArrayList<>();
        reload();
    }

    public void filter() {
        AppHelper app = (AppHelper) FacesUtils.getManagedBean("appHelper");
        datalist = service.getUserRepo()
                .filterByDateRange(AppUtils.getDate(app.getDateFrom()), AppUtils.getDate(app.getDateTo()));
        if (datalist.isEmpty()) {
            login.log(MessageUtil.RECORD_NOT_FOUND, MessageUtil.ERROR, MessageUtil.ERROR_TAG);
        }
    }

    public void reload() {
        datalist = service.getUserRepo().findAll();
    }

    public void search() {
        AppHelper app = (AppHelper) FacesUtils.getManagedBean("appHelper");
        datalist = service.getUserRepo().search(app.getSearchterm());
        if (datalist.isEmpty()) {
            login.log(MessageUtil.RECORD_NOT_FOUND, MessageUtil.ERROR, MessageUtil.ERROR_TAG);
        }
    }

    public void add() {
        try {
            User sp = (User) FacesUtils.getManagedBean("user");
            List<User> lst = service.getUserRepo().findByUsername(sp.getUsername());
            if(lst.isEmpty()){
            sp.setDatecreated(new Date());
            service.getUserRepo().save(sp);
            login.reset("user");
            reload();
            login.log(MessageUtil.RECORD_CREATED, MessageUtil.SUCCESS, MessageUtil.SUCCESS_TAG);
            }else{
                login.log(MessageUtil.DUPLICATE_USER, MessageUtil.ERROR, MessageUtil.ERROR_TAG);
            }
        } catch (Exception e) {
            e.printStackTrace();
            login.log(MessageUtil.INTERNAL_ERROR, MessageUtil.ERROR, MessageUtil.ERROR_TAG);
        }

    }

    public void update() {
        try {
            User sp = (User) FacesUtils.getManagedBean("user");
            sp.setDatecreated(new Date());
            service.getUserRepo().save(sp);
            login.reset("user");
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
            service.getUserRepo().delete(id);
            login.log(MessageUtil.RECORD_DELETED, MessageUtil.SUCCESS, MessageUtil.SUCCESS_TAG);
            reload();
        } catch (Exception e) {
            e.printStackTrace();
            login.log(MessageUtil.INTERNAL_ERROR, MessageUtil.ERROR, MessageUtil.ERROR_TAG);
        }
    }

    public List<User> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<User> datalist) {
        this.datalist = datalist;
    }

}
