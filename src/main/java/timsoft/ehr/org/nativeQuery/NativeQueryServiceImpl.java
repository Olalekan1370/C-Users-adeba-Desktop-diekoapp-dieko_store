/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timsoft.ehr.org.nativeQuery;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mohammed Sadiq
 */
@Service
@Transactional
public class NativeQueryServiceImpl implements NativeQueryService {

    @PersistenceContext
    EntityManager em;
    private final Logger logger = LoggerFactory.getLogger(NativeQueryServiceImpl.class);
    
//    @Override
//   public List<StudentResults> getResult(String coursecode, String matric, Integer category){
//        StoredProcedureQuery sp = em.createStoredProcedureQuery("result_store", StudentResults.class)
//                .registerStoredProcedureParameter("cCode", String.class, ParameterMode.IN)
//                .registerStoredProcedureParameter("matricNo", String.class, ParameterMode.IN)
//                .registerStoredProcedureParameter("category", Integer.class, ParameterMode.IN)
//                .setParameter("cCode", coursecode)
//                .setParameter("matricNo", matric)
//                .setParameter("category", category);
//        
//        List<StudentResults> list = sp.getResultList();
//        return list;
//    }


}
