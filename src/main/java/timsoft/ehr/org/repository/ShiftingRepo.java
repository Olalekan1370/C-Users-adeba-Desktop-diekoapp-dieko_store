/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timsoft.ehr.org.repository;
import java.util.List;
import timsoft.ehr.org.model.Shifting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author JIDEX
 */
public interface ShiftingRepo extends JpaRepository<Shifting,Long>{
     @Query("select st from Shifting where st.staffid.fullname like %:search% or st.staffid.phonenumber like %:search%")
    List<Shifting> search(@Param("search")String search);
    
    @Query(value="select * from shifting where DATE(datecreated)=:from or DATE(datecreated)=:to or DATE(datecreated) between :from and :to", nativeQuery=true)
    List<Shifting> filterByDateRange(@Param("from")String from, @Param("to")String to);
}
