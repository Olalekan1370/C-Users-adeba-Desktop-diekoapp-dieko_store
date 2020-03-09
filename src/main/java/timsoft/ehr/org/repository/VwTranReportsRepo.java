/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timsoft.ehr.org.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import timsoft.ehr.org.model.VwTranReports;

/**
 *
 * @author JIDEX
 */
public interface VwTranReportsRepo  extends JpaRepository<VwTranReports,Long>{
    
    @Query("select st from VwTranReports st where st.trandate=:fromDate or st.trandate=:toDate "
            + " or (st.trandate between :fromDate and :toDate)")
    List<VwTranReports> listByDateRange(@Param("fromDate")Date fromDate, @Param("toDate")Date toDate);
    @Query("select st from VwTranReports st where st.trandate=:fromDate or st.trandate=:toDate "
            + " or (st.trandate between :fromDate and :toDate) and st.productname=:productname")
    List<VwTranReports> listByDateRange(@Param("fromDate")Date fromDate, @Param("toDate")Date toDate, @Param("productname")String productname);
@Query(value="select * from vw_tran_reports where YEAR(trandate)=:reportyear", nativeQuery=true)
    List<VwTranReports> listByDateRange(@Param("reportyear") Integer reportyear);
}
