/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timsoft.ehr.org.repository;
import timsoft.ehr.org.model.Shifting;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author JIDEX
 */
public interface ShiftingRepo extends JpaRepository<Shifting,Long>{
    
}