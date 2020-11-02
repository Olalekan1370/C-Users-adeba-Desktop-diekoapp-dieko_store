/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timsoft.ehr.org.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Olalekan
 */
@ManagedBean
@ViewScoped
@Entity
@Table(name = "purchases")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Purchases.findAll", query = "SELECT p FROM Purchases p")})
public class Purchases implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 50)
    @Column(name = "productname")
    private String productname;
    @Size(max = 50)
    @Column(name = "category")
    private String category;
    @Lob
    @Size(max = 65535)
    @Column(name = "supplier")
    private String supplier;
    @Size(max = 50)
    @Column(name = "customer")
    private String customer;
    @Size(max = 50)
    @Column(name = "customercode")
    private String customercode;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "salesprice")
    private Double salesprice;
    @Column(name = "purchaseprice")
    private Double purchaseprice;
    @Column(name = "quantity")
    private Integer quantity;
    @Size(max = 20)
    @Column(name = "sellingcategory")
    private String sellingcategory;
    @Column(name = "profit")
    private Double profit;
    @Column(name = "datecreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreated;
    @Size(max = 50)
    @Column(name = "paymenttype")
    private String paymenttype;
    @Column(name = "amountpaid")
    private Double amountpaid;
    @Column(name = "discount")
    private Double discount;
    @Column(name = "vat")
    private Double vat;
    @Column(name = "modifiedby")
    private BigInteger modifiedby;
    @Size(max = 30)
    @Column(name = "invoiceno")
    private String invoiceno;
    @Size(max = 50)
    @Column(name = "transactionid")
    private String transactionid;
    @OneToMany(mappedBy = "purchaseid")
    private List<PurchaseDebt> purchaseDebtList;

    public Purchases() {
    }

    public Purchases(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCustomercode() {
        return customercode;
    }

    public void setCustomercode(String customercode) {
        this.customercode = customercode;
    }

    public Double getSalesprice() {
        return salesprice;
    }

    public void setSalesprice(Double salesprice) {
        this.salesprice = salesprice;
    }

    public Double getPurchaseprice() {
        return purchaseprice;
    }

    public void setPurchaseprice(Double purchaseprice) {
        this.purchaseprice = purchaseprice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getSellingcategory() {
        return sellingcategory;
    }

    public void setSellingcategory(String sellingcategory) {
        this.sellingcategory = sellingcategory;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    public String getPaymenttype() {
        return paymenttype;
    }

    public void setPaymenttype(String paymenttype) {
        this.paymenttype = paymenttype;
    }

    public Double getAmountpaid() {
        return amountpaid;
    }

    public void setAmountpaid(Double amountpaid) {
        this.amountpaid = amountpaid;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getVat() {
        return vat;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }

    public BigInteger getModifiedby() {
        return modifiedby;
    }

    public void setModifiedby(BigInteger modifiedby) {
        this.modifiedby = modifiedby;
    }

    public String getInvoiceno() {
        return invoiceno;
    }

    public void setInvoiceno(String invoiceno) {
        this.invoiceno = invoiceno;
    }

    public String getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid;
    }

    @XmlTransient
    public List<PurchaseDebt> getPurchaseDebtList() {
        return purchaseDebtList;
    }

    public void setPurchaseDebtList(List<PurchaseDebt> purchaseDebtList) {
        this.purchaseDebtList = purchaseDebtList;
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
        if (!(object instanceof Purchases)) {
            return false;
        }
        Purchases other = (Purchases) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "timsoft.ehr.org.model.Purchases[ id=" + id + " ]";
    }
    
}
