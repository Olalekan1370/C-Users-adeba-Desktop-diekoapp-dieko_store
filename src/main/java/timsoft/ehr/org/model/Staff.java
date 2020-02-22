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
@ViewScoped
@Entity
@Table(name = "staff")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Staff.findAll", query = "SELECT s FROM Staff s")})
public class Staff implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 250)
    @Column(name = "fullname")
    private String fullname;
    @Column(name = "age")
    private Integer age;
    @Size(max = 250)
    @Column(name = "sex")
    private String sex;
    @Size(max = 250)
    @Column(name = "phonenumber")
    private String phonenumber;
    @Size(max = 250)
    @Column(name = "qualification")
    private String qualification;
    @Size(max = 250)
    @Column(name = "address")
    private String address;
    @Size(max = 250)
    @Column(name = "designation")
    private String designation;
    @Column(name = "datecreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreated;
    @OneToMany(mappedBy = "staffid")
    private List<Expenditure> expenditureList;
    @OneToMany(mappedBy = "staffid")
    private List<Transactions> transactionsList;
    @OneToMany(mappedBy = "createdby")
    private List<Bankdeposit> bankdepositList;
    @OneToMany(mappedBy = "staffid")
    private List<Shifting> shiftingList;
    @OneToMany(mappedBy = "staffid")
    private List<User> userList;

    public Staff() {
    }

    public Staff(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    @XmlTransient
    public List<Expenditure> getExpenditureList() {
        return expenditureList;
    }

    public void setExpenditureList(List<Expenditure> expenditureList) {
        this.expenditureList = expenditureList;
    }

    @XmlTransient
    public List<Transactions> getTransactionsList() {
        return transactionsList;
    }

    public void setTransactionsList(List<Transactions> transactionsList) {
        this.transactionsList = transactionsList;
    }

    @XmlTransient
    public List<Bankdeposit> getBankdepositList() {
        return bankdepositList;
    }

    public void setBankdepositList(List<Bankdeposit> bankdepositList) {
        this.bankdepositList = bankdepositList;
    }

    @XmlTransient
    public List<Shifting> getShiftingList() {
        return shiftingList;
    }

    public void setShiftingList(List<Shifting> shiftingList) {
        this.shiftingList = shiftingList;
    }

    @XmlTransient
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
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
        if (!(object instanceof Staff)) {
            return false;
        }
        Staff other = (Staff) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "timsoft.ehr.org.model.Staff[ id=" + id + " ]";
    }
    
}
