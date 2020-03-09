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
import timsoft.ehr.org.model.User;

/**
 *
 * @author JIDEX
 */
public interface UserRepo extends JpaRepository<User,Long>{
    @Query("select st from User st where st.staffid.fullname like :search or st.username like :search")
    List<User> search(@Param("search")String search);
    
    @Query(value="select * from user where  DATE(datecreated)=:from or DATE(datecreated)=:to or DATE(datecreated) between :from and :to", nativeQuery=true)
    List<User> filterByDateRange(@Param("from")String from, @Param("to")String to);
    @Query("select st from User st where st.username=:username")
    List<User> findByUsername(@Param("username")String username);
    
    @Query("select st from User st where st.username=:username and st.password=:password")
    List<User> checkLogin(@Param("username")String username,
            @Param("password")String password);
}
