/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timsoft.ehr.org.controller;

/**
 *
 * @author JIDEX
 */
import com.smattme.MysqlExportService;
import com.smattme.MysqlImportService;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.apache.commons.io.FileUtils;
import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import timsoft.ehr.org.utils.AppUtils;
import timsoft.ehr.org.utils.FacesUtils;
import timsoft.ehr.org.utils.MessageUtil;

@Component
@ManagedBean
@Scope("session")
public class RestoreController implements Serializable {

    @Autowired
    LoginController login;
    private Boolean uploadvisible;
    private Boolean photouploadvisible;
    private String dataResponse;
    private String photodataResponse;
    private String filename;
    private String backupResponse;
    private String photobackupResponse;
    private Boolean backupVisible;
    private Boolean photobackupVisible;
    private Boolean finish;
    @Value("${custom.datasource.url}")
    private String dburl;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driverClassName}")
    private String driver;

    @Value("${API.BASE_URL}")
    private String BASE_URL;

    @PostConstruct
    public void init() {
        uploadvisible = false;
        finish = false;
        backupVisible = false;
        photouploadvisible = false;
        finish = false;
        photobackupVisible = false;
        dataResponse = "No File Selected.";
        photodataResponse = "No File Selected.";
    }

    public void startBackUpOperation() {
        try {
            Properties properties = new Properties();

            //properties relating to email config 
            properties.setProperty(MysqlExportService.EMAIL_HOST, "mail.swipe.ng"); 
            properties.setProperty(MysqlExportService.EMAIL_PORT, "8025"); 
            properties.setProperty(MysqlExportService.EMAIL_USERNAME, "info@swipe.ng"); 
            properties.setProperty(MysqlExportService.EMAIL_PASSWORD, "t{tjDcgm_tf@");
            properties.setProperty(MysqlExportService.EMAIL_FROM, "support@swipe.ng"); 
            properties.setProperty(MysqlExportService.EMAIL_TO, "odofintimothy@gmail.com");
            
            //properties.setProperty(MysqlExportService.DB_NAME, "database-name");
            properties.setProperty(MysqlExportService.DB_USERNAME, username);

            properties.setProperty(MysqlExportService.DB_PASSWORD, password);
            properties.setProperty(MysqlExportService.PRESERVE_GENERATED_ZIP, "true");
            properties.setProperty(MysqlExportService.JDBC_DRIVER_NAME, driver);
            properties.setProperty(MysqlExportService.JDBC_CONNECTION_STRING, dburl);
            properties.setProperty(MysqlExportService.DELETE_EXISTING_DATA, "true");
            properties.setProperty(MysqlExportService.DROP_TABLES, "true");
            
            properties.setProperty(MysqlExportService.TEMP_DIR, new File(getBackupTemp()).getPath());

            MysqlExportService mysqlExportService = new MysqlExportService(properties);
            mysqlExportService.export();
            File file = mysqlExportService.getGeneratedZipFile();
            unzipFile(file);
            downloadBackup(file.getName());
            System.out.println(file.getAbsoluteFile());
            //mysqlExportService.clearTempFiles(false);
        } catch (IOException ex) {
            Logger.getLogger(RestoreController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RestoreController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RestoreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void downloadBackup(String fname) {
        try {

            FacesUtils.getExternalContext().redirect(BASE_URL + "/download_app_files?file_name=" + fname);

        } catch (Exception ex) {
            Logger.getLogger(RestoreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

/////////////zipping and unzipping
    public void unzipFile(File fileZip) {
        try {
            File destDir = new File(getHistoryFolder());
            if (destDir.exists() == false) {
                destDir.mkdir();
            }
            byte[] buffer = new byte[1024];
            ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                File newFile = newFile(destDir, zipEntry);
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                zipEntry = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();
        } catch (Exception e) {
            e.printStackTrace();
            login.log("Error unzipping file", "Error", MessageUtil.ERROR_TAG);
        }
    }

    public static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }

        return destFile;
    }

    public void loadPhotos() {
        photodataResponse = "Photograph loading in progress...";
        photouploadvisible = true;
        File srcFile = new File(filename);

        // create a directory with the same name to which the contents will be extracted
        ZipFile zipFile = null;
        String name = null;
        try {

            zipFile = new ZipFile(srcFile);

            // get an enumeration of the ZIP file entries
            Enumeration e = zipFile.entries();

            while (e.hasMoreElements()) {

                ZipEntry entry = (ZipEntry) e.nextElement();

                File destinationPath = new File(getPhotoFolder(), entry.getName());

                destinationPath.getParentFile().mkdirs();

                // if the entry is a file extract it
                name=destinationPath.getParent();
               
                if (entry.isDirectory()) {
                    continue;
                } else {
                    BufferedInputStream bis = new BufferedInputStream(zipFile.getInputStream(entry));

                    int b;
                    byte buffer[] = new byte[1024];

                    FileOutputStream fos = new FileOutputStream(destinationPath);

                    BufferedOutputStream bos = new BufferedOutputStream(fos, 1024);

                    while ((b = bis.read(buffer, 0, 1024)) != -1) {
                        bos.write(buffer, 0, b);
                    }

                    bos.close();
                    bis.close();

                }

            }

        } catch (IOException ioe) {
        	ioe.printStackTrace();
        	 login.log("Error importing photograph", "Error", MessageUtil.ERROR_TAG);
        } finally {
            try {
                if (zipFile != null) {
                    zipFile.close();
                }
            } catch (IOException ioe) {
            	ioe.printStackTrace();
            	 login.log("Error importing photograph", "Error", MessageUtil.ERROR_TAG);
            }
        }

        File source = new File(name);
        File dest = new File(getPhotoFolder());
        try {
            FileUtils.copyDirectory(source, dest);
            photodataResponse = "";
            photouploadvisible = false;
            login.log("Photograph uploaded successfully", "Success", MessageUtil.SUCCESS_TAG);
            source.delete();
            srcFile.delete();
        } catch (IOException e) {
            e.printStackTrace();
            photodataResponse = "Error occur";
            photouploadvisible = false;
            source.delete();
            srcFile.delete();
            login.log("Error importing photograph", "Error", MessageUtil.ERROR_TAG);
        }
        if(source.exists()){
            source.delete();
        }
    }

    public void restoreData(FileUploadEvent event) throws InterruptedException {
        try {
            String parent = getFileFolder();
            File file = new File(parent + event.getFile().getFileName());
            InputStream inputStream = event.getFile().getInputstream();

            OutputStream out = new FileOutputStream(file);

            int read = 0;

            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            inputStream.close();
            out.flush();
            out.close();
            uploadvisible = true;
            dataResponse = "Data Restore In Progress Please Wait...";
            filename = file.getAbsolutePath();

            //restoreDB(getFileFolder() + file.getName());
        } catch (IOException e) {
            login.log(MessageUtil.INTERNAL_SERVER_ERROR, AppUtils.ERROR, AppUtils.ERROR_TAG);
        }
    }

    public void restorePics(FileUploadEvent event) throws InterruptedException {
        try {
            String parent = getFileFolder();
            File file = new File(parent + event.getFile().getFileName());
            InputStream inputStream = event.getFile().getInputstream();

            OutputStream out = new FileOutputStream(file);

            int read = 0;

            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            inputStream.close();
            out.flush();
            out.close();
            photouploadvisible = true;
            photodataResponse = "Data Restore In Progress Please Wait...";
            filename = file.getAbsolutePath();

            //restoreDB(getFileFolder() + file.getName());
        } catch (IOException e) {
            login.log(MessageUtil.INTERNAL_SERVER_ERROR, AppUtils.ERROR, AppUtils.ERROR_TAG);
        }
    }

    public void restoreFile() {
        dataResponse = "Data restore in progress...";
        uploadvisible = true;
        File sql = null;
        try {
            File fileZip = new File(filename);
            File destDir = new File(getHistoryFolder());
            byte[] buffer = new byte[1024];
            ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                File newFile = newFile(destDir, zipEntry);
                if (newFile.getName().endsWith(".sql")) {
                    sql = newFile;
                }
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                zipEntry = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();
            dataResponse = "Data restore completed";

        } catch (Exception e) {
            e.printStackTrace();
            login.log("Error unzipping file", "Error", MessageUtil.ERROR_TAG);
            uploadvisible = false;
            dataResponse = "Error unzipping file";
        }
        if (sql != null) {
            restoreDatabase(sql);
        }
    }

    public void restoreDatabase(File fname) {
        try {
            String sql = new String(Files.readAllBytes(Paths.get(fname.getAbsolutePath())));

            boolean res = MysqlImportService.builder()
                    .setJdbcConnString(dburl)
                    .setJdbcDriver(driver)
                    .setSqlString(sql)
                    .setUsername(username)
                    .setPassword(password)
                    .setDeleteExisting(true)
                    .setDropExisting(true)
                    .importDatabase();
            if (res) {
                login.log("Backup successfully restored", "Success", MessageUtil.SUCCESS_TAG);
                uploadvisible = false;
                fname.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
            login.log("Error restoring database script", "Error", MessageUtil.ERROR_TAG);
            uploadvisible = false;
        }

    }

//properties relating to email config
//            properties.setProperty(MysqlExportService.EMAIL_HOST, emailhost);
//
//            properties.setProperty(MysqlExportService.EMAIL_PORT, emailport);
//
//            properties.setProperty(MysqlExportService.EMAIL_USERNAME, emailusername);
//
//            properties.setProperty(MysqlExportService.EMAIL_PASSWORD, emailpassword);
//
//            properties.setProperty(MysqlExportService.EMAIL_FROM, emailfrom);
//            properties.setProperty(MysqlExportService.EMAIL_TO, "odofintimothy@gmail.com");
    public String getFileFolder() {
        String parent = FacesContext.getCurrentInstance().getExternalContext()
                .getRealPath("")
                + "/WEB-INF/documents/";

        return parent;
    }

    public String getPhotoFolder() {
        String parent = FacesContext.getCurrentInstance().getExternalContext()
                .getRealPath("")
                + "/students/images/";

        return parent;
    }

    public String getBackupTemp() {
        String parent = FacesContext.getCurrentInstance().getExternalContext()
                .getRealPath("")
                + "/WEB-INF/db_backups_temp/";

        return parent;
    }

    public String getHistoryFolder() {
        String parent = FacesContext.getCurrentInstance().getExternalContext()
                .getRealPath("")
                + "/students/histories/";

        return parent;
    }

    public Boolean getUploadvisible() {
        return uploadvisible;
    }

    public void setUploadvisible(Boolean uploadvisible) {
        this.uploadvisible = uploadvisible;
    }

    public String getDataResponse() {
        return dataResponse;
    }

    public void setDataResponse(String dataResponse) {
        this.dataResponse = dataResponse;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getBackupResponse() {
        return backupResponse;
    }

    public void setBackupResponse(String backupResponse) {
        this.backupResponse = backupResponse;
    }

    public Boolean getBackupVisible() {
        return backupVisible;
    }

    public void setBackupVisible(Boolean backupVisible) {
        this.backupVisible = backupVisible;
    }

    public Boolean getFinish() {
        return finish;
    }

    public void setFinish(Boolean finish) {
        this.finish = finish;
    }

    public LoginController getLogin() {
        return login;
    }

    public void setLogin(LoginController login) {
        this.login = login;
    }

    public String getPhotobackupResponse() {
        return photobackupResponse;
    }

    public void setPhotobackupResponse(String photobackupResponse) {
        this.photobackupResponse = photobackupResponse;
    }

    public Boolean getPhotobackupVisible() {
        return photobackupVisible;
    }

    public void setPhotobackupVisible(Boolean photobackupVisible) {
        this.photobackupVisible = photobackupVisible;
    }

    public String getDburl() {
        return dburl;
    }

    public void setDburl(String dburl) {
        this.dburl = dburl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getBASE_URL() {
        return BASE_URL;
    }

    public void setBASE_URL(String BASE_URL) {
        this.BASE_URL = BASE_URL;
    }

    public Boolean getPhotouploadvisible() {
        return photouploadvisible;
    }

    public void setPhotouploadvisible(Boolean photouploadvisible) {
        this.photouploadvisible = photouploadvisible;
    }

    public String getPhotodataResponse() {
        return photodataResponse;
    }

    public void setPhotodataResponse(String photodataResponse) {
        this.photodataResponse = photodataResponse;
    }

}
