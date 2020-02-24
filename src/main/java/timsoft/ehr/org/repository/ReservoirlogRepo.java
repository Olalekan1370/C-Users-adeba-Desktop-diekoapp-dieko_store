/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timsoft.ehr.org.repository;
import java.util.List;
import timsoft.ehr.org.model.Reservoirlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author JIDEX
 */
public interface ReservoirlogRepo extends JpaRepository<Reservoirlog,Long>{
    @Query("select st from Reservoirlog st where st.reservoirid.id=:reservoirid order by st.id desc")
    List<Reservoirlog> listByParent(@Param("reservoirid")Long reservoirid);
    @Query(value="select * from reservoirlog  where pumpid=:pumpid and available>0 limit 1 order by available", nativeQuery=true)
    List<Reservoirlog> list(@Param("pumpid")Long pumpid);
    
     @Query(value="select * from reservoirlog where DATE(datesupplied)=:from or DATE(datesupplied)=:to or DATE(datesupplied) between :from and :to", nativeQuery=true)
    List<Reservoirlog> filterByDateRange(@Param("from")String from, @Param("to")String to);

}
