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
import javax.faces.bean.SessionScoped;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
 * @author JIDEX
 */
@ManagedBean
@SessionScoped
@Entity
@Table(name = "reservoir")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reservoir.findAll", query = "SELECT r FROM Reservoir r")})
public class Reservoir implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "quantity")
    private Double quantity;
    @Column(name="deficitamount")
    private Double deficitamount;
    @Size(max = 50)
    @Column(name = "units")
    private String units;
    @Column(name = "datecreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreated;
    @Column(name = "lastmodified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastmodified;
    @Size(max = 100)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "reservoirid")
    private List<Reservoirlog> reservoirlogList;
    @JoinColumn(name = "stockid", referencedColumnName = "id")
    @ManyToOne
    private Stock stockid;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "reservoirid")
    private List<Pump> pumpList;

    public Reservoir() {
    }

    public Reservoir(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getDeficitamount() {
        return deficitamount;
    }

    public void setDeficitamount(Double deficitamount) {
        this.deficitamount = deficitamount;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    public Date getLastmodified() {
        return lastmodified;
    }

    public void setLastmodified(Date lastmodified) {
        this.lastmodified = lastmodified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<Reservoirlog> getReservoirlogList() {
        return reservoirlogList;
    }

    public void setReservoirlogList(List<Reservoirlog> reservoirlogList) {
        this.reservoirlogList = reservoirlogList;
    }

    public Stock getStockid() {
        return stockid;
    }

    public void setStockid(Stock stockid) {
        this.stockid = stockid;
    }

    @XmlTransient
    public List<Pump> getPumpList() {
        return pumpList;
    }

    public void setPumpList(List<Pump> pumpList) {
        this.pumpList = pumpList;
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
        if (!(object instanceof Reservoir)) {
            return false;
        }
        Reservoir other = (Reservoir) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "timsoft.ehr.org.model.Reservoir[ id=" + id + " ]";
    }
    
}
