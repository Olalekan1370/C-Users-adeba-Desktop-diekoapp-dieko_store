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
    @Query(value="select * from reservoirlog  where pumpid=:pumpid and available>0 limit 1 order by available", nativeQuery=true)
    List<Reservoirlog> list(@Param("pumpid")Long pumpid);
}
