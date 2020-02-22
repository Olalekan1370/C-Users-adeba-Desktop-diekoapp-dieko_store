/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timsoft.ehr.org.repository;
import java.util.List;
import timsoft.ehr.org.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import timsoft.ehr.org.model.Supplier;

/**
 *
 * @author JIDEX
 */
public interface StockRepo extends JpaRepository<Stock,Long>{
     @Query(value="select * from stock where name like %:search%", nativeQuery=true)
    List<Stock> search(@Param("search")String search);
    
    @Query(value="select * from stock where DATE(datecreated)=:from or DATE(datecreated)=:to or DATE(datecreated) between :from and :to", nativeQuery=true)
    List<Stock> filterByDateRange(@Param("from")String from, @Param("to")String to);

}
