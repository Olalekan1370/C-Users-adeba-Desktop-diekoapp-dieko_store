/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timsoft.ehr.org.repository;

import javax.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author JIDEX
 */
public interface TransactionRepo extends JpaRepository<Transaction,Long>{
    
}
