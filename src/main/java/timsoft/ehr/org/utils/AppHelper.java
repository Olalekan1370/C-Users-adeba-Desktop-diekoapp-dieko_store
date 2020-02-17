/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timsoft.ehr.org.utils;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author JIDEX
 */
@ManagedBean
@ViewScoped
public class AppHelper implements Serializable {

    private String courselist;
    private String confirmPassword;
    private Long staffid;
    private Long patientid;
    private Long unitid;
    private Long departmentid;
    private Long postedbyid;
    private Long postedbyunitid;
    private Long postedtoid;
    private Long postedtounitid;
    private String patientno;
    private String selectedCourse;
    private String matricno;
    private String choice;
    private String oldpass;
    private String newpass;

    public String getOldpass() {
        return oldpass;
    }

    public void setOldpass(String oldpass) {
        this.oldpass = oldpass;
    }

    public String getNewpass() {
        return newpass;
    }

    public void setNewpass(String newpass) {
        this.newpass = newpass;
    }

    public String getMatricno() {
        return matricno;
    }

    public void setMatricno(String matricno) {
        this.matricno = matricno;
    }
    
    
private Long passageid;

    public String getSelectedCourse() {
        return selectedCourse;
    }

    public void setSelectedCourse(String selectedCourse) {
        this.selectedCourse = selectedCourse;
    }

    public String getCourselist() {
        return courselist;
    }

    public void setCourselist(String courselist) {
        this.courselist = courselist;
    }

    public Long getPassageid() {
        return passageid;
    }

    public void setPassageid(Long passageid) {
        this.passageid = passageid;
    }

    public String getPatientno() {
        return patientno;
    }

    public void setPatientno(String patientno) {
        this.patientno = patientno;
    }

    
    public Long getPostedbyid() {
        return postedbyid;
    }

    public void setPostedbyid(Long postedbyid) {
        this.postedbyid = postedbyid;
    }

    public Long getPostedbyunitid() {
        return postedbyunitid;
    }

    public void setPostedbyunitid(Long postedbyunitid) {
        this.postedbyunitid = postedbyunitid;
    }

    public Long getPostedtoid() {
        return postedtoid;
    }

    public void setPostedtoid(Long postedtoid) {
        this.postedtoid = postedtoid;
    }

    public Long getPostedtounitid() {
        return postedtounitid;
    }

    public void setPostedtounitid(Long postedtounitid) {
        this.postedtounitid = postedtounitid;
    }

    

    public Long getUnitid() {
        return unitid;
    }

    public void setUnitid(Long unitid) {
        this.unitid = unitid;
    }

    public Long getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(Long departmentid) {
        this.departmentid = departmentid;
    }

    public Long getPatientid() {
        return patientid;
    }

    public void setPatientid(Long patientid) {
        this.patientid = patientid;
    }

    public Long getStaffid() {
        return staffid;
    }

    public void setStaffid(Long staffid) {
        this.staffid = staffid;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

}
