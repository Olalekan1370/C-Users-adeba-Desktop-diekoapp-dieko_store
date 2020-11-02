/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timsoft.ehr.org.controller;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import timsoft.ehr.org.model.Accounts;
import timsoft.ehr.org.repository.AppService;
import timsoft.ehr.org.utils.AppHelper;
import timsoft.ehr.org.utils.AppUtils;
import timsoft.ehr.org.utils.FacesUtils;
import timsoft.ehr.org.utils.MessageUtil;

/**
 *
 * @author JIDEX
 */
@Component
@Scope("session")
public class LoginController {

    @Autowired
    AppService service;

    @PostConstruct
    public void init() {

    }

    public void checkAccountsLogin() {
        Accounts acct = (Accounts) FacesUtils.getManagedBean("acct");
        List<Accounts> login = service.getAccountsRepo().checkLogin(acct.getAccountsname(), acct.getPassword());
        if (login.isEmpty()) {
            log("Either username/password not correct", "Error", MessageUtil.ERROR_TAG);
        } else {
            getSession().setAttribute(AppUtils.LOGIN_ADMIN, login.get(0));

            try {
                FacesUtils.getExternalContext().redirect("./secure/dashboard.xhtml");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void changeAccountsPassword() {
        AppHelper app = (AppHelper) FacesUtils.getManagedBean("appHelper");
        Accounts mk = getLoginAccounts();
        if (app.getConfirmPassword().matches(app.getNewpass()) == false) {
            log("Confirm password not match", "Error", MessageUtil.ERROR_TAG);
        } else if (app.getOldpass().matches(mk.getPassword()) == false) {
            log("Old password not matched", "Error", MessageUtil.ERROR_TAG);
        } else {
            mk.setPassword(app.getNewpass());
            service.getAccountsRepo().save(mk);
            log("Password Changed Successfully", "Success", MessageUtil.SUCCESS_TAG);
        }
    }

    public Accounts getLoginAccounts() {
        Accounts login = (Accounts) getSession().getAttribute(AppUtils.LOGIN_ADMIN);
        return login;
    }

    public void logoutAdmin() {
        try {
            getSession().setAttribute(AppUtils.LOGIN_ADMIN, null);
            FacesUtils.getExternalContext().redirect(FacesUtils.getServletContext().getContextPath() + "/index.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void storeToSession(String name, Object t) {
        HttpSession session = FacesUtils.getHttpSession(false);
        session.setAttribute(name, t);
    }

    public void reset(String data) {
        FacesUtils.resetManagedBean(data);
    }

    public void log(String content, String title, FacesMessage.Severity sign) {
        FacesContext contexts = FacesContext.getCurrentInstance();
        contexts.addMessage(null, new FacesMessage(sign, title, content));
    }

    public char getRandomCharacter(char ch1, char ch2) {
        return (char) (ch1 + Math.random() * (ch2 - ch1 + 1));
    }

    public char getRandomDigitCharacter() {
        return getRandomCharacter('0', '9');
    }

    public char getRandomUpperCaseLetter() {
        return getRandomCharacter('A', 'Z');
    }

    public HttpSession getSession() {
        HttpSession session = FacesUtils.getHttpSession(false);
        return session;
    }

    public String generateC(int no) {
        String value = "";
        for (int i = 1; i < no; i++) {

            value += "" + getRandomUpperCaseLetter();
        }
        return value;
    }

    public String generateN(int no) {
        String value = "";
        for (int i = 1; i < no; i++) {

            value += "" + getRandomDigitCharacter();
        }
        return value;
    }

    public String getInvoice() {
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();
        String no = "" + year + month + day + hour + minute + second;
        return no;
    }

    public Double getDouble(Double value) {
        if (value == null) {
            return 0.0;
        } else {
            DecimalFormat df = new DecimalFormat("#.##");
            return Double.valueOf(df.format(value));
        }
    }
}
