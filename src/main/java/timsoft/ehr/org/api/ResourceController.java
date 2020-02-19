/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timsoft.ehr.org.api;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import timsoft.ehr.org.nativeQuery.NativeQueryService;
import timsoft.ehr.org.repository.AppService;

/**
 *
 * @author JIDEX
 */
@RestController
@RequestMapping("/api")
public class ResourceController {

    @Autowired
    AppService service;
    @Autowired
    private JavaMailSender sender;
    @Autowired
    private Configuration freemarkerConfig;
    @Autowired
    ServletContext context;

 @Autowired
    NativeQueryService store;
//    @GetMapping("/download_backup")
//    public void getFile(
//            @RequestParam("file_name") String fileName,
//            HttpServletResponse response) {
//        response.setContentType("application/zip");
//        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
//        try {
//            String files = context.getRealPath("/WEB-INF/db_backups_temp/") + fileName;
//            
//            InputStream is = new FileInputStream(new File(files));
//            org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
//            response.flushBuffer();
//           // return "Done";
//        } catch (IOException ex) {
//           // throw new RuntimeException("IOError writing file to output stream");
//          //return "Error downloading sql file";
//        }
//
//    }

    @GetMapping("/download_app_files")
    public void downloadAppResult(
            @RequestParam("file_name") String fname,
            HttpServletResponse response) {
        Calendar cl = Calendar.getInstance();
        String realname = cl.get(Calendar.DAY_OF_MONTH) + "_" + cl.get(Calendar.MONTH) + "_" + cl.get(Calendar.YEAR) + "_" + cl.get(Calendar.HOUR_OF_DAY) + "_"
                + cl.get(Calendar.MINUTE) + "_" + cl.get(Calendar.SECOND) + "_toefcbt_app_file_bk.zip";
        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=" + realname);
        try {
            String f = context.getRealPath("/students/histories/");
            File files = new File(f);
            System.out.println("Absolute Path" + files.getAbsolutePath());
            ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream());
            for (String fileName : files.list()) {
                FileSystemResource resource = new FileSystemResource(files.getAbsolutePath() + "/" + fileName);
                ZipEntry zipEntry = new ZipEntry(resource.getFilename());
                zipEntry.setSize(resource.contentLength());
                zipOut.putNextEntry(zipEntry);
                StreamUtils.copy(resource.getInputStream(), zipOut);
                zipOut.closeEntry();
            }
            zipOut.finish();
            zipOut.close();
           File ft = new File(f+fname);
           if(ft.exists()){
               ft.delete();
           }
           

        } catch (IOException ex) {

            ex.printStackTrace();
            
        }

    }

    
    public String SendMail(String email, String course, ByteArrayOutputStream in) throws MalformedTemplateNameException, ParseException, IOException {
        try {

            Map model = new HashMap();
            model.put("message", "Examination result");
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");
            Template t = freemarkerConfig.getTemplate("message.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(email);
            helper.setFrom("info@swipe.ng");
            helper.setText(html, true);
            helper.setSubject("Payee Report");
            ByteArrayDataSource attachment = new ByteArrayDataSource(in.toByteArray(), "application/vnd.ms-excel");
            helper.addAttachment("resultstatement.xls", attachment);

            sender.send(message);
            return "Success: Email activation sent to the registered email address";
        } catch (MessagingException ex) {

            return "Error: Unable to send Email...................................... Please check your internet connection";
        } catch (TemplateException ex) {

            return "Error: Unable to send Email......................................"
                    + " " + ex.getMessage();

        }
    }

   
}
