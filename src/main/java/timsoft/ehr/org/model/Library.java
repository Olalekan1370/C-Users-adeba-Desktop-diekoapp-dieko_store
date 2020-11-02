/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timsoft.ehr.org.model;

import java.io.Serializable;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "library")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Library.findAll", query = "SELECT l FROM Library l")})
public class Library implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 150)
    @Column(name = "pname")
    private String pname;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "sales_price_single")
    private Double salesPriceSingle;
    @Column(name = "purchase_price")
    private Double purchasePrice;
    @Column(name = "quantity")
    private Integer quantity;
    @Size(max = 50)
    @Column(name = "category")
    private String category;
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Column(name = "date_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified;
    @Column(name = "available")
    private Integer available;
    @Column(name = "sales_price_bulk")
    private Double salesPriceBulk;
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    @ManyToOne
    private Accounts createdBy;
    @OneToMany(mappedBy = "libraryid")
    private List<ProductSupply> productSupplyList;

    public Library() {
    }

    public Library(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Double getSalesPriceSingle() {
        return salesPriceSingle;
    }

    public void setSalesPriceSingle(Double salesPriceSingle) {
        this.salesPriceSingle = salesPriceSingle;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public Double getSalesPriceBulk() {
        return salesPriceBulk;
    }

    public void setSalesPriceBulk(Double salesPriceBulk) {
        this.salesPriceBulk = salesPriceBulk;
    }

    public Accounts getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Accounts createdBy) {
        this.createdBy = createdBy;
    }

    @XmlTransient
    public List<ProductSupply> getProductSupplyList() {
        return productSupplyList;
    }

    public void setProductSupplyList(List<ProductSupply> productSupplyList) {
        this.productSupplyList = productSupplyList;
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
        if (!(object instanceof Library)) {
            return false;
        }
        Library other = (Library) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "timsoft.ehr.org.model.Library[ id=" + id + " ]";
    }
    
}
