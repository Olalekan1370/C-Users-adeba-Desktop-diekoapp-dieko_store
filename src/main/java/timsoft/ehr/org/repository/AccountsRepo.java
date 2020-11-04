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
import timsoft.ehr.org.model.Accounts;

/**
 *
 * @author JIDEX
 */
public interface AccountsRepo extends JpaRepository<Accounts, Long> {

//    @Query(value="select * from user  where staffaffid like :search or acct.username like :search", nativeQuery = true)
// public   List<Accounts> searchTerm(@Param("search")String term);
    @Query(value = "select * from user where  DATE(datecreated)=:from or DATE(datecreated)=:to or DATE(datecreated) between :from and :to", nativeQuery = true)
    List<Accounts> filterByDateRange(@Param("from") String from, @Param("to") String to);

    @Query("select acct from Accounts acct where acct.username=:username")
    List<Accounts> findByAccountsname(@Param("username") String username);

    @Query("select acct from Accounts acct where acct.username=:username and acct.password=:password")
    List<Accounts> checkLogin(@Param("username") String username,
            @Param("password") String password);

}
