/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timsoft.ehr.org.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import timsoft.ehr.org.model.Transactions;

/**
 *
 * @author JIDEX
 */
public interface TransactionRepo extends JpaRepository<Transactions,Long>{
    @Query(value="select st  from Transactions st where st.pumpid.name like %:search% or st.invoicenumber=:search", nativeQuery=true)
    List<Transactions> search(@Param("search")String search);
    @Query(value="select * from transactions where DATE(datecreated)=:from or "
            + "DATE(datecreated)=:to or DATE(datecreated) between :from and :to", nativeQuery=true)
    List<Transactions> filterByDateRange(@Param("from")String from, @Param("to")String to);
}
