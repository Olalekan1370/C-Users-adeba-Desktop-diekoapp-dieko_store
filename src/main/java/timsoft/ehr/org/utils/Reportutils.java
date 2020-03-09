/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timsoft.ehr.org.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.model.SelectItem;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;

/**
 *
 * @author JIDEX
 */
public class Reportutils {

    public static String getMonthName(Integer id) {
        HashMap<Integer, String> maps = new HashMap<>();
        maps.put(1, "Jan");
        maps.put(2, "Feb");
        maps.put(3, "Mar");
        maps.put(4, "Apr");
        maps.put(5, "May");
        maps.put(6, "Jun");
        maps.put(7, "Jul");
        maps.put(8, "Aug");
        maps.put(9, "Sep");
        maps.put(10, "Oct");
        maps.put(11, "Nov");
        maps.put(12, "Dec");
        return maps.get(id);

    }

    public static List<SelectItem> getMonths() {
        List<SelectItem> list = new ArrayList<>();
        list.add(new SelectItem(1, "January"));
        list.add(new SelectItem(2, "February"));
        list.add(new SelectItem(3, "March"));
        list.add(new SelectItem(4, "April"));
        list.add(new SelectItem(5, "May"));
        list.add(new SelectItem(6, "June"));
        list.add(new SelectItem(7, "July"));
        list.add(new SelectItem(8, "August"));
        list.add(new SelectItem(9, "September"));
        list.add(new SelectItem(10, "Octomber"));
        list.add(new SelectItem(11, "November"));
        list.add(new SelectItem(12, "December"));
        return list;

    }

    public static String formatDate(Date d) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
        return f.format(d);
    }

    public static String getDateAsString(Date d) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        return f.format(d);
    }

    public static String formatDateTime(Date d) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss a");
        return f.format(d);
    }

    public static String modifyValue(Double d) {
        return String.format("%,.2f", d);
    }

    public static String getDate(Date d) {
        SimpleDateFormat ft = new SimpleDateFormat("E");
        return ft.format(d);
    }

    public static String evaluateResult(String results) {
        return (results == null || results.isEmpty()) ? "" : results;
    }

    public static Double getValue(Double result) {
        return result == null ? 0.0 : result;
    }

    public static Integer getIntegerValue(Integer result) {
        return result == null ? 0 : result;
    }


    public static void createContent(Row row, String text, int i) {
        Cell cell = row.createCell(i);
        cell.setCellValue(text);

    }

    public static void createHeader(Sheet sh) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        CellStyle cellStyle = sh.getWorkbook().createCellStyle();
        Font font = sh.getWorkbook().createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 16);
        cellStyle.setFont(font);
        Row row = sh.createRow(0);

        Cell cell = row.createCell(0);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("CUSTOMER NAME");
        cell = row.createCell(1);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("INVOICE NO");
        cell = row.createCell(2);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("QUANTITY PURCHASED");
        cell = row.createCell(3);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("SALES PRICE");
        cell = row.createCell(4);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("DELIVER COST");
        cell = row.createCell(5);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("AMOUNT");
        cell = row.createCell(6);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("TRANSACTION DATE");  
         cell = row.createCell(7);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("DESCRIPTION");
        

    }


    private static Workbook getWorkbook(String excelFilePath)
            throws IOException {
        Workbook workbook = null;

        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

}
