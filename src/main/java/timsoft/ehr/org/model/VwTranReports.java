/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timsoft.ehr.org.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JIDEX
 */
@Entity
@Table(name = "vw_tran_reports")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VwTranReports.findAll", query = "SELECT v FROM VwTranReports v")})
public class VwTranReports implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    @Id
    private long id;
    @Size(max = 30)
    @Column(name = "productname")
    private String productname;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "openingbalance")
    private Double openingbalance;
    @Column(name = "closingbalance")
    private Double closingbalance;
    @Column(name = "quantity")
    private Double quantity;
    @Column(name = "salesprice")
    private Double salesprice;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "profit")
    private Double profit;
    @Column(name = "amountreceived")
    private Double amountreceived;
    @Column(name = "loss")
    private Double loss;
    @Column(name = "gain")
    private Double gain;
    @Column(name = "deposit")
    private Double deposit;
    @Size(max = 250)
    @Column(name = "bank")
    private String bank;
    @Column(name = "expenses")
    private Double expenses;
    @Column(name = "balance")
    private Double balance;
    @Column(name="months")
    private String months;
    @Column(name = "trandate")
    @Temporal(TemporalType.DATE)
    private Date trandate;

    public VwTranReports() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public Double getOpeningbalance() {
        return openingbalance;
    }

    public void setOpeningbalance(Double openingbalance) {
        this.openingbalance = openingbalance;
    }

    public Double getClosingbalance() {
        return closingbalance;
    }

    public void setClosingbalance(Double closingbalance) {
        this.closingbalance = closingbalance;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getSalesprice() {
        return salesprice;
    }

    public void setSalesprice(Double salesprice) {
        this.salesprice = salesprice;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Double getAmountreceived() {
        return amountreceived;
    }

    public void setAmountreceived(Double amountreceived) {
        this.amountreceived = amountreceived;
    }

    public Double getLoss() {
        return loss;
    }

    public void setLoss(Double loss) {
        this.loss = loss;
    }

    public Double getGain() {
        return gain;
    }

    public void setGain(Double gain) {
        this.gain = gain;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public Double getExpenses() {
        return expenses;
    }

    public void setExpenses(Double expenses) {
        this.expenses = expenses;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Date getTrandate() {
        return trandate;
    }

    public void setTrandate(Date trandate) {
        this.trandate = trandate;
    }

    public String getMonths() {
        return months;
    }

    public void setMonths(String months) {
        this.months = months;
    }
    
}
