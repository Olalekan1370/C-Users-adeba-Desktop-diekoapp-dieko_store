/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timsoft.ehr.org.repository;
import java.util.List;
import timsoft.ehr.org.model.Reservoir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import timsoft.ehr.org.model.Staff;

/**
 *
 * @author JIDEX
 */
public interface ReservoirRepo extends JpaRepository<Reservoir,Long>{
      @Query(value="select * from reservoir where units like %:search%  or name like %:search%", nativeQuery=true)
    List<Reservoir> search(@Param("search")String search);
    
    @Query(value="select * from reservoir where DATE(datecreated)=:from or DATE(datecreated)=:to or DATE(datecreated) between :from and :to", nativeQuery=true)
    List<Reservoir> filterByDateRange(@Param("from")String from, @Param("to")String to);

}
