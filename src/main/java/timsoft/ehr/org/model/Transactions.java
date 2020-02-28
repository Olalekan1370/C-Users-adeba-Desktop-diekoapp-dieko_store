/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timsoft.ehr.org.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JIDEX
 */
@ManagedBean
@ViewScoped
@Entity
@Table(name = "transactions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transactions.findAll", query = "SELECT t FROM Transactions t")})
public class Transactions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "invoicenumber")
    private String invoicenumber;
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
    @Column(name="cashavailable")
    private Double cashavailable;
    @Column(name = "datecreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreated;
    @Column(name = "purchaseprice")
    private Double purchaseprice;
    @Column(name = "profit")
    private Double profit;
    @Column(name="productname")
    private String productname;
    @Column(name="deficitamount")
    private Double deficitamount;
    @Column(name="gain")
    private Double gain;
    @Column(name = "trandate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date trandate;
    @JoinColumn(name = "stockid", referencedColumnName = "id")
    @ManyToOne
    private Stock stockid;
    @JoinColumn(name = "staffid", referencedColumnName = "id")
    @ManyToOne
    private Staff staffid;
    @JoinColumn(name = "pumpid", referencedColumnName = "id")
    @ManyToOne
    private Pump pumpid;

    public Transactions() {
    }

    public Transactions(Long id) {
        this.id = id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getGain() {
        return gain;
    }

    public void setGain(Double gain) {
        this.gain = gain;
    }

    public String getInvoicenumber() {
        return invoicenumber;
    }

    public void setInvoicenumber(String invoicenumber) {
        this.invoicenumber = invoicenumber;
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

    public Double getCashavailable() {
        return cashavailable;
    }

    public void setCashavailable(Double cashavailable) {
        this.cashavailable = cashavailable;
    }

    public Double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(Double unitprice) {
        this.unitprice = unitprice;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    public Double getDeficitamount() {
        return deficitamount;
    }

    public void setDeficitamount(Double deficitamount) {
        this.deficitamount = deficitamount;
    }

    public Double getPurchaseprice() {
        return purchaseprice;
    }

    public void setPurchaseprice(Double purchaseprice) {
        this.purchaseprice = purchaseprice;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Date getTrandate() {
        return trandate;
    }

    public void setTrandate(Date trandate) {
        this.trandate = trandate;
    }

    public Stock getStockid() {
        return stockid;
    }

    public void setStockid(Stock stockid) {
        this.stockid = stockid;
    }

    public Staff getStaffid() {
        return staffid;
    }

    public void setStaffid(Staff staffid) {
        this.staffid = staffid;
    }

    public Pump getPumpid() {
        return pumpid;
    }

    public void setPumpid(Pump pumpid) {
        this.pumpid = pumpid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transactions)) {
            return false;
        }
        Transactions other = (Transactions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "timsoft.ehr.org.model.Transactions[ id=" + id + " ]";
    }
    
    
}
