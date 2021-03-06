/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timsoft.ehr.org.utils;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;

/**
 *
 * @author JIDEX
 */
public class MessageUtil {
    
    public final static String DUPLICATE_USER = "Duplicate user";
    public final static String ACCOUNT_ACTIVE="Student with matric number already in session. Kindly contact administrator";
     
    public final static String APP_SESSION = "mocks-app";
     public final static String SUCCESS = "Success";
    public final static String ERROR = "Error";
    public final static Severity SUCCESS_TAG = FacesMessage.SEVERITY_INFO;
    public final static Severity ERROR_TAG = FacesMessage.SEVERITY_ERROR;
    public final static String INTERNAL_ERROR="An error occur...";
    public final static String INTERNAL_SERVER_ERROR="An error occur...";
    public final static String RECORD_NOT_FOUND="Record not found";
    public final static String RECORD_CREATED="Record Successfully Created";
    public final static String RECORD_DELETED="Record Successfully Deleted";
    
    
}
