/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timsoft.ehr.org.utils;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.data.domain.PageRequest;

/**
 *
 * @author Olalekan
 */
public class AppUtils {
    public final static String CUSTOM_QUESTION_COMBINE="combine";
    public final static String PASSAGE_DISPLAY_ON_DIALOG="dialog_open";
	public final static String SELECTION_CATEGORY="category_s";
    public final static String MARK_AUTO = "AUTO";
    public final static String APP_COUNTER = "CXT";
    public final static String MARK_MANUAL = "MANUAL";
   public final static String DEFAULT = "English";
	public final static String LITERATURE = "Literature";
	public final static String LOGIN_USER = "user";
        public final static String LOGIN_MARKER = "marker";
        public final static String LOGIN_ADMIN = "admin";
        public final static String COURSE_LIST = "clist";
	public final static String TEST_TIME = "times";
        public final static String SESSION_ID = "sessionid";
	public final static String FINAL_RESULT = "results";
	public final static String DONE = "Done";
        public final static String STATUS_NEW = "new";
	public final static String UP = "online";
	public final static String OUTCOME = "outcome";
	public final static String QUESTION_PASSAGE = "passage";
	public final static String ONLINE = "#4FC3F7";
	public final static String FINISHED = "#F9A825";
	public final static String NEW = "#9CCC65";
	//////////////////////////exam events....................
	public final static String RELOAD_EVENT = "Page Reloaded";
	public final static String CLOSE_EVENT = "Browser Closed";
	public final static String LOGIN_EVENT = "New Login";
	public final static String RE_LOGIN_EVENT = "Re-Login";
	public final static String CHANGE_SUBJECT_EVENT = "Subject Changed";
	public final static String LOGOUT_EVENT = "End Exam";
         public final static String SUCCESS = "Success";
    public final static String ERROR = "Error";
    public final static Severity SUCCESS_TAG = FacesMessage.SEVERITY_INFO;
    public final static Severity ERROR_TAG = FacesMessage.SEVERITY_ERROR;
        
     public static String getCode(Integer result){
       String data=String.format("%02d", result);
       return  data;
   }
    
    public static String getPatientNo() {
        Calendar cl =Calendar.getInstance();
       String invoice=cl.get(Calendar.YEAR)+""+cl.get(Calendar.MONTH)+cl.get(Calendar.DAY_OF_MONTH)
               +cl.get(Calendar.MINUTE)
               +cl.get(Calendar.SECOND);
       return "FHRS"+invoice;

    }
    public static PageRequest getPage(Integer page, Integer size) {
        PageRequest request = new PageRequest(page, size);
        return request;
    }
    public static List<String> testHeaders(){
        List<String> headers = new ArrayList<>();
        headers.add("QUESTION");
        headers.add("OPTION_A");
        headers.add("OPTION_B");
        headers.add("OPTION_C");
        headers.add("OPTION_D");
        headers.add("ANSWER");
        headers.add("QUESTION_TYPE");
        headers.add("PASSAGE_CODE");
        headers.add("CATEGORY");
        headers.add("MARKS");
        headers.add("MARKSTYPE");
        headers.add("COMMENTS");
        return headers;
    }
    public static String getExcelHeader(Sheet sheet) {
        String response = "";
        Row row = sheet.getRow(1); //Get first row
        short minColIx = row.getFirstCellNum(); //get the first column index for a row
        short maxColIx = row.getLastCellNum(); //get the last column index for a row
        for (short colIx = minColIx; colIx < maxColIx; colIx++) { //loop from first to last index
            Cell cell = row.getCell(colIx); //get the cell
            if (cell.getStringCellValue().trim() != null && !cell.getStringCellValue().trim().equalsIgnoreCase("")) {
                if(testHeaders().contains(cell.getStringCellValue().toUpperCase())){
                    response="";
                }else{
                    response=" Invalid Column: "+cell.getStringCellValue();
                }
            }
         }
        return response;
    }
    public static Double evaluate(Double result){
        if(result==null){
            return 0.0;
        }else{
            return result;
        }
    }
}
