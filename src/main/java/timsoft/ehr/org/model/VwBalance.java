/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timsoft.ehr.org.model;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JIDEX
 */
@Entity
@Table(name = "vw_balance")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VwBalance.findAll", query = "SELECT v FROM VwBalance v")})
public class VwBalance implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    @Id
    private long id;
    @Column(name = "staffid")
    private Long staffid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "openingbalance")
    private Double openingbalance;
    @Column(name = "closingbalance")
    private Double closingbalance;
    @Column(name = "quantity")
    private Double quantity;
    @Column(name = "unitprice")
    private Double unitprice;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "loss")
    private Double loss;
    @Column(name = "gain")
    private Double gain;
    @Column(name = "months")
    private Integer months;
    @Column(name="years")
    private Integer years;
    @Column(name="monthnames")
    private String monthnames;
    @Size(max = 250)
    @Column(name = "fullname")
    private String fullname;

    public VwBalance() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMonthnames() {
        return monthnames;
    }

    public void setMonthnames(String monthnames) {
        this.monthnames = monthnames;
    }

    public Long getStaffid() {
        return staffid;
    }

    public void setStaffid(Long staffid) {
        this.staffid = staffid;
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

    public Double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(Double unitprice) {
        this.unitprice = unitprice;
    }

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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

    public Integer getMonths() {
        return months;
    }

    public void setMonths(Integer months) {
        this.months = months;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    
}
