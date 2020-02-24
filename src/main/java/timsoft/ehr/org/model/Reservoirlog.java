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
    private Double quantity;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cost")
    private Double cost;
    @Column(name = "unitcost")
    private Double unitcost;
    @Column(name="deficitamount")
    private Double deficitamount;
    @Column(name="available")
    private Integer available;
    @Column(name="pumpid")
    private Long pumpid;
    @Column(name = "datesupplied")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datesupplied;
     @Temporal(TemporalType.TIMESTAMP)
    @Column(name="datecreated")
    private Date datecreated;
    @Size(max = 250)
    @Column(name = "previousreading")
    private Double previousreading;
    @Size(max = 250)
    @Column(name = "currentreading")
    private Double currentreading;
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

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
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

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public Long getPumpid() {
        return pumpid;
    }

    public void setPumpid(Long pumpid) {
        this.pumpid = pumpid;
    }

    public Date getDatesupplied() {
        return datesupplied;
    }

    public void setDatesupplied(Date datesupplied) {
        this.datesupplied = datesupplied;
    }

    public Double getPreviousreading() {
        return previousreading;
    }

    public void setPreviousreading(Double previousreading) {
        this.previousreading = previousreading;
    }

    public Double getCurrentreading() {
        return currentreading;
    }

    public void setCurrentreading(Double currentreading) {
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
