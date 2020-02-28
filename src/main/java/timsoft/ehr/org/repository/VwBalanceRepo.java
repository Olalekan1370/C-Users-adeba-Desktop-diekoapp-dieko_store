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
import timsoft.ehr.org.model.VwBalance;

/**
 *
 * @author JIDEX
 */
public interface VwBalanceRepo extends JpaRepository<VwBalance,Long>{
    @Query("select st from VwBalance st where st.staffid=:staffid order by st.years, st.months")
    List<VwBalance> findByStaffId(@Param("staffid")Long staffid);
    @Query("select st from VwBalance st where st.staffid=:staffid and st.years=:years order by st.years desc")
    List<VwBalance> findByStaffIdAndYear(@Param("staffid")Long staffid, @Param("years")Integer year);
    
    
}