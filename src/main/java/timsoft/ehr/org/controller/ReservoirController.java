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
import timsoft.ehr.org.model.Reservoir;
import timsoft.ehr.org.model.Reservoirlog;
import timsoft.ehr.org.model.Stock;
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
public class ReservoirController implements Serializable {

    private List<Reservoir> datalist;
    @Autowired
    AppService service;
    @Autowired
    LoginController login;
    private Reservoir currentReservoir;
    private List<Reservoirlog> reservelogs;
    Double qty;
Double previous;

    @PostConstruct
    public void init() {
        qty = 0.0;
        reservelogs = new ArrayList<>();
        currentReservoir = new Reservoir();
        datalist = new ArrayList<>();
        reload();
    }

    public void updateQuantity() {
        Reservoir sp = (Reservoir) FacesUtils.getManagedBean("reservoir");
        qty = sp.getQuantity();
    }
    public void updateQuantity2() {
       Reservoirlog sp = (Reservoirlog) FacesUtils.getManagedBean("reservoirlog");
        qty = sp.getQuantity();
        System.out.println(qty);
    }
    public void updatePrevious() {
       Reservoirlog sp = (Reservoirlog) FacesUtils.getManagedBean("reservoirlog");
        previous = sp.getPreviousreading();
        System.out.println(previous);
    }
public void updateDeficit() {
        Reservoirlog log = (Reservoirlog) FacesUtils.getManagedBean("reservoirlog");
        System.out.println(" QTY: "+ qty);
        System.out.println("Previous:.............."+previous);
        log.setDeficitamount(log.getCurrentreading() - (qty+previous));
    }
    public void loadDefinicit() {
        Reservoirlog log = (Reservoirlog) FacesUtils.getManagedBean("reservoirlog");
        log.setQuantity(qty);
        log.setDeficitamount(log.getCurrentreading() - (qty+previous));
    }

    public void addLog() {
        try {
            Stock st = service.getStockRepo().findOne(currentReservoir.getStockid().getId());
            Reservoirlog log = (Reservoirlog) FacesUtils.getManagedBean("reservoirlog");
            log.setDatecreated(new Date());
            log.setStockname(st.getName());
            log.setReservoirid(currentReservoir);
            log.setUnitcost(log.getCost() / log.getQuantity());
            currentReservoir.setLastmodified(new Date());
            service.getReservoirlogRepo().save(log);
            login.reset("reservoirlog");
            loadChildren(currentReservoir);
            login.log(MessageUtil.RECORD_CREATED, MessageUtil.SUCCESS, MessageUtil.SUCCESS_TAG);
        } catch (Exception e) {
            e.printStackTrace();
            login.log(MessageUtil.INTERNAL_ERROR, MessageUtil.ERROR, MessageUtil.ERROR_TAG);
        }
    }

    public void filterLog() {
        AppHelper app = (AppHelper) FacesUtils.getManagedBean("appHelper");
        reservelogs = service.getReservoirlogRepo()
                .filterByDateRange(AppUtils.getDate(app.getDateFrom()), AppUtils.getDate(app.getDateTo()));
        if (reservelogs.isEmpty()) {
            login.log(MessageUtil.RECORD_NOT_FOUND, MessageUtil.ERROR, MessageUtil.ERROR_TAG);
        }
    }

    public void deleteLog(Long id) {
        service.getReservoirlogRepo().delete(id);
        loadChildren(currentReservoir);
    }

    public void loadChildren(Reservoir parentid) {
        reservelogs = service.getReservoirlogRepo().listByParent(parentid.getId());
        currentReservoir = parentid;
    }

    public void filter() {

        AppHelper app = (AppHelper) FacesUtils.getManagedBean("appHelper");
        datalist = service.getReservoirRepo()
                .filterByDateRange(AppUtils.getDate(app.getDateFrom()), AppUtils.getDate(app.getDateTo()));
        if (datalist.isEmpty()) {
            login.log(MessageUtil.RECORD_NOT_FOUND, MessageUtil.ERROR, MessageUtil.ERROR_TAG);
        }
    }

    public void reload() {
        datalist = service.getReservoirRepo().findAll();
    }

    public void search() {
        AppHelper app = (AppHelper) FacesUtils.getManagedBean("appHelper");
        datalist = service.getReservoirRepo().search(app.getSearchterm());
        if (datalist.isEmpty()) {
            login.log(MessageUtil.RECORD_NOT_FOUND, MessageUtil.ERROR, MessageUtil.ERROR_TAG);
        }
    }

    public void add() {
        try {
            Reservoir sp = (Reservoir) FacesUtils.getManagedBean("reservoir");
            sp.setDatecreated(new Date());
            Stock st = service.getStockRepo().findOne(sp.getStockid().getId());
            Reservoirlog log = (Reservoirlog) FacesUtils.getManagedBean("reservoirlog");
            log.setDatecreated(new Date());
            log.setStockname(st.getName());
            log.setQuantity(sp.getQuantity());
            log.setReservoirid(sp);
            log.setUnitcost(log.getCost() / sp.getQuantity());
            sp.setLastmodified(new Date());
            List<Reservoirlog> loglist = new ArrayList<>();
            loglist.add(log);
            sp.setReservoirlogList(loglist);
            service.getReservoirRepo().save(sp);
            login.reset("reservoir");
            login.reset("reservoirlog");
            reload();
            login.log(MessageUtil.RECORD_CREATED, MessageUtil.SUCCESS, MessageUtil.SUCCESS_TAG);
        } catch (Exception e) {
            e.printStackTrace();
            login.log(MessageUtil.INTERNAL_ERROR, MessageUtil.ERROR, MessageUtil.ERROR_TAG);
        }

    }

    public void update() {
        try {
            Reservoir sp = (Reservoir) FacesUtils.getManagedBean("reservoir");
            sp.setDatecreated(new Date());
            service.getReservoirRepo().save(sp);
            login.reset("reservoir");
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
            service.getReservoirRepo().delete(id);
            login.log(MessageUtil.RECORD_DELETED, MessageUtil.SUCCESS, MessageUtil.SUCCESS_TAG);
            reload();
        } catch (Exception e) {
            e.printStackTrace();
            login.log(MessageUtil.INTERNAL_ERROR, MessageUtil.ERROR, MessageUtil.ERROR_TAG);
        }
    }

    public List<Reservoir> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<Reservoir> datalist) {
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

    public Reservoir getCurrentReservoir() {
        return currentReservoir;
    }

    public void setCurrentReservoir(Reservoir currentReservoir) {
        this.currentReservoir = currentReservoir;
    }

    public List<Reservoirlog> getReservelogs() {
        return reservelogs;
    }

    public void setReservelogs(List<Reservoirlog> reservelogs) {
        this.reservelogs = reservelogs;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

}
