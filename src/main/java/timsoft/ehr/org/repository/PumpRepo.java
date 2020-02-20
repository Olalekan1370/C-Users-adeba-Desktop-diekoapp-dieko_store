/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timsoft.ehr.org.repository;
import java.util.List;
import timsoft.ehr.org.model.Pump;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author JIDEX
 */
public interface PumpRepo extends JpaRepository<Pump,Long>{
     @Query(value="select * from pump where name like %:search%", nativeQuery=true)
    List<Pump> search(@Param("search")String search);
    
    @Query(value="select * from pump where DATE(datecreated) between :from and :to", nativeQuery=true)
    List<Pump> filterByDateRange(@Param("from")String from, @Param("to")String to);
}
