/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timsoft.ehr.org.controller;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import timsoft.ehr.org.utils.FacesUtils;

/**
 *
 * @author JIDEX
 */
@Component
@Scope("session")
public class LoginController {

    @PostConstruct
    public void init() {

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
    public Double getDouble(Double value){
        if(value==null){
            return 0.0;
        }else{
             DecimalFormat df = new DecimalFormat("#.##");
         return Double.valueOf(df.format(value));
        }
    }
}
