/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timsoft.ehr.org.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import timsoft.ehr.org.model.ReceiptLogs;

/**
 *
 * @author JIDEX
 */
public interface ReceiptLogsRepo extends JpaRepository<ReceiptLogs, Long> {

}
