/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timsoft.ehr.org.model;

import java.io.Serializable;
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
@Table(name = "reservoirlog")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reservoirlog.findAll", query = "SELECT r FROM Reservoirlog r")})
public class Reservoirlog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "quantity")
    private Integer quantity;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cost")
    private Double cost;
    @Column(name = "unitcost")
    private Double unitcost;
    @Column(name = "datesupplied")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datesupplied;
    @Size(max = 250)
    @Column(name = "previousreading")
    private String previousreading;
    @Size(max = 250)
    @Column(name = "currentreading")
    private String currentreading;
    @Size(max = 250)
    @Column(name = "stockname")
    private String stockname;
    @JoinColumn(name = "reservoirid", referencedColumnName = "id")
    @ManyToOne
    private Reservoir reservoirid;
    @JoinColumn(name = "supplierid", referencedColumnName = "id")
    @ManyToOne
    private Supplier supplierid;

    public Reservoirlog() {
    }

    public Reservoirlog(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getUnitcost() {
        return unitcost;
    }

    public void setUnitcost(Double unitcost) {
        this.unitcost = unitcost;
    }

    public Date getDatesupplied() {
        return datesupplied;
    }

    public void setDatesupplied(Date datesupplied) {
        this.datesupplied = datesupplied;
    }

    public String getPreviousreading() {
        return previousreading;
    }

    public void setPreviousreading(String previousreading) {
        this.previousreading = previousreading;
    }

    public String getCurrentreading() {
        return currentreading;
    }

    public void setCurrentreading(String currentreading) {
        this.currentreading = currentreading;
    }

    public String getStockname() {
        return stockname;
    }

    public void setStockname(String stockname) {
        this.stockname = stockname;
    }

    public Reservoir getReservoirid() {
        return reservoirid;
    }

    public void setReservoirid(Reservoir reservoirid) {
        this.reservoirid = reservoirid;
    }

    public Supplier getSupplierid() {
        return supplierid;
    }

    public void setSupplierid(Supplier supplierid) {
        this.supplierid = supplierid;
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
        if (!(object instanceof Reservoirlog)) {
            return false;
        }
        Reservoirlog other = (Reservoirlog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "timsoft.ehr.org.model.Reservoirlog[ id=" + id + " ]";
    }
    
}
