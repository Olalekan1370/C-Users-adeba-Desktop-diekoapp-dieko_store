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
import timsoft.ehr.org.model.Pump;
import timsoft.ehr.org.model.Reservoir;
import timsoft.ehr.org.model.Reservoirlog;
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
@ManagedBean
public class TransactionController implements Serializable {

    private List<Transactions> datalist;
    @Autowired
    AppService service;
    @Autowired
    LoginController login;
    List<Pump> pumplist;
    Reservoirlog log;
    Stock stock;
    Double qty;
    Double previous_amount;
    Double openingbalance;
    private Date currentDate;

    @PostConstruct
    public void init() {
        currentDate= new Date();
        qty = 0.0;
        previous_amount = 0.0;
        stock = new Stock();
        log = new Reservoirlog();
        pumplist = new ArrayList<>();
        datalist = new ArrayList<>();
        reload();
    }

    public void updateOpening() {
        Transactions tr = (Transactions) FacesUtils.getManagedBean("transactions");
        this.openingbalance = tr.getOpeningbalance();
    }

    public void computePrice() {
        Transactions tr = (Transactions) FacesUtils.getManagedBean("transactions");
        tr.setOpeningbalance(openingbalance);
        qty = tr.getOpeningbalance() - tr.getClosingbalance();
        tr.setQuantity(qty);
        tr.setAmount(tr.getQuantity() * tr.getUnitprice());
        tr.setPurchaseprice(stock.getPurchaseprice());
        previous_amount = tr.getUnitprice() * tr.getQuantity();
        Double cost = tr.getPurchaseprice() * tr.getQuantity();
        tr.setProfit(previous_amount - cost);

    }

    public void computeAmount() {
        Transactions tr = (Transactions) FacesUtils.getManagedBean("transactions");
        Double balance = previous_amount - tr.getCashavailable();
        if (balance >= 0) {
            tr.setDeficitamount(balance);
            tr.setGain(0.0);
        } else {
            balance *= -1;
            tr.setGain(balance);
            tr.setDeficitamount(0.0);
        }

    }

    public void filterPump() {
        Transactions tr = (Transactions) FacesUtils.getManagedBean("transactions");
        pumplist = service.getPumpRepo().findByStockname(tr.getStockid().getId());
        stock = service.getStockRepo().findOne(tr.getStockid().getId());
        tr.setUnitprice(stock.getUnitprice());

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
            sp.setQuantity(login.getDouble(sp.getQuantity()));
            sp.setAmount(login.getDouble(sp.getAmount()));
            sp.setCashavailable(login.getDouble(sp.getCashavailable()));
            sp.setClosingbalance(login.getDouble(sp.getClosingbalance()));
            sp.setDeficitamount(login.getDouble(sp.getDeficitamount()));
            sp.setGain(login.getDouble(sp.getGain()));
            sp.setOpeningbalance(login.getDouble(sp.getOpeningbalance()));
            sp.setProfit(login.getDouble(sp.getProfit()));
            sp.setPurchaseprice(login.getDouble(sp.getPurchaseprice()));
            sp.setUnitprice(login.getDouble(sp.getUnitprice()));
            Pump p = service.getPumpRepo().findOne(sp.getPumpid().getId());
            Reservoir reserve = p.getReservoirid();
            reserve.setLastmodified(new Date());
            reserve.setQuantity(reserve.getQuantity() - login.getDouble(qty));
            sp.setInvoicenumber(login.getInvoice());
            service.getReservoirRepo().save(reserve);
            service.getTransactionRepo().save(sp);
            login.reset("transactions");
            login.log(MessageUtil.RECORD_CREATED, MessageUtil.SUCCESS, MessageUtil.SUCCESS_TAG);
            init();
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

    public Reservoirlog getLog() {
        return log;
    }

    public void setLog(Reservoirlog log) {
        this.log = log;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public Double getPrevious_amount() {
        return previous_amount;
    }

    public void setPrevious_amount(Double previous_amount) {
        this.previous_amount = previous_amount;
    }

    public Double getOpeningbalance() {
        return openingbalance;
    }

    public void setOpeningbalance(Double openingbalance) {
        this.openingbalance = openingbalance;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

}
